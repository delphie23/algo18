package rbtree;

public class Tree {
	Node NIL, root;
	
	public Tree() {
		/**
		 * Create and initiate the NIL sentinel.
		 */
		this.NIL = new Node(0,0);
		this.NIL.p = this.NIL;
		this.NIL.left = this.NIL;
		this.NIL.right = this.NIL;
		
		this.root = NIL;
	}
	
	public void showTree() {
		this.preorderPrint(this.root);
	}
	
	private void preorderPrint(Node x) {
		if (x != NIL) {
			System.out.println("k:"+x.key+". s|h:"+x.side+"|"+x.height+". ms|mh:"+x.max_side+"|"+x.max_height+".");
			preorderPrint(x.left);
			preorderPrint(x.right);
		}
	}
	
	public void inorderPrint() {
		this.inorderPrint(this.root);
	}
	
	private void inorderPrint(Node x) {
		if (x != NIL) {
			inorderPrint(x.left);
			System.out.println(x.key);
			inorderPrint(x.right);
		}
	}
	
	void leftRotate(Node x){
		Node y = x.right;
		x.right = y.left;
		if (y.left != NIL) {
			y.left.p = x;
		}
		y.p = x.p;
		if (x.p == NIL) {this.root = y;}
		else {
			if (x == x.p.left) {x.p.left = y;}
			else {x.p.right = y;}
		y.left = x;
		x.p = y;
		}
	}
	
	void rightRotate(Node x){
		Node y = x.left;
		x.left = y.right;
		if (y.right != NIL) {
			y.right.p = x;
		}
		y.p = x.p;
		if (x.p == NIL) {this.root = y;}
		else {
			if (x == x.p.right) {x.p.right = y;}
			else {x.p.left = y;}
		y.right = x;
		x.p = y;
		}
	}

	void RBInsertFixup(Node z) {
		Node y;
		while (z.p.color == Color.RED) {
			if (z.p == z.p.p.left) {
				y = z.p.p.right;
				if (y.color == Color.RED) /* Case 1 */
				{
					/**
					 * I've put the red coloring before the black coloring
					 * in order to prevent an infinite loop caused by the
					 * red coloring of the NIL sentinel.
					 * The same goes for everywhere there is both red
					 * coloring and black coloring at the same time.
					 */
					z.p.p.color = Color.RED;
					z.p.color = Color.BLACK;
					y.color = Color.BLACK;
					z = z.p.p;
				}
				else {
					if (z == z.p.right) /* Case 2 */
					{
						z = z.p;
						leftRotate(z);
					}
					/* Case 3 */
					z.p.p.color = Color.RED;
					z.p.color = Color.BLACK;
					rightRotate(z.p.p);
				}
			}
			else {
				y = z.p.p.left;
				if (y.color == Color.RED) {
					z.p.p.color = Color.RED;
					z.p.color = Color.BLACK;
					y.color = Color.BLACK;
					z = z.p.p;
				}
				else {
					if (z == z.p.left) {
						z = z.p;
						rightRotate(z);
					}
					z.p.p.color = Color.RED;
					z.p.color = Color.BLACK;
					leftRotate(z.p.p);
				}
			}
		}
		this.root.color = Color.BLACK;
	}

	
	void RBInsert(Node z) {
		Node y = NIL;
		Node x = this.root;
		while (x != NIL) {
			y = x;
			if (z.key < x.key) {x = x.left;}
			else  {x = x.right;}
		}
		z.p = y;
		if (y == NIL) {this.root = z;}
		else if (z.key < y.key) {y.left = z;}
		else {y.right = z;}
		z.left = NIL;
		z.right = NIL;
		z.color = Color.RED;
		RBInsertFixup(z);
	}
	
	Node treeMinimum(Node x) {
		while (x.left != NIL) {x = x.left;}
		return x;
	}
	
	Node treeSuccessor(Node x) {
		if (x.right != NIL) {return treeMinimum(x);}
		Node y = x.p;
		while (y != NIL && x == y.right) {
			x = y;
			y = y.p;
		}
		return y;
	}
	
	void RBDeleteFixup(Node x) {
		Node w;
		while (x != root && x.color == Color.BLACK) {
			if (x == x.p.left) {
				w = x.p.right;
				if (w.color == Color.RED) /* Case 1 */
				{
					w.color = Color.BLACK;
					x.p.color = Color.RED;
					leftRotate(x.p);
					w = x.p.right;
				}
				if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) /* Case 2 */
				{
					w.color = Color.RED;
					x = x.p;
				}
				else {
					if (w.right.color == Color.BLACK) /* Case 3 */
					{
						w.left.color = Color.BLACK;
						w.color = Color.RED;
						rightRotate(w);
						w = x.p.right;
					}
					/* Case 4 */
					w.color = x.p.color;
					x.p.color = Color.BLACK;
					w.right.color = Color.BLACK;
					leftRotate(x.p);
					x = root;
				}
			}
			else {
				w = x.p.left;
				if (w.color == Color.RED) {
					w.color = Color.BLACK;
					x.p.color = Color.RED;
					rightRotate(x.p);
					w = x.p.left;
				}
				if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.p;
				}
				else {
					if (w.left.color == Color.BLACK) {
						w.right.color = Color.BLACK;
						w.color = Color.RED;
						leftRotate(w);
						w = x.p.left;
					}
					w.color = x.p.color;
					x.p.color = Color.BLACK;
					w.left.color = Color.BLACK;
					rightRotate(x.p);
					x = root;
				}
			}
		}
		x.color = Color.BLACK;
	}
	
	Node RBDelete(Node z) {
		Node x, y;
		if (z.left == NIL || z.right == NIL) {y = z;}
		else {y = treeSuccessor(z);}
		if (y.left != NIL) {x = y.left;}
		else {x = y.right;}
		x.p = y.p;
		if (y.p == NIL) {root = x;}
		else {
			if (y == y.p.left) {y.p.left = x;}
			else {y.p.right = x;}
		}
		if (y != z) {z.copy(y);}
		if (y.color == Color.BLACK) {RBDeleteFixup(x);}
		return y;
	}
	
	void recursiveCalcMax(Node x) {
		if (x != NIL) {
			x.calcMax();
			recursiveCalcMax(x.p);
		}
	}
	
	public void insert(Node z) {
		this.RBInsert(z);
		this.recursiveCalcMax(z);
	}
	
	public void delete(Node z) {
		Node y = this.RBDelete(z);
		this.recursiveCalcMax(y.p);
	}
	
	private Node getBest(int min_side, int min_height, Node x) {
		if (x.left.checkMax(min_side, min_height)) {return getBest(min_side, min_height, x.left);}
		if (x.checkData(min_side, min_height)) {return x;}
		if (x.right.checkMax(min_side, min_height)) {return getBest(min_side, min_height, x.right);}
		return NIL;
	}
}
