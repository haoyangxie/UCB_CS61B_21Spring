package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{

    private Node<T> sentinel;
    private int size;

    /** Deque implementation using linked list
     * Make it circular
     */
    private class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(T x) {
            data = x;
        }
    }

    /* Constructor, creates an empty linked list deque */
    public LinkedListDeque() {
        sentinel = new Node<T>(null);  // Not sure for now...
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* Should take constant time */
    @Override
    public int size() {
        return size;
    }

    /** Add item at the first of deque
     * The add method should not use any looping or recursion
     * such operation should take constant amount of time, not depend on size
     */
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
        T first = sentinel.next.data;
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
        T last = sentinel.prev.data;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return last;
    }

    /* Print all items in deque */
    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }

        // currentNode points at sentinel
        Node<T> currentNode = sentinel;
        System.out.print("{");

        // Iterate over the queue
        while (currentNode.next.next != sentinel) {
            currentNode = currentNode.next;
            System.out.print(currentNode.data + " ");
        }
        currentNode = currentNode.next;
        System.out.print(currentNode.data + "}");
        System.out.println();
    }

    /** Get the item at the specified index
     * Must use iteration, not recursion
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        Node<T> currentNode = sentinel;
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /* Same as get(), but use recursion */
    public T getRecursive(int index, Node<T> node) {
        if (index < 0 || index > size) {
            return null;
        }
        if (index == 1) {
            return node.data;
        }
        return getRecursive(index - 1, node.next);
    }

    /** Build the iterator class for linked list deque
     * Class method: hasNext(), next()
     */
    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;
        private Node<T> currentNode;

        public LinkedListDequeIterator() {
            position = 0;
            currentNode = sentinel;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            position += 1;
            currentNode = currentNode.next;
            return currentNode.data;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();  // Why need a new keyword?
    }

    /* Comparison using class method */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof LinkedListDeque)) {
            return false;
        } else if (o == this) {
            return true;
        }

        // Casting
        LinkedListDeque<T> otherObject = (LinkedListDeque<T>) o;
        if (otherObject.size() != size()) {
            return false;
        }

        Iterator<T> iteratorOther = otherObject.iterator();
        Iterator<T> iteratorThis = iterator();
        while (iteratorOther.hasNext() && iteratorThis.hasNext()) {
            if (iteratorOther.next() != iteratorThis.next()) {
                return false;
            }
        }

        // All tests passed, return true
        return true;
    }
}
