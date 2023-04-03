package deque;

import net.sf.saxon.functions.PositionAndLast;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item>{
    private Comparator cmp;

    /** creates a MaxArrayDeque with the given Comparator */
    public MaxArrayDeque (Comparator<Item> c) {
        size = 0;
        NextFirst = 0;
        NextLast = 1;
        this.cmp = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
      If the MaxArrayDeque is empty, simply return null.*/
    public Item max() {
        if (size == 0) {
            return null;
        }
        int tempIndex = LastHelper(NextFirst);
        Item max = items[tempIndex];
        for (int i = 0; i < size; i++) {
            if (cmp.compare(items[tempIndex], max) > 0) {
                max = items[tempIndex];
            }
            tempIndex = LastHelper(tempIndex);
        }
        return max;
    }


    /** returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.*/

    public Item max(Comparator<Item> c) {
        if (size == 0) {
            return null;
        }
        int tempIndex = LastHelper(NextFirst);
        Item max = items[tempIndex];
        for (int i = 0; i < size; i++) {
            if (c.compare(items[tempIndex], max) > 0) {
                max = items[tempIndex];
            }
            tempIndex = LastHelper(tempIndex);
        }
        return max;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>(10);
        test.addFirst(5);
        test.addFirst(15);
        test.addLast(20);
        MaxArrayDeque<Integer> tmp = new MaxArrayDeque<Integer>((Comparator<Integer>) test);
        System.out.print(tmp);


    }


}
