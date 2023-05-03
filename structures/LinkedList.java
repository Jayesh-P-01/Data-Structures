package structures;
/**
* A generic implementation of the IList iterface, that uses LinkElements.
*/
public class LinkedList<E> implements IList<E> {
    
    LinkedListElement<E> head;
    int count;
    /**
     * initiailsing the linked list 
     */
    public LinkedList() {
        this.head = null;
        this.count = 0;
    }
    /**
     * gets if the linked list is empty or not 
     * @return - if its empty
     */
    public boolean isEmpty() {
        // Returns whether the list is empty.
        if (this.head == null){
            return true;
        }
        return false;
    }

    /**
     * puts the value at the front of the list 
     * @param element - the item to be added
     * @return - if it was a successful add
     */
    public boolean add(E element) {
        // Adds an element to the head of the list.
        this.count ++;
        LinkedListElement<E> temp = new LinkedListElement<>(element);
        
        // if the list is not empty, point the new link to head
        if (!isEmpty()) {
            temp.setNext(head);
        }
        // update the head
        head = temp;
        
        return true;
    }
    /**
     * @return - the size of the linked list 
     */
    public int size() {
        return this.count;
    }
    /**
     * gets the head
     * @return - the head
     */
    public LinkedListElement<E> getHead(){
        return this.head;
    }
    /**
     * puts a new element at the back of the linked list 
     * @param element - the element to be added
     * @return - if it is added
     */
    public boolean addToTail(E element) {
        // Adds element to tail of the list
        this.count++; // new element so it is bigger
        LinkedListElement<E> elementToAdd = new LinkedListElement<>(element); 
        if (size() == 1){ // no where else to add to 
            this.head = elementToAdd;
            return true;
        }
        LinkedListElement<E> currenObj = this.head; 
        while (currenObj.getNext() != null){ // keep going from the head until you find the tail
            currenObj = currenObj.getNext();
        }
        currenObj.setNext(elementToAdd); // set the tails element to the new element
        return true;
    }
    /**
     * finds the tail and then deletes the object
     * @return - the value that has been removed
     */
    public E removeFromTail() {
        // Adds element to tail of the list
        if (size() == 0){
            return (null);
        }
        LinkedListElement<E> currenObj = this.head; 
        for (int i = 0; i<size()-1;i++){ // fidning the tail
            currenObj = currenObj.getNext();
        }
        E temp = currenObj.getValue();
        currenObj.setNext(null); // removing the item
        this.count --; // its now smaller
        return temp;
    }

    /**
     * deletes the first value
     * @return - the head
     */
    public E removeFromHead() {
        this.count --;
        E returnedValue = (E)this.head.getValue();
        this.head = head.getNext();
        return returnedValue;
    }
    
    
    /**
     * gets the value at any index
     * @param index - the index to retrive
     * @return - the value 
     */
    public E get(int index) {
        LinkedListElement<E> ptr = head;
        for (int i=size()-1;i>index;i--) { // keepts tracking until it finds the index
            ptr = ptr.getNext();
        }
        return ptr.getValue(); // returns the index
    }

    /**
     * get the final value
     * @return - the tail
     */
    public E getTail(){
        return get(this.count);
    }
    /**
     * finds the position of a certain element
     * @param element - the element to search for 
     */
    public int indexOf(E element) {
        // Gets the index of element in the list
        LinkedListElement<E> ptr = head;
        int i=0;
        while (ptr != null) { // searching through the whole linked list 
            if (element.equals(ptr.getValue())) { // found the element
                return i;
            }
            i++;
            ptr = ptr.getNext();
        }
        return -1; // not there
    }
    /**
     * finds the index and sets the value to that 
     * @param index - the index to add to 
     * @param element - the replacement value
     * @return - the value that has been repalced
     */
    public E set(int index, E element) {
        // Sets element at index in the list
        LinkedListElement<E> ptr = head;
        for (int i=0;i<index;i++) {
            ptr = ptr.getNext();
        }
        E ret = ptr.getNext().getValue();
        LinkedListElement<E> newlink = new LinkedListElement<>(element);
        newlink.setNext(ptr.getNext().getNext());
        ptr.setNext(newlink);
        return ret;
    }
    /**
     * gets rid of everything
     */
    public void clear() {
        head = null;
    }
    /**
     * returns if an element is in the list 
     * @param element - the element you are checking for 
     * @return - true or false
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /**
     * removes an element from the list 
     * @param element - the element to delete
     * @return - if it was successful
     */
    public boolean remove(E element) {

        if (isEmpty()) return false;
        LinkedListElement<E> ptr = head;
        while (ptr.getNext().getNext() != null) { // tracking through to find the value
            if (element.equals(ptr.getNext().getValue())) {
                ptr.setNext(ptr.getNext().getNext());
                this.count--; // removed so the size has decrased
                return true;
            }
            ptr = ptr.getNext();
        }
        if (element.equals(ptr.getNext().getValue())) { // found 
            ptr.setNext(null); // remove 
            this.count--; // removed so decrease size
            return true;
        }
        return false;
    }

    
}
