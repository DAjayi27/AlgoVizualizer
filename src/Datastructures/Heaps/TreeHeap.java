package Datastructures.Heaps;

import Datastructures.Trees.BinaryTree;

/**
 * This is a modifed version of a heap implemented with a binary tree.
 * It cant hold a tuple. Because i didn't want to modify the binary tree i already had
 * This heap follows the min heap property
 * **/
public class TreeHeap<T extends Comparable<T>> {
    private BinaryTree<T> heap;
    private BinaryTree<T> lastAddedElement;

    private BinaryTree<T> prevLastAddedElement;
    private int size;


    public TreeHeap( ){
        heap = null;
        lastAddedElement = null;
    }

    /**
     * enqueues a ne value into the heap.
     * I also preforms shifting operations to maintain the min heap property.
     * @param value the value being inserted into the heap.
     * **/
    public void enqueue( T value ){
        insert(value);
        size++;

        if ( size > 1){
            validateHeapProperty();
        }

    }

    /**
     * determines where next a value is to be added in the heap.
     * the values must be added left to right .
     * @param value the value being inserted.
     * **/
    private void insert(T value) {
        if ( heap == null  ){
            heap = new BinaryTree<>(value);
            lastAddedElement =  heap;
            return;
        }

        BinaryTree<T> lastAddedElementParent = lastAddedElement.getParent();

        if( lastAddedElement.getParent() == null ){
            if (lastAddedElement.getLeft() == null){
                lastAddedElement.setLeft(new BinaryTree<>(value,lastAddedElement));
                lastAddedElement.getLeft().previous = lastAddedElement;
                lastAddedElement = lastAddedElement.getLeft();
                return;
            }

            if (lastAddedElement.getRight() == null){
                lastAddedElement.setRight(new BinaryTree<>(value,lastAddedElement));
                lastAddedElement.getRight().previous = lastAddedElement;
                lastAddedElement = lastAddedElement.getRight();
                return;
            }

        }

        if (!lastAddedElementParent.isFilled()){
            lastAddedElementParent.setRight(new BinaryTree<>(value,lastAddedElementParent));
            lastAddedElementParent.getRight().previous = lastAddedElement;
            lastAddedElement = lastAddedElementParent.getRight();
        }
        else{
            BinaryTree next = findNextAvailable(lastAddedElement);
            next.setLeft(new BinaryTree<>(value,next));
            next.getLeft().previous = lastAddedElement;
            lastAddedElement = next.getLeft();
        }
    }

    /**
     * checks if the min heap property has being violated after an insert.
     * If it has been violated it preforms shifting operations to maintain the min heap property.
     * **/
    private void validateHeapProperty(){
        BinaryTree node = lastAddedElement;
        BinaryTree<T> parentNode =  lastAddedElement.getParent();

       while (parentNode != null && node.getData().compareTo(parentNode.getData())< 0 ){
           swap(node,parentNode);
           node = lastAddedElement.getParent();
           parentNode = node.getParent();
       }
    }


    /**
     * Swaps the data o f twon tree nodes
     * @param nodeA is the first node to be swapped
     * @param nodeB the second node to be swapped
     * **/
    private <T extends Comparable<T>> void swap(BinaryTree<T> nodeA, BinaryTree<T> nodeB) {
        T temp = nodeA.getData();

        nodeA.setData(nodeB.getData());
        nodeB.setData(temp);
    }

    /**
     * returns the position where the new value being inserted is inserted into
     * It ensures values are always added left to right.
     * @param startingNode the location for where the last node was added.
     * **/

    private BinaryTree findNextAvailable(BinaryTree startingNode){

        if (startingNode == null){
            return null;
        }
        BinaryTree parentNode = startingNode.getParent();
        BinaryTree lastParentAccessed = null;

        while (true){

            if (parentNode.getLeft() != startingNode && parentNode.getRight() != startingNode) {

                if (!parentNode.isFilled()){
                    return parentNode;
                }

                if (!parentNode.getLeft().isFilled()) {
                    return parentNode.getLeft();
                }

                if (!parentNode.getRight().isFilled()) {
                    return parentNode.getRight();
                }

            }

            if (parentNode.getParent() == null){
                if (lastParentAccessed == parentNode.getLeft()){
                    parentNode = parentNode.getRight();
                }
                else{
                    parentNode = parentNode.getLeft();
                }
            }
            else{
                lastParentAccessed = parentNode;
                parentNode = parentNode.getParent();
            }

        }

    }

    /**
     * removes the value at the root of the heap.
     * @return the tree node at the root of the heap.
     * **/
    public BinaryTree<T> dequeue( ){
        T data = this.heap.getData();
        this.heap.setData(lastAddedElement.getData());

        if (size == 0){
            return null;
        }

        if (size ==  1){
            BinaryTree temp =  heap;
            heap = null;
            return temp;
        }

        if (lastAddedElement.getParent().getLeft() == lastAddedElement){
            lastAddedElement.getParent().setLeft(null);
        }
        if (lastAddedElement.getParent().getRight() == lastAddedElement){
            lastAddedElement.getParent().setRight(null);
        }

        lastAddedElement.setParent(null);
        prevLastAddedElement = lastAddedElement.previous;
        lastAddedElement = prevLastAddedElement;

        bubbleDown();
        size--;
        return new BinaryTree<>(data);
    }


    /**
     * this is called after a value is dequeued to ensure the replacement value
     * follow the min heap property.
     * **/
    private void bubbleDown(){
        BinaryTree node =  heap;

        BinaryTree childNode = minNode(node.getLeft() , node.getRight());

        while ( childNode != null && node.getData().compareTo(childNode.getData()) > 0 ){
            swap(node,childNode);
            node = childNode;
            childNode = minNode(node.getLeft(),node.getRight());
        }

    }

    /***
     * returns the tree node with the lowst data value.
     * @param nodeLeft the first value
     * @param nodeRight the second value.
     * **/
    private BinaryTree minNode(BinaryTree nodeLeft, BinaryTree nodeRight){

        if (nodeLeft == null){
            return nodeRight;
        }

        if (nodeRight == null){
            return nodeLeft;
        }

        if (nodeLeft.getData().compareTo(nodeRight.getData()) < 1){
            return nodeLeft;
        }
        return nodeRight;
    }




}
