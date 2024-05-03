package Datastructures.Graphs;
import java.util.*;

/**
 * Implementation of the vertex class
 * with an adjadency list, implemented using a hash table.
 *
 * **/
public class Vertex <T extends Comparable<T>> {
    public T data;
    public int distance;
    private boolean isVisited = false;
    private Hashtable<T,Vertex<T>> vertixAdjadency;
    private Hashtable<Vertex<T>,Integer> vertixAdjadencyWeights;
    public Vertex<T> parent;
    public int minWeight = Integer.MAX_VALUE;
    public int maxWeight = Integer.MIN_VALUE;


    public Vertex(){
        this.vertixAdjadency =  new Hashtable<>();
        vertixAdjadencyWeights = new Hashtable<>();
        this.data = null;
        this.distance =  0;
    }

    public Vertex(T data){
        this.vertixAdjadency =  new Hashtable<>();
        this.data = data;
        vertixAdjadencyWeights = new Hashtable<>();
        this.distance =  0;
    }

    public T getData() {
        return this.data;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void addAdjadency(Vertex<T> adj ){
        vertixAdjadency.put( adj.getData()  , adj );
    }

    public void addAdjadencyWeights(Vertex<T> adj, int weight ){
        vertixAdjadencyWeights.put( adj,weight );

        minWeight = Math.min(weight,minWeight);
        maxWeight= Math.max(weight,maxWeight);

    }
    
    public List<Vertex<T>> getAdjacencies(){
        return new LinkedList<>(vertixAdjadency.values()) ;
    }

    public List<T> getAdjacencyValues(){
        List<T> list = new ArrayList<>();
        
        for (Vertex<T> vertex : vertixAdjadency.values()){
            list.add(vertex.data);
        }

       list.sort(null);

        return list;
    }

    public int getWeight(Vertex<T> vertex){

        if (!this.vertixAdjadency.contains(vertex)){
            return 0;
        }


        if ( this.vertixAdjadencyWeights.get(vertex) == null){
            return 0;
        }

        return this.vertixAdjadencyWeights.get(vertex) ;

    }
    public void removeAdjacency(Vertex<T> vertex){
        vertixAdjadency.remove(vertex);

        // remove the weight if it exists
        vertixAdjadencyWeights.remove(vertex);
    }

    public void clearAdjacencies(){
        this.vertixAdjadency.clear();
        this.vertixAdjadencyWeights.clear();
    }

    


}
