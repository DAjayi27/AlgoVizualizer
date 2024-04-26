package Datastructures.Graphs;
import Datastructures.PriorityQueue;

import java.util.*;

/**
 * This graph is implemented using an adjadency list for all the vertices in the graph
 * the implementation is meant to be for an undirected graph
 * **/
public class UndirectedGraph<T extends Comparable<T>> implements Graph<T> {
    public HashMap<T, Vertex<T>> vertexList;
    private boolean dfsFoundFlag = false;

    public UndirectedGraph(){
        vertexList = new HashMap<>();
    }

    /**
     * Add a new vertex to the graph. only using the vertex data.
     * **/
    public void addVertex(T vertexData) {
        vertexList.put(vertexData,new Vertex<>(vertexData));
    }

    /**
     * Add a new vertex to the graph using a vertex object
     * **/
    public void addVertex(Vertex<T> vertexData) {
        vertexList.put(vertexData.data,vertexData);
    }

    /***
     * returns the vertex with the specified data, or null
     * if the vertex is not in the graph
     * @return the vertex with the specified data.
     * */
    public Vertex<T> getVertex( T data ){
        return vertexList.get(data);
    }

    /**
     * returns a reference to the list of vertices in the vertex list.
     * @return list of vertices in the vertex list
     * **/
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>( vertexList.values());
    }

    /**
     * Returns a copy of the list of vertices in the vertex list
     * @return a copy of the list of vertices in the vertex list
     * **/
    public List<Vertex<T>> getVerticesCopy() {
        return new LinkedList<>(vertexList.values());
    }


    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
    public void addEdge(T a, T b) {
       Vertex<T> personA =  vertexList.get(a);
       Vertex<T> personB =  vertexList.get(b);

       if (personA  == null || personB == null ){
           throw new NoSuchElementException("one of the vertex can't be found");
       }

       addEdge(personA,personB);
    }


    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
    public void addEdge(T a, T b,int weight) {
        Vertex<T> personA =  vertexList.get(a);
        Vertex<T> personB =  vertexList.get(b);

        if (personA  == null || personB == null ){
            throw new NoSuchElementException("one of the vertex can't be found");
        }

        addEdge(personA,personB,weight);
    }

    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * If the two vertices are the same, no action is preformed.
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/

    public void addEdge(Vertex<T> a , Vertex<T> b){

        if (!vertexList.containsKey(a.getData())){
            throw new NoSuchElementException("vertex not in the graph");
        }

        if (!vertexList.containsKey(b.getData())){
            throw new NoSuchElementException("vertex not in the graph");
        }

        if (a.getData().equals(b.getData())){
            return;
        }

        boolean isConnected = a.getAdjacencies().contains(b) && b.getAdjacencies().contains(a);
        if (isConnected){
            return;
        }


        a.addAdjadency(b);
        b.addAdjadency(a);
    }

    /**
     * Adds a connection between 2 vertices in the graph, with an associated weight.
     * If they are already connected, no action is preformed
     * If the two vertices are the same, no action is preformed.
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
    public void addEdge(Vertex<T> a , Vertex<T> b, int weight){
        addEdge(a,b);
        a.addAdjadencyWeights(b,weight);
        b.addAdjadencyWeights(a,weight);

    }

    /**
     * returns wether or not the graph is empty
     * @return true if it is empty , false if it is not empty
     * **/
    public boolean isEmpty(){
        return vertexList.isEmpty();
    }

    /**
     *
     * returns the weights between 2 vertices
     * returns 0 if there is no connection or weight assigned between 2 nodes
     * @param a the first vertex
     * @param b the second vertex
     * @return the weight between two vertices
     * **/
    public int getWeight( Vertex<T> a , Vertex<T> b ){

        if (a == null || b == null){
            return 0;
        }

        return a.getWeight(b);
    }


    /**
     * removes a vertex completely from the graph.
     * i also removes all its adjacency and connections to all adjacency
     *
     * @param vertex is the vertesx to be removed
     * **/
    public void removeVertex ( Vertex<T> vertex ){
        List<Vertex<T>> vertexAdjadency =  vertex.getAdjacencies();

        for (Vertex<T> adj : vertexAdjadency){
            adj.removeAdjacency(vertex);

        }
        vertex.clearAdjacencies();

        vertexList.remove(vertex.data);
    }

    /**
     *  Removes the connection between 2 nodes.
     *  I also removes the weights associated with the nodes if present
     *
     * **/
    public void removeEdge(Vertex<T> a, Vertex<T> b){
        a.removeAdjacency(b);
        b.removeAdjacency(a);
    }

    /**
     * checks if a vertex is inside the graph.
     * @return true if the vertex is in the graph, false if the vertex ix not in the graph
     * **/
    public boolean contains(Vertex<T> vertex){
        return contains(vertex.data);
    }

    /**
     * checks if a vertex is inside the graph. the check is preformed using the data
     * @return true if the vertex is in the graph, false if the vertex ix not in the graph
     * **/
    public boolean contains(T data){
        return vertexList.containsKey(data) ;
    }

    /**
     *
     * returns the list of adjacencies of a given vertex within the graph
     * @return a list of adjacency of the vertex, null if the vertex is not in the graph or the vertex is null
     *
     * **/

    public List<Vertex<T>> getAdjacencies(Vertex<T> vertex){
        if (vertex == null){
            return null;
        }

        if (!vertexList.containsValue(vertex)){
            return null;
        }

        return vertex.getAdjacencies();
    }

    /**
     * checks if the graph is fully connected or not
     * @return true if the graph is fully connected, false if the graph is not fully connected.
     * **/
    public boolean isConnected(){
        Vertex<T> vertex = new LinkedList<>(vertexList.values()).getFirst();
        Set<Vertex<T>> visited =  new HashSet<>();
       return countVerticies(vertex,visited) == vertexList.size();
    }

    /**
     * counts the number of nodes that can be visited once in the graph.
     * uses a DFS (depth first search) traversal to preform the operation.
     * @return the number of vertices that can be visited once in graph
     * **/
    public int countVerticies(Vertex<T> vertex ,Set<Vertex<T>> visited ){
        int count  = 0;
        if (!visited.contains(vertex)){
            count =  1;
            visited.add(vertex);
            List<Vertex<T>> vertexAdjadency =  vertex.getAdjacencies();
            for (Vertex<T> v : vertexAdjadency){
               count+= countVerticies(v,visited);
            }
        }
        return count;
    }

    /**
     * Custom path finding algorithm using DFS
     * **/
    public void pathFindingDFS(Vertex<T> a , Vertex<T> b , List<Vertex<T>> path){
        this.dfsFoundFlag = false;
        dfsHelper(a, b, path);
        this.dfsFoundFlag = false;
    }

    /**
     * Custom path finding algorithm using DFS.
     * Uses a flag to signal when the vertex has been found.
     * @apiNote : refactor this to look more like the bfs using parent references instead of a found flag.
     * **/
    private void dfsHelper(Vertex<T> a, Vertex<T> b, List path) {
        if (this.dfsFoundFlag){
            return;
        }

        if (a.equals(b)){
            path.add(a.getData());
            this.dfsFoundFlag = true;
            return;
        }

        if (!a.isVisited()){
            a.setVisited(true);
            path.add(a.getData());
            List<Vertex<T>> vertexAdjadency =  a.getAdjacencies();
            for (Vertex<T> v : vertexAdjadency){
                dfsHelper(v, b, path);
            }
            if (!this.dfsFoundFlag){
                path.remove(path.size()-1);
            }

        }
    }

    /**
     *
     * Preforms a BFS (breadth first search ) on the graph from the strting to end point.
     * @return a list representing the path between the 2 vertices.
     * **/
    public List<T> bfsSearch(String startingPoint, String endPoint){
        Vertex<T> vertexA = vertexList.get(startingPoint);
        Vertex<T> vertexB = vertexList.get(endPoint);

        if (vertexA == null || vertexB == null){
            return null;
        }

       return bfsSearch(vertexA,vertexB);
    }

    /**
     *
     * Preforms a BFS (breadth first search ) on the graph from the strting to end point.
     * @return a list representing the path between the 2 vertices.
     * **/
    public List<T> bfsSearch(Vertex<T> startingPoint, Vertex<T> endPoint){
        bfsHelper(startingPoint,endPoint);

        List<T> shortestPath =  new LinkedList<>();

        Vertex<T>vertex =  startingPoint;

        while (vertex != null){
            shortestPath.add(vertex.data);
            vertex = vertex.parent;
        }

        return shortestPath;
    }

    /**
     * Preforms the bfs on the graph, starting from the end point to the start point
     * **/

    public void bfsHelper( Vertex<T> startingPoint, Vertex<T> endPoint ){
        Vertex<T> vertex =  endPoint;
        Set<Vertex<T>> visited =  new HashSet<>();
        Queue<Vertex<T>> graphVerticies =  new LinkedList<>();

        graphVerticies.add(vertex);
        visited.add(endPoint);

        while (!graphVerticies.isEmpty()){
            vertex = graphVerticies.remove();


            for (Vertex<T> adj: vertex.getAdjacencies()){
                if( !visited.contains(adj)){
                    adj.parent = vertex;
                    if (adj.equals(startingPoint)){
                        return;
                    }

                    visited.add(adj);
                    graphVerticies.add(adj);
                }

            }

        }
    }


    /***
     * Preforms pathfinding between 2 vertices using dijkstra's algorithm.
     * Parent refrences formed need to be cleared afterwards.
     * @param startingNode starting node
     * @param destinationNode node to be found
     * **/
    public List<T> dijkstra(Vertex<T> startingNode, Vertex<T> destinationNode){
        List<T> path =  new ArrayList<>();
        Set<Vertex<T>> visited =  new HashSet<>();
        dijkstraHelper(startingNode,visited);
        path.add(0,destinationNode.getData());
        Vertex tempNode;

        while (destinationNode.parent != null){
            path.add(0,destinationNode.parent.getData());
            destinationNode = destinationNode.parent;
        }
        resetParentRefrences();

        return path;
    }

    /**
     * reset's the parent references of all the vertices in the graph, to avoid reference errors.
     * **/
    private void resetParentRefrences() {
        for (Vertex<T> vertex : vertexList.values()){
            vertex.parent = null;
        }
    }

    /***
     * Helper method for  dijkstra search method.
     *
     * @param v the starting node
     * @param visited the set of visited nodes.
     * **/
    private void dijkstraHelper( Vertex<T> v , Set visited ){
       for( Vertex<T> v1 : vertexList.values() ){
            v1.distance = Integer.MAX_VALUE;
       }

       v.distance = 0;
       Datastructures.PriorityQueue<Integer,Vertex<T>> pq = new Datastructures.PriorityQueue<>();
        int overallDistance  = 0;
       pq.enqueue(0,v);

       while (pq.getSize() > 1){
           Vertex<T> vertex = pq.dequeue();

           if (!visited.contains(vertex)){
               List<Vertex<T>> list = vertex.getAdjacencies();
               visited.add(vertex);
               for (Vertex<T> vertexAdj : list ){
                    overallDistance = vertex.distance + getWeight(vertex,vertexAdj);
                    if ( overallDistance < vertexAdj.distance ){
                        vertexAdj.distance = overallDistance;
                        vertexAdj.parent = vertex;
                        pq.enqueue(vertexAdj.distance,vertexAdj);
                    }
               }
           }
       }

    }


    /***
     * Preforms pathfinding b   etween 2 vertices using dijkstra's algorithm.
     * Parent refrences formed need to be cleared af    terward.
     * @param startingNode starting node
     * @param destinationNode node to be found
     * **/
    public List<T> modifiedDijkstra(Vertex<T> startingNode, Vertex<T> destinationNode){
        List<T> path =  new ArrayList<>();
        Set<Vertex<T>> visited =  new HashSet<>();
        modifiedDijkstraHelper(startingNode,visited);
        path.add(0,destinationNode.getData());
        Vertex tempNode;

        while (destinationNode.parent != null){
            path.add(0,destinationNode.parent.getData());
            destinationNode = destinationNode.parent;
        }
        resetParentRefrences();

        return path;
    }

    /***
     * Helper method for  dijkstra search method.
     *
     * @param v the starting node
     * @param visited the set of visited nodes.
     * **/
    private void modifiedDijkstraHelper( Vertex<T> v , Set visited ){
        for( Vertex<T> v1 : vertexList.values() ){
            v1.distance = Integer.MAX_VALUE;
        }

        v.distance = 0;
        Datastructures.PriorityQueue<Integer,Vertex<T>> pq = new PriorityQueue<>();
        int overallDistance  = 0;
        pq.enqueue(0,v);

        while (pq.getSize() > 1){
            Vertex<T> vertex = pq.dequeue();

            if (!visited.contains(vertex)){
                List<Vertex<T>> list = vertex.getAdjacencies();
                visited.add(vertex);
                for (Vertex<T> vertexAdj : list ){
                    overallDistance = vertex.distance + (getWeight(vertex,vertexAdj)*-1);
                    if ( overallDistance < vertexAdj.distance ){
                        vertexAdj.distance = overallDistance;
                        vertexAdj.parent = vertex;
                        pq.enqueue(vertexAdj.distance,vertexAdj);
                    }
                }
            }
        }

    }


}
