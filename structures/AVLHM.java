package structures;
// This line allows us to cast our object to type (E) without any warnings.
@SuppressWarnings("unchecked") 
public class AVLHM<K extends Comparable<K>,V> implements IMap<K,V> {
    
    protected AVLTree<K,V>[] table;
    private int entities = 0; 
    private int size;
    
    public AVLHM() {
        this(1000); // default size is 1000
    }
    /**
     * 
     * @param size - this is the size of the hashmap
     */
    public AVLHM(int size) { // if there is a number in the initialisation then that will be the new size of the hashmap
        this.size = size;
        table = (AVLTree<K,V>[]) new AVLTree[size];
        initTable();
    }

    /**
     * retuns the table 
     * @return - the arrays of AVLtrees
     */
    public AVLTree<K,V>[] getTable(){
        return this.table; // returning the table is useful for being able to perform a search of the whole hashmap
    }
    /**
     * sets up the table
     */
    protected void initTable() { // setting up the table 
        for(int i = 0; i < table.length; i++) { // going through and making each position an AVL tree
            table[i] = new AVLTree<>();
        }
    }
    /**
     * Gets the number of elements in a linked list at teh key provided
     * @param key - the key that the linked list is being searched for at
     * @return - the number of elements in the linked list 
     */
    public int getNumberLinked(K key){
        int keyInt = (Integer) key;
        int location = keyInt % table.length; // finding location again
        return table[location].findAll(table[location].getTopNode(), key).length;
    }
    /**
     * Returns the linked list at the key 
     * @param key - the key that we are looking for the linked list at 
     * @return - the linked list elements as an array (the values are what is returned)
     */
    public V[] getAllLinked(K key){
        int keyInt = (Integer) key;
        int location = keyInt % table.length; // finding location again
        return table[location].findAll(table[location].getTopNode(), key);
    }

    /**
     * 
     * @param key - this is the key of the value to be added
     * @param value - this is the value of the value to be added
     */
    public void add(K key, V value) {
        int keyInt = (Integer) key; // the key is always an integer so this is turning the key into one so that it can be dealt with as such
        int location = keyInt % table.length; // finding the position that the value needs to go in 
        table[location].addNode(key, value); // adding to the hashmap (this is the add function for the AVL tree)
        this.entities++; // now we have one more entitiy for when we get the size so that is to be incremented
        return;
    }

    /**
     * 
     * @param key the key to be found and then removed
     */
    public void remove(K key){
        int keyInt = (Integer) key;
        int location = keyInt % table.length; // finding location again
        table[location].remove(key); // remove function of the avl tree
        this.entities--; // now there is one less entity so that is decremented
    }

    /**
     * 
     * @param key the key to be found and then removed
     */
    public V get(K key) {
        int keyInt = (Integer) key;
        int location = keyInt % table.length; // position 
        BinaryTreeNode<K,V> topNode = table[location].getTopNode(); // getting the topnode to use in the find function
        if (topNode == null){ // there isn't anything to find
            return null;
        }
        return (V)table[location].find(topNode,key); // returning the value of the key
    }
    /**
     * 
     * @return - the size of the hashmap
     */
    public int getTableLength(){
        return table.length;
    }

    /**
     * 
     * @return the number of items inside of the hashmap
     */
    public int getEntities(){
        return this.entities;
    }

    /**
     * 
     * @return - the size of the hashmap
     */
    public int getSize(){
        return this.size;
    }
}
