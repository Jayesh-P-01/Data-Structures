package structures;

/**
 * Element of a singly linked-list holds Strings as data elements
 */
public class LinkedListElement<E> {

    private E value;
    private LinkedListElement<E> next_elem;

    // class constructor
    public LinkedListElement(E val) {
        value = val;
        next_elem = null;
    }

    // access method for next pointer
    public LinkedListElement<E> getNext() {
        return next_elem;
    }

    // access method for value
    public E getValue() {
        return value;
    }

    // setter method for value
    public void setValue(E val) {
        value = val;
    }

    // setter method for next pointer
    public void setNext(LinkedListElement<E> next) {
        this.next_elem = next;
    }
    
    public String toString() {
        return ""+value;
    }
}
