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

}
