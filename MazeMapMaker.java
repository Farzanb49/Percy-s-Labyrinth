/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : Creates an interactable grid that user  |*/ 
/*| can play with to make their own mazes!The starting    |*/
/*| screen for the game I call                            |*/                                              
/*/////////////////////////////////////////////////////////*/

//imports needed libraries
import java.awt.Color;

//libraries needed for interactions with the window itself
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//library for files
import java.io.File;

//libraries that can read files
import java.io.BufferedReader;
import java.io.FileReader;

//Java.io.PrintWriter class prints formatted representations of objects to a text-output stream.
import java.io.PrintWriter;

//allows construction of array lists
import java.util.ArrayList;

//necessary swing libraries for this class
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
/////////////////////////////////////////////////////////
//                 Class MazeMapMaker                  //
/////////////////////////////////////////////////////////
public class MazeMapMaker extends JFrame{

    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////

    //creates static int values for number of rows and columns on mazemaker grid
    static int rows = 20;
    static int columns = 20;

    //sets a panel size
    int panelSize = 25;

    //creates the map
    static int map[][] = new int[columns][rows];

    //Array list for listing the various levels created 
    ArrayList<String> mapList = new ArrayList<String>();

    //initializes int value
    int level = 0;

    //initializes boolean value to determine if level exists
    boolean levelsExistAlready = false;

    /////////////////////////////////////////////////////////
    //             constructor MazeMapMaker                //
    /////////////////////////////////////////////////////////
    public MazeMapMaker(){
        //retrieves list of maps and levels
        getMapList();
        getLevelChoice();

        //displays map on screen
        if(level != -1){
            loadMap();
            
            //sets layout, ability to reize, size, title for JFrame
            this.setResizable(false);
            this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
            this.setTitle("Maze Map Maker");
            this.setLayout(null);
            
            //creates background image
            JLabel img = new JLabel("");
            img.setBounds (0, 0, 1000, 749);

            img.setIcon(new ImageIcon("GamemodeBacground.jpg"));
            
            this.addWindowListener(new WindowAdapter(){
                    //when closing screen with the map, it saves the maze that has just been created and returns to main menu screen
                    public void windowClosing(WindowEvent e) {
                        saveMap();
                        MainMenu2.clip.stop();
                        new MainMenu2();
                        
                    }
                });
            //centers screen 
            this.setLocationRelativeTo(null);
            //organizes the tiles into a grid
            //makes sure grid is size established by the x and y int values, and that the grid
            //is organized and formatted to that size, and tiles are not created beyond the 
            //number of rows and columns indicated
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    //calls tiles that have been created as JPanels
                    MapMakerTile tile = new MapMakerTile(x, y);
                    //sets size for tiles
                    tile.setSize(panelSize-1, panelSize-1);
                    //sets location of tiles
                    tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
                    //value that indicates if tile is a wall (gray) or path (white)
                    if(map[x][y] == 0){
                        tile.setBackground(Color.GRAY);
                    }else{
                        tile.setBackground(Color.WHITE);
                    }
                    //sets tiles as visible
                    tile.setVisible(true);
                    //displays tiles on the screen
                    this.add(tile);
                }
            }
            //sets grid as visible
            this.setVisible(true);
            //adds background image
            this.add(img);
        }else{
            MainMenu2.clip.stop();
            
            //returns to main menu
            new MainMenu2();
            
        }
        
    }
    //creates a list of the maps created
    public void getMapList(){
        //initial i value set as 4 to ensure that levels 1, 2, and 3 cannot be edited
        for(int i = 4;  i < 99; i++){
            //saves maps as files
            File map = new File("./Level "+i+".map");
            //once map is created, saves it as Level i.map
            if(map.exists()){
                //System.out.println("Level "+i+" exists");
                mapList.add("Level "+i+".map");
                //sets boolean value as true
                levelsExistAlready = true;
            }
        }
    }
    //method that creates an Option pane that allows user to select the maze they want to edit. The selected maze will then be displayed on the screen as an interactable grid
    public void getLevelChoice(){
        //ensures level actually exists
        if(levelsExistAlready){
            String maps[] = new String[99];
            mapList.toArray(maps);
            //maps[mapList.size()] = "New level";
            //shows option pane that allows user to select level
            String choice = (String)JOptionPane.showInputDialog(null, "Which level would you like to play?", "Maze Level Selector", JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);
            //System.out.println(choice);
            //loads selected map
            if(choice != null && !choice.equals("New level")){
                level = Integer.parseInt((choice.replace("Level ", "").replace(".map", "")));
            }else if(choice == null){
                level = -1;
            }else{
                level = mapList.size();
            }
        }
    }
    //saves the map as a maze
    public void saveMap(){
        try{
            //stores maze as level #.map (saves as MAP file)
            PrintWriter writer = new PrintWriter("Level "+level+".map", "UTF-8");
            //prints map
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    writer.print(map[x][y]);
                }
                writer.print("\r\n");
            }
            writer.close();
            //catches exception
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //method that loads map
    public void loadMap(){
        try{
            //reads the lines of text which are the boolean values (0s and 1s) that represent the tiles
            BufferedReader br = new BufferedReader(new FileReader("Level "+level+".map"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            //appends string to a file, storing the 0s and 1s as a MAP file
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String mapStr = sb.toString();
            //loads map as it is saved if the level already exists
            int counter = 0;
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    String mapChar = mapStr.substring(counter, counter+1);
                    if(!mapChar.equals("\r\n") && !mapChar.equals("\n")&& !mapChar.equals("\r")){//If it's a number
                        //System.out.print(mapChar);
                        map[x][y] = Integer.parseInt(mapChar);
                    }else{//If it is a line break
                        x--;
                        //System.out.print(mapChar);
                    }
                    counter++;
                }
            }
            //exception resets maze
        }catch(Exception e){
            //System.out.println("Unable to load existing map(if exists), creating new map.");
            for(int y = 0; y < columns; y++){
                for(int x = 0; x < rows; x++){
                    map[x][y] = 0;
                }
            }
        }
    }
}

