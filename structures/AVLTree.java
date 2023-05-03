package structures;
public class AVLTree<K extends Comparable<K>,V> {
    private BinaryTreeNode<K,V> topNode = null; // the parent node of the whole tree

    /**
     * 
     * @return - this is the node in the tree that has no parent
     */
    public BinaryTreeNode<K, V> getTopNode() {
        return topNode;
    }

    /**
     * 
     * @param topNode - the value of the topnode
     */
    public void setTopNode(BinaryTreeNode<K, V> topNode) {
        this.topNode = topNode;
    }

    /**
     * 
     * @param key - the key of the BinaryTreeNode that is about to be added
     * @param value - the value of the BinaryTreeNode that is about to be added
     * @return - this will always be true because it is always possible to add a node
     */
    public boolean addNode(K key, V value){
        if (this.topNode == null){
            this.topNode = new BinaryTreeNode<>(key, value);
            return true;
        }

        this.topNode = addRecurser(this.topNode, key, value);
        return true;
    }

    /**
     * This is a functions that will be recursivley called in order to find where to add
     * @param node - the current node that we need to add the new node at one end of
     * @param key - the key of the value to be added
     * @param value - the value of the value to be added
     * @return - the value of the node after the addition. This could change if there is a rotation that needs to be performed and thus a different node is to be returned
     * Alternatively, the node will be different because it has an addition to the subtree that it is the parent node of
     */
    private BinaryTreeNode<K,V> addRecurser(BinaryTreeNode<K,V> node, K key, V value){
        if (node == null){ // this is where the node needs to be added so this is where the node is returned
             return new BinaryTreeNode<>(key, value);
        }
        if (key.compareTo(node.getKey()) > 0) { // This means that the key is greater than the key of the node it is currently at, so needs to go right
            node.setRightNode(addRecurser(node.getRightNode(), key, value)); // recursivley going right and knows that the right side of the node is what is going to change
        } else if (key.compareTo(node.getKey()) < 0) {// This means that the key is less than the key of the node it is currently at, so needs to go left
            node.setLeftNode(addRecurser(node.getLeftNode(), key, value));// recursivley going left and knows that the left side of the node is what is going to change
        } else{
            node.setLinkedElement(new BinaryTreeNode<>(key, value)); // this means that the key is equal so the element will be added to the linked list section of the BinaryTreeNode 
        }
        node = rebalance(node);
        return node;
    } 


    /**
     * takes a node and track as far to the left as it can and then add the nodeToAdd to it and then return said node. This works very similar to the addRecurser
     * @param addingNode - the node that is being added to/ tracked left from 
     * @param nodeToAdd - the node that needs to be added as far left as possible
     * @return - the node that has been added to/ had their subtree changed
     */
    private BinaryTreeNode<K,V> addToLeft(BinaryTreeNode<K,V> addingNode, BinaryTreeNode<K,V> nodeToAdd){ 
        if (addingNode.getLeftNode() == null){
            addingNode.setLeftNode(nodeToAdd);
            return addingNode;
        }
        addingNode.setLeftNode(addToLeft(addingNode.getLeftNode(), nodeToAdd));
        return addingNode;
    }
    /**
     * takes a node and track as far to the right as it can and then add the nodeToAdd to it and then return said node. This works very similar to the addRecurser
     * @param addingNode - the node that is being added to/ tracked right from 
     * @param nodeToAdd - the node that needs to be added as far right as possible
     * @return - the node that has been added to/ had their subtree changed
     */
    private BinaryTreeNode<K,V> addToRight(BinaryTreeNode<K,V> addingNode, BinaryTreeNode<K,V> nodeToAdd){ 
        if (addingNode.getRightNode() == null){
            addingNode.setRightNode(nodeToAdd);
            return addingNode;
        }
        addingNode.setRightNode(addToRight(addingNode.getRightNode(), nodeToAdd));
        return addingNode;
    }

