package structures;

public class SortedArrayList<K extends Comparable<K>,V> {
    private ListElement<KeyValuePair<K,V>>[] store;
    private int size;
    /**
     * sets up the array
     */
    public SortedArrayList(){
        this.store = new ListElement[10];
    }

    public boolean add(K key, V value){
        KeyValuePair<K,V> element =  new KeyValuePair<>(key,value);
        ListElement<KeyValuePair<K,V>> newElement = new ListElement<>(element);
        
        if (checkIfFull()){
            resize();
        }

        // finding the position to add the item to 
        int high = size-1;
        int low = 0;
        int mid = ((high + low)/2);
        int position = -1;
        while (low <= high){
            mid = (low + high)/2;
            if (key.compareTo(this.store[mid].getValue().getKey()) == 0){
                position = mid+1;
            }
            if (key.compareTo(this.store[mid].getValue().getKey()) < 0){
                high = mid -1;
            } else{
                low = mid+1;
            }
        }
        if (position == -1){
        position = low;
        }
        for (int i = size-1;i>=position;i-- ){
            this.store[i+1] = this.store[i];
        }
        this.store[position] = newElement;
        size++;
        return true;
    }

    public K keyAtPos(int position){
        return this.store[position].getValue().getKey();
    }

    /**
     * finds the value at a position
     * @param position - the positions to be searched for 
     * @return - the value at the position
     */
    public V getPosition(int position){
        return this.store[position].getValue().getValue();
    }

    public void removeFromPosition(int position){
        this.store[position] = null;
        for (int j = position; j<size-1;j++){
            this.store[j] = this.store[j+1];
        }
        this.store[size-1] = null;
        size--;
    }

    public boolean remove(K key){
        
        int high = getSize()-1;
        int low = 0;
        int mid = ((high + low)/2);
        int position = 0;
        while (low <= high){
            mid = (high+low)/2;
            if (key.compareTo(this.store[mid].getValue().getKey()) < 0){
                high = mid-1;
            } else if(key.compareTo(this.store[mid].getValue().getKey()) > 0){
                low = mid+1;
            } else{
                position = mid;
                break;
            }
        }
        this.store[position] = null;

        for (int i = position; i<this.size-1;i++){
            if (i != position & this.store[i] == null){ // this will mean that all the elements from here are null, which means that no more elements need to be moved down
                break;
            }
            this.store[i] = this.store[i+1];
        }
        this.store[this.size-1] = null;
        size--;
        return true;
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

    public V findValue(K key){
        int high = getSize()-1;
        int low = 0;
        int mid = ((high + low)/2);
        while (low <= high){
            mid = (high+low)/2;
            if (key.compareTo(this.store[mid].getValue().getKey()) < 0){
                high = mid-1;
            } else if(key.compareTo(this.store[mid].getValue().getKey()) > 0){
                low = mid+1;
            } else{
                return this.store[mid].getValue().getValue();
            }
        }
        return null;
    }
    /**
     * this if for when a range is being searched for. If the item being searched for is not there then the position of the value immeditaly higher will be returned instead 
     * @param key - the key being searched for 
     * @return - the position for a range
     */
    public int binarySearchLow(K key) {
        int left = 0;
        int right = getSize()-1;
        int result = -1; // this will return -1 if the value given is above the highest value in the list.
    
        while (left <= right) { // while the search is still going
            int mid = left + (right - left) / 2;
    
            if (key.compareTo(this.store[mid].getValue().getKey()) == 0) { // if the value is the value being searched for then the position of the next highest value should be returned
                return mid+1;
            } else if (key.compareTo(this.store[mid].getValue().getKey()) > 0) {
                left = mid + 1; // the value is to the right of mid
            } else {
                result = mid; // this is the possible position of the next higher value
                right = mid - 1; // the value is to the left of mid
            }
        }
        return result;
    }
    /**
     * this if for when a range is being searched for. If the item being searched for is not there then the position of the value immeditaly lower will be returned instead 
     * @param key - the key being searched for 
     * @return - the position for a range
     */
    public int binarySearchHigh(K key) {
        int left = 0;
        int right = getSize()-1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
    
            if (key.compareTo(this.store[mid].getValue().getKey()) == 0) { // if this is the value being searched for then return the immediately lower value
                return mid-1;
            } else if (key.compareTo(this.store[mid].getValue().getKey()) > 0) {
                result = mid;
                left = mid + 1; // the value is to the right of mid
            } else {
                right = mid - 1; // the value is to the left of mid
            }
        }
        return result;
    }

    /**
     * Finds the position of a value
     * @param key - the key of the value to search for
     * @return - the position 
     */
    public int binarySearch(K key){
        int left = 0;
        int right = getSize()-1;
        int result = -1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2;
    
            if (key.compareTo(this.store[mid].getValue().getKey()) == 0) {
                return mid; // the value has been found so return the position 
            } else if (key.compareTo(this.store[mid].getValue().getKey()) > 0) {
                result = mid;
                left = mid + 1; // the value is to the right of mid so move left up 
            } else {
                right = mid - 1;// the valu is to the left of mid so move the right down
            }
        }
        return result;
    }
    
    /**
     * This finds the top and bottom values that form a range of values
     * @param low - the lowest value that should be included in the range
     * @param high - the highest value that should be included in the range
     * @return - an array that has value 0 as the lowest position and value 1 of the highest positon
     */
    public int[] findRange(K low, K high){
        int lowPosition = binarySearchLow(low);
        int highPosition = binarySearchHigh(high);
        int[] positions = {lowPosition, highPosition};
        return positions;
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
        ListElement<KeyValuePair<K,V>>[] newStore = new ListElement[size * 2]; // creates a new array that is double the size
        System.arraycopy(this.store, 0, newStore, 0, this.store.length); // copies the current array into the bigger array
        this.store = newStore; // sets the current array to be the bigger array
    }
}
