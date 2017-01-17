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

    private Node<E> getNode(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> tempNode = first;

        if (index == 0) {
            return first;
        }

        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }

        return tempNode;

    }


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

        if (index == size()) {
            add(element);
        } else {

            Node<E> newNode = new Node<>(element);
            Node<E> tempNode = first;

            if (index == 0) {
                newNode.next = first;
                first = newNode;
                size++;
            } else {


                for (int i = 1; i < index; i++) {
                    tempNode = tempNode.next;
                }

                newNode.next = tempNode.next;
                tempNode.next = newNode;
                size++;
            }
        }

    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (first == null) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> tempNode = first;

        // om index är 0
        if (index == 0) {
            // om
            if (first == last) {
                first = null;
                last = null;
                size--;
                return tempNode.data;
            } else {

                first = first.next;
                size--;
                return tempNode.data;
            }
        }

        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }

        E oldData = tempNode.next.data;

        if (tempNode.next == last) {
            tempNode.next = null;
            last = tempNode;
            size--;
        } else {

            tempNode.next = tempNode.next.next;
            size--;
        }

        return oldData;

    }

    @Override
    public boolean remove(E element) {
        if (first.data == element || first.data.equals(element)) {
            if (first.next != null) {
                first = first.next;
                return true;
            } else {
                clear();
                return true;
            }
        }

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (temp.next != null) {
                if (temp.next.data == element || temp.next.data.equals(element)) {
                    if (temp.next.next != null) {
                        temp.next = temp.next.next;
                        return true;
                    } else {
                        temp.next = null;
                        last = temp;
                        return true;

                    }

                }


            }
        }

        return false;
    }

    @Override
    public E get(int index) {
        if (first == null) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    @Override
    public boolean contains(E element) {

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return index;
            }
            index++;
        }

        return -1;

    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;

    }


    @Override
    public int size() {
        int siz = 0;
        for (Node<E> temp = first; temp != null; temp = temp.next) {
            siz++;

        }
        return siz;
    }

    @Override
    public String toString() {
        Iterator<E> iter = iterator();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (iter.hasNext()) {
            builder.append(iter.next().toString());
            if (iter.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append("]");
        return builder.toString();
    }

    private class MyALDAListIterator<T> implements Iterator<E> {

        Node<E> current = first;
        private Node<E> nextNode = first;
        private boolean okToRemove =false;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (hasNext()) {

                current = nextNode;
                nextNode = nextNode.next;
                okToRemove = true;
                return current.data;

            } else throw new java.util.NoSuchElementException();
        }

        @Override
        public void remove() {
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyALDAList.this.remove(current.data);
            okToRemove = false;

        }
    }
}
