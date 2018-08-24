import java.io.BufferedWriter;
import java.io.Serializable;

public class Cookies extends Candies implements Serializable{
    private boolean Homemade;
    String homemade;

    public boolean isHomemade(){
        return Homemade;
    }

    public void setHomemade(boolean homemade) {
        Homemade = homemade;
    }

    public Cookies(){}

    @Override
    public void OutputData(){
        super.OutputData();
        if(this.Homemade == true)
            System.out.println(", Homemade");
        else
            System.out.println(", NotHomemade");
        System.out.println("");
    }

    {
        this.Homemade = false;
    }

    @Override
    public void inputData(){
        super.inputData();
        System.out.println("Is it homemade food or not?");
        homemade = input.nextLine();
        if(homemade == "yeah" || homemade == "yea" || homemade == "homemade"||homemade == "Homemade")
            this.Homemade = true;
        else
            this.Homemade = false;
    }

    @Override
    public void ReadingFromFile(String[] information){
        super.ReadingFromFile(information);
        if(information[4] == "homemade"||information[4] == "Homemade")
            this.Homemade = true;
        else
            this.Homemade = false;
    }

    @Override
    public void inputDataToFile(BufferedWriter bufferedWriter) throws Exception{
        bufferedWriter.write(this.getClass().getSimpleName() + " ");
        super.inputDataToFile(bufferedWriter);
        if(this.Homemade == true)
            bufferedWriter.write("Homemade\n");
        else
            bufferedWriter.write("notHomemade\n");
    }
}
