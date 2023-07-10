import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader;
        List<Object> l=openFile(args[0]);
        if(args.length>0) myReader = new Scanner((File)l.get(0));
        else throw new RuntimeException("Please enter the input file name");
        int i=0;
        int rouversCounter=0;
        while (myReader.hasNextLine()) {
            //when i==0 we can go to the next line because plateau is already set in openFile() method.
            if(i==0) myReader.nextLine();
            else{
                String data = myReader.nextLine();
                String[] tab=data.split(" ");
                Plateau pl = (Plateau)l.get(1);
                if((i%2)!=0){
                    int x = Integer.valueOf(tab[0]);
                    int y = Integer.valueOf(tab[1]);
                    Direction direction = Direction.valueOf(tab[2]);
                    pl.getRoversList().add(new Rover(x,y, direction));
                    rouversCounter++;
                    pl.getRoversList().get(pl.getRoversList().size()-1).setName("Rover "+rouversCounter);
                }
                else{
                    pl.getRoversList().get(pl.getRoversList().size()-1).setMovementToDo(data);
                    Rover lastRoverAdded = pl.getRoversList().get(pl.getRoversList().size()-1);
                    lastRoverAdded.move(pl);
                    System.out.println(lastRoverAdded.getX()+" "+lastRoverAdded.getY()+" "+lastRoverAdded.getDirection());
                }
            }
            i++;
        }
        myReader.close();

    }



    /*
     * Check if file content is valid or not and return the file if its content respects the rules
     */
    public static List<Object> openFile(String fileName) throws FileNotFoundException {
        String inputFileName;
        if(fileName==null || (fileName!=null && fileName.length()==0)) throw new RuntimeException("Please enter the input file name");
        else inputFileName=fileName;
        File myObj = new File(inputFileName);
        if(myObj.length()==0){
            throw new RuntimeException("The input file is empty !");
        }
        int i=0;
        Plateau plateau=new Plateau(0,0);
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
        List<Object> l= new ArrayList<>();
        l.add(myObj);
        l.add(plateau);
        return l;
    }
}