import java.util.*;
import boxManager.Inventory;

public class test1 {
	public static void main(String[] args) {
		Random rand = new java.util.Random();
		Inventory inv = new Inventory();
		

		for (int i=0; i<25; i++) {
			float side = rand.nextInt(7)+1;
			float height = rand.nextInt(4)+1;
			System.out.println(side+","+height+" = "+side*side*height);
			inv.insertBox(side, height);
		}

		
		
		
		//inv.insertBox(1,3);
		inv.insertBox(6,3);
		inv.insertBox(6,2);
		//inv.insertBox(1,3);
		//inv.insertBox(3,1);
		//inv.insertBox(5,1);
		//inv.insertBox(4,3);
		//inv.insertBox(5,4);
		//inv.insertBox(5,1);
		inv.insertBox(6,4);
		//inv.insertBox(2,4);
		inv.insertBox(7,4);
		inv.insertBox(7,4);
		//inv.insertBox(2,4);
		//inv.insertBox(1,4);
		inv.insertBox(9,3);
		inv.insertBox(9,2);
		inv.insertBox(9,1);

		inv.removeBox(6,3);
		
		inv.printInv();
		
		float[] out = inv.getBox(3, 4);
		
		System.out.println("\n"+out[0]+","+out[1]);
	}

}
