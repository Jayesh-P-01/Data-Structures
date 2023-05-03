package structures;

public class BinaryTreeNode<K extends Comparable<K>,V> {
    private K key; // the key of the ndoe
    private V value; // value of the ndoe
    private BinaryTreeNode<K,V> leftNode = null; // the left child of the node
    private BinaryTreeNode<K,V> rightNode = null; // the right child of the node
    private BinaryTreeNode<K,V> linkedElement = null; // the other values that have the same key as the current element but in the form of a liked list

    /**
     * 
     * @param key - the key of the new element
     * @param value - the value of the new element
     */
    public BinaryTreeNode(K key, V value){
        setKey(key);
        setValue(value);
        return;
    }
    /**
     * This finds the linked element of the node
     * @return - the linked element
     */
    public BinaryTreeNode<K,V> getLinked(){
        return this.linkedElement;
    }

    /**
     * This method finds the total number of linked elements (the size of the linked list)
     * @return - the number of linked elements
     */
    public int getAllLinked(){
        int count = 1; // there will always be one because it is has itself
        BinaryTreeNode<K,V> checkNode = new BinaryTreeNode<>(this.key, this.value); // this is so that the object is initialised, otherwise there will be issues with the comparison even though it won't be used 
        boolean needToCheck = false; // to check if there is a need to continue down
        if (this.linkedElement != null){
            checkNode = this.linkedElement;
            needToCheck = true; // there is a need to go further down into the linked elements
            count++; // one more has been found so the size needs to increase by one 
                  }
        if (needToCheck == true){
            while (checkNode.linkedElement != null){ // going as far deep as possible
                count++; // new element found
                checkNode = checkNode.linkedElement; // moving on to the next node
                }
            }   
        return count;

    }

    /**
     * Adds to the linked list
     * @param node
     * @return - if it is a success which is will always be because it is always possible if this code is reached
     */
    public boolean setLinkedElement(BinaryTreeNode<K,V> node){
        if (this.linkedElement == null){ // it can be added instantly 
            this.linkedElement = node;
            return true;
        }
        BinaryTreeNode<K,V> linkingNode = this.linkedElement; // get the linked node so it can traverse to the end of the linked list
        linkingNode.setLinkedElement(node);
        return true;
    }
    /**
     * return the key of the node
     * @return - the key of the node
     */
    public K getKey() {
        return key;
    }
    /**
     * Sets the key of the node
     * @param key - the key to be added
     */
    public void setKey(K key) {
        this.key = key;
    }
    /**
     * return the value of the node
     * @return - the value of the node
     */
    public V getValue() {
        return value;
    }
    /**
     * Sets the value of the node
     * @param key - the value to be added
     */
    public void setValue(V value) {
        this.value = value;
    }
    /**
     * return the left node of the node
     * @return - the left node of the node
     */
    public BinaryTreeNode<K, V> getLeftNode() {
        return leftNode;
    }
    /**
     * Sets the left child of the node
     * @param key - the value to be added
     */
    public void setLeftNode(BinaryTreeNode<K, V> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * return the right node of the node
     * @return - the right node of the node
     */
    public BinaryTreeNode<K, V> getRightNode() {
        return rightNode;
    }
    /**
     * Sets the right child of the node
     * @param key - the value to be added
     */
    public void setRightNode(BinaryTreeNode<K, V> rightNode) {
        this.rightNode = rightNode;
    }
    /**
     * This finds the hight of both the left and right subtree and return the difference beteween the two
     * @return - hight of the left subtree - height of right subtree
     */
    public int getBalance(){
        if (this.leftNode == null && this.rightNode == null){ // no child nodes
            return 0;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.leftNode != null){
            leftHeight = this.leftNode.getHeight(); // finds the height of the left node
        }
        if (this.rightNode != null){
        rightHeight = this.rightNode.getHeight(); // finds the height of the right node
        }
        int balance = leftHeight-rightHeight;
        return balance;
    }

    /**
     * gets the height of the biggest subtree + 1
     * @return - the height of the node
     */
    public int getHeight(){
        if (this.getLeftNode() == null && this.getRightNode() == null){ // no child nodes
            return 1;
        }
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.leftNode != null){
            leftHeight = this.getLeftNode().getHeight(); // recursivley finds the height of the left child
        }
        if (this.rightNode != null){
            rightHeight = this.getRightNode().getHeight(); // recursivly finds the height of the right child
        }
        int maxHeight = 0;
        // finding the greater height
        if (leftHeight > rightHeight){
            maxHeight = leftHeight;
        } else{
            maxHeight = rightHeight;
        }
        return maxHeight+1; // this node also counts towards the height so it adds 1
    }
}
