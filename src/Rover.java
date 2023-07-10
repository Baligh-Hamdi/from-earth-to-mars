import java.util.List;

public class Rover {
    private String name;
    private int x;
    private int y;

    private Direction direction;
    private String movementToDo;

    public Rover(int x, int y, Direction d){
        this.x=x;
        this.y=y;
        this.direction=d;
    }

    /*
     * Update a rover position and direction
     */
    public void move(Plateau plateau) {
        char[] orders = this.getMovementToDo().toCharArray();
        int counter=0;
        for(char order : orders){
            if(order==Action.Move.getValeur()){
                //for each rover before updating its position we must check if it will still exist in the plateau or not
                if(Direction.W.equals(this.getDirection())) {
                    // Here O is the x of the lower-left point of the plateau
                    if((this.getX()-1)>=0) this.x--;
                    else {
                        throw new RuntimeException(this.getName()+" can't be out of Plateau. Please check the input file !");
                    }
                }
                else if(Direction.E.equals(this.getDirection())) {
                    if((this.getX()+1)<=plateau.getxUpperRightR())  this.x++;
                    else {
                        throw new RuntimeException(this.getName()+" can't be out of Plateau. Please check the input file !");
                    }
                }
                else if(Direction.N.equals(this.getDirection())) {
                    if((this.getY()+1)<=plateau.getyUpperRight())  this.y++;
                    else {
                        throw new RuntimeException(this.getName()+" can't be out of Plateau. Please check the input file !");
                    }

                }
                else if(Direction.S.equals(this.getDirection())){
                    // Here O is the y of the lower-left point of the plateau
                    if((this.getY()-1)>=0)  this.y--;
                    else {
                        throw new RuntimeException(this.getName()+" can't be out of Plateau. Please check the input file !");
                    }
                }
            }
            else{
                //update rover direction
                if(Direction.N.equals(this.getDirection())){
                    if(order==Action.Left.getValeur()) this.setDirection(Direction.W);
                    else if(order==Action.Right.getValeur()) this.setDirection(Direction.E);
                }
                else if(Direction.S.equals(this.getDirection())){
                    if(order==Action.Left.getValeur()) this.setDirection(Direction.E);
                    else if(order==Action.Right.getValeur()) this.setDirection(Direction.W);
                }
                else if(Direction.E.equals(this.getDirection())){
                    if(order==Action.Left.getValeur()) this.setDirection(Direction.N);
                    else if(order==Action.Right.getValeur()) this.setDirection(Direction.S);
                }
                else if(Direction.W.equals(this.getDirection())){
                    if(order==Action.Left.getValeur()) this.setDirection(Direction.S);
                    else if(order==Action.Right.getValeur()) this.setDirection(Direction.N);
                }
            }
            if((counter==orders.length-1) && (!this.checkPosition(plateau.getRoversList()))) throw new RuntimeException(this.getName()+" can't be at the same position with an other rover");
            counter++;
        }
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x=x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y=y;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getMovementToDo() {
        return movementToDo;
    }
    public void setMovementToDo(String movement) {
        this.movementToDo=movement;
    }
    public void setDirection(Direction d) {
        this.direction=d;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean checkPosition(List<Rover> roversList){
        boolean check=true;
        int i =0;
        while(check && i<roversList.size()-1){
            if((this.x==roversList.get(i).getX()) && (this.y==roversList.get(i).getY())) check= false;
            i++;
        }
        return check;

    }

}
