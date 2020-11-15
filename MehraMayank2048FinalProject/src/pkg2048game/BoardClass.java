
package pkg2048game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/**
 * Abstract class allows different boards to be implemented in the future with different icons
 * and functionalities
 * Abstract class implements the btnGridMethods interface inorder to make sure all btns are defined in the grid
 */
public abstract class BoardClass extends JFrame implements ActionListener, btnGridMethods{
    
    
    //declaring abstract variables that must be included in a instance of the board class
    public abstract void actionPerformed(ActionEvent event); 
    public abstract void exitCheckMax(); 
    public abstract void sortRows();
    public abstract void sortColumns();
    public abstract void combineBtns(int intR1, int intC1, int intR2, int intC2);
    public abstract void checkBtns(); 
    public abstract void startGame();
    public abstract void resetGame();
    public abstract void displayScore(); 
    public abstract void winCheck(); 
    public abstract int addValuesRecursively(int intDouble); 
    public abstract ImageIcon getIcon(int intNum); 
    
    //declares needed jframe components and arrays needed to keep track of buttons and numbers on the gird
    JLabel lblTitle; 
    JLabel lblScore; 
    JLabel lblGetGameBeat; 
    JButton [][] btnGrid; 
    Integer [][] intGrid;
    Integer [][] intSameNum; 
    
    
    JButton btnExit; 
    JButton btnStartGame; 
    JButton btnSortRows;
    JButton btnSortColumns; 
    JButton btnResetBoard;
    JButton btnDisplayScore; 
    JTextField txtGetGameNum; 
    JPanel panelBackground; 
    
    //declaring variables
    int intCounter = 0;
    int intBtn1IndexR, intBtn1IndexC; 
    int intBtn2IndexR, intBtn2IndexC; 
    
