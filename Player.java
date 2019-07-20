/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : Creates the player icon that will       |*/
/*| navigate through the mazes. The player is set as a    |*/
/*| small JPanel that navigates through maze with         |*/
/*| keylistener (key listener that allows arrow key       |*/
/*| interaction with the mazes is incorporated in Maze    |*/
/*| class)                                                |*/
/*/////////////////////////////////////////////////////////*/

//imports needed libraries
import java.awt.Color;
import java.util.*;
import java.awt.*;
import javax.swing.*;

//libraries that read files
import java.io.BufferedReader;
import java.io.FileReader;

// Needed for ActionListener
import java.awt.event.*;

/////////////////////////////////////////////////////////
//                    Class Player                     //
/////////////////////////////////////////////////////////
public class Player extends JPanel{
    
    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////
    
    //sets int values x and y coordinates of player icon
    int x, y;

    /////////////////////////////////////////////////////////
    //                    constructor                      //
    /////////////////////////////////////////////////////////
    
    // the player icon that navigates through the maze is constructed as a JPanel in this class
    public Player() {
        
        //sets background colour and size of JPanel
        JLabel dragon = new JLabel ();
        dragon.setIcon(new ImageIcon("Dragon-512.png"));
        dragon.setBounds(0,0,30,30); 
        //this.setBackground(Color.red);
        this.setSize(Maze.panelSize, Maze.panelSize);
        this.add(dragon);
    }
    //method that sets direction and distance player moves left when player presses key to go left
    public void moveLeft() {
        //ensures movement is within the bounds of the grid and does not surpass the edges of the maze
        if(x > 0 && Maze.map[x-1][y] == 1){
            //sets new location of the player
            this.setLocation(this.getX()-25, this.getY());
            //reduces x coordinate of player by 1
            x--;
        }
    }
// method that sets direction and distance player moves right when player presses right arrow key to go right
    public void moveRight() {
        //ensures movement is within the bounds of the grid and does not surpass the edges of the maze
        if(x < Maze.columns-1 && Maze.map[x+1][y] == 1){
            //sets new location of the player
            this.setLocation(this.getX()+25, this.getY());
            //increases x coordinate of player by 1 
            x++;
        }
    }
//method that sets direction and distance player moves up when player presses up arrow key to go up 
    public void moveUp() {
        //ensures movement is within the bounds of the grid and does not surpass the edges of the maze
        if(y > 0 && Maze.map[x][y-1] == 1){
            //sets new location of the player
            this.setLocation(this.getX(), this.getY()-25);
            //reduces y coordinate of player by 1
            y--;
        }
    }
//method that sets direction and distance player moves down when player presses down arrow key to go down
    public void moveDown() {
        //ensures movement is within the bounds of the grid and does not surpass the edges of the maze
        if(y < Maze.rows-1 && Maze.map[x][y+1] == 1){
            //sets new location of the player
            this.setLocation(this.getX(), this.getY()+25);
            //increases y coordinate of player 1
            y++;
        }
    }

}
