package studio10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Moving {

	
	
	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		double x,y;
		int k=0;
		x=0.5;
		y=0.5;
		while(true){
			StdDraw.clear();
			StdDraw.setPenColor(Color.BLUE);
			StdDraw.filledSquare(x,y, 0.01);
			y=y-0.01;
			if(StdDraw.hasNextKeyTyped())
			{
				k=StdDraw.nextKeyTyped();
				if(k=='a')
					x=x-0.01;
				else if(k=='s')
					x=x+0.01;
				else
					x=x;
			}
		    StdDraw.pause(100);
		    k=0;		
		}		

	}

}
