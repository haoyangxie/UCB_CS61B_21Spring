package deque;

import net.sf.saxon.functions.ConstantFunction;

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

//    public Iterator<T> iterator() {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (this.get(i) != ((LinkedListDeque<?>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addLast(3);
        test.addLast(4);
        test.addFirst(5);
        test.printDeque();
        System.out.println(test.getRecursive(0, test.sentinel));
    }
}
