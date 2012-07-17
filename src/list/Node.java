package list;
/**
 * Represents a node in a double-sided linked list, which carries abstract satellite data.
 * @author ariel
 * @param <D> the type of the sattellite data.
 */
public class Node<D> {
	 private Node<D> prev, next;
	 private float key;
	 private D data;
	
	public Node(float key, D data) {
		this.key = key;
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	/**
	 * @return the next node
	 */
	public Node<D> getNext() {
		return next;
	}

	/**
	 * @param next the node to set as the next one
	 */
	public void setNext(Node<D> next) {
		this.next = next;
		if (next != null) next.prev = this;
	}

	/**
	 * @return the previous node
	 */
	public Node<D> getPrev() {
		return prev;
	}

	/**
	 * @return the key
	 */
	public float getKey() {
		return key;
	}

	/**
	 * @return the data
	 */
	public D getData() {
		return data;
	}
	
	/**
	 * Inserts a node right between this node and the next one.
	 * @param node the node to insert.
	 */
	public void insertNext(Node<D> node) {
		node.setNext(this.getNext());
		this.setNext(node);
	}
}
