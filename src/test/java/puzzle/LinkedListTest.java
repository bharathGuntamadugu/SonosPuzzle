package puzzle;


import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by Bharath on 17-Apr-17.
 */

public class LinkedListTest {

    @Test
    public void testListBuildSuccess() {
        LinkedList generated = LinkedList.listBuild(new int[]{1, 2, 3, 4});
        assertEquals("1 -> 2 -> 3 -> 4 -> ", generated.toString());
    }

    @Test
    public void testEmptyListBuild() {
        LinkedList generated = LinkedList.listBuild(new int[]{});
        assertEquals(null, generated);
    }

    @Test
    public void testGivenSample() {
        LinkedList in = LinkedList.listBuild(new int[]{6, 2, 3, 1, 4, 1, 9});
        LinkedList expected = LinkedList.listBuild(new int[]{3, 1, 1, 9, 6, 2, 4});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testEvenNumberList() {
        LinkedList in = LinkedList.listBuild(new int[]{2});
        LinkedList expected = LinkedList.listBuild(new int[]{2});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testOddNumberList() {
        LinkedList in = LinkedList.listBuild(new int[]{1});
        LinkedList expected = LinkedList.listBuild(new int[]{1});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testEvenNumbersList() {
        LinkedList in = LinkedList.listBuild(new int[]{0,2,-6,-8,66,88,224});
        LinkedList expected = LinkedList.listBuild(new int[]{0,2,-6,-8,66,88,224});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testOddNumbersList() {
        LinkedList in = LinkedList.listBuild(new int[]{-9,-55,5,1,19,7});
        LinkedList expected = LinkedList.listBuild(new int[]{-9,-55,5,1,19,7});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testSequenceList() {
        LinkedList in = LinkedList.listBuild(new int[]{0,1,2,3,4,5,6,7,8,9,10});
        LinkedList expected = LinkedList.listBuild(new int[]{1,3,5,7,9,0,2,4,6,8,10});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testRandomList() {
        LinkedList in = LinkedList.listBuild(new int[]{6,-5,6,9,7,22,-59,-26,99,44});
        LinkedList expected = LinkedList.listBuild(new int[]{-5,9,7,-59,99,6,6,22,-26,44});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testRandomList2() {
        LinkedList in = LinkedList.listBuild(new int[]{-9,-7,-5,2,11,16,58,99,100});
        LinkedList expected = LinkedList.listBuild(new int[]{-9,-7,-5,11,99,2,16,58,100});
        assertEquals(expected, in.moveEvensToEnd());
    }

    @Test
    public void testEqualsSuccess() {
        LinkedList one = LinkedList.listBuild(new int[]{1,2,3,4,5});
        LinkedList two = LinkedList.listBuild(new int[]{1,2,3,4,5});

        assertEquals(true, one.equals(two));
    }

    @Test
    public void testEqualsFailure() {
        LinkedList one = LinkedList.listBuild(new int[]{1,2,3,4,5});
        LinkedList two = LinkedList.listBuild(new int[]{1,2,3,4,5,6});

        assertEquals(false, one.equals(two));
    }

    @Test
    public void testEqualsOneEmptyList() {
        LinkedList one = LinkedList.listBuild(new int[]{1,2,3,4,5});
        LinkedList two = LinkedList.listBuild(new int[]{});

        assertEquals(false, one.equals(two));
    }

    @Test
    public void testEqualsBothEmptyList() {
        LinkedList one = LinkedList.listBuild(new int[]{});
        LinkedList two = LinkedList.listBuild(new int[]{});
        try {
            one.equals(two);
        } catch (Exception e) {
            if (e instanceof NullPointerException){
            } else {
                fail("NullpointerException expected");
            }
        }
    }

    @Test
    public void testHashcodeSuccess() {
        int hashCodeOne = LinkedList.listBuild(new int[]{1,2,3,4,5}).hashCode();
        int hashCodeTwo = LinkedList.listBuild(new int[]{1,2,3,4,5}).hashCode();

        assertEquals(hashCodeOne, hashCodeTwo);
    }

    @Test
    public void testHashcodeSameNodesScrambledOrder() {
        int hashCodeOne = LinkedList.listBuild(new int[]{1,2,3,4,5}).hashCode();
        int hashCodeTwo = LinkedList.listBuild(new int[]{2,1,3,5,4}).hashCode();

        assertNotEquals(hashCodeOne, hashCodeTwo);
    }

    @Test
    public void testHashcodeDifferentSizeLists() {
        int hashCodeOne = LinkedList.listBuild(new int[]{1,2,3,4,5}).hashCode();
        int hashCodeTwo = LinkedList.listBuild(new int[]{1,2,3,4,5,6}).hashCode();

        assertNotEquals(hashCodeOne, hashCodeTwo);
    }
}
