/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : Main logic screen; Displays the maze    |*/
/*| that the user plays. Interactable using arrow keys    |*/ 
/*/////////////////////////////////////////////////////////*/

//imports needed libraries
import java.util.*;
import java.awt.*;

//allows placing of widgets
import javax.swing.*;
import java.awt.Color;

//allows us to implement action listeners, window listeners, key listeners, and mouse listeners, etc
import java.awt.event.*;

//reads files
import java.io.BufferedReader;
import java.io.FileReader;

//libraries for audio files 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/////////////////////////////////////////////////////////
//                      Class Maze                     //
/////////////////////////////////////////////////////////
public class Maze extends JFrame {

    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////
    
    //static clips for audio
    public static Clip clip;
    public static Clip clip2;
    
    //int values initialized, will be needed to create maze
    public static int rows = 20;
    public static int columns = 20;
    public static int panelSize = 25;
    public static int map[][] = new int[columns][rows];
    public static int endLevelLoc;
    
    //audio files, Public static variables allow audio files to be accessed from other classes 
    public static String fullSlot = "wdm9l.wav";
    public static String mymovie = "My Movie.wav";
    public static String mazemusic = "l9tgu.wav";
    public static String win = "rjezq.wav";
    
    //creates a static player variable
    static Player p;

    /////////////////////////////////////////////////////////
    //                 constructor Maze                    //
    /////////////////////////////////////////////////////////
    public Maze(String str){
        
        loadMap(str);
        
        //Setting cordinates and dimensions of the JFrame itself
        setBounds(100, 100, 864, 622);

        //Setting title to Percy's Labyrinth on the upper tab of the window
        setTitle("Percy's Labyrinth");

        //Centres the window
        setLocationRelativeTo(null);

        this.setResizable(false);
        this.setSize((columns*panelSize)+50, (rows*panelSize)+70);
        this.setTitle("Maze");

        //Layout must be set as null to set bounds for other component
        this.setLayout(null);

        //creates component for background as an image
        JLabel image = new JLabel ("");
        image.setIcon(new ImageIcon("mazebackground.jpg"));
        image.setBounds(0, -100, 900, 749);

        //adds key listener that allows interaction with keys
        this.addKeyListener(new KeyListener(){

                //overrides the keyPressed method
                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();

                    revalidate();
                    repaint();

                    //Player movement
                    //if up arrow key is pressed, player moves up a tile
                    if(key == KeyEvent.VK_UP){
                        p.moveUp();
                    }

                    //if left arrow key is pressed, player moves left a tile
                    if(key == KeyEvent.VK_LEFT){
                        p.moveLeft();
                    }

                    //if down arrow key is pressed, player moves down a tile
                    if(key == KeyEvent.VK_DOWN){
                        p.moveDown();
                    }

                    //if right arrow key is pressed, player moves right a tile
                    if(key == KeyEvent.VK_RIGHT){
                        p.moveRight();
                    }

                    //if player reaches to end of the maze, a JOptionPane pops up that congratules them and indicates they have won and maze is completed
                    if(p.x == columns-1 && p.y == endLevelLoc){
                        //MainMenu2.clip.stop();
                        MainMenu2.clip.stop();
                        playSound(win);
                        JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
                        
                        //closing Option pane disposes of the current screen and returns user to main menu
                        dispose();
                        clip.stop();
                        new MainMenu2();
                    }
                }

                @Override
                public void keyReleased(KeyEvent arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyTyped(KeyEvent arg0) {
                    // TODO Auto-generated method stub

                }

            });
        //if player closes game window, they are returned to the main menu
        this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    //System.out.println((columns*panelSize)+50+"-"+((rows*panelSize)+70));
                    //current audio stops so it doesn't overlap from the audio that generates again when the window is displayed
                    MainMenu2.clip.stop();
                    new MainMenu2();
                }
            });

        //Create player
        p = new Player();
        //makes player visible
        p.setVisible(true);
        //sets player to the frame
        this.add(p);

        //Colours map
        for(int y = 0; y < columns; y++){
            for(int x = 0; x < rows; x++){
                //creates tiles
                Tile tile = new Tile(x, y);
                tile.setSize(panelSize, panelSize);
                tile.setLocation((x*panelSize)+23, (y*panelSize)+25);
                //sets colour of tile as gray, which is a tile player cannot pass through
                if(map[x][y] == 0){
                    tile.setBackground(Color.GRAY);
                }else{
                    //sets colour of tile as white, which is a tile player can pass through
                    tile.setBackground(Color.WHITE);
                    //allows player to navigate through white tiles
                    tile.setWall(false);
                    if(x == 0){
                        //sets location of player when game starts
                        p.setLocation((x*panelSize)+23, (y*panelSize)+25);
                        p.y = y;
                    }
                    //recognizes when to end game
                    if(x == columns-1){
                        endLevelLoc = y;
                    }
                }
                //sets tiles as visible
                tile.setVisible(true);
                //ads tiles to the frame
                this.add(tile);
            }
        }
        //sets maze as visible
        this.setVisible(true);
        //adds background image to the screen
        this.add(image);
    }

    //method that loads map
    public void loadMap(String str){
        try{
            //reads the lines of text which are the boolean values (0s and 1s) that represent the tiles
            BufferedReader br = new BufferedReader(new FileReader(str));
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
        }

    }
        public void playSound(String audioFileName) {
        //Tries the code, but provides a "catch" to handle any exceptions
        try {

            //Opens an audio input stream
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(MainMenu2.class.getResource(audioFileName));

            //Gets a sound clip resource
            clip = AudioSystem.getClip();

            //Open audio clip and loads clip from the audio input stream.
            clip.open(audioIn);

            //Starts clip
            clip.start();

            //In case of error, message to console is printed
        } catch (Exception ex) {
            System.out.println("Customized Error 404! AudioFile Not Found!\nCould be due to user settings");
        }
    }

    //Same as playSound, but this loops the audiofile as well
    public static void playSoundLoop(String audioFileName, boolean playOrNot) {
        if (playOrNot == true) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MainMenu2.class.getResource(audioFileName));
                clip2 = AudioSystem.getClip();
                clip2.open(audioInputStream);
                clip2.loop(Clip.LOOP_CONTINUOUSLY);
                clip2.start();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR 404; AUDIOFILE NOT FOUND!", "AUDIO: " + "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            clip2.stop();
        }
    }
}

