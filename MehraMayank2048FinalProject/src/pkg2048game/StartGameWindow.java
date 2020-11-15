/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048game;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
----------------------------------------------------------
Author: Mayank Mehra
Date: Thursday August 24, 2020
Purpose: 2048 Game Program
----------------------------------------------------------
Description of code : This program will allow the user to play a 2048 game 
--------------------------------------------------------
 */

/**
 * Class creates and displays the start window screen when game is run
 */
public class StartGameWindow extends JFrame implements ActionListener {
    
    //declaring all components of jFrame
    JLabel lblWelcome;
    JLabel lblRules; 
    JButton btnStart;
    JPanel panelBackground;
    JTextArea txtARules;
    JTextField txtGetGame;
    JLabel lblGetGame; 
    JLabel lblOutputScore; 
    JButton btnGetScore; 
    
    //declaring score keeping objects to inherit its methods and declare arrayLists to store txt file outcomes
    ArrayList <String> strGameNum = new ArrayList<String> (); 
    ArrayList <String> strScore = new ArrayList<String> ();
    ScoreKeeping scoreKeep = new ScoreKeeping(); 
    
    /**
     * Constructor method for start window will take in size and caption and create the window 
     * @param intSizeH takes in a height value for the jframe
     * @param intSizeW takes in a width value for the jframe 
     * @param strCaption takes in a caption for the jframe window
     */
    public StartGameWindow (int intSizeH, int intSizeW, String strCaption) {
        
        resize(intSizeH,intSizeW);
        setLayout(null);
        this.setTitle(strCaption);
        
        //sets up and displays all components that need to be on a board jframe 
        panelBackground = new JPanel(); 
        panelBackground.setLocation(0,0); 
        panelBackground.setSize(intSizeH, intSizeW); 
        panelBackground.setLayout(null);
        panelBackground.setBackground(new Color(0xFAF8EF));
        add(panelBackground); 
        
        lblWelcome = new JLabel("2048");
        lblWelcome.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 60)); 
        lblWelcome.setSize(450, 50);
        lblWelcome.setLocation(180, 0);
        panelBackground.add(lblWelcome);
        
        
        //Displays the rules for the game in text area
        txtARules = new JTextArea( "Welcome to the 2048 Experience \n" +
                                    "Instructions\n" +
                                    "1. The Goal of the game is to reach 2048\n" +
                                    "2. Combine Buttons with the same value in the grid that are adjecent to one another\n" +
                                    "3. In order to help you win the game, there is a sort rows and sort column button that will sort the grid and make it easier for you to combine numbers.\n" +
                                    "4. If you doesn’t feel continuing the game you can either exit or reset the board any time during their playthrough\n" +
                                    "5. Most Importantly remember to have FUN!!!");
                                
        txtARules.setFont(new Font("Serif", Font.PLAIN, 16));
        txtARules.setSize(400, 200);
        txtARules.setLocation(50, 100);
        txtARules.setLineWrap(true); //makes it so that all the lines appear within the text area
        txtARules.setWrapStyleWord(true);
        panelBackground.add(txtARules); 
        
        lblGetGame = new JLabel("Get Game Score:");
        lblGetGame.setFont(new Font("Courier New", Font.ITALIC, 16)); 
        lblGetGame.setSize(200, 30);
        lblGetGame.setLocation(40, 325);
        panelBackground.add(lblGetGame);

        txtGetGame = new JTextField(); 
        txtGetGame.setFont(new Font("Serif", Font.ITALIC, 16));
        txtGetGame.setSize(250, 30);
        txtGetGame.setLocation(200, 325);
        panelBackground.add(txtGetGame); 
        
        lblOutputScore = new JLabel("Intput a Game # above");
        lblOutputScore.setFont(new Font("Courier New", Font.ITALIC, 16)); 
        lblOutputScore.setSize(400, 30);
        lblOutputScore.setLocation(150, 375);
        panelBackground.add(lblOutputScore);
        
        btnGetScore = new JButton("Get Score");
        btnGetScore.setSize(150, 25);
        btnGetScore.setLocation(175, 400);
        panelBackground.add(btnGetScore);
           
        btnGetScore.setActionCommand("GetScore");
        btnGetScore.addActionListener(this);

        btnStart = new JButton("START");
        btnStart.setSize(100, 50);
        btnStart.setLocation(0, 450);
        panelBackground.add(btnStart);
           
        btnStart.setActionCommand("Start");
        btnStart.addActionListener(this);
        
       
    }

    /**
     * Method will check the button clicked on screen and run its respective commands
     * @param event 
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("Start")) { //opens the 2048 board game window
            Board2048 myBoard = new Board2048();
            myBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myBoard.setVisible(true);
            myBoard.drawGrid(590,700, "2048"); 
            this.dispose(); //closes this window
        
       } else if (command.equals("GetScore")) { //if the get score button is clicked
           
           //gets text from text field for game num score the user is looking for 
           String intFindGame = txtGetGame.getText(); 
           
           //saves the past game scores in the textfile into the current arraylists
           scoreKeep.saveScores(strGameNum, strScore);
           
           //runs these arraylists through binary search to see if the game value exists
           boolean isGame = scoreKeep.findGameScoreBinarySearch(strGameNum, 0, strGameNum.size() - 1, intFindGame); 
           
           
           //if the inputed reference number is in the array the game score is outputed into the jlabel 
            if (isGame == true) {
                //loops through the game num array
                for (int i = 0; i < strGameNum.size(); i++) {
                    //if the element in index i is equal to the user inputed game num then the score is ouputed for that game 
                    if (strGameNum.get(i).equals(intFindGame)) {
                        lblOutputScore.setText("Score for game " + intFindGame + " was " + strScore.get(i)); //outputs the score for the game in index i
                    }
                }
            } else { //tells user that the number does not exist
                lblOutputScore.setText("Game does not exist!");
            }

           
           
       }
    }
}