    /**
     * This method will perform the necessary rotations to balance the tree
     * @param node - the node to be rebalanced
     * @return - the new node that has been rebalanced
     */
    public BinaryTreeNode<K,V> rebalance(BinaryTreeNode<K,V> node){
        int balance = node.getBalance(); // finding if there needs to be a rabalance 
        if (balance == -2){ // right heavy - need to know if the right child is left or right heavy
            if (node.getRightNode().getBalance()<=0){ // the right child is right heavy
                // Right Right --> rotate left
                BinaryTreeNode<K,V> rightChild = node.getRightNode(); // for later use
                if (rightChild.getLeftNode() != null){ // that means that there is something there
                    node.setRightNode(null); // this will allow it to be changed
                    rightChild.setLeftNode(addToLeft(rightChild.getLeftNode(),node)); // this will set the left child as far left as possible because it will always be smaller than all the other left nodes
                    node = rightChild; //updating what should be returned
                    return node;
                } else{
                    node.setRightNode(null); // the right node won't exist anymore
                }
                rightChild.setLeftNode(node);
                node = rightChild;
            } else{ // the right child is left heavy
                //Right Left --> rotate right-left
                BinaryTreeNode<K,V> rightChild = node.getRightNode(); //getting the right child
                node.setRightNode(rightChild.getLeftNode()); // the left node of the right child needs to become the right node of node
                rightChild.setLeftNode(null); // this node has been moved so no need for it
                BinaryTreeNode<K,V> newRightChild = node.getRightNode(); // the same as above for the rest of the statement
                newRightChild = addToRight(newRightChild, rightChild); // adding as far right as possible
                rightChild = node.getRightNode(); 
                if (rightChild.getLeftNode() != null){
                    node.setRightNode(null);
                    rightChild.setLeftNode(addToLeft(rightChild.getLeftNode(),node));
                    node = rightChild;
                    return node;
                    //node.setLeftNode(rightChild.getLeftNode());
                } else{
                    node.setRightNode(null);
                }
                rightChild.setLeftNode(node);
                node = rightChild;
            }
        } else if(balance == 2){ // left heavy - need to know which was the left child is heavy
            if (node.getLeftNode().getBalance() >= 0){ // all of the following is the same as above but to the left side (all directions are reversed)
                // Left Left -->rotate right
                BinaryTreeNode<K,V> leftChild = node.getLeftNode();
                if (leftChild.getRightNode() != null){
                    node.setLeftNode(null);
                    leftChild.setRightNode(addToRight(leftChild.getRightNode(), node));
                    node = leftChild;
                    return node;
                } else{
                    node.setLeftNode(null);
                }
                leftChild.setRightNode(node);
                node = leftChild;
            } else{
                // Left Right --> rotate left-right
                BinaryTreeNode<K,V> leftChild = node.getLeftNode();
                node.setLeftNode(leftChild.getRightNode());
                leftChild.setRightNode(null);
                BinaryTreeNode<K,V> newLeftChild = node.getLeftNode();
                newLeftChild = addToLeft(newLeftChild,leftChild);
                leftChild = node.getLeftNode();
                if (leftChild.getRightNode() != null){
                    node.setLeftNode(null);
                    leftChild.setRightNode(addToRight(leftChild.getRightNode(), node));
                    node = leftChild;
                    return node;
                    //node.setLeftNode(leftChild.getRightNode());
                } else{
                    node.setLeftNode(null);
                }
                leftChild.setRightNode(node);
                node = leftChild;
            }
        }
        return node;
    } 

