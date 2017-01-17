package alda.linear;

import java.util.Iterator;

public class MyALDAList<E> implements ALDAList<E> {

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    @Override
    public Iterator<E> iterator() {
        return new MyALDAListIterator<>();
    }


    public void add(E element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
            size++;

        } else {
            last.next = new Node<>(element);
            last = last.next;
            size++;
        }

    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> newNode = new Node<>(element);
        Node<E> tempNode = first;


        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }

        newNode.next = tempNode.next;
        tempNode.next = newNode;
        size++;



    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
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

        for(Node<E> temp=first; temp!=null; temp=temp.next) {
            if(temp.data== element || temp.data.equals(element)) {
                return true;
            }
        }
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


    private class MyALDAListIterator<E> implements Iterator<E> {

        private Node<E> nextNode = (Node<E>) first;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (hasNext()) {

                Node<E> temp = nextNode;
                nextNode = nextNode.next;
                return temp.data;

            } else throw new java.util.NoSuchElementException();
        }

        @Override
        public void remove() {

        }
    }
}
