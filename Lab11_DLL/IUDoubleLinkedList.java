
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

       while (node != null || node.getElement().equals(target)) {
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
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public E removeLast() {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    /*=====ACCESS AND UPDATE METHODS=====*/

    @Override
    public void set(int index, E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
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
        // TODO: Implement
        throw new UnsupportedOperationException();
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