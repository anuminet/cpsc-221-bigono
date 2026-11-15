import java.util.*;

/**
 * Single-linked node implementation of IndexedUnsortedList.
 * An Iterator with working remove() method is implemented, but
 * ListIterator is unsupported.
 * 
 * @author 
 * 
 * @param <E> type to store
 */
public class IUSingleLinkedList<E> implements IndexedUnsortedList<E> {
	private LinearNode<E> front, rear;
	private int count;
	private int modCount;
	
	/** Creates an empty list */
	public IUSingleLinkedList() {
		front = rear = null;
		count = 0;
		modCount = 0;
	}

	@Override
	public void addToFront(E element) {
		// Create node containing element
		LinearNode<E> newNode = new LinearNode<>(element);

		// Determine if list is empty
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			// If list contains elements, new node connects to old
			// first node and update front to refrence new first node. 
			newNode.setNext(front);
			front = newNode;
		}
		count++;
		modCount++;
	}

	@Override
	public void addToRear(E element) {
		LinearNode<E> newNode = new LinearNode<>(element);

		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			// If list contains elements, new node connects to old 
			// rear node and update rear to refrence new last node.
			rear.setNext(newNode);
			rear = newNode;
		}
		count++;
		modCount++;
	}

	@Override
	public void add(E element) {
		// Add element to rear using existing method
		addToRear(element);
		
	}

	@Override
	public void addAfter(E element, E target) {
		// Search front of list
		LinearNode<E> current = front;
		
		// Check list until target is found
		while (current != null && !current.getElement().equals(target)) {
			current = current.getNext();
		}

		if (current == null) {
			throw new NoSuchElementException();
		}

		LinearNode<E> newNode = new LinearNode<>(element);

		// New node gains refrence to node after target
		newNode.setNext(current.getNext());
		current.setNext(newNode); // Target node connects new node

		// If inserted after rear, update
		if (current == rear) {
			rear = newNode;
		}

		count++;
		modCount++;
		
	}

	@Override
	public void add(int index, E element) {
		// Determine index is valid
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException();
		} 

		if (index == 0) {
			addToFront(element);
		} else if (index == count) {
			addToRear(element);
		} else {
			LinearNode<E> current = front;

			// Stop at node before insert index
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}

			LinearNode<E> newNode = new LinearNode<>(element);

			// Refrence previous node to next node
			newNode.setNext(current.getNext());
			// Refrence previous node to new node
			current.setNext(newNode);

			count++;
			modCount++;
		}
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();

		E result = remove(front.getElement());
		/* Goes to the remove method which takes an element
		filters to make sure the element is in the list
		Then moves the element and the node before it to the remove element
		which removes the element sets the rear and
		front nodes if needed  and modifies the counts */

		return result; //Returns the result element.
	}

	@Override
 	public E removeLast() {
		if (isEmpty()) throw new NoSuchElementException();

		E result = remove(rear.getElement());//See the above method to view logic

		return result;//Returns the result of the remove methods
	}

	@Override
	public E remove(E element) {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		LinearNode<E> current = front, previous = null;
		while (current != null && !current.getElement().equals(element)) {
			previous = current;
			current = current.getNext();
		}
		// Matching element not found
		if (current == null) {
			throw new NoSuchElementException();
		}
		return removeElement(previous, current);		
	}

	@Override
	public E remove(int index) {
		if(index > count - 1 || index < 0) throw new IndexOutOfBoundsException(); //Guard statement to make sure index is in bounds of list
		E result = null;// Setting result for be returned

		LinearNode<E> getter = front; //Makes a getter node to run down the list and get the wanted node

		for(int i = 0; i < index; i ++){// May have to fiddle with the logic of for loop upon testing.
			
			getter = getter.getNext();//Sifts through and gets the node at the correct index
		}
		result = remove(getter.getElement());//Logic for remove in removeFirst()

		return result; //Returns element
	}

	@Override
	public void set(int index, E element) {
		// Determine index within list
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		LinearNode<E> current = front;

		// Find index
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		// Set new element 
		current.setElement(element);
		modCount++;
	}

	@Override
	public E get(int index) {
		// Determine index within list
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		LinearNode<E> current = front;

		// Find index
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}

		// Returns element in index
		return current.getElement();
	}

	@Override
	public int indexOf(E element) {
		LinearNode<E> current = front;
		int index = 0;

		// Traverse each node
		while (current != null) {
			// Found element and returns index
			if (current.getElement().equals(element)) {
				return  index;
			}
			current = current.getNext();
			index++;
		}
		// Element was not found
		return -1;
	}

	@Override
	public E first() {
		if (isEmpty()) throw new NoSuchElementException();

		return front.getElement(); //returns linear node front
	}

	@Override
	public E last() {
		if (isEmpty()) throw new NoSuchElementException();
		
		return rear.getElement(); //returns linear node rear
	}

	@Override
	public boolean contains(E target) {
		// Return true if target exists in list
		return indexOf(target) != -1;

	}

	@Override
	public boolean isEmpty() {
		return count == 0;//Returns true if count =0
	}

	@Override
	public int size() {
		return count; //returns the count of items
	}

	@Override
	public String toString() {
		String result = "[";

		LinearNode<E> current = front;

		while (current != null) {
			result += current.getElement();

			if (current.getNext() != null) {
				result += ",";
			}
			current = current.getNext();
		}
		result += "]";
		return result;
	}

	private E removeElement(LinearNode<E> previous, LinearNode<E> current) {
		// Grab element
		E result = current.getElement();
		// If not the first element in the list
		if (previous != null) {
			previous.setNext(current.getNext());
		} else { // If the first element in the list
			front = current.getNext();
		}
		// If the last element in the list
		if (current.getNext() == null) {
			rear = previous;
		}
		count--;
		modCount++;

		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new SLLIterator();
	}

	/** Iterator for IUSingleLinkedList */
	private class SLLIterator implements Iterator<E> {
		private LinearNode<E> previous;
		private LinearNode<E> current;
		private LinearNode<E> next;
		private int iterModCount;
		private boolean canRemove;
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			canRemove = false;
			previous = null;
			current = null;
			next = front;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
			return next != null;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			previous = current;
			current = next;
			E result = current.getElement();
			next = next.getNext();
            canRemove = true;
            return result;
		}
		
		@Override
		public void remove() {
			if (iterModCount != modCount) throw new ConcurrentModificationException();
            if (!canRemove) throw new IllegalStateException();
			removeElement(previous, current);
			current = previous;
			iterModCount++;
            canRemove = false;
		}
	}

	// IGNORE THE FOLLOWING CODE
	// DON'T DELETE ME, HOWEVER!!!
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int startingIndex) {
		throw new UnsupportedOperationException();
	}
}
