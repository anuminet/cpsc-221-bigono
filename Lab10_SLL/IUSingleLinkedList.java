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
		// TODO 
		
	}

	@Override
	public void addToRear(E element) {
		// TODO 
		
	}

	@Override
	public void add(E element) {
		// TODO 
		
	}

	@Override
	public void addAfter(E element, E target) {
		// TODO 
		
	}

	@Override
	public void add(int index, E element) {
		// TODO 
		
	}

	@Override
	public E removeFirst() {
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
		if(index > count - 1 || index < 0) throw new NoSuchElementException(); //Guard statement to make sure index is in bounds of list
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
		// TODO 
		
	}

	@Override
	public E get(int index) {
		// TODO 
		return null;
	}

	@Override
	public int indexOf(E element) {
		// TODO 
		return 0;
	}

	@Override
	public E first() {
		return front.getElement(); //returns linear node front
	}

	@Override
	public E last() {
		return rear.getElement(); //returns linear node rear
	}

	@Override
	public boolean contains(E target) {
		// TODO 
		return false;
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
		// TODO
		return "";
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
		
		/** Creates a new iterator for the list */
		public SLLIterator() {
			previous = null;
			current = null;
			next = front;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			// TODO 
			return false;
		}

		@Override
		public E next() {
			// TODO 
			return null;
		}
		
		@Override
		public void remove() {
			// TODO
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
