package deque;

import org.junit.Test;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private Node<T> sentinel;
    private  int size;

    private class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;

        public Node(T x) {
            item = x;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node<T>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<T>(item);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<T>(item);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }


    @Override
    // take constant time
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }

        Node<T> pointer = sentinel;
        while (pointer.next != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.print(pointer.item + " ");
        System.out.println();
    }

    // use iteration
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> pointer = sentinel;
        for(int i = 0; i <= index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    public T getRecursive(int index, Node<T> node) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return node.next.item;
        } else {
            return getRecursive(index - 1, node.next);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int pos;
        private Node<T> currNode;

        public LinkedListIterator() {
            pos = 0;
            currNode = sentinel;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            pos += 1;
            currNode = currNode.next;
            return currNode.item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof LinkedListDeque)) {
            return false;
        } else if (o == this) {
            return true;
        }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if(other.size() != this.size()) {
            return false;
        }

        Iterator<T> otherIterator = other.iterator();
        Iterator<T> thisIterator = this.iterator();

        while(otherIterator.hasNext() && thisIterator.hasNext()) {
            if(thisIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        return true;
    }
}
