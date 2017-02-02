package studio10;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sedgewick.StdDraw;

/**
 * 
 * @author limeng
 *	main function of the game
 *	input constants
 *
 *	decide the game process
 *	update the game process
 *
 */

public class DescendingBlocks {

	public static void main(String[] args) {
		final int showPauseTime = 20; // milliseconds
		final double cellunit = 0.08;
		final int tablecol = 10;
		final int tablerow = 12;
		int fallcount = 0;
		int currentscore = 0;
		int currentlvl = 0;
		int fallloopnum = 20; //10
		int nextblocktype = 0;
		
		//
		// Want to be able to continually iterate through a list of
		//    things to draw on the screen.  Each of those things
		//    should be able to draw itself, and also tell me when
		//    it is done.
		//
		List<Anim> scene1 = new LinkedList<Anim>();
		//Cell testCell=new Cell(0.5,0.5,Color.yellow,false);
		CellTable testCellTable = new CellTable(0.05,0.05,tablecol,tablerow,cellunit);
		scene1.add(testCellTable);
		Block nextBlock = new Block (0,0,0);
		scene1.add(nextBlock);
		nextblocktype = randomblock();
		
		mainloop:
		while (!scene1.isEmpty()) {
			
			// update game state
			//
			if(!testCellTable.activeblock){ // 
		
				for(int j=0;j<3;++j){
					int points = testCellTable.CheckFullRow();
					currentscore += points; // full row	
					
					// difficulty set
					currentlvl = currentscore/5;
					//fallloopnum -= points*5;
					fallloopnum -= points*2;
					if(fallloopnum<4){
						fallloopnum = 3;
					}
				}
				
				testCellTable.SetCurrentBlock(new Block(tablecol/2,tablerow-3,nextblocktype));
					
				nextblocktype = randomblock();

				testCellTable.SetLastBlock();
				testCellTable.activeblock = true;
				
				if(testCellTable.CheckEndGame()){
					
					//System.out.println("end");
					break mainloop; 
				} // end game
			}
			else{
				//testCellTable.ClearTable();
				testCellTable.SetLastBlock();
				
				// first check fall
				//
				if(fallcount>fallloopnum){
					if(testCellTable.FallBlockFlag()){
						testCellTable.FallBlock();
					}
					else{
						// Merge Block
						testCellTable.LandBlock(); // special: switch isBlock to isFilled
						testCellTable.activeblock = false;
					}
					fallcount = 0;
				}
				else{
					// normal loop
					//
					fallcount++;
					
					// then check move
					//
					if(StdDraw.hasNextKeyTyped())
					{
						int k=StdDraw.nextKeyTyped();
						if(k=='a'){ 
							// to the left
							if(testCellTable.MoveBlockFlag(true)){
								testCellTable.MoveBlock(true);
							}
						}
						else if(k=='d'){ 
						// to the right
							if(testCellTable.MoveBlockFlag(false)){
								testCellTable.MoveBlock(false);
							}
						}
						else if(k=='s'){ 
							// accelerate!
							if(testCellTable.FallBlockFlag()){
								testCellTable.FallBlock();
							}
						}
						else if(k=='w'){
							// Rotation!
							if(testCellTable.RotateBlockFlag()){
								testCellTable.RotateBlock();
							}
						}		
					} // haskeytyped
				} //nofall				
			} // activeblock
			
			// Update the block info
			//testCellTable.UpdateBlock();
			testCellTable.ClearLastBlock();
			testCellTable.UpdateBlock();

			// update other UI
			//
			nextBlock.setBlockType(nextblocktype); // real next block
			nextBlock.UpdateCellList();
			
			StdDraw.clear();
			// draw everything
			//
			
			StdDraw.text(0.9, 0.1, "score " + currentscore);
			StdDraw.text(0.9, 0.2, "level " + currentlvl);
			
			for (Anim a : scene1) {
				a.draw();
			}
			//
			// Delete what is done, the safe way
			//
			Iterator<Anim> iter = scene1.iterator();
			while (iter.hasNext()) {
				Anim a = iter.next();
				if (a.isDone()) {
					iter.remove();   // removes the current object safely
				}
			}
			StdDraw.show(showPauseTime);
		}
		
		// end game wrap-up
		testCellTable.UpdateBlock();
		for (Anim a : scene1) {
			a.draw();
		}
		StdDraw.text(0.9, 0.3, "END!");
		StdDraw.show(showPauseTime);
		int k = 0;
		while( !StdDraw.hasNextKeyTyped() || (k != 'q')){
			if(StdDraw.hasNextKeyTyped()){
				k = StdDraw.nextKeyTyped();
			}
		}
		//
		// clear the screen
		//
		StdDraw.clear();
		StdDraw.show(showPauseTime);
		
	}

	public static int randomblock(){
		int rand = ((int)(Math.random()*100)%7); // block type num = 7	
		//if (rand>0) rand = 6;
		return rand;
	}
	
}
