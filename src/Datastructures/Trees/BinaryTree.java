package Datastructures.Trees;

/**
 * Custom implementation of a Binary Tree.
 * */
public class BinaryTree<T extends Comparable<T>> {
    private T data;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    private BinaryTree<T> parent;
    public BinaryTree<T> previous;

    private int size;

    public BinaryTree(T data){
        this.data = data;
        this.size++;
    }

    public BinaryTree(T data ,BinaryTree parent){
        this.data = data;
        this.size++;
        this.parent = parent;
    }

    /**
     * sets the value of the tree itself.
     * @param data the bale that is being set.
     * **/
    public void setData(T data) {
        this.data = data;
    }

    /***
     * set the left child
     * @param left the value of the right child
     * **/
    public void setLeft(BinaryTree<T> left) {
        this.left = left;
        this.size++;
    }

    /***
     * set the right child
     * @param right the value of the right child
     * **/
    public void setRight(BinaryTree<T> right) {
        this.right = right;
        this.size++;
    }

    /****
     * returns the left child of the tree
     * @return the left child of a tree. It returns a tree object.
     * **/
    public BinaryTree<T> getLeft() {
        return this.left;
    }

    /****
     * returns the right child of the tree
     * @return the right child of a tree. It returns a tree object.
     * **/
    public BinaryTree<T> getRight() {
        return this.right;

    }

    public BinaryTree<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTree<T> parent) {
        this.parent = parent;
    }

    /**
     * returns the value of the tree node
     * @return the value of the tree
     * **/
    public T getData() {
        return this.data;
    }

    /**
     * Inserts a value to the left of a specified node
     * If there is already a node in the left child, Then it attempts to insert
     * in the left of that left node. and so on.
     * @param node the node being inserted into
     * @param data the data being inserted.
     * **/
    public void insertLeft(BinaryTree node, T data){

        if( node.left == null ){
            node.setLeft(new BinaryTree(data));
        }
        else{
            insertLeft(node.left , data);
        }
    }


    /**
     * Inserts a value to the right of a specified node
     * If there is already a node in the right child, Then it attempts to insert
     * in the right of that right node. and so on.
     * @param node the node being inserted into
     * @param data the data being inserted.
     * **/

    public void insertRight(BinaryTree node, T data){

        if( node.right == null ){
            node.setRight(new BinaryTree(data));
        }
        else{
            insertRight(node.left , data);
        }
    }

    /**
     * gets the number of nodes in the tree.
     * @return the number of nodes in the tree.
     * **/

    public int getSize(){
        int size = 1;
        if (this.isLeaf()){
            return 1;
        }

        if( this.left != null){
            size += this.left.getSize();
        }

        if (this.right != null){
            size += this.right.getSize();
        }

        return size;
    }

    /**
     * This maethod takes in a binary tree and checks if its filled
     * A binary tree is filled if both its left and right children are not null
     */
    public boolean isFilled(){
        if (this.getLeft() != null && this.getRight() != null){
            return true;
        }
        return false;
    }

    public static BinaryTree getNextRightSibling(BinaryTree tree){
        if (tree.getParent() == null){
            return null;
        }

        if (tree.getParent().getRight() == null ||tree.getParent().getRight() == tree){
            return null;
        }
        else{
            return tree.getParent().getRight();
        }
    }
    /**
     * returns the leftmost node in a tree.
     * @param tree the starting node to start searching from
     * @return a node representing the leftmost node from a starting node.
     * **/
    public static BinaryTree getLeftMostNode(BinaryTree tree){
        if (tree.isLeaf()){
            return tree;
        }else{
            return getLeftMostNode(tree.left);
        }
    }

    /**
     * Checks whether a node is a leaf or internal node.
     * @return true if a tree is a left
     * @return false if a tree is an internal node.
     * **/
    public boolean isLeaf(){
        return this.left ==  null && this.right == null;
    }

    /**
     * gets the height of the tree
     * @return the height of the tree.
     * **/
    public int getHeight(){
        int height = 1;
        int left = 0;
        int right = 0;

        if (this.isLeaf()){
            return 0;
        }

        if (this.left != null){
            left = this.left.getHeight();
        }

        if (this.right != null){
            right = this.right.getHeight();
        }

        return height + Math.max(left,right);
    }

    /**
     * checks if a node is the sibling of another node. Meaning they have the same parent node.
     * @param node the node being checked against
     * @return true if both node are sibling nodes
     * @return false id they aren't.
     * */
    public boolean isSibling(BinaryTree node){
        if( this.getParent() == node.getParent() ){
            return true;
        }
        return false;
    }






}
