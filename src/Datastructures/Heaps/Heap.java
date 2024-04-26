package Datastructures.Heaps;

import Datastructures.Trees.BinaryTree;

public interface Heap <K extends Comparable<K> , V>{
    public void enqueue( K value );
    public BinaryTree<K> dequeue( );
}
