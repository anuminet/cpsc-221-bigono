
import java.util.Iterator;
import java.util.ListIterator;

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
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void addToRear(E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAfter(E element, E target) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        // TODO: Implement
        throw new UnsupportedOperationException();
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
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public E last() {
        // TODO: Implement
        throw new UnsupportedOperationException();
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