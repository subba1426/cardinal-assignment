package assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;


/**
 *
 * @author Subba Uppalapati
 */
public class GameOfLife implements MouseListener, ActionListener {

	private static final Dimension WINDOW_SIZE = new Dimension(750, 600);

	private static final String _20X20 = "20x20";
	
	private static final String _10X10 = "10x10";

	private static final String _8X6 = "8x6";
	
	private int cellHeight = 6;
	private int cellWidth = 8;
	
	private boolean[][] grid ;
	private JFrame frame;
	private MyPanel panel;
	private JButton step; 
	private JButton reset;
	private JComboBox<String> selectGridSize ;
	private Container north;

	public GameOfLife() {
		
		frame = new JFrame("Game Of Life");
		String[] items = { _8X6, _10X10, _20X20 };
		grid = new boolean[cellWidth][cellHeight];
		panel = new MyPanel(grid);
		panel.setBackground(Color.LIGHT_GRAY);
		
		step = new JButton("Click For Updated State");  
		reset = new JButton("Reset");
		north = new Container();
		selectGridSize = new JComboBox<String>(items);
		
		frame.setSize(WINDOW_SIZE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		north.setLayout(new GridLayout(1, 0));
		north.add(step);
		step.addActionListener(this);
		north.add(reset);
		reset.addActionListener(this);
		north.add(selectGridSize);
		selectGridSize.addActionListener(this);
		frame.add(north, BorderLayout.NORTH);
	

		panel.addMouseListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) {
		new GameOfLife();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(selectGridSize)) {
			@SuppressWarnings("unchecked")
			JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
			String slectedGridSize = comboBox.getSelectedItem().toString();
			if (slectedGridSize.equals(_8X6)) {
				resetTheGrid(8, 6);
			} else if (slectedGridSize.equals(_10X10)) {
				resetTheGrid(10, 10);
			} else if (slectedGridSize.equals(_20X20)) {
				resetTheGrid(20, 20);
			} 
		}
		
		if (e.getSource().equals(step)) {
			nextGeneration();
			frame.repaint();
		}
	
		if (e.getSource().equals(reset)) {
			resetTheGrid(cellWidth,cellHeight);
		}
	}


	private void resetTheGrid(int width , int height) {
		boolean[][] newGrid = new boolean[width][height];
		cellHeight=height;
		cellWidth=width;
		grid = newGrid;
		panel.setGrid(newGrid);
		frame.repaint();
	}

	private void nextGeneration() {

		boolean[][] newGrid = new boolean[cellWidth][cellHeight];

		for (int x = 0; x < cellWidth; x++) {

			for (int y = 0; y < cellHeight; y++) {

				int  neighboursCellLiveCount = getNeighborsLiveCount(x, y);

				if (grid[x][y]) {
					if (neighboursCellLiveCount == 2 || neighboursCellLiveCount == 3) {
						newGrid[x][y] = true;
					} else {
						newGrid[x][y] = false;
					}

				} else if (neighboursCellLiveCount == 3) {
					newGrid[x][y] = true;
				} else {
					newGrid[x][y] = false;
				}
			}

		}
		grid = newGrid;
		panel.setGrid(newGrid);
		frame.repaint();
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		int row = Math.min(e.getY() / (panel.getHeight() / cellHeight), cellHeight-1);
		int col = Math.min(e.getX() / (panel.getWidth() / cellWidth), cellWidth-1);

		grid[col][row] = !grid[col][row];
		frame.repaint();
	}

	private int getNeighborsLiveCount(int x, int y) {
		int neighboursCellLiveCount = 0;
		
		if (y > 0 && grid[x][y - 1]) {
			neighboursCellLiveCount++;
		}
		if (x > 0 && grid[x - 1][y]) {
			neighboursCellLiveCount++;
		}

		if (x < cellWidth - 1 && grid[x + 1][y]) {
			neighboursCellLiveCount++;
		}

		if (y < cellHeight - 1 && grid[x][y + 1]) {
			neighboursCellLiveCount++;
		}

		if (x > 0 && y < cellHeight - 1 && grid[x - 1][y + 1]) {
			neighboursCellLiveCount++;
		}

		if (x < cellWidth - 1 && y < cellHeight - 1 && grid[x + 1][y + 1]) {
			neighboursCellLiveCount++;
		}
		if (x < cellWidth - 1 && y > 0 && grid[x + 1][y - 1]) {
			neighboursCellLiveCount++;
		}

		if (x > 0 && y > 0 && grid[x - 1][y - 1]) {
			neighboursCellLiveCount++;
		}
		return neighboursCellLiveCount;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

}
