package puzzle;

import java.util.Objects;

/**
 * Created by Bharath on 18-Apr-17.
 */
public class Node {
    private final int val;
    private Node next = null;

    public Node(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Node)) {
            return false;
        }
        return this.getVal() == ((Node)obj).val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
