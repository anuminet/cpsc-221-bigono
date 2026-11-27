
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<E> implements IndexedUnsortedList<E> {

    /*=====FIELDS=====*/

    private BidirectionalNode<E> head;
    private BidirectionalNode<E> tail;
    private int count;
    private int modCount;

    /*=====CONSTRUCTOR=====*/

    public IUDoubleLinkedList() {
        head = null;
        tail = null;
        count = 0;
        modCount = 0;
    }

    /*=====INSERTION METHODS=====*/

    @Override
    public void addToFront(E element) {

        // New node to store Element
        BidirectionalNode<E> node = new BidirectionalNode<E>(element);

        // If the list is empty, set both head & tail to the new node
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            // Connect new node to current head
            node.setNext(head);
            head.setPrevious(node);
            // Set new node as the head
            head = node;
        }
        // Increment the element & modification counts
        count++;
        modCount++;
    }

    @Override
    public void addToRear(E element) {

        // New node to store Element
        BidirectionalNode<E> node = new BidirectionalNode<E>(element);

        // If the list is empty, set both head & tail to the new node
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            // Connect the new node to the current tail
            tail.setNext(node);
            node.setPrevious(tail);
            // Set the new node as the tail
            tail = node;
        }
        // Increment the element & modification counts
        count++;
        modCount++;
    }

    @Override
    public void add(E element) {
        addToRear(element);
        //Should just be easiest to use the created method
    }

    @Override
    public void addAfter(E element, E target) {
       BidirectionalNode<E> node = head;
       BidirectionalNode<E> inserter = new BidirectionalNode<E>(element);
       //Creates two nodes for use in method

       while (node != null && !node.getElement().equals(target)) {
            node = node.getNext();
       }
       //Goes through list to find element
       if(node == null){
        throw new NoSuchElementException();
       }
       // If the target is the tail, add to the rear
       if (node == tail) {
        addToRear(element);
        return;
       }
       //If element doesnt exist throws exception
       inserter.setNext(node.getNext());
       inserter.setPrevious(node);
       node.getNext().setPrevious(inserter);
       node.setNext(inserter);
       //Sets in order all directions of nodes to their proper nexts and previouses in the proper order
       count++;
       modCount++;
       //Increase counts

    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > count) throw new IndexOutOfBoundsException();
        //Catches if index provided is out of bounds of the list
        if(index == 0){
            addToFront(element);
            return;
        //if the index is zero it's essentially adding to the front
        }else if (index == count){
            addToRear(element);
            return;
        //catches if the index is the end in which case we can use the add to rear
        }
        BidirectionalNode<E> node = head;
        BidirectionalNode<E> inserter = new BidirectionalNode<E>(element);
        //Creates our nodes that we'll use for moving around
        for(int i = 0; i < index; i++){
            node = node.getNext();
            //Traverses the list
        }
         // Insert the new node before the node at the specified index
        inserter.setNext(node);
        inserter.setPrevious(node.getPrevious());
        node.getPrevious().setNext(inserter);
        node.setPrevious(inserter);
       //Sets in order all directions of nodes to their proper nexts and previouses in the proper order
        count++;
        modCount++;
       //Increase counts

    }

    /*=====REMOVAL METHODS=====*/

    @Override
    public E removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        //Sends exception if list is empty
        E result = head.getElement();
        //Stores the element to be returned

        // If there is only 1 element, reset both head & tail
        if (count == 1) {
            head = null;
            tail = null;
        } else {
            // Move head to the next node
            head = head.getNext();
            // Remove connection to previous node
            head.setPrevious(null);
        }
        // Update the element & modification counts
        count--;
        modCount++;
        return result;
    }

    @Override
    public E removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        //Sends exception if the list is empty
        E result = tail.getElement();

        // If there is only 1 element, reset both head & tail
        if (count == 1) {
            head = null;
            tail = null;
        } else {
            // Move tail to previous node
            tail = tail.getPrevious();
            // Remove connection to next node
            tail.setNext(null);
        }
        // Update the element & modification counts
        count--;
        modCount++;
        return result;
    }

    @Override
    public E remove(E element) {
        if(isEmpty()) throw new NoSuchElementException();
            //throws exception if there are no elements in the list
        BidirectionalNode<E> sifter = head;
            //Creates node to go through list
        while(sifter != null && !sifter.getElement().equals(element)){
            sifter = sifter.getNext();
        }
            //Goes through list until we either get to the end of the list or find the target element
        if(sifter == null){
            throw new NoSuchElementException();
        }

        // Handle removal of the head or tail element
        if (sifter == head) {
            return removeFirst();
        } else if (sifter == tail) {
            return removeLast();
        }

        // Store element to be returned
        E result = sifter.getElement();

        // Connect surrounding nodes through sifter
        sifter.getPrevious().setNext(sifter.getNext());
        sifter.getNext().setPrevious(sifter.getPrevious());

        // Update the element & modification counts
        count--;
        modCount++;
        return result;


    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= count) throw new IndexOutOfBoundsException();
            //Throws exception if index is out of bounds of the list
        if (index == count -1){
            return removeLast();
        }
        if (index == 0){
            return removeFirst();
        }
            //If the index is at beginning or end of list uses those methods to avoid loop if possible
        BidirectionalNode<E> sifter = head;
        for(int i = 0; i < index; i++){
            sifter = sifter.getNext();
        }
            //Sifts through list to find element at the correct index
        E result = sifter.getElement();
        sifter.getPrevious().setNext(sifter.getNext());
        sifter.getNext().setPrevious(sifter.getPrevious());
            //Sets the element to result and removes the element by attaching the surrounding nodes
            //to the correct nodes
        count--;
        modCount++;
            //Adjust counts and return result
        return result;
    }

    /*=====ACCESS AND UPDATE METHODS=====*/

    @Override
    public void set(int index, E element) {
        if(index < 0 || index >= count) throw new IndexOutOfBoundsException();
        //Catches if index given is outside of the bounds of the list
        BidirectionalNode<E> sifter = head;
        //Creates node to sift through the list
        for(int i = 0; i < index; i++){
            sifter = sifter.getNext();
        }
        //Goes through list until we get to the index
        sifter.setElement(element);
        //sets the element
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= count) throw new IndexOutOfBoundsException();
        
        BidirectionalNode<E> sifter = head;
       //Creates a node to sift through the list
        for (int i = 0; i < index; i++){
        sifter = sifter.getNext();
        }
       //Sifts through till we get to the index
        return sifter.getElement();
       //returns the sifter element which is the element 
    }

    @Override
    public int indexOf(E element) {

        // New node to traverse the list
        BidirectionalNode<E> sifter = head;
        int index = 0;

        // Traverse the list until the element is found
        while(sifter != null && !sifter.getElement().equals(element)){
            sifter = sifter.getNext();
            index++;
        }
        
        // Return -1 if the element is not found
        if(sifter == null){
            return -1;
        }
        // Return the index of the element
        return index;
    }

    @Override
    public E first() {
        if(isEmpty()) throw new NoSuchElementException();
        return head.getElement();
    }

    @Override
    public E last() {
        if(isEmpty()) throw new NoSuchElementException();
        return tail.getElement();
    }

    /*=====UTILITY METHODS=====*/

    @Override
    public boolean contains(E target) {
        
        // Return true if the target exists in the list
        int result = indexOf(target);

        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {

        // Create empty string to store elements
        String result = "";

        // Helper node to traverse list
        BidirectionalNode<E> sifter = head;

        // Traverse the list and link/attach each element
        while (sifter != null) {
            result += sifter.getElement();
            sifter = sifter.getNext();
        }
        return result;
    }

    /*=====ITERATOR METHODS=====*/

    @Override
    public Iterator<E> iterator() {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DoubleLinkedListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int startingIndex) {
        return new DoubleLinkedListIterator(startingIndex);
    }

    /*=====LIST ITERATOR IMPLEMENTATION=====*/

    private class DoubleLinkedListIterator implements ListIterator<E> {

        /*=====ITERATOR FIELDS=====*/

        private BidirectionalNode<E> nextNode;
        private BidirectionalNode<E> lastReturned;
        private int nextIndex;
        private int expectedModCount;

        /*=====ITERATOR CONSTRUCTOR=====*/

        public DoubleLinkedListIterator(int startingIndex) {
            // Validate Starting Index
            if (startingIndex < 0 || startingIndex > count) {
                throw new IndexOutOfBoundsException();
            }

            // Find the node at the starting position
            nextNode = head;

            for (int i = 0; i < startingIndex; i++) {
                nextNode = nextNode.getNext();
            }

            // Initalize remaining fields
            nextIndex = startingIndex;
            lastReturned = null;
            expectedModCount = modCount;
        }

        /*=====FORWARD TRAVERSAL=====*/

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            // Checks list wasn't modified outside iterator
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            
            // Determines another element exists
            if (!hasNext()) {
                throw new NoSuchElementException();
            } 
            
            // Saves node being returned
            lastReturned = nextNode;
            // Moves to the next node
            nextNode = nextNode.getNext();
            // Updates cursor position
            nextIndex++;

            return lastReturned.getElement();
        }

        /*=====BACKWARD TRAVERSAL=====*/

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            // Checks for modifications outside of iterator
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            // Determines previous element
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            // Moves backwards through the list
            if (nextNode == null) {
                nextNode = tail;
            } else {
                nextNode = nextNode.getPrevious();
            }

            // Saves node being returned
            lastReturned = nextNode;
            // Updates cursor position
            nextIndex--;
            
            return lastReturned.getElement();
        }

        /*=====CURSOR POSITION=====*/

        @Override
        public int nextIndex() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        /*=====ITERATOR MODIFICATION METHODS=====*/

        @Override
        public void add(E element) {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E element) {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }
    }
}