package boxManager;

import list.*;
/**
 * The main class.
 * Implements an inventory of boxes sorted in a list by dimensions.
 * The class uses 2 types of lists:
 * 1. An "Heights List" in which each node represents a set of boxes with the same dimensions
 * (side AND height) and contains information about the quantity of those boxes.
 * All the nodes in a specific Heights List have the same "side" value, and sorted by height.
 * 2. A "Sides List" in which each node represents a set of boxes with the same side size,
 * and the data field of the node points to an Heights List.
 * The Sides List is sorted by side.
 * @author ariel
 *
 */
public class Inventory {
	private List<List<Dimensions>> inv;
	
	/**
	 * Constructs a new inventory with a sides list in the "inv" field.
	 * @WCP O(1).
	 */
	public Inventory() {
		this.inv = new List<List<Dimensions>>();
	}
	
	/**
	 * Creates and returns a new node with a Dimensions instance in the data field.
	 * The key of the node will be the height value.
	 * The node will represent a set of boxes with the specified dimensions,
	 * it will be initialized to a quantity of 1 box. 
	 * @WCP O(1).
	 * @param side the value of the side field.
	 * @param height the value of the height field.
	 * @return the new node.
	 */
	private Node<Dimensions> createHeight(float side, float height) {
		Dimensions dims = new Dimensions(side,height);
		Node<Dimensions> heightNode = new Node<Dimensions>(height, dims);
		return heightNode;
	}
	
	/**
	 * Creates and returns a new node with an Heights List instance in the data field.
	 * The key of the node will be the side value.
	 * The node will represent a set of boxes with the specified side size,
	 * the list in the data field will be initialized with an "height node" with the specified height
	 * as the head of the list.
	 * @WCP O(1).
	 * @param side the value of the side field of all the "height nodes" in the Height List. 
	 * @param height the value of the height field of the head of the Height List.
	 * @return the new node.
	 */
	private Node<List<Dimensions>> createSide(float side, float height) {
		List<Dimensions> list = new List<Dimensions>();
		Node<List<Dimensions>> sideNode = new Node<List<Dimensions>>(side, list);
		sideNode.getData().setHead(createHeight(side, height));
		return sideNode;
	}
	
	/**
	 * Inserts information about a new box into the correct place.
	 * This method is used in order to keep the list sorted by height.
	 * It must be used only with an already existing Side Node.
	 * @WCP O(n). 
	 * @param side the side size of the new box.
	 * @param height the height size of the new box.
	 * @param sideNode the node which holds the Heights List with the relevant height.
	 */
	private void insertHeight(float side, float height, Node<List<Dimensions>> sideNode) {
		if (height > sideNode.getData().getHead().getKey()) sideNode.getData().setHead(createHeight(side, height)); 
		else {
			Node<Dimensions> node = sideNode.getData().getHead();
			while (node.getNext() != null && node.getKey() > height) node = node.getNext();
			if (node.getKey() < height) node.getPrev().insertNext(createHeight(side, height));
			else if (node.getKey() == height) node.getData().add(); /* If the dimensions already exists it adds 1 to the
			quantity in the existing node */
			else node.setNext(createHeight(side, height)); /* If the tail of the list is bigger */

			
		}
	}
	
	/**
	 * Inserts a new side node to the Sides List,
	 * initialized with a new Height Node as the head.
	 * This method is used in order to keep the list sorted by side.
	 * @WCP O(m*n).
	 * @param side the new side value.
	 * @param height the height value of the head of the new Height List
	 * (the data field of the side node).
	 */
	private void insertSide(float side, float height) {
		if (side > inv.getHead().getKey()) inv.setHead(createSide(side, height)); 
		else {
			Node<List<Dimensions>> node = inv.getHead();
			while (node.getNext() != null && node.getKey() > side) node = node.getNext();
			if (node.getKey() < side) node.getPrev().insertNext(createSide(side, height));
			else if (node.getKey() == side) insertHeight(side, height, node); /* If the side value already exists it inserts
			the data into the encapsulated list */
			else node.setNext(createSide(side, height)); /* If the tail of the list is bigger */
		}
	}
	
	/**
	 * Adds a box with the specified dimensions.
	 * @WCP O(m*n).
	 * @param side the side size of the box.
	 * @param height the height size of the box.
	 */
	public void insertBox(float side, float height) {
		if (inv.getHead() == null) inv.setHead(createSide(side, height));
		else insertSide(side, height);
	}
	
