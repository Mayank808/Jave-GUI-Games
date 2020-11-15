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


public class GameTester {

    /**
     * Tester method will start the game 
     * @param args Main Method
     */
    public static void main(String[] args) {
        StartGameWindow startGame = new StartGameWindow(500,600, "Start Game Window");
        startGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame.setVisible(true);
        
    }
}
