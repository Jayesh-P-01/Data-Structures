package structures;

public class nonSortedArrayList<V extends Comparable<V>> {
    private ListElement<V>[] store; // the acctual array
    private int size; // the number of elements in the array
    /**
     * sets up the array
     */
    public nonSortedArrayList(){
        this.store = new ListElement[10];
    }

    /**
     * adds an element to the array
     * @param value - the value to be added
     */
    public void add(V value){
        ListElement<V> newElement = new ListElement<>(value);
        
        if (checkIfFull()){ // if it is full then the size of the array needs to be increased
            resize();
        }
        this.store[size] = newElement; // setting the new element in the array
        size++; // now there is a new element so this needs to increase
    }
    /**
     * removes an element from the array
     * @param value - the value of the element to be removed
     * @return - if the removal was successful or not
     */
    public boolean remove(V value){
        for (int i = 0; i<size;i++){ // going through the list 1 element at a time
            if (value.compareTo(this.store[i].getValue()) == 0){ // if this is the item the program is looking for 
                this.store[i] = null; // remove it 
                for (int j = i; j<size-1;j++){ // move all of the other elements down one
                    this.store[j] = this.store[j+1];
                }
                this.store[size-1] = null; // the element isn't being replaced by anything so it is set to null
                size--; // item has been removed so the  size should decrease
                return true;
            }
        }
        
        return false;
    }

    /**
     * finds the value at a position
     * @param position - the positions to be searched for 
     * @return - the value at the position
     */
    public V getValue(int position){
        return this.store[position].getValue();
    }

    /**
     * updates size
     * @param newSize - the value size should be set to 
     */
    public void setSize(int newSize){
        this.size = newSize;
    }
    /**
     * gets size
     * @return - size
     */
    public int getSize(){
        return this.size;
    }
    /**
     * Sees if the number of elements is the same as the length
     * @return - if the array is full or not
     */
    public boolean checkIfFull(){
        if (this.size == this.store.length){ // needs to be increased in size
            return true;
        }
        return false;
    }
    /**
     * doubles the size of the array
     */
    public void resize(){
        ListElement<V>[] newStore = new ListElement[size * 2]; // new array
        System.arraycopy(this.store, 0, newStore, 0, this.store.length); // copy all of the elements currently stored into the bigger list
        this.store = newStore; // set the array to the new array 
    }
}
