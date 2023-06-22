import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Plateau plateau;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader;
        if(args.length>0) myReader = new Scanner(openFile(args[0]));
        else throw new RuntimeException("Please enter the input file name");
        List<Rover> rovers=new ArrayList<>();
        int i=0;
        int rouversCounter=0;
        while (myReader.hasNextLine()) {
            //when i==0 we can go to the next line because plateau is already set in openFile() method.
            if(i==0) myReader.nextLine();
            else{
                String data = myReader.nextLine();
                String[] tab=data.split(" ");
                if((i%2)!=0){
                    int x = Integer.valueOf(tab[0]);
                    int y = Integer.valueOf(tab[1]);
                    Direction direction = Direction.valueOf(tab[2]);
                    rovers.add(new Rover(x,y, direction));
                    rouversCounter++;
                    rovers.get(rovers.size()-1).setName("Rover "+rouversCounter);
                }
                else{
                    rovers.get(rovers.size()-1).setMovementToDo(data);
                }
            }
            i++;
        }
        myReader.close();
        rovers.stream().forEach(element-> {
                updateRoverPositionAndDirection(element);
                System.out.println(element.getX()+" "+element.getY()+" "+element.getDirection());
        });

    }

    /*
    * Update a rover position and direction
    */
    public static void updateRoverPositionAndDirection(Rover r) throws RuntimeException {
            char[] orders = r.getMovementToDo().toCharArray();
            for(char order : orders){
                if(order==Action.Move.getValeur()){
                    //for each rover before updating its position we must check if it will still exist in the plateau or not
                    if(Direction.W.equals(r.getDirection())) {
                        // Here O is the x of the lower-left point of the plateau
                        if((r.getX()-1)>=0) r.setX(r.getX()-1);
                        else {
                            throw new RuntimeException(r.getName()+" can't be out of Plateau. Please check the input file !");
                        }
                    }
                    else if(Direction.E.equals(r.getDirection())) {
                        if((r.getX()+1)<=plateau.getxUpperRightR())  r.setX(r.getX()+1);
                        else {
                            throw new RuntimeException(r.getName()+" can't be out of Plateau. Please check the input file !");
                        }
                    }
                    else if(Direction.N.equals(r.getDirection())) {
                        if((r.getY()+1)<=plateau.getyUpperRight())  r.setY(r.getY()+1);
                        else {
                            throw new RuntimeException(r.getName()+" can't be out of Plateau. Please check the input file !");
                        }

                    }
                    else if(Direction.S.equals(r.getDirection())){
                        // Here O is the y of the lower-left point of the plateau
                        if((r.getY()-1)>=0)  r.setY(r.getY()-1);
                        else {
                            throw new RuntimeException(r.getName()+" can't be out of Plateau. Please check the input file !");
                        }
                    }
                }
                else{
                    //update rover direction
                    if(Direction.N.equals(r.getDirection())){
                        if(order==Action.Left.getValeur()) r.setDirection(Direction.W);
                        else if(order==Action.Right.getValeur()) r.setDirection(Direction.E);
                    }
                    else if(Direction.S.equals(r.getDirection())){
                        if(order==Action.Left.getValeur()) r.setDirection(Direction.E);
                        else if(order==Action.Right.getValeur()) r.setDirection(Direction.W);
                    }
                    else if(Direction.E.equals(r.getDirection())){
                        if(order==Action.Left.getValeur()) r.setDirection(Direction.N);
                        else if(order==Action.Right.getValeur()) r.setDirection(Direction.S);
                    }
                    else if(Direction.W.equals(r.getDirection())){
                        if(order==Action.Left.getValeur()) r.setDirection(Direction.S);
                        else if(order==Action.Right.getValeur()) r.setDirection(Direction.N);
                    }
                }
            }
    }

    /*
     * Check if file content is valid or not and return the file if its content respects the rules
     */
    public static File openFile(String fileName) throws FileNotFoundException {
        String inputFileName;
        if(fileName==null || (fileName!=null && fileName.length()==0)) throw new RuntimeException("Please enter the input file name");
        else inputFileName=fileName;
        File myObj = new File(inputFileName);
        if(myObj.length()==0){
            throw new RuntimeException("The input file is empty !");
        }
        int i=0;
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] tab=data.split(" ");
            //Set Plateau Position
            if(i==0){
                if(data.split(" ").length==2){
                    int x = Integer.valueOf(tab[0]);
                    int y = Integer.valueOf(tab[1]);
                    if(x<=0 || y<=0) throw new RuntimeException("Upper-right point coordinates of the plateau must be bigger than its lower-left point coordinates : line "+(i+1)+" in input.txt");
                    //Set Plateau Position : fix its upper-right point
                    plateau=new Plateau(x,y);
                }
                else{
                    throw new RuntimeException("First line in 'input.txt' must contain the 2 position parameters for plateau !");
                }
            }
            else{
                if((i%2)!=0){
                    if(data.split(" ").length==3){
                        int x = Integer.valueOf(tab[0]);
                        int y = Integer.valueOf(tab[1]);
                        if(x<0 || x>plateau.getxUpperRightR() || y<0 || y>plateau.getyUpperRight()) throw new RuntimeException("Rover can't be out of Plateau. Please check the input file !");
                        Direction.valueOf(tab[2]);
                    }
                    else{
                        throw new RuntimeException("Missing or bad Rover parameters !");
                    }
                }
                else{
                    char[] movementToDo = data.toCharArray();
                    for(char action : movementToDo){
                        if(action!=Action.Left.getValeur() && action!=Action.Right.getValeur() && action!=Action.Move.getValeur()){
                            throw new RuntimeException("Unknown action to do, please check input file, line "+(i+1));
                        }
                    }
                }

            }
            i++;
        }
        myReader.close();
        if(myObj.length()>0 && i==1){
            throw new RuntimeException("No rovers found !");
        }
        return myObj;
    }
}