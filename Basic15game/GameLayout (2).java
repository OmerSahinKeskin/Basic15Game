
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameLayout implements ActionListener {

	private JFrame frame;
	private GameGenerator Game;
	private Cell[][] CellArray;
	
	//comment in to test if everything works
	//private int[] test = {1,2,3,4,5,6,7,8,9,10,11,12,0,13,14,15};
	
	// saving the place where 0 is
	private int row;
	private int col;
	
	//how many moves you have done
	private int flytt;
	
	/*
	 * initializes all variables
	 * fills the Array with buttons in a 4x4 matrix
	 * if one of the numbers returned from GameGenerator is 0 makes an empty button and saves the coordinates of where the empty button is
	 * adds an ActionListener to every button
	 * adds the buttons to the content pane
	 * 
	 */
	public void newGame() {
		
		//Array for the buttons
		CellArray = new Cell[4][4];
		//The Window for the game
		frame = new JFrame("The Fifttheen Game");
		Container ContentPane = frame.getContentPane();
		ContentPane.setLayout(new GridLayout(4,4,3,3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game = new GameGenerator(4); //getting the random numbers from GameGenerator
		
		
		int k = 0, l = 0;// counting Variables
		
		flytt = 0;//resetting the flytt variable just in case
		
		
		//comment out for int i :game and put for int i test instead to test if everything works
		//for(int i :test){
		for(int i : Game) {
			if(i == 0) {
				Cell cell = new Cell(l, k, "");
				CellArray[l][k] = cell;
				CellArray[l][k].addActionListener(this);
				ContentPane.add(CellArray[l][k]);
				row = l;
				col = k;
			}else {
				
				Cell cell = new Cell(l, k , "" + i);
				CellArray[l][k] = cell;
				CellArray[l][k].addActionListener(this);
				ContentPane.add(CellArray[l][k]);
			}
			
			
			//if k has been on every column go to next row and start again l
			if(k == 3) {
				l++;
				k = 0;
			}else {
				k++;
			}
		}
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/*
	 * @param what row is the button on
	 * @param what column is the button on
	 * checks if the button is near the empty button with 1 space apart if not make a sound
	 * renaming the empty button to the button pressed and changing the button pressed to be the empty one
	 * saving the new location of the empty button
	 * incrementing how many moves have been made
	 * 
	 */
	public void moveButton(int rows, int coloums) {
		if((rows+1 == row && coloums == col) || (rows-1 == row && coloums == col) || (coloums+1 == col && row == rows) || (coloums-1 == col && row == rows) || (rows == row && coloums == col)) {
			CellArray[row][col].setText(CellArray[rows][coloums].getText());
			CellArray[rows][coloums].setText("");
			row = rows;
			col = coloums;
			flytt++;
		}else {
			Toolkit.getDefaultToolkit().beep();
		}
	}
	/*
	 * checks if every number is on the correct place
	 * returns true if everything is correct
	 * false if something is not in place
	 */
	public boolean checkWin() {
		int ratt = 0;
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				if(CellArray[i][j].getText().equals(""+( (4*i+j+1)))) {
					ratt++;
				};
				if(ratt == 15){
					return true;				}
			}
		}
		return false;
		
	}
	/*
	 * @param EventCell object
	 * checks which button has been pressed
	 * send the row and column to to function Move button
	 * check if player has won if the player has won tell the player that he won and how many moves has been done
	 * if not make a sound 
	 */
	public void checkArray(Object EventCell) {
		for(int row=0; row<4; row++) {
			for(int col=0; col<4; col++ ) {
				if(CellArray[row][col] == EventCell) {
					moveButton(row,col);
					if(checkWin() == true) {
						JOptionPane.showMessageDialog(frame," \n You Won!! \nMoves done " + flytt, "Won", JOptionPane.INFORMATION_MESSAGE);
						Toolkit.getDefaultToolkit().beep();
					}
				}else {
					Toolkit.getDefaultToolkit().beep();
					
				}
			}
		}
	}
	/*
	 * @param ActionEvent
	 * what made a event and pass it on to next method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object EventCell = e.getSource();
		checkArray(EventCell);
		
	}
	
	
	
}
