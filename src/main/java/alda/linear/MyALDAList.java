package alda.linear;

import java.util.Iterator;

public class MyALDAList<E> implements ALDAList<E> {

    // --------------- The Node class -----------------------------------------
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }


    // --------------- MyADLAList class ---------------------------------------

    // --------------- Variables ----------------------------------------------

    private Node<E> first;
    private Node<E> last;

    // --------------- Methods ------------------------------------------------


    @Override
    public Iterator<E> iterator() {
        return new MyALDAListIterator<>();
    }

    @Override
    public void add(E element) {
            addToBack(element);

    }

    @Override
    public void add(int index, E element) {

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size()) {
            addToBack(element);
            return;
        }

        if (index == 0) {
            addToFront(element);
            return;

        } else {

            addToMiddle(index, element);

        }
    }

    private void addFirstElement(E element) {
        first = new Node<>(element);
        last = first;
    }

    private void addToFront(E element) {
        if (first == null) {
            addFirstElement(element);

        } else {

            Node<E> newNode = new Node<>(element);
            newNode.next = first;
            first = newNode;
        }
    }

    private void addToMiddle(int index, E element) {
        Node<E> newNode = new Node<>(element);
        Node<E> tempNode = first;

        for (int i = 1; i < index; i++) {
            tempNode = tempNode.next;
        }

        newNode.next = tempNode.next;
        tempNode.next = newNode;
    }

    private void addToBack(E element) {
        if (first == null) {
            addFirstElement(element);

        } else {
        last.next = new Node<>(element);
        last = last.next;

        }
    }


    @Override
    public E remove(int index) {

        Node<E> tempNode = first;
        E oldData;

        if (index < 0 || index >= size() || first == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {

            if (size() == 1) {
                clear();
                return tempNode.data;

            } else {
                first = first.next;
                return tempNode.data;
            }

        } else {

            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }

            oldData = tempNode.next.data;

            if (tempNode.next == last) {
                tempNode.next = null;
                last = tempNode;

            } else {
                tempNode.next = tempNode.next.next;
            }

            return oldData;
        }
    }

    @Override
    public boolean remove(E element) {
        if (size() == 0) {
            return false;
        }

        // If the element is the first one
        if (first.data == element || first.data.equals(element)) {
            remove(0); //We call remove with index0
            return true;
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

        return getNode(index).data;
    }

    private Node<E> getNode(int index) {

        if (index < 0 || index >= size() || first == null) {
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
    public boolean contains(E element) {

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return true;
            }
        }

        // Return false if not found
        return false;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;

        for (Node<E> temp = first; temp != null; temp = temp.next) {
            if (temp.data == element || temp.data.equals(element)) {
                return index;

            } else {
                index++;
            }
        }

        //Return -1 if not found
        return -1;

    }

    @Override
    public void clear() {
        first = null;
        last = null;
    }


    @Override
    public int size() {
        int size = 0;
        for (Node<E> temp = first; temp != null; temp = temp.next) {
            size++;

        }
        return size;
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


    // ------------ Iterator class --------------------------------------------

    private class MyALDAListIterator<T> implements Iterator<E> {

        Node<E> current = first;
        private Node<E> nextNode = first;
        private boolean okToRemove = false;

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
