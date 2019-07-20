/*/////////////////////////////////////////////////////////*/
/*|                         Header                        |*/
/*/////////////////////////////////////////////////////////*/
/*| Name  : Farzan                                        |*/
/*| ID    : 834221                                        |*/
/*| Date   : June 7, 2019                                 |*/
/*| Description : If player wants to view storyline again |*/ 
/*| to get an understanding of the plot of the game, they |*/
/*| can feel free to do so by rewatching the gif, created |*/
/*| in this class, under a seperate JFrame                |*/
/*/////////////////////////////////////////////////////////*/

//Import libraries
import java.awt.*;
import javax.swing.*;

//Needed for ActionListener
import java.awt.event.*;

/////////////////////////////////////////////////////////
//                 Class storyline                     //
/////////////////////////////////////////////////////////

public class storyline extends JFrame implements ActionListener {
    
    /////////////////////////////////////////////////////////
    //             Instance Variables (Global)             //
    /////////////////////////////////////////////////////////
    
    //one global variable to create background and indicate to click screen
    JLabel theBigButton;
    
    //other variable allows user to click anywhere to go to main menu screen
    JButton Return3;

    //Creates the big panel that contains all of the components
    JPanel cards = new JPanel(new CardLayout());
    
    /////////////////////////////////////////////////////////
    //             Constructor storyline                   //
    /////////////////////////////////////////////////////////
    public storyline (){
        //sets layout of JPanel as null to allow to set bounds of widgets
        cards.setLayout(null);
        
        //sets background colour as black
        cards.setBackground(Color.black);
        
        //initializes widgets
        theBigButton = new JLabel ("");
        
        //sets image as a JLabel
        theBigButton.setIcon(new ImageIcon("4zoo9Copy.gif"));
        
        //sets bounds within screen to place widget
        theBigButton.setBounds(20, 0, 864, 600);
        
        //adds widget to JPanel
        
        //creates component in similar matter for a JButton
        cards.add(theBigButton);
        Return3 = new JButton ("Go Back");
        Return3.setBounds(900, 250, 200, 75);
        Return3.setBackground(Color.red);
        Return3.setFont(new Font("Jokerman",Font.BOLD, 22));
        
        //sets actioncommand and actionlistener to allow response to clicking button
        Return3.setActionCommand("go back2");
        Return3.addActionListener(this);
        
        //adds JButton to the JPanel
        cards.add(Return3);
        
        //Set the size of the JFrame
        //PERSONAL side note, size of tab header is 22 pixels
        setSize(1200, (600 + 22));

        //Put Title on top of JFrame
        setTitle("Percy's Labyrinth");
        
        //does not allow user to resize the screen
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

    //creates actionPerformed method that responds to commands set for JButtons
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand ().equals ("go back2")){
            MainMenu2.clip.stop();
            //takes user to JFrame constructed in MainMenu2 (the main menu screen)
            MainMenu2 object = new MainMenu2 ();           
            //disposes of this screen
            dispose();
        }
    }
}
