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
	 * 
	 */
	public List() {
		this.head = null;
	}
		
	/**
	 * 
	 * @param node
	 */
	public void delete(Node<D> node) {
		if (node.getPrev() != null) node.getPrev().setNext(node.getNext());
		else head = node.getNext();
	}
	
	/**
	 * 
	 * @return
	 */
	public Node<D> getHead() {
		return head;
	}
	
	/**
	 * 
	 * @param node
	 */
	public void setHead(Node<D> node) {
		node.setNext(head);
		head = node;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public Node<D> search(float key) {
		Node<D> node = head;
		while (node != null && node.getKey() > key) node = node.getNext();
		if (node != null && node.getKey() == key) return node;
		else return null;
	}
}
