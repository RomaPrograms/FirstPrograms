require 'nokogiri'
require 'byebug'
require 'curl'
require 'httparty'
require 'csv'
require 'webdrivers'
require 'watir'
require 'selenium-webdriver'

def scraper
  # puts("Enter reference to the required site: ")
  # url = gets.chomp()
  # puts("Enter reference to the csv file for result: ")
  # url_of_result = gets.chomp();
  url = "https://www.petsonic.com/snacks-huesos-para-perros/"
  url_of_result = "result.csv"

  begin
    unparsed_page = Curl::Easy.new(url)
    unparsed_page.ssl_verify_peer = false
    unparsed_page.perform
    parsed_page = Nokogiri::HTML(unparsed_page.body_str)
    products = Array.new
    page = 2
    per_page = parsed_page.xpath('//div[@class = "pro_outer_box"]').count
    total = parsed_page.xpath('//span[@class = "heading-counter"]').text.split(' ')[0].to_i
    last_page = (total.to_f / per_page.to_f).round
    while page <= 2
      pagination_url = url
      if page != 1
        pagination_url += "?p=#{page}"
      end
      puts pagination_url
      puts "Page: #{page}"
      puts ''
      pagination_unparsed_page = Curl::Easy.new(pagination_url)
      pagination_unparsed_page.ssl_verify_peer = false
      pagination_unparsed_page.perform
      pagination_parsed_page = Nokogiri::HTML(pagination_unparsed_page.body_str)
      pagination_product_listings = pagination_parsed_page.xpath('//div[@class = "pro_outer_box"]')
      pagination_product_listings.each do |product_listing|
        product_pagination_url = product_listing.xpath('div[@class = "pro_first_box "]/a')[0].attributes["href"].value
        product_unparsed_page = Curl::Easy.new(product_pagination_url)
        product_unparsed_page.ssl_verify_peer = false
        product_unparsed_page.perform
        product_parsed_page = Nokogiri::HTML(product_unparsed_page.body_str)
        if(product_parsed_page.xpath('//div[@class = "substitucion-text-container"]').count == 0)
          title = product_parsed_page.xpath('//h1[@class = "product_main_name"]').text
          pictures_number = product_parsed_page.xpath('//ul[@id = "thumbs_list_frame"]/li').count
          product_volumes = product_parsed_page.xpath('//div[@id = "attributes"]/fieldset[@class = "attribute_fieldset"]')
          product_volumes.each do |products_data|
            product_volume_type = products_data.xpath('label')[0].text
             product_data = products_data.xpath('div[@class = "attribute_list"]/ul[@class = "attribute_radio_list"]/li');
              product_data.each do |specific_product|

              #As I understood such way for getting picture of curent variation
              # doesn't work because curl download html before js will change it.
              # curent_unparsed_page = Curl::Easy.new(specific_url)
              # curent_unparsed_page.ssl_verify_peer = false
              # curent_unparsed_page.perform
              # curent_parsed_page = Nokogiri::HTML(curent_unparsed_page.body_str)
              # image = curent_parsed_page.xpath('//span[@id = "view_full_size"]/img[@id = "bigpic"]')[0].attributes["src"].value

              #Such way works perfect but it opens web-page by specific_url.
              # curent_unparsed_page = Watir::Browser.new
              # curent_unparsed_page.goto specific_url
              # curent_parsed_page = Nokogiri::HTML(curent_unparsed_page.html)
              # image = curent_parsed_page.xpath('//span[@id = "view_full_size"]/img[@id = "bigpic"]')[0].attributes["src"].value
              # curent_unparsed_page.close

              #I tried to use such gem like capybara, but i always got some issues with phantom.js

              #The best i could do
              curent_product_volume = specific_product.xpath('label/span[@class = "radio_label"]').text
              if(product_data.count > 1 &&  pictures_number > 1)
                curent_product = specific_product.xpath('input')[0].attributes["value"].value
                curent_product_volume = specific_product.xpath('label/span[@class = "radio_label"]').text
                curent_product_url = curent_product + '-' + product_volume_type[0..product_volume_type.length-2] + '-' + curent_product_volume
                curent_product_url = curent_product_url.downcase.gsub(" ", "_")
                curent_product_url = curent_product_url.downcase.gsub(".", "_")
                specific_url = product_pagination_url + '#/' + curent_product_url

                args = ['--ignore-certificate-errors', '--disable-popup-blocking', '--disable-translate']
                curent_unparsed_page = Watir::Browser.new :chrome, options: {args: args}
                curent_unparsed_page.goto specific_url
                curent_parsed_page = Nokogiri::HTML(curent_unparsed_page.html)
                image = curent_parsed_page.xpath('//span[@id = "view_full_size"]/img[@id = "bigpic"]')[0].attributes["src"].value
                curent_unparsed_page.close
              else
                image = product_parsed_page.xpath('//span[@id = "view_full_size"]/img[@id = "bigpic"]')[0].attributes["src"].value
              end

              product = {
                title: title + ' ' + curent_product_volume,
                price: specific_product.xpath('label/span[@class = "price_comb"]').text.split(' ')[0],
                images: image
              }

              products << product
              puts "Added #{product[:title]}"
              puts ""
            end
          end
        end
       end
      page += 1
    end
    headers = ["Name", "Price", "Image"]
    CSV.open(url_of_result, 'w', write_headers: true, headers: headers) do |writer|
      products.each do |product|
        writer << [product[:title], product[:price], product[:images]]
      end
    end
  rescue Curl::Err::HostResolutionError
    puts "Incorrect URL!"
  end
end

scraper
