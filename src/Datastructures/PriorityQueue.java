package Datastructures;

import Datastructures.Heaps.ArrayHeaps;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * A custom implementation of a priority queue
 * Achieved using a custom array heap.
 * Elements enqueued will automatically be ordered
 *
 * @param <K> represents the priority
 * @param <V> represents the value
 *
 * **/
public class PriorityQueue<K extends Comparable ,V>{

    private int size;

    private ArrayHeaps<K,V> heap;

    public PriorityQueue(){
        heap = new ArrayHeaps<>();
    }


    /**
     * Enqueues an element into the priority queue
     * @param key the key (priority of the element)
     * @param value the value of the element
     * **/
    public void enqueue(K key, V value){
        heap.enqueue(key,value);
        size++;
    }

    /**
     * removes the element in the root of the priority queue
     * It should be the element with the lowest priority in the queue
     *
     * @return the value at the root of the priority queue
     * **/

    public V dequeue(){
        return heap.dequeue();
    }

    /**
     * returns the element at the root of the queue
     *  It does not remove the element at the root though.
     * @return the value with the lowest priority in the queue
     * **/

    public V peek(){
        return heap.peek();
    }

    /**
     * returns the size of the priority queue
     * **/

    public int getSize(){
        return heap.getSize()-1;
    }

    public List<V> getSortedList(){
        List<V> sortedList =  new LinkedList<>();

        while (heap.getSize() > 1){
            sortedList.addLast(heap.dequeue());
        }

        return sortedList;

    }


}
