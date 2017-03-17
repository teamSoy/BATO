package net.aguel.bato.List;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class EmergencyNumbers {
    String name;
    int number;

    public EmergencyNumbers() {

    }

    public EmergencyNumbers(String name, int number)
    {
        this.name = name;
        this.number = number;

    }

    public void setName(String name) {
        this.name = name;
    }


    public void setNumber(int number) {
        this.number = number;
    }



    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

}
