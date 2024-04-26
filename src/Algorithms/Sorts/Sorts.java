package Algorithms.Sorts;

import Datastructures.Graphs.DirectedGraph;
import Datastructures.Graphs.Vertex;
import Datastructures.PriorityQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sorts {

    public Sorts(){}

    /***
     *
     * Preforms a topological sort on a directed acyclic graph
     * @return a list representing the sorted elements in the graph.
     *
     * **/
    public  List<String> topSort(DirectedGraph graph){

        Vertex vertex  = graph.getRandVertex();
        List<Vertex<String>> adj =  graph.getVertices();
        int num = graph.getSize();

        num =  recursiveSearch(vertex,num);

        for (Vertex v : adj){
            num =  recursiveSearch(v,num);
        }

        PriorityQueue<Integer,String> queue =  new PriorityQueue<>();

        for (Vertex<String> v : adj){
            queue.enqueue(v.distance,v.getData());
        }

        return queue.getSortedList();
    }

    /**
     * helper method for topological sort.
     * **/

    private int recursiveSearch(Vertex vertex, int numValue){
        List<Vertex> adj = vertex.getAdjacencies();

        if (vertex.isVisited()){
            return numValue;
        }

        if (vertex.getAdjacencies().isEmpty()){
            vertex.distance = numValue;
            vertex.setVisited(true);
            numValue--;
            return numValue;
        }
        else{
            for (Vertex v : adj){
                numValue =   recursiveSearch(v,numValue);
            }
            vertex.setVisited(true);
            vertex.distance = numValue;
            numValue --;
            return numValue;
        }

    }

    /**
     * Preforms insertion Sort on a List
     * **/
    public  <T extends Comparable<T>> void insertSort(List<T> list){

        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                T value1 =  list.get(j);
                T value2 =  list.get(j-1);

                if (value2.compareTo(value1) > 0){
                    T temp =  value1;
                    value1 = value2;
                    value2 = temp;

                    list.set(j,value1);
                    list.set(j-1,value2);
                }
            }
        }

    }

    /***
     *
     * Preforms a heap sort on a list passed in to the function
     * **/

    public  <T extends Comparable<T>> void heapsort(List<T> list){

        PriorityQueue<T,T> heap = new PriorityQueue<>();

        for (T data : list){
            heap.enqueue(data,data);
        }

        list.clear();

        while (heap.getSize() > 0){
            list.add(heap.dequeue());
        }

    }

    /***
     *
     * preforms merge sort on a list passed into the function
     *
     * **/

    public  <T extends Comparable<T>> List<T> mergeSort(List<T> list){
        int midPoint =  list.size()/2;
        List<T> list1 ;
        List<T> list2;

            if (list.size() > 1 ){
            list1 = list.subList(0,midPoint);
            list2 = list.subList(midPoint,list.size());
           list1 =  mergeSort(list1);
           list2 =  mergeSort(list2);
           list =  merge(list1,list2);
        }

        return list;


    }

    private   <T extends Comparable<T>> List<T> merge(List<T> list1,List<T> list2){

        int firstListIndex = 0;
        int secondListIndex = 0;

        List<T> mergedList = new ArrayList<>();

        while (firstListIndex < list1.size() || secondListIndex < list2.size()){



            if (firstListIndex >= list1.size()){
                T secondListValue = list2.get(secondListIndex);
                mergedList.add(secondListValue);
                secondListIndex++;
                continue;
            }

            if (secondListIndex >= list2.size()){
                T firstListValue = list1.get(firstListIndex);
                mergedList.add(firstListValue);
                firstListIndex++;
                continue;
            }

            T firstListValue = list1.get(firstListIndex);
            T secondListValue = list2.get(secondListIndex);

            if (firstListValue.compareTo(secondListValue) < 0){
                mergedList.add(firstListValue);
                firstListIndex++;
            }
            else{
              mergedList.add(secondListValue);
              secondListIndex++;
            }


        }



        return mergedList;

    }

    public  <T extends Comparable<T>> void quickSort(List<T> list){

        if (list.size() <= 1){
            return;
        }

        Random rand =  new Random(System.currentTimeMillis());
        int pivotIndex = rand.nextInt(list.size()-1);
        int lowIndex =  1;
        int highIndex =  list.size();
        T pivot = list.get(pivotIndex);

        swap(0,pivotIndex,list);
        pivotIndex = 0;

        while (lowIndex < highIndex){
            T compareValue =  list.get(lowIndex);

            if (pivot.compareTo(compareValue) < 0){
                highIndex--;
                swap(lowIndex,highIndex,list);
            }

            else{
                lowIndex++;
            }

        }

        swap(pivotIndex,highIndex-1,list);
        pivotIndex = highIndex -1;

        quickSort(list.subList(0,pivotIndex));
        quickSort(list.subList(pivotIndex+1,list.size()));




    }

    public   <T extends Comparable<T>> void swap(int dataA , int dataB, List<T> list){

        T secondData = list.get(dataA);
        T firstData = list.get(dataB);


        list.set(dataA,firstData);
        list.set(dataB,secondData);



    }





}
