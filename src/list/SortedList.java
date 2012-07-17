/**
 * 
 */
package list;

/**
 * @author ariel
 * @param <D>
 *
 */
public class SortedList<D> {
	private Node<D> head;
	
	public SortedList() {
		this.head = null;
	}
	
	public void insert(Node<D> node) {
		if (head == null || (head != null && head.getKey() < node.getKey())) {
			node.setNext(head);
			head = node;
		}
		else {
			Node<D> x = head;
			while (x.getNext() != null && x.getNext().getKey() > node.getKey()) x = x.getNext();
			x.insertNext(node);
		}
	}
		
	public void delete(Node<D> node) {
		if (node.getPrev() != null) node.getPrev().setNext(node.getNext());
		else head = node.getNext();
	}
	
	public Node<D> getHead() {
		return head;
	}
}
