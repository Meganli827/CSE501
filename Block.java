package studio10;

import java.awt.Color;
//import java.util.List;

import sedgewick.StdDraw;

/**
 * 
 * @author limeng
 * 
 * the block falling down (and the nextblock display)
 * centerx/y:	the current center of the block
 * 
 * cell configs:
 * blocktype:	the block type
 * blockdir:	the block direction
 * blockcolor:	the block color
 * cells[]:		the cells in the block
 * 
 * UpdateCellList():	calculate the current block config based on the type and direction
 * RotateBlock():		change the block to the next block direction
 * calccolor():			use the blocktype to find the block's color from the palette
 * 
 */

public class Block implements Anim{

	private int centerx;
	private int centery;
	
	private int blocktype;
	private int blockdir;
	private Color blockcolor;
	public Color[] palette = { StdDraw.BLUE, StdDraw.CYAN, StdDraw.GRAY, StdDraw.GREEN,
			StdDraw.MAGENTA, StdDraw.ORANGE, StdDraw.PINK, StdDraw.RED,
			StdDraw.LIGHT_GRAY, StdDraw.YELLOW };
	
	public Cell[] cells = new Cell[4];
	//public List<Cell> CurrentCells;
	private ShapeTable a = new ShapeTable();
	//private int curBlock[][][] = new int[4][4][4];

	
	public Block(int centerx, int centery, int blocktype) {
		super();
		//this.celltable = celltable;
		this.centerx = centerx;
		this.centery = centery;
		this.blocktype = blocktype; // use math.random? 
		this.blockdir = 0;
		this.blockcolor = calccolor(this.blocktype);
		for(int i=0;i<4;++i){
			cells[i] = new Cell(0,0,blockcolor);
		}
	}
	
	public void UpdateCellList(){
		for(int i=0;i<4;++i){
			//cells[i] = new Cell(getCenterx()+getDevx(i),getCentery()+getDevy(i),blockcolor);
			cells[i].x = getCenterx()+getDevx(i);
			cells[i].y = getCentery()+getDevy(i);
			cells[i].setCellcolor(blockcolor);
		}
	}
	
	private int getDevx(int index){
		return a.table[blocktype][blockdir][index][0];
	}
	
	private int getDevy(int index) {
		return a.table[blocktype][blockdir][index][1];
	}
	
	private Color calccolor(int blocktype){
		//int randomcolor = (int)(Math.random()*10);
		return palette[blocktype];// [randomcolor];
	}
	
	public Color getColor(){
		return blockcolor;
	}
	public void setColor(Color color){
		this.blockcolor = color;
	}
	
	public int getCenterx() {
		return centerx;
	}
	public void setCenterx(int centerx) {
		this.centerx = centerx;
	}
	public int getCentery() {
		return centery;
	}
	public void setCentery(int centery) {
		this.centery = centery;
	}
	
	public void setBlockType(int blocktype){
		this.blocktype = blocktype;
	}
	public int getBlockType(){
		return this.blocktype;
	}
	// RotateBlock
	public void rotateBlock(){
		blockdir+=1;
		if (blockdir > 3){
			blockdir = 0;
		}
	}
	public void setBlockDir(int blockdir){
		this.blockdir = blockdir;
	}
	public int getBlockDir(){
		return this.blockdir;
	}
	
	@Override
	public void draw() {
		
		double cellunit = 0.04;
		double shrinkratio = 0.5;
		double refx = 0.9;
		double refy = 0.8;
		double tempcellx = 0;
		double tempcelly = 0;
		
		for (int i=0;i<4;++i){
			tempcellx = refx+((double)getDevx(i))*cellunit;
			tempcelly = refy+((double)getDevy(i))*cellunit;
			StdDraw.setPenColor(Color.GRAY );
			StdDraw.filledSquare(tempcellx,tempcelly, cellunit*shrinkratio);
		}
	}

	@Override
	public boolean isDone() {
		// FIXME Auto-generated method stub
		return false;
	}
}
