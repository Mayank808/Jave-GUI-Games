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


public class WinnerWindow extends JFrame {
    
    //declares necessary components for the jframe 
    JLabel lblWinner;   
    JLabel lblGameScore; 
    JButton btnExit; 
    JLabel lblBackground; 
    JPanel panelBackground; 
    ImageIcon winner = new ImageIcon("oprahGoat.jpg");
    
    /**
     * Constructor class takes in the game number and score from the 2048 board and displays it in the winner window
     * @param strScore score for the game the user just won
     * @param strGame game number of the game the user just played
     */
    public WinnerWindow(String strScore, String strGame) {
        resize(600,600);
        setLayout(null);
        this.setTitle("Winner");
        
        panelBackground = new JPanel(); 
        panelBackground.setLocation(0,0); 
        panelBackground.setSize(900,700); 
        panelBackground.setLayout(null);
        panelBackground.setBackground(new Color(0xFAF8EF));
        add(panelBackground); 
        
        lblBackground = new JLabel(); 
        lblBackground.setIcon(winner); 
        lblBackground.setSize(625,465);
        lblBackground.setLocation(0,0);
        panelBackground.add(lblBackground);
        
        lblGameScore = new JLabel("You were able to score " + strScore + " in game " + strGame);
        lblGameScore.setFont(new Font("Courier New", Font.ITALIC, 20)); 
        lblGameScore.setSize(600, 50);
        lblGameScore.setLocation(75, 500);
        panelBackground.add(lblGameScore);


    }
}
