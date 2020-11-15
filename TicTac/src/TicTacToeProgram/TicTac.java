package TicTacToeProgram;

import java.awt.*;
import javax.swing.*;

public class TicTac extends JFrame {
    TicTacEvent tictac = new TicTacEvent(this);
    
    //declaring visual code 
    JLabel lblBackground = new JLabel(new ImageIcon("tictactoebackground.jpg")); 
    JButton[][] btnBoxes = new JButton[4][4];
    JButton btnPlay = new JButton("Play");
    JButton btnReset = new JButton("Reset");
    JTextArea txtAXWinner = new JTextArea();
    JTextArea txtAOWinner = new JTextArea();
    JOptionPane opWin = new JOptionPane("Winner");
    ImageIcon imgBack = new ImageIcon("ovoOwlBack.jpg");


    public TicTac() {
        //changes caption of window
        super ("Tic Tac Toe");
        setSize (1000,1050); //sets size of jframe 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout(); 
        setLayout(layout);
        
        //declare variables that will display numbers on button
        int intName = 0;
        String newname;
        
        //adds photo to the back
        add(lblBackground);
        
        //sets the background image with gridlayout so anything added onto the background will be put into a grid
        GridLayout layout1 = new GridLayout(6, 4, 50, 50);
        lblBackground.setLayout(layout1);
        
        //adds the tic tac toe buttons to array 
        for (int x=0; x<=3; x++){
            for (int y=0; y<=3; y++){
                intName = intName + 1;
                newname = Integer.toString(intName);
                btnBoxes[x][y] = new JButton(newname);
                btnBoxes[x][y].setIcon(imgBack);
                lblBackground.add((btnBoxes[x][y]));
            }
        }
        
        //adds text areas and play/reset/tic tac toe array buttons to the background in gridlayout
        lblBackground.add((txtAXWinner));
        lblBackground.add((btnPlay));
        lblBackground.add((btnReset));
        lblBackground.add((txtAOWinner)); 
        
        //adds action listeners to buttons so they can recieve user input
        btnPlay.addActionListener(tictac);
        btnReset.addActionListener(tictac); 
        for (int x=0; x <= 3; x++){
            for (int y=0; y <= 3; y++){
                btnBoxes[x][y].addActionListener(tictac);
            }
        }

        setVisible(true);
    }

    public static void main(String[] arguments){
        TicTac frame = new TicTac();
    }
}