    /**
     * This method will find the value in the AVL tree
     * @param node - this is the parent node of a subtree thats subtree needs to be searched to find the node that is beign looked for
     * @param key - the key of the node that is being looked for 
     * @return -the value of the node that has the key of key 
     */
    public V find(BinaryTreeNode<K,V> node,K key){
        V value = null;
        if(key.compareTo(node.getKey()) == 0){
            value = node.getValue();
        } else if (key.compareTo(node.getKey()) > 0){ // this means that the key we are searching for is greater than that of the key of the current node
            if (node.getRightNode() != null){ // the program needs to go right and is able to 
            node = node.getRightNode(); // recursively go right and set the result to node so that when the program comes back out of this it can pass in the value
            value = find(node,key);
            } else{
                return null; // the node isn't present in the AVLtree
            }
        } else{
            if (node.getLeftNode() != null){ // if it is then we know that the element isn't there
            node = node.getLeftNode(); // recursivly go left
            value = find(node,key);
            } else{
                return null;// the node isn't present in the AVLtree
            }
        }
        return value;
    }

    /**
     * This is the same as the previous find function except it returns an array of all of the values with the key of key
     * @param <V>
     * @param node - the node that is being started with 
     * @param key - the key that is being searched for 
     * @return this is an array of all of the values that have the key key because this will have been stored in a linked list kind of fashion
     */
    public <V> V[] findAll(BinaryTreeNode<K,V> node,K key){
    V[] values = null;
    if(key.compareTo(node.getKey()) == 0){
        values = (V[]) new Object[node.getAllLinked()]; // the program is looking for a linked list and will return this in an array fashion
        values[0] = node.getValue(); // the value of the current node will be the first element
        int count = 1; // keeping track of where it is being added
        while (node.getLinked() != null){ // while there is still a linked element to find
            values[count] = node.getLinked().getValue(); // setting the value of the next linked element 
            node = node.getLinked(); // go to the next node so that the next element can be found 
            count++; // the position will increase
        }
    } else if (key.compareTo(node.getKey()) > 0){ // this means that the key we are searching for is greater than that of the key of the current node
        if (node.getRightNode() != null){
        node = node.getRightNode();
        values = findAll(node,key);
        }else{
            return null;
        }
    } else{
        if (node.getLeftNode() != null){
        node = node.getLeftNode();
        values = findAll(node,key);
        }else{
            return null;
        }
    }
    return values; 
    }

    /**
     * This will find a value and then remove it 
     * @param key - the key of the value to be removed
     */
    public void remove(K key){
        if (this.topNode.getHeight() == 1){ // if there is only 1 node
            if (this.topNode.getKey() == key){ // the node can't be removed so nothing will happen if this isn't true
                this.topNode = null;
            }
            return;
        }
        this.topNode = deleteRecurser(this.topNode, key);
        this.topNode = rebalance(topNode); // there has been a change and therefore it is necessary to check if there needs to be a rebalance
    }

    /**
     * Finding the key to be removed and then updating the tree
     * @param node - the node thats subtree is being searched
     * @param key - the key of the value being removed
     * @return - the updated node that has had its subtree updated
     */
    public BinaryTreeNode<K,V> deleteRecurser(BinaryTreeNode<K,V> node, K key){
        if (node == null){
            return node;
        }
        // below is the same as finding:
        if (key.compareTo(node.getKey()) > 0){
            node.setRightNode(deleteRecurser(node.getRightNode(), key));
        } else if (key.compareTo(node.getKey()) < 0){
            node.setLeftNode(deleteRecurser(node.getLeftNode(), key));
        } else{
            if (node.getLeftNode() == null){ // the node above the one to be deleted needs to update its child
                return node.getRightNode(); // the new child

            } else if (node.getRightNode() == null){
                return node.getLeftNode(); // if there is no right node then the left node should be the child
            }
            BinaryTreeNode<K,V> child = node.getRightNode();
            while (child.getLeftNode() != null){
                child = child.getLeftNode(); // updating the child
            }
            node.setKey(child.getKey()); // the node has changed so there needs to be an update
            node.setValue(child.getValue());

            node.setRightNode(deleteRecurser(node.getRightNode(), node.getKey())); // the leftmost node of the right node needs to be removed
        }
        return node;
    }

    /**
     * getting the balance of the topnode
     * @return - the balance of the topnode
     */
    public int getHeadBalance(){
        return this.topNode.getBalance();
    }

}
