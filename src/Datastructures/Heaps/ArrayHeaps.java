package Datastructures.Heaps;

import java.util.ArrayList;

/***
 * Implementation of a heap using a custom array heap class
 * It follows the Minimum Heap Property principle
 * **/
public class ArrayHeaps<K extends Comparable , V> {


    private ArrayList<HeapNode> bucketArray;
    private int size;

    public ArrayHeaps( ){
        bucketArray =  new ArrayList<>();
        bucketArray.add(null);
        size++;
    }

    public ArrayHeaps(int originalSize){
        bucketArray =  new ArrayList<>(originalSize);
        bucketArray.add(null);

    }

    /**
     * returns the current size of the heap
     * @return the current size of the heap
     * */
    public int getSize() {
        return size;
    }



    /**
     * adds a new entry to the array heap.
     * I also preforms a bubble up operation if the heap new insertion violates the min heap property
     * **/
    public void enqueue( K key, V value ){
        bucketArray.add(new HeapNode(key,value));
        size++;
        bubbleUp();

    }

    /**
     * removes the entry at the root of the heap
     * only returns the value and no the key associated with it.
     * it also preforms a shift down operation to find a replacement fot the removed node
     * **/

    public V dequeue(){
        int heapHeadIndex =  1;
        V value = bucketArray.get(1).value;

        if (size < 2){
            return null;
        }

        if (size == 2){
            bucketArray.remove(bucketArray.size()-1);
            this.size--;
            return value;
        }


        HeapNode lastElement = bucketArray.get(bucketArray.size()-1);
        bucketArray.set(heapHeadIndex,lastElement);
        bucketArray.remove(bucketArray.size()-1);
        shiftDown();

        this.size-- ;
        return value;
    }

    /**
     * rearranges the elements in the eap after an insertion to maintain the minimum heap property
     * **/
    private void bubbleUp(){

        if (size <= 2) {
            return;
        }

        HeapNode lastElement =  bucketArray.get(bucketArray.size()-1);
        int lastElementIndex =  bucketArray.size() - 1;
        int parentIndex  = lastElementIndex/2;
        HeapNode parent = bucketArray.get(parentIndex);

        while ( parentIndex > 0 && lastElement.compareTo(parent) < 0 ){

            bucketArray.set(lastElementIndex,parent);
            bucketArray.set(parentIndex,lastElement);

            lastElementIndex = bucketArray.indexOf(lastElement);
            parentIndex = lastElementIndex/2;
            parent = bucketArray.get(parentIndex);
        }

    }

    /**
     *
     * swaps the values of two nodes in the heap.
     * It doesn't swap the keys.
     *
     * **/

    private void swap (HeapNode nodeA , HeapNode nodeB ){
        HeapNode temp = nodeA;
        nodeA = nodeB;
        nodeA = temp;
    }

    /**
     * Moves elements around in the heap ,after a deque to,
     * find a new root value that follow the min heap property.
     *
     * **/

    private void shiftDown(){

        HeapNode heapHead =  bucketArray.get(1);
        int parentIndex = bucketArray.indexOf(heapHead);
        HeapNode left = null;
        HeapNode right = null;
        HeapNode minChild = null;

        left = getLeft(parentIndex);
        right = getRight(parentIndex);

        minChild = getMinChild(left,right);

        boolean hasChildren = left != null || right != null;

        while ( hasChildren && heapHead.compareTo(minChild) > 0 ){

            int childIndex =  bucketArray.indexOf(minChild);

            bucketArray.set(childIndex,heapHead);
            bucketArray.set(parentIndex,minChild);

            parentIndex = bucketArray.indexOf(heapHead);

            left = getLeft(parentIndex);
            right = getRight(parentIndex);
            minChild = getMinChild(left,right);

            childIndex = bucketArray.indexOf(minChild);

            hasChildren = left != null || right != null;
        }
    }

    /**
     * returns the left child of a parent node in the heap.
     * @param parentIndex the index of the parent node in the array heap
     * @return the left child of the parent at parentIndex
     * **/
    private HeapNode getLeft(int parentIndex) {

        if ( parentIndex * 2 < bucketArray.size() ){
            return bucketArray.get(parentIndex *2);
        }
        return null;
    }

    /**
     * returns the right child of a parent node in the heap.
     * @param parentIndex the index of the parent node in the array heap
     * @return the right child of the parent at parentIndex
     * **/

    private HeapNode getRight(int parentIndex) {

        if ( parentIndex * 2+1 < bucketArray.size() ){
            return bucketArray.get(parentIndex *2+1);
        }
        return null;
    }

    /***
     *
     * Returns the heap node with the smallest priority
     * @param left the left node
     * @param right right node
     * @return  the node with the smallest priority (key Value)
     * **/

    private HeapNode getMinChild(HeapNode left, HeapNode right ){

        if( left == null ){
            return right;
        }

        if (right == null){
            return left;
        }

        if( left.compareTo(right) < 0  ){
            return left;
        }
        return right;
    }

    /***
     * returns the value of the root node in the array heap
     * */
    public V peek(){
        return bucketArray.get(1).value;
    }

    /***
     * Represents a wrapper class for entries into the array heap
     * **/
    private class HeapNode implements Comparable<HeapNode>{

        private K key;
        private V value;

        private HeapNode(K key , V value){
            this.key = key;
            this.value = value;
        }

        /**
         * returns the key (priority) of a node
         * @return the key of a node
         * **/
        private K getKey() {
            return key;
        }

        /**
         * returns the value of a node
         * @return the value of a node
         * **/
        private V getValue() {
            return value;
        }

        /**
         * sets the value of a node to the specified value
         * @param value the value to be set in the node
         * **/
        private void setValue(V value) {
            this.value = value;
        }

        /**
         * sets the key of a node to the specified key
         * @param key the key to be set in the node
         * **/
        private void setKey(K key) {
            this.key = key;
        }


        /**
         * compares 2 nodes together by their keys.
         * @param o the node being compared to.
         * @return 0 if they are equal , -1 if less than +1 if greater than.
         * **/

        @Override
        public int compareTo(HeapNode o) {
            return key.compareTo(o.key);
        }
    }






}
