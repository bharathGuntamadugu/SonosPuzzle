package puzzle;

import java.util.Objects;

/**
 * Created by Bharath on 17-Apr-17.
 */
public class LinkedList {

    private Node head;
    //Private access modifier to only allow to create the list using static listBuild method.
    private LinkedList(final Node head) {
        this.head = head;
    }

    public LinkedList moveEvensToEnd() {
        //Checks for empty list or list with single node.
        if ( head == null || head.getNext() == null) {
            return this;
        }

        //Odd list
        Node odd = new Node(0);
        Node oddStart = odd;

        //Even list
        Node even = new Node(0);
        Node evenStart = even;
        while (head != null){
            //If node val is divisible by 2, it is even, so we add the node to even list.
            if ((head.getVal() & 1) == 0) {
                even.setNext(head);
                even = head;
            } else {  // else we add to it odd list.
                odd.setNext(head);
                odd = head;
            }
            head = head.getNext();
        }
        //Point the end of odd list to the start of the even list.
        odd.setNext(evenStart.getNext());
        //Breaks the end of even list loop if any by setting last node next value to null.
        even.setNext(null);

        head = oddStart.getNext();
        //Returns the odd List Head.
        return this;
    }

    public static LinkedList listBuild(int[] vals) {
        if (vals == null || vals.length <= 0) {
            return null;
        }
        Node head = new Node(vals[0]);
        Node temp = head;
        for (int i = 1; i < vals.length; i++) {
            temp.setNext(new Node(vals[i]));
            temp = temp.getNext();
        }
        return new LinkedList(head);
    }

    @Override
    public String toString() {
        Node head = this.head;
        if (head == null) {
            return "Empty list";
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.getVal() + " -> ");
            head = head.getNext();
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LinkedList)) {
            return false;
        }

        if (this == null && obj == null) {
            return true;
        }
        if (this == null || obj == null) {
            return false;
        }
        Node currentHead = this.head;
        Node givenHead = ((LinkedList)obj).head;
        while (currentHead != null && givenHead != null) {
            if (!currentHead.equals(givenHead)) {
                return false;
            }
            currentHead = currentHead.getNext();
            givenHead = givenHead.getNext();
        }
        return (givenHead == null && givenHead == null) ? true : false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
