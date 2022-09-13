package listClasses;

import java.util.Iterator;


public class BasicLinkedList<T> extends java.lang.Object implements 
java.lang.Iterable<T> {

	protected class Node {
		protected Node next;
		protected T data;

		Node(T data) {
			this.data = data;
			this.next = null;
		}

	}

	class LinkListIterator implements Iterator<T> {
		private Node curr = head;

		@Override
		public boolean hasNext() {
			return (curr != null);
		}

		@Override
		public T next() {
			Node temp = curr;
			curr = curr.next;
			return temp.data;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	protected int listSize;
	protected Node head;
	protected Node tail;

	public BasicLinkedList() {
		listSize = 0;
		head = null;
		tail = null;
	}

	public int getSize() {
		return listSize;
	}

	public BasicLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
		if(tail == null) { //both head and tail will point to the first element
			tail = newNode;
		}

		listSize++;

		return this;
	}

	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		if(head == null) { //both head and tail will point to the first element
			tail = newNode;
			head = newNode;
		} else { //for when an element is added to a list that has >= 1 element
			tail.next = newNode;
			tail = newNode;
		}

		listSize++;

		return this;
	}

	public T getFirst() {
		if(head == null) {
			return null;
		} else {
			return head.data;
		}
	}

	public T getLast() {
		if(tail == null) {
			return null;
		} else {
			return tail.data;
		}
	}

	public T retrieveFirstElement() {
		if(head == null) {
			return null;
		}

		Node temp = head; //a temporary element becomes is assigned first element
		head = head.next; //head is reassigned to remove first element from list
		temp.next = null;
		listSize--;

		return temp.data;
	}

	public T retrieveLastElement() {
		if(tail == null) {
			return null;
		}

		Node curr = head;
		Node previous = null;
		while(curr.next != null) { //loop to find element before last element
			previous = curr; 
			curr = curr.next;
		}

		Node temp = tail; //last element is stored 
		tail = previous; //tail is reassigned to element before last element
		if(previous != null) {
			previous.next = null;
		}

		listSize--;

		return temp.data;
	}

	public BasicLinkedList<T> remove(T targetData,
			java.util.Comparator<T> comparator) {
		if(head == null ) {
			return null;
		}

		Node curr = head;
		Node previous = null;
		while(curr != null) {
			//first if statement is for when the first item in a list is removed
			if(comparator.compare(curr.data, targetData) == 0 && curr == head) {
				head = head.next;
				previous = curr;
				curr = curr.next;
				listSize--;
			//second if statement is for when removing any other element
			} else if(comparator.compare(curr.data, targetData) == 0
					&& curr != head) {
				previous.next = curr.next;
				tail = previous;
				curr = curr.next;
				listSize--;
			//third if statement for when no element is removed
			} else {
				previous = curr;
				curr = curr.next;
			}
		}
		return this;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkListIterator();
	}

	public java.util.ArrayList<T> getReverseArrayList() {
		java.util.ArrayList<T> reverseList = new java.util.ArrayList<T>();
		return makeReverseArray(reverseList, head);
	}

	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> reverseList = new BasicLinkedList<T>();
		return makeReverseList(reverseList, head);
	}

	private java.util.ArrayList<T> makeReverseArray(
			java.util.ArrayList<T> reverseList, Node curr) {
		if(curr == null) {
			return reverseList;
		}
		//adding at zero will make reverse order since adding at an index where
		//an element exists will move that element up an index
		reverseList.add(0, curr.data);
		return makeReverseArray(reverseList, curr.next);
	}

	private BasicLinkedList<T> makeReverseList(
			BasicLinkedList<T> reverseList, Node curr) {
		if(curr == null) {
			return reverseList;
		}
		reverseList.addToFront(curr.data);
		return makeReverseList(reverseList, curr.next);
	}

}


