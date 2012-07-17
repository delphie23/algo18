package test1;
import BoxManager;

import java.util.Random;


public class run_me {
	
	public static Random rand = new Random();
	
	public static void main(String[] args) {
		
		BoxManager bm = new BoxManager();	

		for (int i=0; i<7; i++) {
			//System.out.print(rand.nextInt(20)+",");
			//System.out.println("LOL WUT?!");
			int side = rand.nextInt(5)+1;
			int height = rand.nextInt(5)+1;
			System.out.println(side*side*height+", "+side+","+height);
			bm.insertBox(side, height);
		}
		
		System.out.println("OK.\n\n");
		
		int out = bm.getBox(3, 3);
		System.out.println(out);

	}

}
