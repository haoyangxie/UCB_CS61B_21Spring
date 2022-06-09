package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private int capacity;
    private int size;
    private T[] items;
    private int first;
    private int last;

    public ArrayDeque() {
        capacity = 8;
        size = 0;
        items = (T[])new Object[capacity];
    }

    private  boolean isFull() {
        return size == capacity;
    }

    private void resize(double factor) {
        int newCapacity = (int)(capacity * factor);
        T[] newItems = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, newItems, 0, size);
        capacity = newCapacity;
        items = newItems;
    }

    @Override
    // take constant time
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(2);
        }

        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (first == 0) {
            first = capacity - 1;
        } else {
            first -= 1;
        }
        items[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(2);
        }
        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if(last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
        items[last] = item;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if ((size-1) < 0.25*capacity && capacity > 8) {
            resize(0.5);
        }

        T delItem = items[first];
        items[first] = null;
        size -= 1;

        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return delItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if ((size-1) < 0.25*capacity && capacity > 8) {
            resize(0.5);
        }

        T delItem = items[last];
        items[last] = null;
        size -= 1;

        if(last == 0) {
            last = capacity - 1;
        } else {
            last -= 1;
        }
        return delItem;
    }

    @Override
    public void printDeque() {
        int firstPointer = first;
        int lastPointer = last;

        while(firstPointer != lastPointer) {
            System.out.print(items[first] + " ");
            if (firstPointer == capacity - 1) {
                firstPointer = 0;
            } else {
                firstPointer += 1;
            }
        }
        System.out.print(items[firstPointer]);
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int pos = (first + index) % capacity;
        return items[pos];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T result = items[(first+pos)%capacity];
            pos ++;
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof ArrayDeque)) {
            return false;
        } else if (o == this) {
            return true;
        }

        ArrayDeque<T> other = (ArrayDeque<T>) o;
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
