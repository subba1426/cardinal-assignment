package assignment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Subba Uppalapati
 */
public class MyPanel extends JPanel{
  
	private static final long serialVersionUID = 1L;
	
	boolean[][] grid;
    
    public MyPanel(boolean[][] grid){
        this.grid = grid;
    }
    
    public void setGrid(boolean[][] grid){
        this.grid=grid;
    }
    
    public void addStatusLabel(JLabel jLabel , String location){
        
        super.add(jLabel, location);
    }
    
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        double cellWidth = (double)this.getWidth()/grid.length;
        double cellHeight = (double)this.getHeight()/grid[0].length;
        
        for(int i=0 ; i < grid.length; i++){
            for(int j=0 ; j < grid[0].length; j++){
                if(grid[i][j]==true){
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect((int)(i*cellWidth), (int)(j*cellHeight), (int)(cellWidth), (int)(cellHeight));
                }
            }
            
        }
        
         for(int i=0 ; i < grid.length; i++){
                    graphics.setColor(new Color(0,0,0));
                    graphics.drawLine((int) (i*cellWidth), 0, (int) (i*cellWidth) , this.getHeight());
            }
         
         
         for(int i=0 ; i < grid[0].length; i++){
                    graphics.setColor(new Color(0,0,0));
                    graphics.drawLine(0, (int) (i*cellHeight), this.getWidth() , (int) (i*cellHeight));
            }
            
        
    }
    public void setBorder(int top, int left, int bottom, int right ){
    	super.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }
    
}
