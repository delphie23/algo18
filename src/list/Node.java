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
	
	/**
	 * Constucts a node with the specified key and data fields.
	 * @WCP O(1).
	 * @param key the key field of the new node.
	 * @param data the data field of the new node.
	 */
	public Node(float key, D data) {
		this.key = key;
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	/**
	 * @WCP O(1).
	 * @return the next node
	 */
	public Node<D> getNext() {
		return next;
	}

	/**
	 * @WCP O(1).
	 * @param next the node to set as the next one
	 */
	public void setNext(Node<D> next) {
		this.next = next;
		if (next != null) next.prev = this;
	}

	/**
	 * @WCP O(1).
	 * @return the previous node
	 */
	public Node<D> getPrev() {
		return prev;
	}

	/**
	 * @WCP O(1).
	 * @return the key field
	 */
	public float getKey() {
		return key;
	}

	/**
	 * @WCP O(1).
	 * @return the data field
	 */
	public D getData() {
		return data;
	}
	
	/**
	 * Inserts a node right between this node and the next one.
	 * @WCP O(1).
	 * @param node the node to insert.
	 */
	public void insertNext(Node<D> node) {
		node.setNext(this.getNext());
		this.setNext(node);
	}
}
