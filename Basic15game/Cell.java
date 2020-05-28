import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton {
	
	public final int row;
	public final int col;
	public String button;
	/*
	 * @param row what row
	 * @param col what column
	 * @param buttons name of the button
	 * pass the button name to super
	 */
	public Cell(int row, int col, String buttons) {
		super(buttons);
		
		
			
		this.row = row;
		this.col = col;
	}
	
}
