/*//////////////////////////////////////////////////////////*/
/*|                         Header                         |*/
/*//////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                         |*/
/*| ID    : 834221                                         |*/
/*| Date   : June 7, 2019                                  |*/
/*| Description : Major portion of the game's setup. Uses  |*/
/*| card layout to create main menu screen and screens for |*/
/*| a majority of the buttons on the main menu screen,     |*/
/*| including instructions, settings, levels, and mazemaker|*/
/*//////////////////////////////////////////////////////////*/

//imports needed libraries 
import java.util.*;
import java.awt.*;
import javax.swing.*;

//reads files
import java.io.BufferedReader;
import java.io.FileReader;

// Needed for ActionListener and WindowListener
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener;  

//for files in java
import java.io.File;

//part of util library, allows creation of arraylist
import java.util.ArrayList;

//swing libraries for various components//not necessary to be included
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

//libraries for audio files 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//////////////////////////////////////////////////////////
//                   Class MainMenu2                    //
//////////////////////////////////////////////////////////

public class MainMenu2 extends JFrame implements ActionListener {

    /////////////////////////////////////////////////////////
    //*****************************************************//
    //*                                                   *//
    //*            Instance Variables (Global)            *//
    //*                                                   *//
    //*****************************************************//
    /////////////////////////////////////////////////////////

    //Makes it easier to reference CardLayout
    CardLayout cardLayout = new CardLayout ();

    //static clips for audio files
    public static Clip clip;
    public static Clip clip2;

    //JButtons for traversing in between different panels and for different functions (muting, etc)
    JButton button1, button2, button3, gamemode, settings, sound, Return, testbttn, testbttn2, Return2, level1, level2, level3, Return3, mute, Return4, continuer;

    //Background images for each panel
    JLabel menuTitle, backgroundImage1, bgImage, variantBackground, gamemodeback, gamemodeback2, Background;

    //All the different JPanels
    JPanel card0 = new JPanel();
    JPanel card1 = new JPanel();
    JPanel card2 = new JPanel();
    JPanel card3 = new JPanel();
    JPanel card4 = new JPanel();
    JPanel card5 = new JPanel();
    JPanel card6 = new JPanel();
    JPanel card7 = new JPanel();

    //Creates the big panel that contains all of the screens/"cards".
    JPanel cards = new JPanel(new CardLayout());

    //radio buttons for selecting colour of player icon in settings
    JRadioButton red, blue, green, yellow, orange, black, white, purple;

    //audio file as a static String variable
    public static String fullSlot = "wdm9l.wav";
    public static String mymovie = "My Movie.wav";
    public static String mazemusic = "l9tgu.wav";
    public static String win = "rjezq.wav";

    //array list to list levels that exist and can be edited in mazemaker
    ArrayList<String> mapList = new ArrayList<String>();

    //combo box to allow user to select level
    JComboBox<String> lvlList;

    //adjust components of combobox
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 50; //Button/item location on display

