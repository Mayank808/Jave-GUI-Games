
package pkg2048game;

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
----------------------------------------------------------
Author: Mayank Mehra
Date: Thursday August 24, 2020
Purpose: 2048 Game Program
----------------------------------------------------------
Description of code : This program will allow the user to play a 2048 game 
--------------------------------------------------------
 */

public class Board2048 extends BoardClass {
    
    //declaring all image icons specific to the 2048 board 
    ImageIcon icon2 = new ImageIcon("num2.jpg");
    ImageIcon icon4 = new ImageIcon("num4.jpg");
    ImageIcon icon8 = new ImageIcon("num8.jpg");
    ImageIcon icon16 = new ImageIcon("num16.jpg");
    ImageIcon icon32 = new ImageIcon("num32.jpg");
    ImageIcon icon64 = new ImageIcon("num64.jpg");
    ImageIcon icon128 = new ImageIcon("num128.jpg");
    ImageIcon icon256 = new ImageIcon("num256.jpg");
    ImageIcon icon512 = new ImageIcon("num512.jpg");
    ImageIcon icon1024 = new ImageIcon("num1024.jpg");
    ImageIcon icon2048 = new ImageIcon("num2048.jpg");
    
    //creates a instance of the scorekeeping class in order to inherit some of its methods 
    ScoreKeeping scoreKeep = new ScoreKeeping(); 
    
    //declares variables for running score
    int intRunningScore = 0; 
    
    //stores the past game nums and scores in the txt file into an array list
    ArrayList <String> strGameNum = new ArrayList<String> (); 
    ArrayList <String> strScore = new ArrayList<String> (); 
    
    /**
     * Method checks what button is pressed based on the preset button action command
     * @param event gets the event that is clicked, the action command of the button clicked, 
     * this can be used to call the respective method
     */
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand(); //gets the string action command of the button pressed 
        