	/**
	 * Removes a box specified by the heightNode in the specified sideNode.
	 * If the quantity of the boxes with the relevant dimensions is bigger than 1
	 * it will only subtract 1 from the quantity;
	 * If the quantity is exactly one it will remove the whole node;
	 * If the box was the only box with the relevant side it will remove the whole sideNode;
	 * @WCP O(1).
	 * @param sideNode the node which holds the relevant Sides List.
	 * @param heightNode the relevant node in the Sides List which holds the relevant dimensions.
	 */
	private void removeBoxNode(Node<List<Dimensions>> sideNode, Node<Dimensions> heightNode) {
		if (heightNode.getData().getCount() == 1) sideNode.getData().delete(heightNode);
		else heightNode.getData().remove();
		if (sideNode.getData().getHead() == null) inv.delete(sideNode);
	}

	/**
	 * Removes a box with the specified dimensions.
	 * First it uses linear search to find the relevant node,
	 * and then does the relevant operation to "remove a box" it represents.
	 * @WCP O(m*n).
	 * @param side the side size of the box.
	 * @param height the height size of the box.
	 */
	public void removeBox(float side, float height) {
		Node<List<Dimensions>> sideNode = inv.search(side);
		if (sideNode != null) {
			Node<Dimensions> heightNode = sideNode.getData().search(height);
			if (heightNode != null) removeBoxNode(sideNode, heightNode);
		}
	}
	
	/**
	 * 
	 */
	public void printInv() {
		Node<List<Dimensions>> sideNode = inv.getHead();
		while (sideNode != null) {
			System.out.print("|"+sideNode.getKey()+"| ");
			Node<Dimensions> heightNode = sideNode.getData().getHead();
			while (heightNode != null) {
				System.out.print(heightNode.getKey()+" ");
				heightNode = heightNode.getNext();
			}
			sideNode = sideNode.getNext();
		}
		
		System.out.print("\n");
		sideNode = inv.getHead();
		while (sideNode != null) {
			System.out.print("|"+sideNode.getKey()+"| ");
			sideNode = sideNode.getNext();
		}
	}
	
	/**
	 * Returns a node which represents a box with the smallest volume
	 * above the specified dimensions using linear search.
	 * First it uses the volume of the first Height Node as the minimal volume value,
	 * then it searches the Heights List in each Side Node in the sorted Sides List
	 * for a smaller volume until it reaches the smallest height allowed,
	 * and until it reaches the smallest side allowd.    
	 * @param side the side size of the box.
	 * @param height the height size of the box.
	 * @return a node which represents a box with the specified dimensions.
	 */
	private Node<Dimensions> getBoxNode(float side, float height) {
		Node<List<Dimensions>> sideNode = inv.getHead();
		if (sideNode != null) {
			Node<Dimensions> heightNode = sideNode.getData().getHead();
			Node<Dimensions> minNode = heightNode;
			while (sideNode != null && sideNode.getKey() >= side) {
				while (heightNode != null && heightNode.getKey() >= height) {
					if (heightNode.getData().getVol() < minNode.getData().getVol()) minNode = heightNode;
					heightNode = heightNode.getNext();
				}
				sideNode = sideNode.getNext();
				heightNode = sideNode.getData().getHead();
			}
			return minNode;
		}
		return null;
	}
	
	/**
	 * Checks if there is a box which fits the dimensions.
	 * @param side the side size of the box. 
	 * @param height the height size of the box.
	 * @return true if there is a box, false if there isn't.
	 */
	public boolean checkBox(float side, float height) {
		if (getBoxNode(side, height) != null) return true;
		return false;
	}
	
	/**
	 * Returns a pair {side,height} with the dimensions of the smallest box
	 * which fits the specified dimensions of the present.
	 * @param side the side size of the box.
	 * @param height the height size of the box.
	 * @return  a pair {side,height} with the dimensions of the smallest box which fits,
	 * or {0.0,0.0} if there isn't any.
	 */
	public float[] getBox(float side, float height) {
		Node<Dimensions> node = getBoxNode(side, height);
		float[] dimensions = new float[2];
		if (node != null) {
			dimensions[0] = node.getData().getSide();
			dimensions[1] = node.getData().getHeight();
		}
		return dimensions;
	}

}
