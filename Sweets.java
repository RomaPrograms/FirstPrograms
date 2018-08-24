import java.io.BufferedWriter;
import java.io.Serializable;

public class Sweets extends Candies implements Serializable {
    private String type;
    private double numberOfCacao;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getNumberOfCacao() {
        return numberOfCacao;
    }

    public void setNumberOfCacao(double numberOfCacao) {
        this.numberOfCacao = numberOfCacao;
    }

    public Sweets(){}

    @Override
    public void OutputData(){
        super.OutputData();
        System.out.print(", " + this.type);
        System.out.println(", " + this.numberOfCacao);
        System.out.println("");
    }

    {
        this.numberOfCacao = 0;
        this.type = "notype";
    }

    @Override
    public void inputData(){
        super.inputData();
        System.out.println("Write the kind of chocolate: ");
        type = input.nextLine();
        System.out.println("Write the number of cacao in your sweets: ");
        numberOfCacao = input.nextDouble();
        input.nextLine();
    }

    @Override
    public void ReadingFromFile(String[] information){
        super.ReadingFromFile(information);
        this.type = information[4];
        this.numberOfCacao = Double.parseDouble(information[5]);
    }

    @Override
    public void inputDataToFile(BufferedWriter bufferedWriter)throws Exception{
        bufferedWriter.write(this.getClass().getSimpleName() + " ");
        super.inputDataToFile(bufferedWriter);
        bufferedWriter.write(type + " ");
        bufferedWriter.write(numberOfCacao + "\n");
    }
}
