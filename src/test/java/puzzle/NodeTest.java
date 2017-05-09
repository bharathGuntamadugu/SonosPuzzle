package puzzle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Bharath on 18-Apr-17.
 */
public class NodeTest {
    @Test
    public void testEqualsSuccess() {
        Node one = new Node(1);
        Node two = new Node(1);

        assertEquals(one, two);
    }

    @Test
    public void testEqualsFailure() {
        Node one = new Node(1);
        Node two = new Node(2);

        assertNotEquals(one, two);
    }

    @Test
    public void testHashCodeSuccess() {
        int hashCodeOne = new Node(1).hashCode();
        int hashCodeTwo = new Node(1).hashCode();

        assertEquals(hashCodeOne, hashCodeTwo);
    }

    @Test
    public void testHashCodeFailure() {
        int hashCodeOne = new Node(1).hashCode();
        int hashCodeTwo = new Node(2).hashCode();

        assertNotEquals(hashCodeOne, hashCodeTwo);
    }
}
