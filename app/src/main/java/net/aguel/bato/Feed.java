package net.aguel.bato;

/**
 * Created by appli on 3/15/2017.
 */

public class Feed {
    String switch_name;
    int switch_state;

    public Feed() {

    }

    public Feed(String name, int state)
    {
        this.switch_name = name;
        this.switch_state = state;

    }

    public void setProductName(String name) {
        this.switch_name = name;
    }


    public void setState(int state) {
        this.switch_state = state;
    }



    public String getName() {
        return switch_name;
    }

    public int getState() {
        return switch_state;
    }

}
