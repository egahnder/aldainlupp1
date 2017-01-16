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

    private Node<E> first;
    private Node<E> last;

    @Override
    public Iterator<E> iterator() {
        return null;
    }


    public void add(E element) {
        if (first == null) {
            first = new Node<>(element);
            last = first;
        } else {
            last.next = new Node<>(element);
            last = last.next;

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
        return 0;
    }


}