    /**
     * Constructer will draw the playable grid and all the necessary buttons that need to be on the board
     * @param intSizeH takes in a height value for the jframe
     * @param intSizeW takes in a width value for the jframe 
     * @param strCaption take in a caption for the window
     */
    public void drawGrid(int intSizeH, int intSizeW, String strCaption) {
        
        resize(intSizeH,intSizeW);
        setLayout(null);
        this.setTitle(strCaption);
        
        //sets up and displays all components that need to be on a board jframe 
        panelBackground = new JPanel(); 
        panelBackground.setLocation(0,0); 
        panelBackground.setSize(intSizeH,intSizeW); 
        panelBackground.setLayout(null);
        panelBackground.setBackground(new Color(0xFAF8EF));
        add(panelBackground); 
        
        intGrid = new Integer[4][4];
        intSameNum = new Integer[4][4]; 
        
        lblTitle = new JLabel("2048");
        lblTitle.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 60)); 
        lblTitle.setSize(450, 50);
        lblTitle.setLocation(225, 20);
        panelBackground.add(lblTitle);
        
        lblScore = new JLabel("Score: 0");
        lblScore.setFont(new Font("Courier New", Font.BOLD, 16)); 
        lblScore.setSize(450, 50);
        lblScore.setLocation(400, 40);
        panelBackground.add(lblScore);
        
        lblGetGameBeat = new JLabel("Game Score to beat: 0");
        lblGetGameBeat.setFont(new Font("Courier New", Font.BOLD, 16)); 
        lblGetGameBeat.setSize(450, 50);
        lblGetGameBeat.setLocation(270, 60);
        panelBackground.add(lblGetGameBeat);
        
        //adds button grid to screen based on x and y values changed after every row
        btnGrid = new JButton [4][4];
        int x = 100;
        int y = 100;
        int name = 0; 
        String newname; 
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                name = name + 1;
                newname = Integer.toString(name);
                btnGrid[i][j] = new JButton(newname);
                btnGrid[i][j].setLocation(x, y);
                btnGrid[i][j].setSize(100, 100); 
                panelBackground.add(btnGrid[i][j]);
                x += 100;
                //let's add the action listerener  
                btnGrid[i][j].setActionCommand(newname);
                btnGrid[i][j].addActionListener(this);
                btnGrid[i][j].setEnabled(false); 

            }
            x= 100;
            y += 100;
        }
        
        btnStartGame = new JButton("Start Game");
        btnStartGame.setLocation(100, 550);
        btnStartGame.setSize(125, 25);
        panelBackground.add(btnStartGame); 
        btnStartGame.setActionCommand("StartGame");
        btnStartGame.addActionListener(this);
        
        btnExit = new JButton("Exit");
        btnExit.setLocation(375, 550);
        btnExit.setSize(125, 25);
        panelBackground.add(btnExit); 
        btnExit.setActionCommand("Exit");
        btnExit.addActionListener(this);
        
        btnResetBoard = new JButton("Reset Board");
        btnResetBoard.setLocation(235, 550);
        btnResetBoard.setSize(125, 25);
        panelBackground.add(btnResetBoard); 
        btnResetBoard.setActionCommand("Reset");
        btnResetBoard.addActionListener(this);
        
        btnSortRows = new JButton("Sort Rows");
        btnSortRows.setLocation(100, 575);
        btnSortRows.setSize(125, 25);
        panelBackground.add(btnSortRows); 
        btnSortRows.setActionCommand("Rows");
        btnSortRows.addActionListener(this);
        
        btnDisplayScore = new JButton("Get Score");
        btnDisplayScore.setLocation(235, 575);
        btnDisplayScore.setSize(125, 25);
        panelBackground.add(btnDisplayScore); 
        btnDisplayScore.setActionCommand("Score");
        btnDisplayScore.addActionListener(this);
        
        txtGetGameNum = new JTextField("Enter a game score you want to try to beat!"); 
        txtGetGameNum.setFont(new Font("Serif", Font.ITALIC, 16));
        txtGetGameNum.setSize(400, 30);
        txtGetGameNum.setLocation(100, 600);
        panelBackground.add(txtGetGameNum); 
        
        btnSortColumns = new JButton("Sort Columns");
        btnSortColumns.setLocation(375, 575);
        btnSortColumns.setSize(125, 25);
        panelBackground.add(btnSortColumns); 
        btnSortColumns.setActionCommand("Columns");
        btnSortColumns.addActionListener(this);
        
        
    } 
    
    
    
    
    /**
     * All Button Methods will use intCounter to determine whether the first or second button is pressed and call the respective methods where needed
     * if intCounter is odd then it is assumed that the first button is clicked and method saves the indexs and adds a 1 to intSameNum array using the buttons index
     * if intCounter is even then it is assumed that the second button is clicked index is saved and value is set to 1 in intSameNum 
     * the checkbtns method is called which will check if the first button and second button are adjecent to one another by looking for adjecent 1's in the intSameNum 2d array
     * the winCheck method is also called to see if the 2048 value has been reached in the grid
     */
    public void btn1() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 0; 
            intBtn1IndexC = 0; 
            intSameNum[0][0] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 0; 
            intBtn2IndexC = 0; 
            intSameNum[0][0] = 1; 
            checkBtns(); 
            winCheck();    
        }
        
    }


    public void btn2() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 0; 
            intBtn1IndexC = 1;
            intSameNum[0][1] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 0; 
            intBtn2IndexC = 1; 
            intSameNum[0][1] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn3() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 0; 
            intBtn1IndexC = 2; 
            intSameNum[0][2] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 0; 
            intBtn2IndexC = 2; 
            intSameNum[0][2] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn4() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 0; 
            intBtn1IndexC = 3; 
            intSameNum[0][3] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 0; 
            intBtn2IndexC = 3; 
            intSameNum[0][3] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn5() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 1; 
            intBtn1IndexC = 0;
            intSameNum[1][0] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 1; 
            intBtn2IndexC = 0; 
            intSameNum[1][0] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn6() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 1; 
            intBtn1IndexC = 1; 
            intSameNum[1][1] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 1; 
            intBtn2IndexC = 1; 
            intSameNum[1][1] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn7() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 1; 
            intBtn1IndexC = 2; 
            intSameNum[1][2] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 1; 
            intBtn2IndexC = 2; 
            intSameNum[1][2] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn8() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 1; 
            intBtn1IndexC = 3; 
            intSameNum[1][3] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 1; 
            intBtn2IndexC = 3; 
            intSameNum[1][3] = 1; 
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn9() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 2; 
            intBtn1IndexC = 0; 
            intSameNum[2][0] = 1; 
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 2; 
            intBtn2IndexC = 0; 
            intSameNum[2][0] = 1;
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn10() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 2; 
            intBtn1IndexC = 1; 
            intSameNum[2][1] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 2; 
            intBtn2IndexC = 1; 
            intSameNum[2][1] = 1;
            checkBtns(); 
            winCheck();    
        }
    }

    
    public void btn11() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 2; 
            intBtn1IndexC = 2; 
            intSameNum[2][2] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 2; 
            intBtn2IndexC = 2; 
            intSameNum[2][2] = 1;
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn12() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 2; 
            intBtn1IndexC = 3; 
            intSameNum[2][3] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 2; 
            intBtn2IndexC = 3; 
            intSameNum[2][3] = 1;
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn13() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 3; 
            intBtn1IndexC = 0; 
            intSameNum[3][0] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 3; 
            intBtn2IndexC = 0; 
            intSameNum[3][0] = 1;
            checkBtns(); 
            winCheck();    
        }
    }


    public void btn14() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 3; 
            intBtn1IndexC = 1; 
            intSameNum[3][1] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 3; 
            intBtn2IndexC = 1;
            intSameNum[3][1] = 1;
            checkBtns();
            winCheck();    
        }
    }


    public void btn15() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 3; 
            intBtn1IndexC = 2;
            intSameNum[3][2] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 3; 
            intBtn2IndexC = 2; 
            intSameNum[3][2] = 1;
            checkBtns();
            winCheck();    
        }
    }

    public void btn16() {
        intCounter += 1;
        if (intCounter % 2 == 1) {
            intBtn1IndexR = 3; 
            intBtn1IndexC = 3; 
            intSameNum[3][3] = 1;
        } else if (intCounter % 2 == 0) {
            intBtn2IndexR = 3; 
            intBtn2IndexC = 3; 
            intSameNum[3][3] = 1;
            checkBtns(); 
            winCheck();    
        }
    }


   
         
}

