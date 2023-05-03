package structures;
public class Queue<E> implements IQueue<E> {

    private ListElement<E> head;
    private ListElement<E> tail;

    /**
     * initialising the queue
     */
    public Queue() {
        head = null;
        tail = null;
    }
    /**
     * chekcs if the queue is empty
     * @return true or false depending on if it is empty or not
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * returns the top value and removes it
     * @return the top value on the queue
     */
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        ListElement<E> tmp = head; // getting the head to be able to return it
        head = tmp.getNext();

        if (head == null) {
            tail = null;
        }

        return tmp.getValue(); // returning the previous head
    }

    /**
     * @param value - the value to be added to the queue
     */
    public void enqueue(E value) {
        ListElement<E> tmp = new ListElement<>(value);
        if (isEmpty()) {
            tail = head = tmp;
        } else {
            tail.setNext(tmp);
            tail = tmp;
        }
    }

    /**
     * changes teh head
     * @param value - the new value of the head
     */
    public void modifyHead(E value) {
        ListElement<E> newHead = new ListElement<>(value);
        if (!(isEmpty())){
            newHead.setNext(head.getNext());
            head = newHead;
        }
    }
}
