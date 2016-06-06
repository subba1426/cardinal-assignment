package assignment;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junitx.util.PrivateAccessor;

public class GameOfLifeTest  {
	GameOfLife gameOfLife;
	
	@Before
	public void setUp() throws Exception {
		
		gameOfLife = new GameOfLife();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGetNeighborsLiveCountWhenAllCellsAroundLive() throws Throwable{
		
		boolean[][] inputGrid = new boolean[10][10];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[5][6] = true;
		inputGrid[5][4] = true;
		inputGrid[4][6] = true;
		inputGrid[6][6] = true;
		inputGrid[6][4] = true;
		inputGrid[4][4] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 10);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 10);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int liveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		assertEquals("Current state neighbors live count", 8, liveCellsCount);
		
	}
	
	@Test
	public void testGetNeighborsLiveCountWhenNoCellsaroundLive() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[7][5] = true;
		inputGrid[4][7] = true;
		inputGrid[5][2] = true;
		inputGrid[2][9] = true;
		inputGrid[3][8] = true;
		inputGrid[1][9] = true;
		inputGrid[8][3] = true;
		inputGrid[1][1] = true;
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int liveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		assertEquals("Current state neighbors live count", 0, liveCellsCount);
		
	}
	
	@Test
	public void testDeadCellAndTwoLiveCellsInNeighborsExpectedToBeDeadInNextGeneration() throws Throwable{
		
		boolean[][] inputGrid = new boolean[10][10];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 10);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 10);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		int updatedStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count",2, currentStateLiveCellsCount);
		assertFalse("Current state neighbors live count",inputGrid[5][5]);
		assertEquals("Current state neighbors live count",0, updatedStateLiveCellsCount);
		
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");

		for (int x = 0; x < cellWidth; x++) {

			for (int y = 0; y < cellHeight; y++) {
				assertFalse("All the cells in the updated state should be dead because of under population", outputGrid[x][y]);
			}
		}
	}
	
	
	@Test
	public void testLiveCellWhenNeighborsLiveCellsCountAreTwoEpectedToBeLive() throws Throwable{
		
		boolean[][] inputGrid = new boolean[10][10];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[5][5] = true;
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count",2, currentStateLiveCellsCount);
		assertTrue("current live cell remains live When 2 or 3 live around", outputGrid[5][5]);
		
	}
	
	
	
	@Test
	public void testCurrentLiveCellAndNeighborsLiveCellsCountiSThree() throws Throwable{
		
		boolean[][] inputGrid = new boolean[10][10];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[5][5] = true;
		inputGrid[4][6] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 10);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 10);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count",3, currentStateLiveCellsCount);
		assertTrue("current live cell become live When 2 or 3 live around", outputGrid[5][5]);
			
	}
	
	@Test
	public void testDeadCellWhenTwoLiveCellsInNeighborsExpectedToBeDead() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count",2, currentStateLiveCellsCount);
		assertFalse("current dead cell remains dead in next generation", outputGrid[5][5]);
		
	}
	
	@Test
	public void testTwoLiveCellsInNeighborsExpectedToBeDead() throws Throwable{
		
		boolean[][] inputGrid = new boolean[10][10];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count",2, currentStateLiveCellsCount);
		
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");
	
		for (int x = 0; x < cellWidth; x++) {

			for (int y = 0; y < cellHeight; y++) {
				assertFalse("Live cells will be dead caused by under population" +x +" : " +y , outputGrid[x][y]);
			}
		}
		
	}
	
	
	@Test
	public void testCurrentDeadCellBecomesLiveWhenNeighborsLiveCellsCountIsThree() throws Throwable{
		
		boolean[][] inputGrid = new boolean[8][8];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 8);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 8);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		int currentStateLiveCellsCount = (Integer) PrivateAccessor.invoke(gameOfLife, "getNeighborsLiveCount", new Class[] {Integer.TYPE,Integer.TYPE}, new Object[]{new Integer(5),new Integer(5)});
		
		
		PrivateAccessor.invoke(gameOfLife, "nextGeneration", new Class[] {}, new Object[] {});
		
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertEquals("Current state neighbors live count", 3, currentStateLiveCellsCount);
		assertTrue("current live cell become live When 2 or 3 live around", outputGrid[5][5]);
		
	}
	
	@Test
	public void testActionPerformedReset() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 8);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 8);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		
		
		JButton reset = (JButton) PrivateAccessor.getField(gameOfLife, "reset");
		 ActionEvent mockEvent = EasyMock.mock(ActionEvent.class);
		 EasyMock.expect(mockEvent.getSource()).andReturn(reset).times(3);
		 EasyMock.replay(mockEvent);
		 
		gameOfLife.actionPerformed(mockEvent);
		boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
		
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");
	
		for (int x = 0; x < cellWidth; x++) {

			for (int y = 0; y < cellHeight; y++) {
				assertFalse("All cell should be reset to status dead " +x +" : " +y , outputGrid[x][y]);
			}
		}
	}
	
	@Test
	public void testActionPerformedForNextGeneration() throws Throwable{
		
		boolean[][] inputGrid = new boolean[8][8];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		PrivateAccessor.setField(gameOfLife, "cellHeight", 8);
		PrivateAccessor.setField(gameOfLife, "cellWidth", 8);
		
		PrivateAccessor.setField(gameOfLife, "grid", inputGrid);
		
		JButton step = (JButton) PrivateAccessor.getField(gameOfLife, "step");
		 ActionEvent mockEvent = EasyMock.mock(ActionEvent.class);
		 EasyMock.expect(mockEvent.getSource()).andReturn(step).times(3);
		 EasyMock.replay(mockEvent);
		 
		 gameOfLife.actionPerformed(mockEvent);
		 boolean[][] outputGrid = (boolean[][]) PrivateAccessor.getField(gameOfLife, "grid");
	
		assertTrue("current live cell become live When 2 or 3 live around", outputGrid[5][5]);
		
	}
	
	@Test
	public void testActionPerformedWhenGridSizeSelected8x6() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		String[] items = { "8x6"};
		JComboBox<String> selectedGridSize = new JComboBox<String>(items);
		
		PrivateAccessor.setField(gameOfLife, "selectGridSize", selectedGridSize);
		 ActionEvent mockEvent = EasyMock.mock(ActionEvent.class);
		 EasyMock.expect(mockEvent.getSource()).andReturn(selectedGridSize).times(4);
		 EasyMock.replay(mockEvent);
		 
		gameOfLife.actionPerformed(mockEvent);
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");
		
		assertEquals(8 , cellWidth);
		assertEquals(6 , cellHeight);
	
	}
	
	
	@Test
	public void testActionPerformedWhenGridSizeSelected20x20() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		String[] items = { "20x20"};
		JComboBox<String> selectedGridSize = new JComboBox<String>(items);
		
		PrivateAccessor.setField(gameOfLife, "selectGridSize", selectedGridSize);
		 ActionEvent mockEvent = EasyMock.mock(ActionEvent.class);
		 EasyMock.expect(mockEvent.getSource()).andReturn(selectedGridSize).times(4);
		 EasyMock.replay(mockEvent);
		 
		gameOfLife.actionPerformed(mockEvent);
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");
		
		assertEquals(20 , cellWidth);
		assertEquals(20 , cellHeight);
	
	}
	
	
	@Test
	public void testActionPerformedWhenGridSizeSelected10x10() throws Throwable{
		
		boolean[][] inputGrid = new boolean[20][20];
		inputGrid[6][5] = true;
		inputGrid[4][5] = true;
		inputGrid[4][6] = true;
		
		String[] items = { "10x10"};
		JComboBox<String> selectedGridSize = new JComboBox<String>(items);
		
		PrivateAccessor.setField(gameOfLife, "selectGridSize", selectedGridSize);
		 ActionEvent mockEvent = EasyMock.mock(ActionEvent.class);
		 EasyMock.expect(mockEvent.getSource()).andReturn(selectedGridSize).times(4);
		 EasyMock.replay(mockEvent);
		 
		gameOfLife.actionPerformed(mockEvent);
		int cellWidth = (Integer) PrivateAccessor.getField(gameOfLife, "cellWidth");
		int cellHeight = (Integer) PrivateAccessor.getField(gameOfLife, "cellHeight");
		
		assertEquals(10 , cellWidth);
		assertEquals(10 , cellHeight);
	
	}
	
	

}
