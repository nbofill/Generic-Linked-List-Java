package listClasses;

public class SortedLinkedList<T> extends BasicLinkedList<T> {

	java.util.Comparator<T> comparator;

	public SortedLinkedList(java.util.Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	public SortedLinkedList<T> add(T element) {
		Node curr = head;
		Node previous = null;

		//case for when the element added is the first one
		if (head == null) { 
			Node newNode = new Node(element);
			head = newNode;
			tail = newNode;
			listSize++;
			return this;
		}

		//loop that finds the position for the element
		while(comparator.compare(element, curr.data) > 0) {
			if(curr.next == null) {
				Node newNode = new Node(element);
				curr.next = newNode;
				tail = newNode;
				listSize++;
				return this;
			}
			previous = curr;
			curr = curr.next;
		}

		//case for when the position of the new element is the first spot
		if (curr == head) {
			Node newNode = new Node(element);
			newNode.next = curr;
			head = newNode;
		//case for when the position of the new element is any spot but the first
		} else {
			Node newNode = new Node(element);
			previous.next = newNode;
			newNode.next = curr;
		}
		
		listSize++;
		return this;
	}

	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException
		("Invalid operation for sorted list.");
	}

	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException
		("Invalid operation for sorted list.");
	}
}