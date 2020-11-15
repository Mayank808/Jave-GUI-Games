package TicTacToeProgram;

/**
----------------------------------------------------------
Author: Mayank Mehra
Date: Saturday August 14, 2020
Purpose: Tic Tac Toe Program
----------------------------------------------------------
Description of code : Program lets users play a tic tac toe game
Program Functions:
* Play - starts the game 
* Reset - resets the board, makes play button visible again, and displays score 
that is being tracked 
--------------------------------------------------------
 */

import TicTacToeProgram.TicTac;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class TicTacEvent implements ItemListener, ActionListener, Runnable {
    //declaring everything needed for program
    TicTac gui;
    Thread playing;
    ImageIcon imageX = new ImageIcon("ximage.jpg");
    ImageIcon imageO = new ImageIcon("oimage.jpg");
    ImageIcon imgBack = new ImageIcon("ovoOwlBack.jpg");
    int intClicks = 0;
    int intWin = 0;
    int intXNumWins = 0; 
    int intONumWins = 0;
    int intTie = 0; 
    int[][] intCheck = new int[4][4];

    
    public TicTacEvent (TicTac in){
        gui = in;
        for (int row = 0; row <= 3; row++){
           for (int col = 0; col <= 3; col++){
               intCheck[row][col] = 0;
           }
       }
    }

    public void actionPerformed (ActionEvent event) {
       String command = event.getActionCommand();
       
       //checks what button is pressed and runs the method for that specific button
       if (command.equals("Play")) {
           startPlaying();
       }
       if (command.equals("Reset")) {
           resetPlay(); 
       }
       if (command.equals("1")) {
           b1();
       }
       if (command.equals("2")) {
           b2();
       }
       if (command.equals("3")) {
           b3();
       }
       if (command.equals("4")) {
           b4();
       }
       if (command.equals("5")) {
           b5();
       }
       if (command.equals("6")) {
           b6();
       }
       if (command.equals("7")) {
           b7();
       }
       if (command.equals("8")) {
           b8();
       }
       if (command.equals("9")) {
           b9();
       }
        if (command.equals("10")) {
           b10();
       }
       if (command.equals("11")) {
           b11();
       }
       if (command.equals("12")) {
           b12();
       }
       if (command.equals("13")) {
           b13();
       }
       if (command.equals("14")) {
           b14();
       }
       if (command.equals("15")) {
           b15();
       }
       if (command.equals("16")) {
           b16();
       }
    }
    
    /**
     * All Button Methods below will set the image of the specific button click into a X or O image
     * Also adds a 1 or 2 value to int check based on whether a x or o is placed into the box respectively 
     * based on the inClick counter being odd or even 
     * intClick = odd number: image of x will be put on button 
     * intClick = even number: image of o will be put on button 
     * winner method is also run to check whether that turn resulted in a win for the user
     */
    void b1() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[0][0].setIcon(imageX);
            intCheck[0][0] = 1;
        } else {
            gui.btnBoxes[0][0].setIcon(imageO);
            intCheck[0][0] = 2;
        }
        winner();

    }
    void b2() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[0][1].setIcon(imageX);
            intCheck[0][1] = 1;
        } else {
            gui.btnBoxes[0][1].setIcon(imageO);
            intCheck[0][1] = 2;
        }
        winner();
    }
    void b3() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[0][2].setIcon(imageX);
            intCheck[0][2] = 1;
        } else {
            gui.btnBoxes[0][2].setIcon(imageO);
            intCheck[0][2] = 2;
        }
        winner();
    }
    void b4() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[0][3].setIcon(imageX);
            intCheck[0][3] = 1;
        } else {
            gui.btnBoxes[0][3].setIcon(imageO);
            intCheck[0][3] = 2;
        }
        winner();
    }
    void b5() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[1][0].setIcon(imageX);
            intCheck[1][0] = 1;
        } else {
            gui.btnBoxes[1][0].setIcon(imageO);
            intCheck[1][0] = 2;
        }
        winner();
    }
    void b6() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[1][1].setIcon(imageX);
            intCheck[1][1] = 1;
        } else {
            gui.btnBoxes[1][1].setIcon(imageO);
            intCheck[1][1] = 2;
        }
        winner();
    }
    void b7() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[1][2].setIcon(imageX);
            intCheck[1][2] = 1;
        } else {
            gui.btnBoxes[1][2].setIcon(imageO);
            intCheck[1][2] = 2;
        }
        winner();
    }
    void b8() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[1][3].setIcon(imageX);
            intCheck[1][3] = 1;
        } else {
            gui.btnBoxes[1][3].setIcon(imageO);
            intCheck[1][3] = 2;
        }
        winner();
    }
    void b9() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[2][0].setIcon(imageX);
            intCheck[2][0] = 1;
        } else {
            gui.btnBoxes[2][0].setIcon(imageO);
            intCheck[2][0] = 2;
        }
        winner();
    }
    void b10() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[2][1].setIcon(imageX);
            intCheck[2][1] = 1;
        } else {
            gui.btnBoxes[2][1].setIcon(imageO);
            intCheck[2][1] = 2;
        }
        winner();
    }
    void b11() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[2][2].setIcon(imageX);
            intCheck[2][2] = 1;
        } else {
            gui.btnBoxes[2][2].setIcon(imageO);
            intCheck[2][2] = 2;
        }
        winner();
    }
    void b12() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[2][3].setIcon(imageX);
            intCheck[2][3] = 1;
        } else {
            gui.btnBoxes[2][3].setIcon(imageO);
            intCheck[2][3] = 2;
        }
        winner();
    }
    void b13() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[3][0].setIcon(imageX);
            intCheck[3][0] = 1;
        } else {
            gui.btnBoxes[3][0].setIcon(imageO);
            intCheck[3][0] = 2;
        }
        winner();
    }
    void b14() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[3][1].setIcon(imageX);
            intCheck[3][1] = 1;
        } else {
            gui.btnBoxes[3][1].setIcon(imageO);
            intCheck[3][1] = 2;
        }
        winner();
    }
    void b15() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[3][2].setIcon(imageX);
            intCheck[3][2] = 1;
        } else {
            gui.btnBoxes[3][2].setIcon(imageO);
            intCheck[3][2] = 2;
        }
        winner();
    }
    void b16() {
        intClicks = intClicks + 1;
        if ((intClicks%2) == 1){
            gui.btnBoxes[3][3].setIcon(imageX);
            intCheck[3][3] = 1;
        } else {
            gui.btnBoxes[3][3].setIcon(imageO);
            intCheck[3][3] = 2;
        }
        winner();
    }

    /**
     * Winner method checks all possible ways a user can win whether they are x and o
     */
    void winner() {
        
        /** Check rows for winner */
        for (int x = 0; x <= 3; x++){
            if (((intCheck[x][0] == intCheck[x][1])&&(intCheck[x][0] == intCheck[x][2])) && (intCheck[x][0] == intCheck[x][3])){
                    if (intCheck[x][0]==1) {
                        JOptionPane.showMessageDialog(null, "X is the winner");
                        intWin = 1;
                        intXNumWins += 1; 
                    } else if (intCheck[x][0]==2){
                        JOptionPane.showMessageDialog(null, "O is the winner");
                        intWin = 1;
                        intONumWins += 1; 
                    }     
            }
        }
        /** Check columns for winner */
        for (int x = 0; x <= 3; x++){
            if (((intCheck[0][x] == intCheck[1][x])&&(intCheck[0][x] == intCheck[2][x])) && (intCheck[0][x] == intCheck[3][x])) {
                    if (intCheck[0][x] == 1) {
                        JOptionPane.showMessageDialog(null, "X is the winner");
                        intWin = 1;
                        intXNumWins += 1; 
                    } else if (intCheck[0][x] == 2) {
                        JOptionPane.showMessageDialog(null, "O is the winner");
                        intWin = 1;
                        intONumWins += 1; 
                    } 
            }
        }
        /** Check diagonal top left to bottom right for winner */
        if (((intCheck[0][0] == intCheck[1][1])&&(intCheck[0][0] == intCheck[2][2])) && (intCheck[0][0] == intCheck[3][3])) {
            if (intCheck[0][0] == 1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                intWin = 1;
                intXNumWins += 1; 
            } else if (intCheck[0][0] == 2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                intWin = 1;
                intONumWins += 1; 
            }
        }    
        /** Check diagonal bottom left to top right for winner */
        if (((intCheck[3][0] == intCheck[2][1])&&(intCheck[3][0] == intCheck[1][2])) && (intCheck[3][0] == intCheck[0][3])) {
            if (intCheck[3][0] == 1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                intWin = 1;
                intXNumWins += 1; 
            } else if (intCheck[3][0] == 2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                intWin = 1;
                intONumWins += 1; 
            }
        }
        /** Checks if the game is a tie */
        if ((intClicks == 16) && (intWin == 0)) {
            JOptionPane.showMessageDialog(null, "The game is a tie");
            intTie += 1; 
        }
    }
    
    /**
     * Starts the game 
     */
    void startPlaying() {
        playing = new Thread(this);
        playing.start();
        gui.btnPlay.setEnabled(false);
    }
    
   /**
    * Resets the board and arrays so users can play again
    */
    void resetPlay() {
        //resets click counter
        intClicks = 0; 
        //makes play button visable again
        gui.btnPlay.setEnabled(true);
        //output score being counted in winner method into the text fields 
        gui.txtAXWinner.setText("X Wins: " + intXNumWins + "\nGame Ties: " + intTie);
        gui.txtAOWinner.setText("O Wins: " + intONumWins);
        
        //resets the button array and images
        for (int row = 0; row<=3; row++){
           for (int col = 0; col <= 3; col++){
               intCheck[row][col] = 0;
               gui.btnBoxes[row][col].setIcon(imgBack); 
           }
       }
    }
    
    

    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

