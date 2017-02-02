package studio10;

/**
 * 
 * the fundamental cell class
 * used in celltable, the playground
 * and the block, the active block
 *  
 * isFilled:	whether this cell is part of the static cells
 * isBlock:		whether this cell is part of the active cells
 * cellcolor:	the color of this cell
 * x/y:			the location of this cell
 * 
 */

import java.awt.Color;

//import sedgewick.StdDraw;

public class Cell {
	public int x;
	public int y;
	private Color cellcolor;
	private boolean isFilled;
	private boolean isBlock;
	
	public Cell(int x, int y, Color cellcolor){
		this.x = x;
		this.y = y;
		this.cellcolor=cellcolor;
		this.isFilled=false;
		this.isBlock = false;
	}
	
	public Color getCellcolor() {
		return cellcolor;
	}
	public void setCellcolor(Color cellcolor) {
		this.cellcolor = cellcolor;
	}
	
	public boolean isFilled() {
		return isFilled;
	}
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	public boolean isBlock(){
		return isBlock;
	}
	public void setBlock(boolean isBlock){
		this.isBlock = isBlock;
	}
	

}
