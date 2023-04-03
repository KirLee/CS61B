package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item> {

    public int size;
    public Item[] items = (Item[]) new Object[8];
    public int NextFirst;
    public int NextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        NextFirst = 0;
        NextLast = 1;
    }

    public ArrayDeque(Item item) {
        items[1] = item;
        size += 1;
        NextFirst = 0;
        NextLast += 2;
    }

    /** Adds an item of type T to the front of the deque*/
    private int FirstHelper(int index) {
        return (index - 1 + items.length) % items.length;
    }
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[NextFirst] = item;
        NextFirst = FirstHelper(NextFirst);
        size += 1;
    }

    /** dds an item of type T to the back of the deque*/
    private int LastHelper(int index) {
        return (index + 1 + items.length) % items.length;
    }
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[NextLast] = item;
        NextLast = LastHelper(NextLast);
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** print the Deque*/
    public void printDeque() {
        int PrintIndex = LastHelper(NextFirst);
        int num = size;
        while (num > 0) {
            System.out.print(items[PrintIndex]);
            System.out.print(' ');
            num -= 1;
            PrintIndex = LastHelper(PrintIndex);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque, if no such item exits, return nulls*/
    private void CheckDiv() {
        if (items.length > 16 && size < items.length/4) {
            resize(items.length/4);
        }
    }
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        CheckDiv();
        NextFirst = LastHelper(NextFirst);
        Item ans = items[NextFirst];
        items[NextFirst] = null;
        size -= 1;
        return ans;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        CheckDiv();
        NextLast = FirstHelper(NextLast);
        Item ans = items[NextLast];
        items[NextLast] = null;
        size -= 1;
        return ans;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque*/
    public Item get(int index) {
        if (index >= size){
            return null;
        }
        int ActualIndex = (index + LastHelper(NextFirst)) % items.length;
        return items[ActualIndex];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int tempSize = size;
        private int tempIndex;

        public ArrayIterator() {
            tempIndex = LastHelper(NextFirst);
        }

        public boolean hasNext() {
            return (tempSize > 0);
        }

        public Item next() {
            Item returnItem = items[tempIndex];
            tempIndex = LastHelper(tempIndex);
            tempSize -= 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<Item> o = (ArrayDeque<Item>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != o.get(i)) {
                return false;
            }
        }
        return true;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, LastHelper(NextFirst), a, 0, size - LastHelper(NextFirst));
        System.arraycopy(items, 0, a, size - LastHelper(NextFirst), NextLast);
        items = a;
        NextFirst = FirstHelper(0);
        NextLast = size;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>(10);
        test.addFirst(5);
        test.addFirst(15);
        test.addLast(20);
        System.out.print(test.get(3));
        for (int i: test) {
            System.out.print(i);
        }
        ArrayDeque<Integer> temp = new ArrayDeque<Integer>(10);
        temp.addFirst(5);
        temp.addFirst(15);
        temp.addLast(20);
        System.out.print(test.equals(temp));
        //test.removeFirst();
        //test.removeLast();
        //test.printDeque();

    }

}
