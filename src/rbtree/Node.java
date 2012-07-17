package rbtree;

public class Node {
	/* <DATA> */
	int side;
	int height;
	int max_side;
	int max_height;
	int key;
	/* </DATA> */
	
	public Node p;
	public Node left;
	public Node right;
	
	Color color;
	
	public Node(int side, int height) {		
		this.side = side;
		this.height = height;
		this.max_side = side;
		this.max_height = height;
		this.key = side*side*height;
		
		this.color = Color.BLACK;
	}
	
	void calcMax() {
		this.max_side = Math.max(this.side, Math.max(this.left.max_side, this.right.max_side));
		this.max_height = Math.max(this.height, Math.max(this.left.max_height, this.right.max_height));
	}
	
	/**
	 * Copy x's satellite data into this node.
	 * @param x
	 */
	void copy(Node x) {
		this.side = x.side;
		this.height = x.height;
		
		this.key = side*side*height;
		
		this.calcMax();
	}
	
	boolean checkMax(int min_side, int min_height) {
		if (this.max_side<min_side || this.max_height<min_height) {return false;}
		return true;
	}
	
	boolean checkData(int min_side, int min_height) {
		if (this.side<min_side || this.height<min_height) {return false;}
		return true;
	}
}
