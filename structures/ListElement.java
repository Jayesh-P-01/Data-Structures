package structures;
public class ListElement<E> {
    private final E value;
    private ListElement<E> next; // - the next item 
    private ListElement<E> prev; // the previous item
    /**
     * sets the value of the list element
     * @param value - the value to go into the list element
     */
    public ListElement(E value) {
        this.value = value;
    }
    /**
     * 
     * @return - the value of the list element
     */
    public E getValue() {
        return this.value;
    }
    /**
     * 
     * @return - the next element in the list
     */
    public ListElement<E> getNext() {
        return this.next;
    }
    /**
     * 
     * @return - the previous element in the list
     */
    public ListElement<E> getPrev() {
        return this.prev;
    }
    /**
     * setting the next element in the list
     * @param e - the item to be the next element
     */
    public void setNext(ListElement<E> e) {
        this.next = e;
    }
    /**
     * setting the previous element in the list
     * @param e - the item to be the previous element
     */
    public void setPrev(ListElement<E> e) {
        this.prev = e;
    }

}
