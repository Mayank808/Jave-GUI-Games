/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048game;

import java.io.*; 
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

public class ScoreKeeping {
    
    /**
     * Method will take in two arraylists and add the values saved into the txt file into the respect arraylist
     * @param strGameNum saves all the past game numbers into this arraylist empty arraylist
     * @param strScores saves all the past game scores into this arraylist empty arraylist
     */
    public void saveScores(ArrayList<String> strGameNum, ArrayList<String> strScores) {
        
        //declaring variables 
        int intTxtCounter = 0; 
        
        //reads the files using a try and catch 
        try {
            //declaring all components necissary to read the txt scorekeeper file 
            FileReader fr = new FileReader("scorekeeper.txt"); 
            BufferedReader br = new BufferedReader(fr); 
            String strLineIn = br.readLine();
            
            //runs a while loop going through each line that has a number in it 
            while (strLineIn != null) {
                
                //if line is even the game number is saved 
                if (intTxtCounter % 2 == 0) {
                    
                    strGameNum.add(strLineIn); 
                    strLineIn = br.readLine(); 

                //if line is odd the game score is saved
                } else if (intTxtCounter % 2 == 1) {
                    //counter made to help add values into the next empty position in the array 
                    strScores.add(strLineIn); 
                    //saves next line in file 
                    strLineIn = br.readLine(); 
                    

                }
            
            //adds up every line that is crossed 
            intTxtCounter += 1; 
            } 
            
            //closes the buffered reader 
            br.close();
            
        } catch (IOException e) {
            
        }
    }
    
    /**
     * Method uses binary search in order to check whether the game the users is looking for exists and has a score
     * @param strArray takes in the array that needs to be searched
     * @param intLeft in the left most value of the array which at the start will be 0 
     * @param intRight takes in the right most value of the array which at the start will be the length of the array 
     * @param strInputNumFind takes in the user input that is being checked in the array  
     * @return returns a boolean based on whether the inputed value is in the array or not
     */
    public boolean findGameScoreBinarySearch(ArrayList<String> strArray, int intLeft, int intRight, String strInputNumFind) {
        
        //declares the variables
        int intMiddleNum, intCompareNums; 
        
        //returns false once the whole array has been checked and the value doesnt exist within the array 
        if (intLeft > intRight) return false; 
        
        //finds the middle index within the array 
        intMiddleNum = (intLeft + intRight) / 2; 
        
        //compares the string input with the middle index element within the array to see if they are equal 
        intCompareNums = strInputNumFind.compareTo(strArray.get(intMiddleNum));
        
        //the value stored in intCompareStrings will be set to 0 by the compareTo command when the two values are the same
        if (intCompareNums == 0) return true; 
        
        //the value stored in intCompareStrings will be less than 0 by the compareTo command when the input value is less then the middle index element in the 
        if (intCompareNums < 0) {
            
            return findGameScoreBinarySearch(strArray, intLeft, intMiddleNum - 1, strInputNumFind); 
            
        } else { //the value stored in intCompareStrings will be greater than 0 by the compareTo command when the inputed value is greateer then the middle index element in the array
            
            return findGameScoreBinarySearch(strArray, intMiddleNum +1 , intRight, strInputNumFind);
            
        }   
        
    }
    
    /**
     * Method will update the scores in the text file by writing over the current values and adding the new game 
     * @param strGameNum takes in the game number arraylist
     * @param strScores takes in the game score arraylist
     */
    public void updateScoresFile(ArrayList<String> strGameNum, ArrayList<String> strScores) {
        
        //uses a try and catch to write in the file
        try {
            //declaring all components necissary to read the txt scorekeeper file  
            FileWriter fw = new FileWriter("scorekeeper.txt"); 
            PrintWriter pw = new PrintWriter(fw); 


            //loops through the two arraylists and puts them in the correct order into the txt file 
            for (int i = 0; i < strGameNum.size(); i++) {
                pw.println(strGameNum.get(i));
                pw.println(strScores.get(i)); 
            }
            
            //closes the printwriter 
            pw.close();
            
        } catch (IOException e) {
            
        }
        
    }
    
    
}
