/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : Class that creates the tiles and makes  |*/
/*| them interactable with a mouse.                       |*/
/*|                                                       |*/
/*| Right click creates walls for maze                    |*/
/*| Left click creates paths                              |*/
/*|                                                       |*/
/*| Incorporates Mouse Listener                            |*/
/*/////////////////////////////////////////////////////////*/

//imports needed libraries
import java.awt.Color;

//imports libraries needed for using mouse to create mazes on the mazemaker grid
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//imports swing library for JPanels
import javax.swing.JPanel;

/////////////////////////////////////////////////////////
//                 Class MapMakerTile                  //
/////////////////////////////////////////////////////////

//extends JPanel to creates class as a JPanel 

public class MapMakerTile extends JPanel{
    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////
    
    //initializes int variables for length and height of tiles
    int x, y;
    
    /////////////////////////////////////////////////////////
    //            constructor MapMakerTile                 //
    /////////////////////////////////////////////////////////
    public MapMakerTile(int x, int y){
        
        //sets tiles as JPanel
        this.x = x;
        this.y = y;
        
        //adds MouseListener so that mouse can interact with MazeMaker grid to create mazes
        addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e) {
                        //left click sets tile as white to indicate it is a path that player can go through
                    	if(e.getButton() == MouseEvent.BUTTON1){
	                        setBackground(Color.WHITE);
	                        //sets value as 1 to indicate path
	                        MazeMapMaker.map[x][y] = 1;
                    	}
                    	//right click sets tile as gray to indicate it is a wall that player cannot pass through
                    	if(e.getButton() == MouseEvent.BUTTON3){
	                        setBackground(Color.GRAY);
	                        //sets value as 0 to indicate wall
	                        MazeMapMaker.map[x][y] = 0;
                    	}
                    }
                });
    }
}
