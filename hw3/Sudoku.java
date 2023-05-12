/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ece326.hw3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nefga
 */
public class Sudoku extends JFrame implements ActionListener, KeyListener {
    
    
    private JButton[][] sudoku = new JButton[9][9];
    private boolean[][] DosmenesTheseis = new boolean[9][9];
    private int[][] sudoku_Int = new int[9][9]; //isws tha mporouse na fygei
    private int[][] Lyseis = new int[9][9];
    
    
    private int click_row = -1;
    private int click_col = -1;
    
    private JCheckBox verify;
    
    JPanel numpad = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    ArrayList<String> kiniseis = new ArrayList<String>();
    
    JDialog message = new JDialog(this);
    

    public Sudoku(){
        
        // Idiotites frame
        this.setTitle("Sudoku");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        setLayout(new BorderLayout());
        
        // Panel sudoku 3x3
        JPanel sudokuPanel = new JPanel(new GridLayout(3, 3));
        sudokuPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));   //Tha mporouse na fygei
        sudokuPanel.setBackground(Color.LIGHT_GRAY);
        
        // Tetragwna tou sudoku
        JPanel[][] sudokuBoxes = new JPanel[3][3];
        
        for (int i = 0; i < sudokuBoxes.length; i++) {
            for (int j = 0; j < sudokuBoxes[i].length; j++) {
                sudokuBoxes[i][j] = new JPanel(new GridLayout(3, 3, 1, 1));
                sudokuBoxes[i][j].setBackground(Color.LIGHT_GRAY);
                sudokuBoxes[i][j].setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
                sudokuPanel.add(sudokuBoxes[i][j]);
            }
        }
        
        // Dimiourgia buttons sta sudoku boxes & prosthiki actionlistener, keylistener
        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku[row].length; col++) {
                sudoku[row][col] = new JButton();
                sudoku[row][col].addActionListener(this);
                sudoku[row][col].addKeyListener(this);

                sudoku[row][col].setActionCommand("click" +","+ row + "," + col);
                DosmenesTheseis[row][col] = false;
                sudoku[row][col].setBackground(Color.WHITE);
                
                int i = row / 3;
                int j = col / 3;
                
                sudokuBoxes[i][j].add(sudoku[row][col]);
            }
        }
        
        
        // Dimiourgia menu
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("New Game");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        
        easy.addActionListener(this);
        intermediate.addActionListener(this);
        expert.addActionListener(this);
        
        menu.add(easy);
        menu.add(intermediate);
        menu.add(expert);
        
        menubar.add(menu);

        setJMenuBar(menubar);

        
        // Dimiourgia Buttons tou numpad
        JButton B1 = new JButton("1");
        JButton B2 = new JButton("2");
        JButton B3 = new JButton("3");
        JButton B4 = new JButton("4");
        JButton B5 = new JButton("5");
        JButton B6 = new JButton("6");
        JButton B7 = new JButton("7");
        JButton B8 = new JButton("8");
        JButton B9 = new JButton("9");
        
        B1.addActionListener(this);
        B1.setActionCommand("1");
        
        B2.addActionListener(this);
        B2.setActionCommand("2");
        
        B3.addActionListener(this);
        B3.setActionCommand("3");
        
        B4.addActionListener(this);
        B4.setActionCommand("4");
        
        B5.addActionListener(this);
        B5.setActionCommand("5");
        
        B6.addActionListener(this);
        B6.setActionCommand("6");
        
        B7.addActionListener(this);
        B7.setActionCommand("7");
        
        B8.addActionListener(this);
        B8.setActionCommand("8");
        
        B9.addActionListener(this);
        B9.setActionCommand("9");
        
        numpad.add(B1);
        numpad.add(B2);
        numpad.add(B3);
        numpad.add(B4);
        numpad.add(B5);
        numpad.add(B6);
        numpad.add(B7);
        numpad.add(B8);
        numpad.add(B9);
        
        // Dimiourgia koumpiwn me eikonidia
        // Eraser
        ImageIcon eraser = new ImageIcon("eraser.png");
        Image eraser_icon = eraser.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        ImageIcon ERASE = new ImageIcon(eraser_icon);
        
        JButton B_erase = new JButton(ERASE);
        B_erase.addActionListener( this );
        B_erase.setActionCommand("Eraser");
        B_erase.setSize(20, 20); //
        
        // Solve
        ImageIcon rubik = new ImageIcon("rubik.png");
        Image rubik_icon = rubik.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        ImageIcon RUBIK = new ImageIcon(rubik_icon);
        
        JButton B_solve = new JButton(RUBIK);
        B_solve.addActionListener( this );
        B_solve.setActionCommand("Rubik");
        
        // Undo
        ImageIcon undo = new ImageIcon("undo.png");
        Image undo_icon = undo.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        ImageIcon UNDO = new ImageIcon(undo_icon);
        
        JButton B_undo = new JButton(UNDO);
        B_undo.addActionListener( this );
        B_undo.setActionCommand("Undo");
        
        // Verify checkbox
        verify = new JCheckBox("Verify against solution");
        verify.addActionListener(this);
        verify.setActionCommand("Verify");
        verify.setSelected(false);
        
        numpad.add(B_erase);
        numpad.add(B_solve);
        numpad.add(B_undo);
        numpad.add(verify);
        
        
        JPanel north = new JPanel();
        north.setBackground(new java.awt.Color(128, 214, 255));
        add(north,BorderLayout.NORTH);
        
        JPanel east = new JPanel();
        east.setBackground(new java.awt.Color(128, 214, 255));
        add(east,BorderLayout.EAST);
        
        JPanel west = new JPanel();
        west.setBackground(new java.awt.Color(128, 214, 255));
        add(west,BorderLayout.WEST);
        
        JPanel kentrikoPanel = new JPanel(new BorderLayout());
        kentrikoPanel.add(sudokuPanel, BorderLayout.CENTER);
        add(numpad, BorderLayout.SOUTH);

        add(kentrikoPanel, BorderLayout.CENTER);
    }

    private boolean isInRow(int number, int row, int column, int flag) {
        for (int i = 0; i < 9; i++) {
            
            int num = 0;
            
            if(flag == 1){
                if((sudoku[row][i].getText().equals("") == false) && i!=column){
                    num = Integer.parseInt(sudoku[row][i].getText());
                }
                if(num == number){
                    unsetBlue();
                    
                    sudoku[row][i].setBackground(Color.RED);
                    
                    sudoku[row][column].setBackground(new java.awt.Color(255, 255, 200));
                    
                    return true;
                }
                continue;
            }
            
            if (Lyseis[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    
    private boolean isInColumn(int number, int row, int column, int flag) {
        for (int i = 0; i < 9; i++) {
            
            int num = 0;
            
            if(flag == 1){
                if((sudoku[i][column].getText().equals("") == false) && i != row){
                    num = Integer.parseInt(sudoku[i][column].getText());
                }
                if(number == num){
                    unsetBlue();
                    
                    sudoku[i][column].setBackground(Color.RED);
                    
                    sudoku[row][column].setBackground(new java.awt.Color(255, 255, 200));
                    
                    return true;
                }
                continue;
            }    
            
            if (Lyseis[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    
    private boolean isInBox(int number, int row, int column, int flag) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                
                int num = 0;
                
                if(flag == 1){
                    if((sudoku[i][j].getText().equals("") == false) && (i!=row && j!=column)){
                        num = Integer.parseInt(sudoku[i][j].getText());
                    }
                    if(num == number){
                        unsetBlue();
                        
                        sudoku[i][j].setBackground(Color.RED);
                        
                        sudoku[row][column].setBackground(new java.awt.Color(255, 255, 200));
                        
                        return true;
                    }
                    continue;
                }
                
                if (Lyseis[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    
    private boolean isValidPlacement(int number, int row, int column) {
        return(isInRow(number, row, column, 0) || isInColumn(number, row, column, 0) || isInBox(number, row, column, 0)); 
    }

    
    private boolean SudokuSol(int row, int column) {
        if (sudoku_Int[row][column] == 0) {
            for (int checkNum = 1; checkNum <= 9; checkNum++) {
                if (isValidPlacement(checkNum, row, column) == false) {
                    Lyseis[row][column] = checkNum;

                    if(row == 8 && column == 8){
                       return true;
                    }

                    else if (column == 8){
                        boolean epistrofi_lysis = SudokuSol(row+1,0);
                        if (epistrofi_lysis == true){
                            return true;
                        }
                    }
                    else{
                        boolean epistrofi_lysis = SudokuSol(row,column+1);
                        if (epistrofi_lysis == true){
                            return true;
                        }
                    }
                }
            }   
            Lyseis[row][column] = 0;
            return false;
        }
        
        else{
            if(column == 8 && row == 8){
                return true;
            }
            else if (column == 8){
                boolean epistrofi_lysis = SudokuSol(row+1,0);
                if (epistrofi_lysis == true){
                    return true;
                }
            }
            else{
                boolean epistrofi_lysis = SudokuSol(row,column+1);
                if (epistrofi_lysis == true){
                    return true;
                }
            }
            return false;
        }
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
                
        if(keycode == KeyEvent.VK_9 || keycode == KeyEvent.VK_NUMPAD9){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "9";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(9, click_row, click_col, 1);
                row = isInRow(9, click_row, click_col, 1);
                col = isInColumn(9,click_row, click_col, 1);
                
                if(box == false && row == false && col == false && sudoku[click_row][click_col].getText().equals("9") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("9");
                }
                                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(9, click_row, click_col, 1);
            isInRow(9, click_row, click_col, 1);
            isInColumn(9,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_8 || keycode == KeyEvent.VK_NUMPAD8){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "8";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(8, click_row, click_col, 1);
                row = isInRow(8, click_row, click_col, 1);
                col = isInColumn(8,click_row, click_col, 1);
                
                if(box == false && row == false && col == false && sudoku[click_row][click_col].getText().equals("8") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("8");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(8, click_row, click_col, 1);
            isInRow(8, click_row, click_col, 1);
            isInColumn(8,click_row, click_col, 1);
            
            checkIfSolved();
            
        }
        
        else if(keycode == KeyEvent.VK_7 || keycode == KeyEvent.VK_NUMPAD7){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "7";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(7, click_row, click_col, 1);
                row = isInRow(7, click_row, click_col, 1);
                col = isInColumn(7,click_row, click_col, 1);
                
                if(box == false && row == false && col == false && sudoku[click_row][click_col].getText().equals("7") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("7");
                }
                
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(7, click_row, click_col, 1);
            isInRow(7, click_row, click_col, 1);
            isInColumn(7,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_6 || keycode == KeyEvent.VK_NUMPAD6){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "6";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(6, click_row, click_col, 1);
                row = isInRow(6, click_row, click_col, 1);
                col = isInColumn(6,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("6") == false ){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("6");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(6, click_row, click_col, 1);
            isInRow(6, click_row, click_col, 1);
            isInColumn(6,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_5 || keycode == KeyEvent.VK_NUMPAD5){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "5";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(5, click_row, click_col, 1);
                row = isInRow(5, click_row, click_col, 1);
                col = isInColumn(5,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("5") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("5");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(5, click_row, click_col, 1);
            isInRow(5, click_row, click_col, 1);
            isInColumn(5,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_4 || keycode == KeyEvent.VK_NUMPAD4){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "4";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(4, click_row, click_col, 1);
                row = isInRow(4, click_row, click_col, 1);
                col = isInColumn(4,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("4") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("4");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(4, click_row, click_col, 1);
            isInRow(4, click_row, click_col, 1);
            isInColumn(4,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_3 || keycode == KeyEvent.VK_NUMPAD3){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "3";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(3, click_row, click_col, 1);
                row = isInRow(3, click_row, click_col, 1);
                col = isInColumn(3,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("3") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("3");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(3, click_row, click_col, 1);
            isInRow(3, click_row, click_col, 1);
            isInColumn(3,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_2 || keycode == KeyEvent.VK_NUMPAD2){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "2";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(2, click_row, click_col, 1);
                row = isInRow(2, click_row, click_col, 1);
                col = isInColumn(2,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("2") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("2");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
            }
            
            isInBox(2, click_row, click_col, 1);
            isInRow(2, click_row, click_col, 1);
            isInColumn(2,click_row, click_col, 1);
            
            checkIfSolved();
            
        }
        
        else if(keycode == KeyEvent.VK_1 || keycode == KeyEvent.VK_NUMPAD1){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "1";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(1, click_row, click_col, 1);
                row = isInRow(1, click_row, click_col, 1);
                col = isInColumn(1,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("1") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("1");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() == true){
                    setBlue();
                }
            }
            
            isInBox(1, click_row, click_col, 1);
            isInRow(1, click_row, click_col, 1);
            isInColumn(1,click_row, click_col, 1);
            
            checkIfSolved();
        
        }
        
        else if(keycode == KeyEvent.VK_BACK_SPACE || keycode == KeyEvent.VK_DELETE){
            
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "0";
            
            kiniseis.add(kinisi);
            
            if (click_col != -1 && click_row!=-1){
                sudoku[click_row][click_col].setText("");

                setWhite_Gray();
            }
        }
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        if ("Easy".equals(e.getActionCommand())) {

            setEnableRec(numpad,true);

            for (int row = 0; row < sudoku.length; row++) {
                for (int col = 0; col < sudoku[row].length; col++) {
                    DosmenesTheseis[row][col] = false;
                    sudoku[row][col].setBackground(Color.WHITE);
                }
            }

            try{
                URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=easy");
                URLConnection conn = url.openConnection();
                InputStreamReader input = new InputStreamReader(conn.getInputStream());
                
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        int number = input.read();
                        if((char) number =='\n' || (char) number ==' '){
                            number = input.read();
                        }
                        if ((char) number =='.'){
                            Lyseis[i][j] = 0;
                            sudoku_Int[i][j] = 0;
                            sudoku[i][j].setText("");
                        }
                        else{
                            String stringNum = ""+(char)number;
                            int num = Integer.parseInt(stringNum);
                            
                            sudoku_Int[i][j] = num;
                            Lyseis[i][j] = num;
                            
                            String str = "";
                            str += num;
                            
                            DosmenesTheseis[i][j] = true;
                            sudoku[i][j].setBackground(Color.LIGHT_GRAY);
                            sudoku[i][j].setText(str);
                            
                        }
                    }
                }
                boolean epistrofiLysis = SudokuSol(0,0);
            }
            catch (MalformedURLException ex){
                System.exit(1);
            }
            catch (IOException ex){
                System.exit(1);
            }
        }
        
        else if ("Intermediate".equals(e.getActionCommand())) {

            setEnableRec(numpad,true);

            for (int row = 0; row < sudoku.length; row++) {
                for (int col = 0; col < sudoku[row].length; col++) {
                    DosmenesTheseis[row][col] = false;
                    sudoku[row][col].setBackground(Color.WHITE);
                }
            }

            try{
                URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=intermediate");
                URLConnection conn = url.openConnection();
                InputStreamReader input = new InputStreamReader(conn.getInputStream());
                
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        int number = input.read();
                        if((char) number =='\n' || (char) number ==' '){
                            number = input.read();
                        }
                        if ((char) number =='.'){
                            Lyseis[i][j] = 0;
                            sudoku_Int[i][j] = 0;
                            sudoku[i][j].setText("");
                        }
                        
                        else{
                            String stringNum = ""+(char)number;
                            int num = Integer.parseInt(stringNum);
                            
                            sudoku_Int[i][j] = num;
                            Lyseis[i][j] = num;
                            
                            String str = "";
                            str += num;
                            
                            sudoku[i][j].setBackground(Color.LIGHT_GRAY);
                            DosmenesTheseis[i][j] = true;
                            sudoku[i][j].setText(str);
                        }
                    }
                }
                boolean epistrofiLysis = SudokuSol(0,0);
            }
            catch (MalformedURLException ex){
                System.exit(1);
            }
            catch (IOException ex){
                System.exit(1);
            }
        }
        
        else if ("Expert".equals(e.getActionCommand())) {

            setEnableRec(numpad,true);

            for (int row = 0; row < sudoku.length; row++) {
                for (int col = 0; col < sudoku[row].length; col++) {
                    DosmenesTheseis[row][col] = false;
                    sudoku[row][col].setBackground(Color.WHITE);
                }
            }

            try{
                URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=expert");
                URLConnection conn = url.openConnection();
                InputStreamReader input = new InputStreamReader(conn.getInputStream());
                
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        int number = input.read();
                        if((char) number =='\n' || (char) number ==' '){
                            number = input.read();
                        }
                        if ((char) number =='.'){
                            Lyseis[i][j] = 0;
                            sudoku_Int[i][j] = 0;
                            sudoku[i][j].setText("");
                        }
                        
                        else{
                            String stringNum = ""+(char)number;
                            int num = Integer.parseInt(stringNum);
                            
                            sudoku_Int[i][j] = num;
                            Lyseis[i][j] = num;
                            
                            String str = "";
                            str += num;
                            
                            sudoku[i][j].setBackground(Color.LIGHT_GRAY);
                            DosmenesTheseis[i][j] = true;
                            sudoku[i][j].setText(str);
                            
                        }
                    }
                }
                boolean epistrofiLysis = SudokuSol(0,0);
            }
            catch (MalformedURLException ex){
                System.exit(1);
            }
            catch (IOException ex){
                System.exit(1);
            }
        }
        
        
        else if("1".equals(e.getActionCommand())){
                
            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "1";
            
            boolean row;
            boolean col;
            boolean box;
                
            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(1, click_row, click_col, 1);
                row = isInRow(1, click_row, click_col, 1);
                col = isInColumn(1,click_row, click_col, 1);
                
                if(box == false && row == false && col == false  && sudoku[click_row][click_col].getText().equals("1") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("1");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                
                box = isInBox(1, click_row, click_col, 1);
                row = isInRow(1, click_row, click_col, 1);
                col = isInColumn(1,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("2".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "2";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(2, click_row, click_col, 1);
                row = isInRow(2, click_row, click_col, 1);
                col = isInColumn(2,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("2") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("2");
                }
                                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(2, click_row, click_col, 1);
                isInRow(2, click_row, click_col, 1);
                isInColumn(2,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("3".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "3";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(3, click_row, click_col, 1);
                row = isInRow(3, click_row, click_col, 1);
                col = isInColumn(3,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("3") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("3");
                }
                                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(3, click_row, click_col, 1);
                isInRow(3, click_row, click_col, 1);
                isInColumn(3,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("4".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "4";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(4, click_row, click_col, 1);
                row = isInRow(4, click_row, click_col, 1);
                col = isInColumn(4,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("4") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("4");
                }
                
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(4, click_row, click_col, 1);
                isInRow(4, click_row, click_col, 1);
                isInColumn(4,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("5".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "5";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(5, click_row, click_col, 1);
                row = isInRow(5, click_row, click_col, 1);
                col = isInColumn(5,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("5") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("5");
                }
                
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(5, click_row, click_col, 1);
                isInRow(5, click_row, click_col, 1);
                isInColumn(5,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("6".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "6";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(6, click_row, click_col, 1);
                row = isInRow(6, click_row, click_col, 1);
                col = isInColumn(6,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("6") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("6");
                }
                
                                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(6, click_row, click_col, 1);
                isInRow(6, click_row, click_col, 1);
                isInColumn(6,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("7".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "7";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(7, click_row, click_col, 1);
                row = isInRow(7, click_row, click_col, 1);
                col = isInColumn(7,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("7") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("7");
                }
                
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(7, click_row, click_col, 1);
                isInRow(7, click_row, click_col, 1);
                isInColumn(7,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("8".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "8";
            
            boolean box;
            boolean row;
            boolean col;


            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(8, click_row, click_col, 1);
                row = isInRow(8, click_row, click_col, 1);
                col = isInColumn(8,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true   && sudoku[click_row][click_col].getText().equals("8") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("8");
                }
                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(8, click_row, click_col, 1);
                isInRow(8, click_row, click_col, 1);
                isInColumn(8,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("9".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "9";
            
            boolean box;
            boolean row;
            boolean col;

            //kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                
                box = isInBox(9, click_row, click_col, 1);
                row = isInRow(9, click_row, click_col, 1);
                col = isInColumn(9,click_row, click_col, 1);
                
                if(box != true && row != true && col !=true  && sudoku[click_row][click_col].getText().equals("9") == false){
                    kiniseis.add(kinisi);
                    sudoku[click_row][click_col].setText("9");
                }
                                
                setWhite_Gray();
                
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
                
                if(verify.isSelected() ==true){
                    setBlue();
                }
                
                isInBox(9, click_row, click_col, 1);
                isInRow(9, click_row, click_col, 1);
                isInColumn(9,click_row, click_col, 1);
                
                checkIfSolved();
                
            }
        }
        
        else if("Eraser".equals(e.getActionCommand())){

            String kinisi = "";
            kinisi += click_row;
            kinisi += ",";
            kinisi += click_col;
            kinisi += sudoku[click_row][click_col].getText();
            kinisi += "-";
            kinisi += "0";

            kiniseis.add(kinisi);

            if (click_col != -1 && click_row!=-1){
                sudoku[click_row][click_col].setText("");
                
                setWhite_Gray();
            }
        }
        
        else if("Rubik".equals(e.getActionCommand())){
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    String str = "";
                    str +=  Lyseis[i][j];
                    sudoku[i][j].setText(str);

                    if (DosmenesTheseis[i][j] == false){
                            sudoku[i][j].setBackground(Color.WHITE);
                    }
                    else if(DosmenesTheseis[i][j] == true){
                            sudoku[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                }
            }
            setEnableRec(numpad,false);
        }

        else if("Verify".equals(e.getActionCommand())){
            if(verify.isSelected() == false){
                unsetBlue();
            }

            if(verify.isSelected() == true){
                
                setWhite_Gray();
                
                setBlue();
            }
        }

        else if("Undo".equals(e.getActionCommand())){

            int size = kiniseis.size();

            if(size!=0){

                String kinisi = kiniseis.remove(kiniseis.size()-1);

                click_row = Integer.parseInt(kinisi.substring(0, 1));
                click_col = Integer.parseInt(kinisi.substring(2, 3));

                String num = kinisi.substring(3, 4);
                if(num.equals("-")){
                    sudoku[click_row][click_col].setText("");
                }
                else if(num.equals("0")){
                    sudoku[click_row][click_col].setText("");
                }
                else{
                    sudoku[click_row][click_col].setText(num);
                }
                
                setWhite_Gray();
                
                if(verify.isSelected() ==true){
                    setBlue();
                }      
            }
        }

        else if ("click".equals(e.getActionCommand().substring(0,5))) {
            
            click_row = Integer.parseInt(e.getActionCommand().substring(6,7));
            click_col = Integer.parseInt(e.getActionCommand().substring(8,9));
            
            String number = sudoku[click_row][click_col].getText();
            
            setWhite_Gray();
            
            if(verify.isSelected() == true && sudoku[click_row][click_col].getText().equals("")){
                setBlue();
            }
            
            if(number.equals("")){
                sudoku[click_row][click_col].setBackground(new java.awt.Color(255, 255, 200));
            }
            
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if (sudoku[i][j].getText().equals(number) && !(sudoku[i][j].getText().equals(""))){
                        sudoku[i][j].setBackground(new java.awt.Color(255, 255, 200));
                    }
                    
                } 
            }
            
        }
    }
        
    
    
    public void setBlue(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(!(sudoku[i][j].getText().equals(""))){
                    int num = Integer.parseInt(sudoku[i][j].getText());
                    if(num != Lyseis[i][j]){
                        sudoku[i][j].setBackground(Color.BLUE);
                    }
                }
            }
        }
    }
    
    
    public void setWhite_Gray(){
        for (int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (DosmenesTheseis[i][j] == false){
                        sudoku[i][j].setBackground(Color.WHITE);
                }
                else{
                        sudoku[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }
    
    
    public void unsetBlue(){
        for(int k = 0; k < 9; k++){
            for(int j = 0; j < 9; j++){
                if(sudoku[k][j].getBackground() == Color.BLUE){
                    sudoku[k][j].setBackground(Color.WHITE);
                }
            }
        }
    }
    
    
    public void checkIfSolved(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                
                if(sudoku[i][j].getText().equals("")==false && Integer.parseInt(sudoku[i][j].getText()) == Lyseis[i][j] ){
                    
                    if(i==8 && j==8){
                        JLabel label = new JLabel("You Won !");
                        message.setLayout(new BorderLayout());
                        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        panel.add(label);
                        panel.setBackground(Color.CYAN);
                        message.add(panel, BorderLayout.CENTER);
                        message.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                        message.setVisible(true);
                        message.setSize(100,70);
                        message.setLocationRelativeTo(this);
                        setEnableRec(numpad,false);
                        
                        return;
                    }
                    
                }
                else{
                    return;
                }
            }
        }
        return;
    }
    

    
    private void setEnableRec(Component panel, boolean enable){
        panel.setEnabled(enable);

        try {
            Component[] components= ((Container) panel).getComponents();
            
            for (int i = 0; i < components.length; i++) {
                setEnableRec(components[i], enable);
            }
        }
        catch (ClassCastException e) {
            //
        }
    }
    
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
}
