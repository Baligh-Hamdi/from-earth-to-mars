public class Plateau {
    private int xUpperRight;
    private int yUpperRight;

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
    public void setxyUR(int xUR,int yUR) {
        this.xUpperRight = xUR;
        this.yUpperRight = yUR;
    }
}
