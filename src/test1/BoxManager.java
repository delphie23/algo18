package test1;

import java.util.TreeMap;

import test1.BST;
import test1.Node;

/**
 * @author ariel
 *
 */
public class BoxManager {
	private static final Node NIL = null;
	private BST T = new BST();

	public void insertBox (int side, int height) {
		Node z = new Node(side, height);
		T.insert(z);
	}
	
	public void removeBox (int side, int height) {
	}
	
	public int getBox(int side, int height) {
		Node box = T.getNode(side, height, T.getRoot());
		if (box != NIL) {return box.getKey();}
		return -1;
	}
	
	/*
	public boolean checkBox(int side, int height) {
		
	}
	*/
	
}
