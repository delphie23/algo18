/**
 * 
 */
package list;

/**
 * Represents a double-sided linked-list.
 * The insertion operation is deliberately being done by an external class
 * since it's very different then the ordinary insertion operation.
 * @author ariel
 * @param <D> the type of the sattellite data.
 */
public class List<D> {
	private Node<D> head;
	
	/**
	 * Constructs a list with an empty head.
	 * @WCP O(1).
	 */
	public List() {
		this.head = null;
	}
		
	/**
	 * Removes a node from the list.
	 * (makes the next node the next node of the previous node).
	 * @WCP O(1).
	 * @param node the node to remove.
	 */
	public void delete(Node<D> node) {
		if (node.getPrev() != null) node.getPrev().setNext(node.getNext());
		else head = node.getNext();
	}
	
	/**
	 * Returns the first node in the list.
	 * @WCP O(1).
	 * @return the first node in the list.
	 */
	public Node<D> getHead() {
		return head;
	}
	
	/**
	 * Sets a node as the first node of the list
	 * (so the following nodes will be the new list).
	 * @WCP O(1).
	 * @param node the node intended to be the new head of the list.
	 */
	public void setHead(Node<D> node) {
		node.setNext(head);
		head = node;
	}
	
	/**
	 * Returns the first node with the specified key.
	 * @WCP O(list_size).
	 * @param key the key of the searched node.
	 * @return the first node with the specified key.
	 */
	public Node<D> search(float key) {
		Node<D> node = head;
		while (node != null && node.getKey() > key) node = node.getNext();
		if (node != null && node.getKey() == key) return node;
		else return null;
	}
}
