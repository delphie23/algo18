package test1;
/**
 * 
 */

/**
 * @author ariel
 *
 */
public class Node {
	private static final Node NIL = null;
	
	private Node p;
	private Node left;
	private Node right;
	private int side;
	private int height;
	private int max_side;
	private int max_height;
	
	public Node(int side, int height) {
		this.side = side;
		this.height = height;
		this.setP(NIL);
		this.left = NIL;
		this.right = NIL;
	}

	/**
	 * @return the parent node.
	 */
	public Node getP() {
		return p;
	}

	/**
	 * @param p the parent to set.
	 */
	private void setP(Node p) {
		this.p = p;
	}

	/**
	 * @return the left child node.
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * @param left the left child to set.
	 */
	public void setLeft(Node left) {
		this.left = left;
		this.left.setP(this);
	}

	/**
	 * @return the right child node.
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * @param right the right child to set.
	 */
	public void setRight(Node right) {
		this.right = right;
		this.right.setP(this);
	}

	/**
	 * @return the side of the box.
	 */
	public int getSide() {
		return side;
	}


	/**
	 * @return the height of the box.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 
	 * @return the side of the widest box in the subtree.
	 */
	public int getMaxSide() {
		return max_side;
	}
	
	/**
	 * 
	 * @return the height of the highest box in the subtree.
	 */
	public int getMaxHeight() {
		return max_height;
	}
	
	/**
	 * 
	 * @return the volume of the box.
	 */
	public int getKey() {
		return getSide()*getSide()*getHeight();
	}
	
	public void updateMax() {
		int left_max_side = 0;
		int left_max_height = 0;
		int right_max_side = 0;
		int right_max_height = 0;
		if (this.getLeft() != NIL) {
			left_max_side = this.getLeft().max_side;
			left_max_height = this.getLeft().max_height;
		}
		if (this.getRight() != NIL) {
			right_max_side = this.getRight().max_side;
			right_max_height = this.getRight().max_height;
		}
		this.max_side = Math.max(this.side, Math.max(left_max_side, right_max_side));
		this.max_height = Math.max(this.height, Math.max(left_max_height, right_max_height));
	}
	
	/**
	 * 
	 * @param side
	 * @param height
	 * @return
	 */
	public boolean checkMax(int side, int height) {
		if ((this.getMaxSide() < side) || (this.getMaxHeight() < height)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param side
	 * @param height
	 * @return
	 */
	public boolean checkDimensions(int side, int height) {
		if ((this.getSide() < side) || (this.getHeight() < height)) {
			return false;
		}
		return true;
	}
}