        //checks what button is pressed and runs its respective button
        if (command.equals("StartGame")) { 
           startGame();   
        } else if (command.equals("Exit")) {
           exitCheckMax(); 
        } else if (command.equals("Reset")) {
            resetGame(); 
        } else if (command.equals("Score")) {
            displayScore();  
        } else if (command.equals("Rows")) {
            sortRows(); 
        } else if (command.equals("Columns")) {
            sortColumns(); 
        } else if (command.equals("1")) {
            btn1(); 
        } else if (command.equals("2")) {
            btn2(); 
        } else if (command.equals("3")) {
            btn3(); 
        } else if (command.equals("4")) {
            btn4(); 
        } else if (command.equals("5")) {
            btn5(); 
        } else if (command.equals("6")) {
            btn6(); 
        } else if (command.equals("7")) {
            btn7(); 
        } else if (command.equals("8")) {
            btn8(); 
        } else if (command.equals("9")) {
            btn9(); 
        } else if (command.equals("10")) {
            btn10(); 
        } else if (command.equals("11")) {
            btn11(); 
        } else if (command.equals("12")) {
            btn12(); 
        } else if (command.equals("13")) {
            btn13(); 
        } else if (command.equals("14")) {
            btn14(); 
        }else if (command.equals("15")) {
            btn15(); 
        }else if (command.equals("16")) {
            btn16(); 
        }
    }
 
    /**
     * Method is called when exit button is clicked and finds the largest number on the board
     */
    public void exitCheckMax() {
        
        //declares and saves the first number in the 2d array as a temporary max
        int intLargestNum = intGrid[0][0]; 
        
        for (int i = 0; i < 4; i++) { //loops through the rows in the 2d array 
            for (int j = 0; j < 4; j++) { //loops through the columns in the 2d array 
                if (intLargestNum < intGrid[i][j]) intLargestNum = intGrid[i][j]; //saves the largest number in the array
            }
        }
        
        //displays a option pane with the highest number on the board
        JOptionPane.showMessageDialog(null, "You were only able to reach " +intLargestNum + ". Better Luck next time");
        
        //closes the window and game
        this.dispose();
        
    }
    
    /**
     * Method is called whenever a pair is matched in the grid is pressed, checks if the user has hit 2048 using linear search.
     * If user hits 2048 this method closes the game board window and displays the winner screen.
     * Method will also save the games score only when the user manages to win the game 
     */
    public void winCheck() {
        int intLastGame; 
        String strNewGame; 

        //uses linear search to find the number 2048 in the grid
        for (int i = 0; i < 4; i++) { //loops through the rows in the 2d array
            for (int j = 0; j < 4; j++) { //loops through the columns in the 2d array
                if (intGrid[i][j] == 2048) { //change value to increase game limit

                    //gets presaved game score from text file 
                    scoreKeep.saveScores(strGameNum, strScore);
                    
                    //finds out the current game number by looking at last game number in array
                    intLastGame = Integer.parseInt(strGameNum.get(strGameNum.size() - 1)); 
                    strNewGame = Integer.toString(intLastGame + 1); 
                    

                    strGameNum.add(strNewGame);//saves new game run
                    strScore.add(Integer.toString(intRunningScore)); //adds the score for the game that the user just won 
                    scoreKeep.updateScoresFile(strGameNum, strScore);  //updates the txt files with the new game num and score
                    
                    //creates a new instance of the winner window, and opens the winner window
                    WinnerWindow playerWon = new WinnerWindow(Integer.toString(intRunningScore), strNewGame); 
                    playerWon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    playerWon.setVisible(true);//displays the winner window
                    this.dispose(); //closes the 2048 board window   
                }
            }
        }
        
    }
    
    /**
     * Method is called when the get score button is clicked and will get the score using binary search
     */
    public void displayScore() {
        //gets text from text field for game num score the user is looking for 
        String intFindGame = txtGetGameNum.getText(); 
           
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
                   lblGetGameBeat.setText("Game " + intFindGame + " score to beat: " + strScore.get(i)); //outputs the score for the game in index i
                }
            }
        } else { //tells user that the number does not exist
            txtGetGameNum.setText("Game does not exist!");
        }

    } 
    
    /**
     * Method is called whenever the sort columns button is pressed, the method uses bubble sort on 
     * columns of the 2d array in order to sort them in ascending order.
     */
    public void sortColumns() {
        //declaring a temp variable for bubble sort swap
        int intTemp; 
        
        //loops through the 2d arrays columns, bubble sort is ran on each column
        for(int h = 0; h < intGrid[0].length; h++) {
            //bubble sort
            for (int i = 0; i < intGrid.length; i++) { //runs through bubble sort algorithm, using a duel for loop 

                for (int j = 0; j < intGrid.length - 1 - i; j++) { //loops through the actual rows in order to compare everything in column h 
                    
                    //checks the rows in the column h 
                    if (intGrid[j][h] > intGrid[j+1][h]) { //sorts it in ascending order 
                        //swaps values in rows of column h 
                        intTemp = intGrid[j][h]; 
                        intGrid[j][h] = intGrid[j + 1][h]; 
                        intGrid[j + 1][h] = intTemp; 
                    }
                }
            }
        }
        
        //updates the icons based on the numbers within the grid
        for (int x = 0; x < intGrid.length; x++) {
            for(int y= 0; y < intGrid[0].length; y++) {
                btnGrid[x][y].setIcon(getIcon(intGrid[x][y]));
            }
        }
        
    }
    
    /**
     * Method is called whenever the sort rows button is pressed, the method uses selection sort on 
     * rows of the 2d array in order to sort them in ascending order.
     */
    public void sortRows() {
        
        //declaring variables
        int intTemp; 
        int intMinNumSwap;
        
        //loops through the rows in the 2d array, and runs selection sort on each row
        for(int h = 0; h < intGrid.length; h++) {
            
            for (int i = 0; i < intGrid[0].length - 1; i++) {  //loops through the whole array
                
                //index i is set as a temporary min value in the array
                intMinNumSwap = i; 
                
                //the index i value is then checked with the rest of the numbers in row h of the 2d array after i
                for (int j = i + 1; j < intGrid[0].length; j++) {

                    //if a number in row h of the 2d array is found to be less then the i value it is saved as the new max value, the smallest value after i is found
                    if (intGrid[h][intMinNumSwap] > intGrid[h][j]) { 
                        intMinNumSwap = j; 
                    }

                }

                //smallest value after i is then swaped in row h of the 2d array
                intTemp = intGrid[h][i]; 
                intGrid[h][i] = intGrid[h][intMinNumSwap]; 
                intGrid[h][intMinNumSwap] = intTemp; 
            }
        }
        
        //updates the icons based on the numbers within the grid
        for (int x = 0; x < intGrid.length; x++) {
            for(int y= 0; y < intGrid[0].length; y++) {
                btnGrid[x][y].setIcon(getIcon(intGrid[x][y]));
            }
        }
        
    }
    
    /**
     * This method is run every time a button is pressed in order to check if the buttons can be combined
     */
    public void checkBtns() {
        //declares and saves the int values of the row and column index for both buttons clicked
        int intR1 = intBtn1IndexR; 
        int intC1 = intBtn1IndexC;
        int intR2 = intBtn2IndexR; 
        int intC2 = intBtn2IndexC;
        
        //checks if the buttons even have the same value

        if (intGrid[intR1][intC1] == intGrid[intR2][intC2]) {
            //if the buttons have the same value then it is checked if the two buttons pressed are adjecent to one another by looping through the whole intSameNum array
            //intSameNum will have a 1 value for the button that was clicked and 0 for all the rest 

            //loops through the intSameNum array to see if the buttons clicked are adjecent in the same column of the grid
            for (int i = 0; i < intSameNum[0].length; i++) { //loops through the columns of the 2d array
                for (int j = 0; j < intSameNum.length - 1; j++) { //loops through the rows of the 2d array 

                    //checks the whole grid to see if the two buttons pressed where adjesent to each other in the same column of the grid
                    if (intSameNum[j][i] == 1 && intSameNum[j+1][i] == 1) { 

                        combineBtns(intR1, intC1, intR2, intC2);   //combines the button values and changes icons if the buttons are adjecent in a column
                        break; //breaks out of the if statment as buttons have been found combined
                    }
                }
            }

            //loops through the intSameNum array to see if the buttons clicked are adjecent in the same row of the grid
            for (int i = 0; i < intSameNum.length; i++) { //loops through the columns of the 2d array
                for (int j = 0; j < intSameNum[0].length - 1; j++) { //loops through the rows of the 2d array 
                    //checks the whole grid to see if the two buttons pressed where adjesent to each other in the same row of the grid
                    if (intSameNum[i][j] == 1 && intSameNum[i][j+1] == 1) {

                        combineBtns(intR1, intC1, intR2, intC2); //combines the button values and changes icons if the buttons are adjecent in a row
                        break; //breaks out of the if statment as buttons have been found combined
                    }
                }
            }

        }
        
        //resets the value of the whole intSameNum 2d array to 0 as no buttons have been clicked
        for(int x = 0; x < intSameNum.length; x++){
            for(int y = 0; y < intSameNum[0].length; y++){
                intSameNum[x][y] = 0;
            }
        }

  
    }
    
    /**
     * Method will double the value in the second button click if the buttons are adjecent to one another,
     * and the icon of the buttons are reset based on the numbers in the intGrid 2d array.
     * Method will also update the running score for the game
     * @param intR1 row index of the first button clicked 
     * @param intC1 column index of the first button clicked
     * @param intR2 row index of the second button clicked
     * @param intC2 column index of the second button clicked
     */
    public void combineBtns(int intR1, int intC1, int intR2, int intC2) {
        //calls recursive method and doubles the value in the secon button clicked
        intGrid[intR2][intC2] = addValuesRecursively(intGrid[intR2][intC2]); 

        //adds new doubled value to the running score
        intRunningScore += intGrid[intR2][intC2]; 

        lblScore.setText("Score: " + Integer.toString(intRunningScore)); //updates label with new running score
        intGrid[intR1][intC1] = 2; //resets the first button back to 2 
        
        //uses the int values to get icon images and set them to the respective button
        btnGrid[intR2][intC2].setIcon(getIcon(intGrid[intR2][intC2])); 
        btnGrid[intR1][intC1].setIcon(getIcon(intGrid[intR1][intC1]));
    }
    
    /**
     * Method takes in an int value and doubles it using recursion
     * @param intDouble int value that needs to be doubled
     * @return returns the doubled int value using recursion
     */
    public int addValuesRecursively(int intDouble){

        if (intDouble == 1) return 2;  
        return intDouble * addValuesRecursively(intDouble - (intDouble - 1)); 
    }
    
    /**
     * Gets a integer for the number in the array and returns a image icon based on the int value
     * @param intNum number saved in button that needs to be displayed using its respective image icon 
     * @return an image icon that is same as the inNum
     */
    public ImageIcon getIcon(int intNum) {
        //checks the value of intNum and returns the respective image icon
        if (intNum == 2) { 
            return icon2; 
        } else if (intNum == 4) { 
            return icon4; 
        } else if (intNum == 8) { 
            return icon8; 
        } else if (intNum == 16) { 
            return icon16; 
        } else if (intNum == 32) { 
            return icon32; 
        } else if (intNum == 64) { 
            return icon64; 
        } else if (intNum == 128) { 
            return icon128; 
        } else if (intNum == 256) { 
            return icon256; 
        } else if (intNum == 512) { 
            return icon512; 
        } else if (intNum == 1024) { 
            return icon1024; 
        } else if (intNum == 2048) { 
            return icon2048; 
        }
        
        //returns null if intNum is a value other then the ones above however this method should never return null
        return null; 
 
    }
    
    /**
     * Method is called whenever the start game button is pressed
     */
    public void startGame() {

        //loops through all the 2d array and resets value to start 
        for(int i = 0; i < 4; i++){ //loops through the rows of the 2d array
            for(int j = 0; j < 4; j++){ //loops through the columns of the 2d array
                btnGrid[i][j].setEnabled(true); //enables buttons to be clicked 
                btnGrid[i][j].setIcon(icon2); //resets all the button icons to 2 
                intGrid[i][j] = 2; //resets all the values in the 2d array to 2 like the image icons
                intSameNum[i][j] = 0; //resets the array to indicate no buttons have been pressed 
            }
        }      
        //disabbles the play button that can only be reset once the reset button is pressed
        btnStartGame.setEnabled(false);
    }
    
    /**
     * Method is called whenever the reset button is pressed, and enables the start game button to be pressed and resets the board to blank
     */
    public void resetGame() {
        
        //enables start game button, start game button will reset the board once it is clicked
        btnStartGame.setEnabled(true); 
        //resets the running score
        intRunningScore = 0; 
        lblScore.setText("Score: " + Integer.toString(intRunningScore));
        
        for(int i = 0; i < 4; i++){ //loops through the rows of the 2d array
            for(int j = 0; j < 4; j++){ //loops through the columns of the 2d array
                btnGrid[i][j].setEnabled(false); //disables the buttons to be clicked 
                btnGrid[i][j].setIcon(null); //resets all the button icons to null 
                
            }
        }      
        
        
        
    }

    


    

   
}