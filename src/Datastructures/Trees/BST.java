package Datastructures.Trees;

import java.util.Arrays;

/**
 * Custom implementation of a BST Tree.
 * It follows the BST property.
 * */
public class BST <T extends Comparable<T>>{

    private BST<T> left;
    private BST<T> right;
    private BST<T> parent;
    private T value;

    public BST( T value ){
        this.parent = null;
        this.value = value;
    }

    public BST( T value, BST parent ){
        this.parent = parent;
        this.value = value;
    }

    /***
     * set the right child
     * @param right the value of the right child
     * **/

    public void setRight(BST<T> right) {
        this.right = right;
    }

    /***
     * set the left child
     * @param left the value of the right child
     * **/
    public void setLeft(BST<T> left) {
        this.left = left;
    }

    /**
     * sets the value of the tree itself.
     * @param value the bale that is being set.
     * **/
    public void setData(T value) {
        this.value = value;
    }

    /**
     * returns the value of the tree node
     * @return the value of the tree
     * **/
    public T getData() {
        return value;
    }

    /****
     * returns the left child of the tree
     * @return the left child of a tree. It returns a tree object.
     * **/
    public BST<T> getLeft() {
        return left;
    }

    /****
     * returns the right child of the tree
     * @return the right child of a tree. It returns a tree object.
     * **/
    public BST<T> getRight() {
        return right;
    }

    /**
     * checks if a value is in the tree.
     * @return true if it is the tree.
     * @return false if the value is not in the tree
     * **/
    public boolean contains( T searchValue ){

            if (searchValue.compareTo(this.value) == 0){
            return true;
        }

        if ( searchValue.compareTo(this.value)   < 0 ){
            if (this.left !=  null){
                return this.left.contains(searchValue);
            }
            else{
                return false;
            }
        }

        if (searchValue.compareTo(this.value) > 0){
            if (this.right !=  null){
                return this.right.contains(searchValue);
            }
            else{
                return false;
            }
        }

        return false;
    }

    /**
     * inserts a new value into th tree.
     * @param value the value inserted into the tree.
     * **/
    public void insert(T value){

        if (value.compareTo(this.value) == 0){
            return;
        }

        if ( value.compareTo(this.value)   < 0 ){
            if (this.left ==  null){
                this.setLeft(new BST<>(value,this));
            }
            else{
               this.left.insert(value);
            }
        }

        if (value.compareTo(this.value) > 0){
            if (this.right ==  null){
                this.setRight(new BST<>(value,this));
            }
            else{
                this.right.insert(value);
            }
        }
    }

    /****
     * removes a value from th tree.
     * @param value the value to be removed from the tree
     * @return null if the value is not in the tree.
     * @return the value if it is in th tree.
     *
     * **/
    public boolean remove( T value ){
        BST node =  find(value);

        if (node == null){
            return false;
        }

        BST parent = node.parent;
        BST tempNode;


        if (node.left ==  null && node.right == null){
            if (parent.getLeft() == node){
                parent.setLeft(null);
            }

            if (parent.getRight() == node){
                parent.setRight(null);
            }
        }

        if ( node.left !=  null && node.right != null ){
             tempNode = replacementNode(node.right);

            if(tempNode.parent.left == tempNode){
                tempNode.parent.setLeft(null);
            }

            if( tempNode.parent.right == tempNode ){
                tempNode.parent.setRight(null);
            }

             tempNode.parent = node.parent;
             tempNode.setLeft(node.getLeft());
             tempNode.setRight(node.getRight());

             if (parent.getLeft() == node ){
                 parent.setLeft(tempNode);
             }

            if (parent.getRight() == node ){
                parent.setRight(tempNode);
            }


        }
        return true;
    }

    /***
     * Used to search for a value in the tree.
     * @param value the value to being searched
     * @return a three node representing the value being searched for.
     *
     * ***/
    public BST find(T value){

        if (value.compareTo(this.value) == 0){
            return this;
        }

        if ( value.compareTo(this.value)   < 0 ){
            if (this.left ==  null){
               return null;
            }
            else{
               return this.left.find(value);
            }
        }

        if (value.compareTo(this.value) > 0){
            if (this.right ==  null){
                return null;
            }
            else{
               return this.right.find(value);
            }
        }

        return null;
    }

    /**
     * returns the leftmost node in a tree.
     * @param node the starting node to start searching from
     * @return a node representing the leftmost node from a starting node.
     * **/
    public BST replacementNode(BST node){

        if ( node.left == null ){
            return node ;
        }
        else{
            return replacementNode(node.left);
        }

    }


    /**
     * Builds a BST from an array.
     * The tree is built using a binary search on the array.This method only works for sorted arrays.
     * @param array that the bst is built from.
     * @return the final tree built.
     * **/

    public static <T extends Comparable> BST bstBuilder( T[] array){

        if ( array.length == 1 ){
            return new BST(array[0]);
        }

        int highIndex =  array.length-1;
        int lowIndex = 0;
        int midIndex = (highIndex + lowIndex) / 2  ;

        BST tree = null;

        if ( array.length > 0){
            tree  = new BST(array[midIndex]);
            BST leftNode = bstBuilder(Arrays.copyOfRange(array,0,midIndex));
            tree.setLeft(leftNode);

            BST rightNode = bstBuilder(Arrays.copyOfRange(array,midIndex+1,array.length));
            tree.setRight(rightNode);

        }



        return tree;

    }

    /**
     * Checks whether a node is a leaf or internal node.
     * @return true if a tree is a left
     * @return false if a tree is an internal node.
     * **/
    private boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    /**
     * gets the height of the tree
     * @return the height of the tree.
     * **/
    public int getTreeHeight(){
        int left = 0;
        int right = 0 ;
        int height = 1;

        if (this.isLeaf()){
            return 0;
        }

        if (this.left != null){
            if( !this.left.isLeaf() ){
                left = this.left.getTreeHeight();
            }
        }

        if (this.right != null){
            if (!this.right.isLeaf()){
                right = this.right.getTreeHeight();
            }
        }


        return 1 + Math.max(left,right);
    }

    /**
     * gets the height of the subtree of a tree node.
     * @return the height of the subtree of a tree node.
     * */

    private int getSubTreeHeight(BST<T> node) {
        if (node == null) {
            return 0;
        }

        return node.getTreeHeight();
    }

    /***
     * Checks if the tree is balanced or not
     * @return true is the tree is balanced
     * @return false if it is unbalanced
     *
     * */

    public boolean isBalanced(){

        if (this.isLeaf()){
            return true;
        }

        if (getSubTreeHeight(this.left) - getSubTreeHeight(this.right) > 1){
            return false;
        }

        if (getSubTreeHeight(this.left) - getSubTreeHeight(this.right) <= 1) {
            return this.left.isBalanced() && this.isBalanced();
        }



        return this.left.isBalanced() && this.right.isBalanced();
    }

}
