import java.io.BufferedWriter;
import java.io.Serializable;

public class Chocolates extends Candies implements Serializable {
    private String type;

    public Chocolates(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void OutputData(){
        super.OutputData();
        System.out.println(", " + this.type);
        System.out.println("");
    }

    {
        this.type = "notype";
    }

    @Override
    public void inputData(){
        super.inputData();
        System.out.println("Write the kind of chocolate: ");
        type = input.nextLine();
    }

    @Override
    public void ReadingFromFile(String[] information){
        super.ReadingFromFile(information);
        this.type = information[4];
    }

    @Override
    public void inputDataToFile(BufferedWriter bufferedWriter) throws Exception{
        bufferedWriter.write(this.getClass().getSimpleName() + " ");
        super.inputDataToFile(bufferedWriter);
        bufferedWriter.write(type + "\n");
    }
}
