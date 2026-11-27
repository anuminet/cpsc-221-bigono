
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
        BidirectionalNode<E> node = new BidirectionalNode<E>(element);
        //New node to store Element
        node.setNext(head);
        head.setPrevious(node);
        // Grabs the next node as to not lose the list
        head = node;
        //Sets the new head shouldnt need to change tail
        if(isEmpty()) tail = node;
        //If its a empty list the tail must become the head
        count++;
        modCount++;
        //Changes both count and modcount
    }

    @Override
    public void addToRear(E element) {
        BidirectionalNode<E> node = new BidirectionalNode<E>(element);
        //New node to store Element
        tail.setNext(node);
        node.setPrevious(tail);
        //Makes sure to attach node 
        tail = node;
        //sets node as rear
        if(isEmpty()) head = node;
        //if empty sets the head as well
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
       BidirectionalNode<E> node = head;//
       BidirectionalNode<E> inserter = new BidirectionalNode<E>(element);
       //Creates two nodes for use in method

       while (node != null && node.getElement().equals(target)) {
            node = node.getNext();
       }
       //Goes through list to find element
       if(node.getElement() == null){
        throw new NoSuchElementException();
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
        if(index > count || index > count) throw new IndexOutOfBoundsException();
        //Catches if index provided is out of bounds of the list
        if(index == 0){
            addToFront(element);
        //if the index is zero it's essentially adding to the front
        }else if (index == count){
            addToRear(element);
        //catches if the index is the end in which case we can use the add to rear
        }
        BidirectionalNode<E> node = head;
        BidirectionalNode<E> inserter = new BidirectionalNode<E>(element);
        //Creates our nodes that we'll use for moving around
        for(int i = 0; i < index; i++){
            node = node.getNext();
            //Traverses the list
        }
         //Copied from the above addAfter method in theory it should be the same
        inserter.setNext(node.getNext());
        inserter.setPrevious(node);
        node.getNext().setPrevious(inserter);
        node.setNext(inserter);
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
        head = head.getNext();
        head.setPrevious(null);
        //Sets new head and removes its previous essentially removing the element
        count--;
        modCount++;
        //Changes counts
        return result;
    }

    @Override
    public E removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        //Sends exception if the list is empty
        E result = tail.getElement();
        //Stores the element to be returned
        tail = tail.getPrevious();
        tail.setNext(null);
        //Sets new tail and removes its next to null essentially removing the element
        count--;
        modCount++;
        //Changes counts
        return result;
    }

    @Override
    public E remove(E element) {
        if(isEmpty()) throw new NoSuchElementException();
            //throws exception if there are no elements in the list
        BidirectionalNode<E> sifter = head;
            //Creates node to go through list
        while(!sifter.getElement().equals(sifter) && sifter != null){
            sifter = sifter.getNext();
        }
            //Goes through list until we either get to the end of the list or find the target element
        if(sifter == null){
            throw new NoSuchElementException();
        }
            //Throws exception if the element doesnt exist
        E result = sifter.getElement();
        sifter.getPrevious().setNext(sifter.getNext());
        sifter.getNext().setPrevious(sifter.getPrevious());
            //Makes a result to keep element and removes the element from the list
        count--;
        modCount++;
            //Changes counts and returns the object
        return result;


    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= count) throw new NoSuchElementException();
            //Throws exception if index is out of bounds of the list
        if(index == count -1){
            return removeLast();
        }
        if(index == 0){
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
        if(index > 0 || index >= count) throw new IndexOutOfBoundsException();
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
        if(isEmpty()) throw new NoSuchElementException();
        //Catches aned throws exception for empty lists
        BidirectionalNode<E> sifter = head;
        int index = 0;
        //Creates node to sort through list
        while(!sifter.getElement().equals(element) && sifter != null){
            sifter = sifter.getNext();
            index++;
        }
        //Goes through list and stops once element is found or reaches end of list which would be null
        //and increases the index each time
        if(sifter == null){
            return -1;
            //Returns if the element is null meaning its not found
        }
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
        if(isEmpty()) throw new NoSuchElementException();
        //Catches aned throws exception for empty lists
        BidirectionalNode<E> sifter = head;
        //Creates node to sort through list
        while(!sifter.getElement().equals(target) && sifter != null){
            sifter = sifter.getNext();
        }
        //Goes through list and stops once element is found or reaches end of list which would be null
        if(sifter.getElement().equals(target)){
        return true;
        }
        return false;
        //Returns true if the sifter is the target that we are looking for false if null
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
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
            // TODO: Validate startingIndex
            // TODO: Locate the node at startingIndex
            // TODO: Initialize cursor and modification tracking

            throw new UnsupportedOperationException();
        }

        /*=====FORWARD TRAVERSAL=====*/

        @Override
        public boolean hasNext() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        /*=====BACKWARD TRAVERSAL=====*/

        @Override
        public boolean hasPrevious() {
            // TODO: Implement
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            // TODO: Implement
            throw new UnsupportedOperationException();
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