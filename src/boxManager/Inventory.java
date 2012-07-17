package boxManager;

import list.*;
/**
 * @author ariel
 *
 */
public class Inventory {
	private List<List<Dimensions>> inv;
	
	public Inventory() {
		this.inv = new List<List<Dimensions>>();
	}
	
	private Node<Dimensions> createHeight(float side, float height) {
		Dimensions dims = new Dimensions(side,height);
		Node<Dimensions> heightNode = new Node<Dimensions>(height, dims);
		return heightNode;
	}
	
	private Node<List<Dimensions>> createSide(float side, float height) {
		List<Dimensions> list = new List<Dimensions>();
		Node<List<Dimensions>> sideNode = new Node<List<Dimensions>>(side, list);
		sideNode.getData().setHead(createHeight(side, height));
		return sideNode;
	}
	
	
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
	
	public void insertBox(float side, float height) {
		if (inv.getHead() == null) inv.setHead(createSide(side, height));
		else insertSide(side, height);
	}
	
	private void removeBoxNode(Node<List<Dimensions>> sideNode, Node<Dimensions> heightNode) {
		if (heightNode.getData().getCount() == 1) sideNode.getData().delete(heightNode);
		else heightNode.getData().remove();
		if (sideNode.getData().getHead() == null) inv.delete(sideNode);
	}
		
	public void removeBox(float side, float height) {
		Node<List<Dimensions>> sideNode = inv.search(side);
		if (sideNode != null) {
			Node<Dimensions> heightNode = sideNode.getData().search(height);
			if (heightNode != null) removeBoxNode(sideNode, heightNode);
		}
	}
	
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
	
	public boolean checkBox(float side, float height) {
		if (getBoxNode(side, height) != null) return true;
		return false;
	}
	
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
