package test1;
/**
 * 
 */

/**
 * @author ariel
 *
 */
public class BST {
	private static final Node NIL = null;
	
	private Node root;
	
	
	public BST() {
		this.setRoot(NIL);
	}

	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}
	
	public void insert(Node z) {
		Node y = NIL;
		Node x = this.getRoot();
		while (x != NIL) {
			y = x;
			if (z.getKey() < x.getKey()) {x = x.getLeft();}
			else {x = x.getRight();}
		}
		if (y == NIL) {this.setRoot(z);}
		else {
			if (z.getKey() < y.getKey()) {y.setLeft(z);}
			else {y.setRight(z);}
		}
		//here comes some additions:
		this.fixupMax(z);
	}
	
	public void fixupMax(Node x) {
		while (x != NIL) {
			x.updateMax();
			x = x.getP();
		}
	}
	
	public Node getNode(int side, int height, Node x) {
		System.out.println(x.getKey()+","+x.getSide()+","+x.getHeight()+", MAX:"+x.getMaxSide()+","+x.getMaxHeight());
		if (x.checkMax(side, height)) {
			if (x.checkDimensions(side, height)) {
				if (x.getLeft() == NIL) {System.out.println("blocked by left"); return x;}
				System.out.println("LEFT");
				return this.getNode(side, height, x.getLeft());}
			System.out.println("RIGHT");
			return this.getNode(side, height, x.getRight());
		}
		System.out.println(x.getKey()+","+x.getSide()+","+x.getHeight()+", MAX:"+x.getMaxSide()+","+x.getMaxHeight()+" DENIED!");
		return x.getP();
	}
	
	/*
	public Node getNode(int side, int height, Node x) {
		if (x == NIL) {System.out.println("FUQ"); return null;}
		if (x.checkMax(side, height)) {
			if (x.checkDimensions(side, height)) {System.out.println("A");
				return this.getNode(side, height, x.getLeft());}
			System.out.println("B");
			return this.getNode(side, height, x.getRight());
		}
		System.out.println("C");
		return x.getP();
	}
	*/
}
