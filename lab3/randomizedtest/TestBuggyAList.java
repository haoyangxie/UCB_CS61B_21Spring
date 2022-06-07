package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> alno = new AListNoResizing<>();
        BuggyAList<Integer> bugAl = new BuggyAList<>();
        for(int i = 4; i < 7; i++) {
            alno.addLast(i);
            bugAl.addLast(i);
        }
        assertEquals(alno.size(), bugAl.size());
        for(int i = 0; i < 3; i++) {
            assertEquals(alno.removeLast(), bugAl.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeBL = BL.size();
                assertEquals(size, sizeBL);
            } else if (L.size() > 0 && operationNumber == 2) {
                assertEquals(L.getLast(), BL.getLast());
            } else if (L.size() > 0 && operationNumber == 3) {
                assertEquals(L.removeLast(), BL.removeLast());
            }
        }
    }
}
