/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ece326.hw3;

/**
 *
 * @author nefga
 */
public class Homework3 {
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			Sudoku gui = new Sudoku();
    			gui.setVisible(true);
    		}
    	});
    }
    
}
