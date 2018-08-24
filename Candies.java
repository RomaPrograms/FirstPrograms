import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.String;

public class Candies implements Serializable {
    private String name;
    private double numberOfSugar;
    private double weight;
    transient Scanner input = new Scanner(System.in);

    public Candies() {
    }

    public Candies(String name, int weight, int numberOfSugar) {
        this.name = name;
        this.weight = weight;
        this.numberOfSugar = numberOfSugar;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumberOfSugar() {
        return numberOfSugar;
    }

    public void setNumberOfSugar(double numberOfSugar) {
        this.numberOfSugar = numberOfSugar;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    {
        this.name = "noname";
        this.weight = 0;
        this.numberOfSugar = 0;
    }

    public void OutputData(){
        System.out.print(this.name);
        System.out.print(", " + this.numberOfSugar);
        System.out.print(", " + this.weight);
    }

    public void ReadingFromFile (String[] information){
        this.weight = Double.parseDouble(information[2]);
        this.numberOfSugar = Double.parseDouble(information[3]);
        this.name = information[1];
    }

    public void inputData(){
        System.out.println("Enter the name of company which created your Candie: ");
        this.name = input.nextLine();
        System.out.println("Enter the number of sugar in your Candie: ");
        this.numberOfSugar = input.nextDouble();
        input.nextLine();
        System.out.println("Enter the number of weight in your Candie: ");
        this.weight = input.nextDouble();
        input.nextLine();
    }

    public void inputDataToFile(BufferedWriter bufferedWriter) throws Exception{
        bufferedWriter.write(name + " ");
        bufferedWriter.write(Double.toString(weight) + " ");
        bufferedWriter.write(Double.toString(numberOfSugar ) + " ");
    }
}

