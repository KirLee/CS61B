package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }


    /* The first item (if it exists) is at sentinel.next. */
    private int size;
    private StuffNode sentinel;


    /** Creates an empty SLList. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, null,null);
        sentinel.next = new StuffNode(x, sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        sentinel.next = new StuffNode(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }
    /** Adds x to the end of the list. */
    public void addLast(T x) {
        size = size + 1;
        sentinel.prev = new StuffNode(x, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /** Returns true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }


    /** print the Deque*/
    public void printDeque() {
        StuffNode TempNode = sentinel.next;
        while (TempNode != sentinel) {
            System.out.print(TempNode.item);
            System.out.print(' ');
            TempNode = TempNode.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque, if no such item exits, return nulls*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ans = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return ans;
    }

    /** Removes and returns the item at the back of the deque, if no such item exits, return nulls*/
    public  T removeLast() {
        if (size == 0) {
            return null;
        }
        T ans = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return ans;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque*/
    public T get(int index) {
        if (index >= size){
            return null;
        }
        StuffNode temp = sentinel.next;
        while (index >0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.item;
    }
    /** Same as get, but uses recursion*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index - 1, sentinel.next);
    }
    private T getRecursiveHelper(int index, StuffNode Node) {
        if (index == 0) {
            return Node.item;
        }
        return getRecursiveHelper(index - 1, Node.next);
    }

    /**
     * The Deque objects is iterable
     */
    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class LLDIterator implements Iterator<T> {
        private StuffNode temp;

        public LLDIterator() {
            temp = sentinel.next;
        }

        public boolean hasNext() {
            return (temp != sentinel);
        }

        public T next() {
            T returnItem = (T) temp.item;
            temp = temp.next;
            return returnItem;
        }
    }

    /** Returns whether or not the parameter o is equal to the Deque.*/
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
        LinkedListDeque<T> o = (LinkedListDeque<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        StuffNode temp = o.sentinel.next;
        for (T item : this) {
            if (this != temp.item) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }
    /** Returns the first item in the list. */
    /**
    public int getFirst() {
        return sentinel.next.item;
    }*/







    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        LinkedListDeque<Integer> L = new LinkedListDeque(50);
        LinkedListDeque<Integer> M = new LinkedListDeque(50);
        M.addFirst(30);
        M.addFirst(20);
        L.addFirst(20);
        L.addLast(30);
        L.getRecursive(2);
        System.out.print(L.get(2));
        for (int i: L) {
            System.out.print(i);
        }
        System.out.print(L.equals(L));
        System.out.print(L.equals(M));
        //L.removeFirst();
        //L.removeLast();
        //L.printDeque();


    }
}
