import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.lang.*;
import java.io.*;

//сделать так, чтобы я мог вводить и выводить информацию в определённый файл, а ещё чтобы я мог сохранить в каком-либо виде
//какой-то определённый объект

public class Main {
    final int MY_CONST = 5;  //we can change our "final" variable in constructor.
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        /*String str = "name";
        System.out.println(str);  //this is show us how need to work strings parsing, we need to use StringBuilder or create new string every time.
        str.concat(" noname");
        System.out.println(str);*/
        List<Candies> cands = new ArrayList<>();
        if(SolutionAboutInputInformation() == true) {
            Inputing(cands);
        }
        else{
            if(ReadingFromFile(cands)==false)
                Inputing(cands);
        }

        NumberOfCandies(cands);

        while(2>1) {
           CorrectingInformation(cands);
        }
    }

    static public void Inputing(List<Candies> cands) throws Exception{
        GettingInformation(cands);
    }

    static public boolean ReadingFromFile(List<Candies> cands)throws Exception{
        int currentNumb = 0;
        File file = new File("C:\\Users\\user\\IdeaProjects\\FifthProject\\Information.txt");
        if(!file.exists()){
            System.out.println("You have problems with your file.");
            System.out.println("You have to write information yourself.");
            return false;
        }
        else{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.ready()){
                SweetchForReadingFromFile(bufferedReader, cands);
            }
        }
        return true;
    }

    static public void SweetchForReadingFromFile(BufferedReader bufferedReader, List<Candies> cands)throws Exception{
        boolean def = true;
        String[] string = bufferedReader.readLine().split("\\s");
        switch(string[0]){
            case "Cookies":
            case "cookies":
            case "cookie":
            case "Cookie":{
                cands.add(new Cookies());
                break;
            }
            case "chocolates":
            case "chocolate":
            case "Chocolates":
            case "Chocolate":{
                cands.add(new Chocolates());
                break;
            }
            case "sweets":
            case "sweet":
            case "Sweets":
            case "Sweet": {
                cands.add(new Sweets());
                break;
            }
            default: {
                System.out.print("Your information isn't right, try to do it one more time!");
                def = false;
                break;
            }
        }
        if(def == true){
            cands.get(cands.size() - 1).ReadingFromFile(string);
            AddingInformationToFile(cands.get(cands.size() - 1));
        }
    }

    static public int NumberOfCandies(List<Candies> cands){
        int numb = 0;
        for(Candies i:cands){
            if(i==null)
                break;
            numb++;
        }
        return numb;
    }

    static public boolean SolutionAboutInputInformation(){
        System.out.println("0. You want add information from file.");
        System.out.println("1. You want add information yourself.");
        boolean sol;
        int numb = Input();
        if(numb == 1)
            sol = true;
        else
            sol = false;
        return sol;
    }

    static public void Functions(){
        System.out.println("Choose some options: ");
        System.out.println("1.Add some new candies.");
        System.out.println("2.Refactor some candies.");
        System.out.println("3.Delete some candies.");
        System.out.println("4.Get information about all candies.");
        System.out.println("5.Get information about some special candie.");
        System.out.println("6.Serializate some candies.");
        System.out.println("7.Exit.\n");
        System.out.println("Write number of function that you chose: ");
    }

    static public void CorrectingInformation(List<Candies> cands) throws Exception{
        Functions();
        switch(Input()){
            case 1:{
                AddingSomeCandie(cands);
                break;
            }

            case 2:{
                RefactorSomeCandie(cands);
                break;
            }

            case 3: {
                EliminationSomeCandie(cands);
                break;
            }
            case 4: {
                OutputingInformation(cands);
                break;
            }
            case 5: {
                ExactInformation(cands);
                break;
            }
            case 6: {
                Serrialization(cands);
                break;
            }
            case 7: {
                System.exit(1);
            }
            default: {
                System.out.println("Your information isn't right, try to do it one more time!\n");
                break;
            }
        }
    }

    static public void OutputingInformation(List<Candies> cands) throws Exception{
        System.out.println("Do you wand to output your information in sort view?");
        if(input.nextLine() == "Yeah"||input.nextLine() == "yeah"||input.nextLine() == "Yea"||input.nextLine() == "yea"){

        }
        else {
            System.out.println("");
            for (Candies i : cands) {
                if (i == null)
                    break;
                System.out.println(i.getClass().getSimpleName());
                i.OutputData();
            }
        }
    }

    static public void AddingSomeCandie (List<Candies> cands) throws Exception{
        ChoosenType(cands);
    }

    static public int Input(){
        int a = input.nextInt();
        input.nextLine();
        return a;
    }

    static public void Serrialization(List<Candies> cand) throws Exception{
        int number = Input();
        FileOutputStream fileOutputStream = new FileOutputStream("ObjectInformation");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(cand.get(number));
        objectOutputStream.close();
    }

    static public void RefactorSomeCandie(List<Candies> cands) throws Exception{
        System.out.println("Enter the number of your candie from 1 to " + cands.size() + ": ");
        int numb = Input();
        if(numb-1>cands.size()||numb-1<0)
            System.out.println("Such candie isn't exist, that's why write some else number.\n");
        else {
            cands.remove(numb - 1);
            ChoosenType(cands, numb - 1);
        }
    }

    static public void ExactInformation(List<Candies> cands){
        System.out.println("Enter the number of your candie from 1 to " + cands.size() + ": ");
        int numb = Input();
        if(numb-1>cands.size()||numb-1<0)
            System.out.println("Such candie isn't exist, that's why write some else number.\n");
        else
            cands.get(numb-1).OutputData();
    }

    static public void EliminationSomeCandie(List<Candies> cands){
        System.out.println("Enter the number of your candie from 1 to " + cands.size() + ": ");
        int numb = Input();
        if(numb-1>cands.size()||numb-1<0)
            System.out.println("Such candie isn't exist, that's why write some else number.\n");
        else
            cands.remove(numb - 1);
    }

    static public void GettingInformation(List<Candies> cands) throws Exception{
        String wordForAgreement;
        boolean agreement = true;
        do{
            ChoosenType(cands);
            System.out.println("Do you want to continue add some candies: ");
            wordForAgreement = input.nextLine();
            if(wordForAgreement.equals("yeah")|| wordForAgreement.equals("yea"))
                agreement = true;
            else
                agreement = false;
        }while(agreement == true);
    }

    static public void AddingInformationToFile(Candies cand)throws Exception{
        File file = new File("MyInformation");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        cand.inputDataToFile(bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    static public void ChoosenType( List<Candies> cands) throws Exception{
        boolean def = true;
        System.out.print("Write type of your candies: ");
        String typeOfCandies = input.nextLine();
        switch(typeOfCandies){
            case "Cookie":{
                cands.add(new Cookies());
                break;
            }
            case "Chocolate":{
                cands.add(new Chocolates());
                break;
            }
            case "Sweet": {
                cands.add(new Sweets());
                break;
            }
            default: {
                System.out.print("Your information isn't right, try to do it one more time!\n");
                def = false;
                break;
            }
        }
        if(def == true){
            cands.get(cands.size() - 1).inputData();
            AddingInformationToFile(cands.get(cands.size() - 1));
        }
    }

    static public void ChoosenType( List<Candies> cands, int index) throws Exception{
        boolean def = true;
        System.out.print("Write type of your candies: ");
        String typeOfCandies = input.nextLine();
        switch(typeOfCandies){
            case "Cookie":{
                cands.add(index, new Cookies());
                break;
            }
            case "Chocolate":{
                cands.add(index, new Chocolates());
                break;
            }
            case "Sweet": {
                cands.add(index, new Sweets());
                break;
            }
            default: {
                System.out.print("Your information isn't right, try to do it one more time!\n");
                def = false;
                break;
            }
        }
        if(def == true){
            cands.get(index).inputData();
            AddingInformationToFile(cands.get(index));
        }
    }
}

class CompareCandies implements Comparator<Candies> {
    @Override
    public int compare(Candies o1, Candies o2) {
        return (int) (o1.getNumberOfSugar() - o2.getNumberOfSugar());
    }
}