    ButtonGroup colours;
    /////////////////////////////////////////////////////////
    //               Constructor MainMenu2                 //
    /////////////////////////////////////////////////////////
    public MainMenu2() {
        //Plays sound
        playSound (fullSlot);
        //Loops sound
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        getMapList();
        lvlList = new JComboBox<String>(mapList.toArray(new String[mapList.size()]));

        /*//////////////////////////////////////////////////////////*/
        /*|    __  __                ___                           |*/
        /*|   |  \/  |___ _ _ _  _  / __| __ _ _ ___ ___ _ _       |*/
        /*|   | |\/| / -_) ' \ || | \__ \/ _| '_/ -_) -_) ' \      |*/
        /*|   |_|  |_\___|_||_\_,_| |___/\__|_| \___\___|_||_|     |*/
        /*//////////////////////////////////////////////////////////*/

        /////////////////////////////////////////////////////////
        //     1. Creates/Initializes Components               //
        /////////////////////////////////////////////////////////

        //Creates the title
        menuTitle = new JLabel("");
        menuTitle.setBounds(340, -8, 864, 200);
        menuTitle.setIcon(new ImageIcon("image (1).png"));

        //Creates the gif in the main menu screen
        JLabel maingif = new JLabel("");
        maingif.setBounds(850, 300, 300, 300);
        maingif.setIcon(new ImageIcon("gifmain.gif"));

        //Recreates the gif to avoid flickering; it works for some uncomprehensible/inexplicable reason
        JLabel maingif1 = new JLabel("");
        maingif1.setBounds(864, 600, 0, 0);
        maingif1.setIcon(new ImageIcon("gifmain.gif"));
        maingif1.setDoubleBuffered(true);
        maingif1.setDoubleBuffered(true);

        //Layout must be set as null to set bounds for other components
        card1.setLayout(null);

        //Creates and styles buttons to go to different screens/JPanels
        button1 = new JButton("");
        button1.setBounds(15, 20, 240, 140);
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setIcon(new ImageIcon("Untitled (9).png"));
        button3 = new JButton("");
        button3.setBounds(15, 140, 240, 140);
        button3.setBorderPainted(false);
        button3.setContentAreaFilled(false);
        button3.setIcon(new ImageIcon("Untitled (10).png"));
        testbttn = new JButton("");
        testbttn.setBounds(15, 260, 260, 140);
        testbttn.setBorderPainted(false);
        testbttn.setContentAreaFilled(false);
        testbttn.setIcon(new ImageIcon("levels.png"));
        testbttn.setActionCommand ("q");
        testbttn.addActionListener(this);
        testbttn2 = new JButton("");
        testbttn2.setBounds(15, 380, 260, 140);
        testbttn2.setBorderPainted(false);
        testbttn2.setContentAreaFilled(false);
        testbttn2.setIcon(new ImageIcon("mazemaker.png"));
        testbttn2.setActionCommand ("r");
        testbttn2.addActionListener(this);
        settings = new JButton ("");
        settings.setBounds(15, 500, 240, 140);
        settings.setBorderPainted(false);
        settings.setContentAreaFilled(false);
        settings.setIcon(new ImageIcon("Untitled (12).png"));

        //Non visible button which has no purpose other than to be clicked in case the JFrame freezes, which then continues all events/actions normally
        JButton nothingBttn = new JButton("");
        nothingBttn.setBounds(0, 0, 864, 600);
        nothingBttn.setBorderPainted(false);
        nothingBttn.setContentAreaFilled(false);

        //Creates the background image
        backgroundImage1 = new JLabel("");
        backgroundImage1.setIcon(new ImageIcon("MainMenuBackground (1).jpg"));
        backgroundImage1.setBounds(0, 0, 1200, 600);

        /////////////////////////////////////////////////////////
        //     2. Adds ActionListeners and sets ActionCommands //
        /////////////////////////////////////////////////////////

        //Sets actionListeners for the "Play", "Game Modes", and "Options" buttons, some have already been set while setting components
        button1.addActionListener(this);
        button1.setActionCommand ("button1");

        button3.addActionListener(this);
        button3.setActionCommand ("button3");

        settings.addActionListener(this);
        settings.setActionCommand ("settings");

        /////////////////////////////////////////////////////////
        //     3. Adds Components to the Content Panel         //
        /////////////////////////////////////////////////////////

        //Adds all the components from above to the JPanel
        card1.add(menuTitle);
        card1.add(button1);

        card1.add(button3);
        card1.add(testbttn);
        card1.add(testbttn2);
        card1.add(settings);

        card1.add(maingif);
        card1.add(backgroundImage1);
        card1.add(nothingBttn);

        //Adds the cards to the big JPanel which contains everything
        cards.add(card1);

        //instructions screen       
        /*/ This is where the instructions are displayed; storyline too /*/
        /*///////////////////////////////////////////////////////////////*/

        /*///////////////////////////////////////////////////////////////*/

        /////////////////////////////////////////////////////////
        //     1. Creates/Initializes Components               //
        /////////////////////////////////////////////////////////

        //Layout must be set as null to set bounds for other components
        card3.setLayout(null);

        //Adds the background image
        variantBackground = new JLabel ("");
        variantBackground.setIcon(new ImageIcon("instructions.png"));
        variantBackground.setBounds(0, -10, 1070, 601);
        Return = new JButton ("Go Back");

        Return.setBounds(800, 10, 200, 75);
        Return.setBackground(Color.black);
        Return.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 36));
        Return.setActionCommand("go back");
        Return.addActionListener(this);

        continuer = new JButton ("Storyline");

        continuer.setBounds(550, 10, 200, 75);
        continuer.setBackground(Color.black);
        continuer.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 36));
        continuer.setActionCommand("continue");
        continuer.addActionListener(this);

        //Adds the button to go back to menu
        card3.add(variantBackground);
        card3.add(Return);
        card3.add(continuer);
        //Adds JPanel to content pane
        cards.add(card3);

        //Levels screen//

        /////////////////////////////////////////////////////////
        //     1. Creates/Initializes Components               //
        /////////////////////////////////////////////////////////

        //Layout must be set as null to set bounds for other components
        card4.setLayout(null);
        lvlList.setSize(menuWidth+35, menuHeight);
        lvlList.setLocation(70, menuY);
        card4.add(lvlList);
        JLabel lvl1 = new JLabel("Level 1 - Easiest level maze");
        lvl1.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 44));
        lvl1.setBounds(30,100,600,70);
        lvl1.setForeground(Color.white);
        JLabel lvl2 = new JLabel("Level 2 - Moderate level maze");
        lvl2.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 44));
        lvl2.setBounds(30,170,600,70);
        lvl2.setForeground(Color.white);
        JLabel lvl3 = new JLabel("Level 3 - Hardest level maze");
        lvl3.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 44));
        lvl3.setBounds(30,240,600,70);
        lvl3.setForeground(Color.white);
        JLabel lvl4 = new JLabel("Level 4,5, and 6 - You can edit these levels ");
        lvl4.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 44));
        lvl4.setBounds(30,310,1000,70);
        lvl4.setForeground(Color.white);
        JLabel lvl5 = new JLabel("in MazeMaker into any maze you want!!");
        lvl5.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 44));
        lvl5.setBounds(30,380,1000,50);
        lvl5.setForeground(Color.white);
        Return2 = new JButton ("Go Back");
        Return2.setBounds(750, 33, 200, 75);
        Return2.setBackground(Color.black);
        Return2.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 36));
        Return2.setActionCommand("go back");
        Return2.addActionListener(this);
        gamemodeback = new JLabel ("");
        gamemodeback.setBounds (0, 0, 1000, 749);

        gamemodeback.setIcon(new ImageIcon("GamemodeBacground.jpg"));

        card4.add(Return2);
        card4.add(lvl1);
        card4.add(lvl2);
        card4.add(lvl3);
        card4.add(lvl4);
        card4.add(lvl5);
        card4.add(gamemodeback);
        //Adds the Jpanel to the content pane
        cards.add(card4);
        
        //Settings screen
        /////////////////////////////////////////////////////////
        //     1. Creates/Initializes Components               //
        /////////////////////////////////////////////////////////

        //Layout must be set as null to set bounds for other components
        card5.setLayout(null);
        JLabel settings = new JLabel ("Settings");
        settings.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 91));
        settings.setForeground(Color.black);
        settings.setBounds(15, 20, 500, 90);
        mute = new JButton("");
        mute.setBounds(0, 300,200, 200);
        mute.setBorderPainted(false);
        mute.setContentAreaFilled(false);
        mute.setIcon(new ImageIcon("sound.png"));
        mute.setActionCommand("mute");
        mute.addActionListener(this);
        Return4 = new JButton ("Go Back");
        Return4.setBounds(700, 25, 200, 75);
        Return4.setBackground(Color.black);
        Return4.setFont(new Font("Berlin Sans FB Demi",Font.BOLD, 36));
        Return4.setActionCommand("go back");
        Return4.addActionListener(this);
        JLabel playerchoice = new JLabel ("");
        playerchoice.setIcon(new ImageIcon("playerchoice.png"));
        playerchoice.setBounds(50, 130, 810, 50);

        //creates the radiobuttons and sets their components
        JRadioButton red = new JRadioButton("red");
        red.setBounds(40,200,200,50);
        red.setForeground(Color.red);
        red.setBackground(Color.gray);
        red.setFont(new Font("Arial",Font.BOLD,30));
        red.setActionCommand("red");
        red.addActionListener(this);
        JRadioButton green = new JRadioButton("green");
        green.setBounds(40,250,200,50);

        green.setForeground(Color.green);
        green.setBackground(Color.gray);
        green.setFont(new Font("Arial",Font.BOLD,30));
        green.setActionCommand("green");
        green.addActionListener(this);
        JRadioButton blue = new JRadioButton("blue");
        blue.setBounds(240,200,200,50);

        blue.setForeground(Color.blue);
        blue.setBackground(Color.gray);
        blue.setFont(new Font("Arial",Font.BOLD,30));
        blue.setActionCommand("blue");
        blue.addActionListener(this);
        Background = new JLabel ("");
        Background.setIcon(new ImageIcon("settingsbackground.gif"));
        Background.setBounds(0, 0, 922, 524);
        JRadioButton white = new JRadioButton("white");
        white.setBounds(240,250,200,50);

        white.setForeground(Color.white);
        white.setBackground(Color.gray);
        white.setFont(new Font("Arial",Font.BOLD,30));
        white.setActionCommand("white");
        white.addActionListener(this);
        JRadioButton black = new JRadioButton("black");
        black.setBounds(440,200,200,50);        

        black.setForeground(Color.black);
        black.setBackground(Color.gray);
        black.setFont(new Font("Arial",Font.BOLD,30));
        black.setActionCommand("black");
        black.addActionListener(this);
        JRadioButton purple = new JRadioButton("purple");
        purple.setBounds(440,250,200,50);

        purple.setForeground(new Color(255,0,255));
        purple.setBackground(Color.gray);
        purple.setFont(new Font("Arial",Font.BOLD,30));
        purple.setActionCommand("purple");
        purple.addActionListener(this);
        JRadioButton yellow = new JRadioButton("yellow");
        yellow.setBounds(640,200,200,50);

        yellow.setForeground(Color.yellow);
        yellow.setBackground(Color.gray);
        yellow.setFont(new Font("Arial",Font.BOLD,30));
        yellow.setActionCommand("yellow");
        yellow.addActionListener(this);
        JRadioButton orange = new JRadioButton("orange");
        orange.setBounds(640,250,200,50);

        orange.setForeground(Color.orange);
        orange.setBackground(Color.gray);
        orange.setFont(new Font("Arial",Font.BOLD,30));
        orange.setActionCommand("orange");
        orange.addActionListener(this);

        //groups all JRadioButtons together into a single buttongroup
        colours = new ButtonGroup();

        //adds radiobuttons to buttongroup
        colours.add(red);
        colours.add(orange);
        colours.add(yellow);
        colours.add(purple);
        colours.add(black);
        colours.add(white);
        colours.add(green);
        colours.add(blue);

        //adds components to Panel
        card5.add(settings);
        card5.add(mute);
        card5.add(Return4);
        card5.add(playerchoice);
        card5.add(red);
        card5.add(blue);
        card5.add(green);
        card5.add(white);
        card5.add(black);
        card5.add(purple);
        card5.add(yellow);
        card5.add(orange);
        card5.add(Background);

        //Adds the Jpanel to the content pane
        cards.add(card5);
        //Set the size of the JFrame
        //PERSONAL side note, size of tab header is 22 pixels
        setSize(1200, (600 + 22));

        //Put Title on top of JFrame
        setTitle("Percy's Labyrinth");
        setResizable(false);

        //JFrame is centered on the user's screen
        setLocationRelativeTo(null);

        //Adds the big JPanel to the JFrame which contains each card
        getContentPane().add(cards);

        //Make JFrame visible
        setVisible(true);

        //Entire program is terminated on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //actionPerformed for response to buttons
    public void actionPerformed (ActionEvent e){

        //starts main menu screen
        if (e.getActionCommand ().equals ("startTheActualProgram")){
            cards.removeAll();
            cards.revalidate();
            cards.repaint();
            cards.add(card1);

        }

        //if instructions button is clicked
        if (e.getActionCommand ().equals ("button3")){
            cards.removeAll();
            cards.revalidate();
            cards.repaint();
            cards.add(card3);
            setSize(1070,600);

        }

        //if one of the "go back" buttons is clicked
        if (e.getActionCommand ().equals ("go back")){
            cards.removeAll();
            cards.revalidate();
            cards.repaint();
            cards.add(card1);
            setSize (1200, (600 + 22));

        }

        //JButton in instructions of mainmenu, allows user to visit storyline screen
        if (e.getActionCommand ().equals ("continue")){
            storyline object = new storyline ();
            setVisible(false);
            //dispose();

        }
        // if levels button is selected
        if (e.getActionCommand ().equals("q")){
            cards.removeAll();
            cards.revalidate();
            cards.repaint();
            cards.add(card4);
            setSize(1000,749);
        }
        //takes user to settings page
        if (e.getActionCommand ().equals("settings")){
            //Maze object = new Maze();
            cards.removeAll();
            cards.revalidate();
            cards.repaint();
            cards.add(card5);

            setSize(922,524);
        }
        //mutes the music if user clicks button
        if (e.getActionCommand ().equals("mute")){

            //StartingFile.clip.stop();
            clip.stop();
            //Maze.clip.stop();
            mute.setIcon(new ImageIcon("mute.png"));
            //sets picture of muted button
            mute.setActionCommand ("unmute");
        }
        //unmutes music if user clicks button again
        else if (e.getActionCommand().equals ("unmute")){
            mute.setIcon(new ImageIcon("sound.png"));
            //Play the background audio
            clip.start();
            //Maze.clip.start();
            //Loops the clip
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            mute.setActionCommand ("mute");
        }
        //response to selecting each JRadioButton
        if (e.getActionCommand().equals("blue")){
            //displays the screen with the interactable maze game
            Maze object = new Maze(lvlList.getSelectedItem().toString());
            //changes player colour to desired colour
            Maze.p.setBackground(Color.blue);
            //disposes currently open screen
            dispose();
        }
        else if (e.getActionCommand().equals("red")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.red);
            dispose();
        }
        else if (e.getActionCommand().equals("yellow")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.yellow);
            dispose();
        }
        else if (e.getActionCommand().equals("orange")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.orange);
            dispose();
        }
        else if (e.getActionCommand().equals("black")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.black);
            dispose();
        }
        else if (e.getActionCommand().equals("purple")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(new Color(255,0,255));
            dispose();
        }
        else if (e.getActionCommand().equals("white")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.white);
            dispose();
        }
        else if (e.getActionCommand().equals("green")){

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            Maze.p.setBackground(Color.green);
            dispose();
        }
        else if (e.getActionCommand ().equals ("button1")){ 

            Maze object = new Maze(lvlList.getSelectedItem().toString());
            //Maze.p.setBackground(Color.red);
            dispose();
        }
        //takes user back to levels screen
        if (e.getActionCommand ().equals ("go back2")){
            cards.removeAll(); 
            cards.revalidate();
            cards.repaint();
            cards.add(card4);
            setSize (1000,749);

        }
        //mazemaker button; takes user to mazemaker screen, where they can select a level and edit the maze on a grid to create whatever maze they like
        if (e.getActionCommand ().equals("r")){
            //calls mazemaker class
            MazeMapMaker object = new MazeMapMaker ();
            //dispose main menu screen for now
            dispose();
        }

    }
    //sees if level exists already, if not saves new maze as level #.map
    static boolean levelsExistAlready = false;
    public void getMapList(){
        for(int i = 0; i < 99; i++){
            File map = new File("./Level "+i+".map");
            if(map.exists()){
                //System.out.println("Level "+i+" exists");
                mapList.add("Level "+i+".map");
                levelsExistAlready = true;
            }
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