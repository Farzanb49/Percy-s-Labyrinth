/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date  : June 7, 2019                                  |*/
/*| Description : Class that creates each individual tile |*/
/*| for the MazeMaker grid                                |*/
/*/////////////////////////////////////////////////////////*/

//imports libraries (in this case library, just the swing libary for JPanel's
import javax.swing.JPanel;

/////////////////////////////////////////////////////////
//                    Class Tile                       //
/////////////////////////////////////////////////////////
public class Tile extends JPanel{
    //creates x and y int values for the height and length of each tile
    int x, y;
    
    //creates a boolean variable to distinguish walls from path. This boolean variable 
    //creates a boolean value (1, as true values are stored as 1) to represent a wall
    boolean isWall = true;
    
    //method to display each tile as a JPanel
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    //method to create walls for the maze
    public void setWall(boolean isWall){
        this.isWall = isWall;
    }
}
