import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private int xUpperRight;
    private int yUpperRight;

    private List<Rover> roversList=new ArrayList<>();

    public Plateau(int xUpperRight, int yUpperRight){
        this.xUpperRight=xUpperRight;
        this.yUpperRight=yUpperRight;
    }
    public int getxUpperRightR(){
        return this.xUpperRight;
    }
    public int getyUpperRight(){
        return this.yUpperRight;
    }
    public List<Rover> getRoversList() {
        return roversList;
    }

    public void setRoversList(List<Rover> roversList) {
        this.roversList = roversList;
    }
}
