package deque;

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
        
    }


}
