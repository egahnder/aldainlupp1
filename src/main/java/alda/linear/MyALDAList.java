package alda.linear;

import java.util.Iterator;

public class MyALDAList<E> implements ALDAList<E> {

    private static class Node<E> {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
        }
    }

    private class MyALDAListIterator<E> implements java.util.Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            } else {
                return next();
            }
        }

        @Override
        public void remove() {

        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return null;
    }


    public void add(E element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
            size = 1;
        } else {
            last.next = new Node<>(element);
            last = last.next;
            size +=1;

        }

    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return size;
    }

    private Node next() {
        return Node.next;
    }


}
