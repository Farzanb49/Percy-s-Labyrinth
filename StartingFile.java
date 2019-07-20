/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : The starting screen for the game I call |*/
/*| "Percy's Labyrinth". Introduces an opening animation  |*/
/*| GIF that explains the plot the game is based off of.  |*/
/*| Additional component to create a storylike and        |*/
/*| adventurous appeal to the game. Also gives the game a |*/
/*| theme                                                 |*/
/*/////////////////////////////////////////////////////////*/
//Importing libraries
import java.util.*;
import java.awt.*;
import javax.swing.*;

//Libraries needed for ActionListener
import java.awt.event.*;

//Libraries needed for Audio clips
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/////////////////////////////////////////////////////////
//                 Class StartingFile                  //
/////////////////////////////////////////////////////////

public class StartingFile extends JFrame implements ActionListener {   

    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////

    //sets static variables for audio files (all audio files are in .wav format)
    public static Clip clip;

    public static Clip clip2;

    //Tells the user to click the screen so the game starts
    JLabel bgImage = new JLabel("");

    //Button to click which takes us to the main menu; encompasses the entire screen
    JButton theBigButton;

  
    //creates static variables for audio files that will be incorporated in game. 
    //Public static variables allow audio files to be accessed from other classes 
    public static String fullSlot = "wdm9l.wav";
    public static String mymovie = "My Movie.wav";
    public static String mazemusic = "l9tgu.wav";
       public static String win = "rjezq.wav";
    /////////////////////////////////////////////////////////
    //                     Constructor                     //
    /////////////////////////////////////////////////////////
    public StartingFile() {

        /////////////////////////////////////////////////////////
        //     1. Creates/Initializes Components               //
        /////////////////////////////////////////////////////////

    
        //Styles the background image as a JLabel
        bgImage = new JLabel ("");
        bgImage.setIcon(new ImageIcon("loadingTypeGif.gif"));
        bgImage.setBounds(0, 0, 864, 600);

        //Button must be clicked
        JButton theBigButton = new JButton ("");
        theBigButton.setActionCommand ("startTheActualProgram");
        theBigButton.addActionListener(this);
        theBigButton.setBorderPainted(false);
        theBigButton.setContentAreaFilled(false);
        theBigButton.setIcon(new ImageIcon("4zoo9Copy.gif"));
        theBigButton.setBounds(-10, 0, 864, 600);
        
        //Plays sound
        playSound (mymovie);
        //Loops sound
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        /////////////////////////////////////////////////////////
        //       2. Create Content panel; Sets Layout(s)       //
        /////////////////////////////////////////////////////////

        //The big JPanel where everything will be placed
        JPanel startingContentPane = new JPanel();
        setContentPane(startingContentPane);

        //Layout must be set as null to set bounds for other components
        startingContentPane.setLayout(null);

        /////////////////////////////////////////////////////////
        //       3. Adds Components to the Content Panel       //
        /////////////////////////////////////////////////////////

        //Adds components/widgets to the contentPane
        startingContentPane.add(theBigButton);
        startingContentPane.add(bgImage);

        /////////////////////////////////////////////////////////
        //         4. Sets Window Attributes; Packs it         //
        /////////////////////////////////////////////////////////

        //Closes the entire program upon clicking the x button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting cordinates and dimensions of the JFrame itself
        setBounds(100, 100, 864, 622);

        //Setting title to "Percy's Labyrinth" on the upper tab of the window
        setTitle("Percy's Labyrinth");

        //Centres the window
        setLocationRelativeTo(null);

        //End of Constructor
    }

    /////////////////////////////////////////////////////////
    // actionPerformed method, responds to ActionListeners //
    /////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent e) {

        //takes user to main menu screen when screen is clicked    
        if (e.getActionCommand().equals ("startTheActualProgram")){
            MainMenu2 object = new MainMenu2();
            //stops clip that's currently playing
            clip.stop();
  
            //closes this screen/JFrame/window
            dispose();

        }
    }

    /////////////////////////////////////////////////////////
    //                     Main Method                     //
    /////////////////////////////////////////////////////////
    public static void main(String[] args) {

        //States what the window should display
        StartingFile window = new StartingFile();

        //Makes the JFrame visible
        window.setVisible(true);

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