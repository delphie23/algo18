import java.util.*;

import list.Node;
import rbtree.*;

public class test2 {
	public static void main(String[] args) {

		Random rand = new java.util.Random();
		
		Tree T = new Tree();

		for (int i=0; i<15; i++) {
			Node N = new Node(rand.nextInt(5)+1,rand.nextInt(5)+1);
			T.insert(N);
		}
		
		System.out.println("\n");//
		
		T.inorderPrint();
		
		System.out.println("\n");//
		
		T.showTree();
		
		System.out.println("\nEND.");//

		
		/**
		Node N = new Node(rand.nextInt(5)+1,rand.nextInt(5)+1);
		Node M = new Node(rand.nextInt(5)+1,rand.nextInt(5)+1);
		
		if (N.left == NIL) {System.out.println("yes");} else {System.out.println("no");} 
		System.out.println(N.p);
		System.out.println();System.out.println();System.out.println();
		
		System.out.print("Sentinels.NIL: ");
		System.out.println(Sentinels.NIL);
		System.out.print("Node.NIL: ");
		System.out.println(Node.NIL);
		System.out.print("Tree.NIL: ");
		System.out.println(Tree.NIL);
		System.out.print("N.NIL: ");
		System.out.println(N.NIL);
		System.out.print("T.NIL: ");
		System.out.println(T.NIL);
		System.out.print("N: ");
		System.out.println(N);
		System.out.print("M: ");
		System.out.println(M);
		System.out.print("M.NIL: ");
		System.out.println(M.NIL);
		**/
	}
}
