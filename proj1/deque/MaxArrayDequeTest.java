package deque;

import org.junit.Test;


import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    public static class intComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }

    @Test
    public void testInt() {
        MaxArrayDeque<Integer> test = new MaxArrayDeque<Integer>(new intComparator());
        for(int i = 0; i < 10; i ++) {
            test.addFirst(i);
        }
        assertEquals(9, test.max().intValue());
    }

}
