package Datastructures.Graphs;

import java.util.*;

public interface Graph<T extends Comparable<T>> {
    /**
     * Add a new vertex to the graph. only using the vertex data.
     * **/
    void addVertex(T vertexData);

    /**
     * Add a new vertex to the graph using a vertex object
     * **/
    void addVertex(Vertex<T> vertexData);

    /***
     * returns the vertex with the specified data, or null
     * if the vertex is not in the graph
     * @return the vertex with the specified data.
     * */
    Vertex<T> getVertex(T data);

    /**
     * returns a reference to the list of vertices in the vertex list.
     * @return list of vertices in the vertex list
     * **/
    List<Vertex<T>> getVertices();

    /**
     * Returns a copy of the list of vertices in the vertex list
     * @return a copy of the list of vertices in the vertex list
     * **/
    List<Vertex<T>> getVerticesCopy();


    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
    void addEdge(T a, T b);

    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
     void addEdge(T a, T b,int weight) ;

    /**
     *
     * Adds a connection between 2 vertices in the graph
     * If they are already connected, no action is preformed
     * If the two vertices are the same, no action is preformed.
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/

    void addEdge(Vertex<T> a, Vertex<T> b);

    /**
     * Adds a connection between 2 vertices in the graph, with an associated weight.
     * If they are already connected, no action is preformed
     * If the two vertices are the same, no action is preformed.
     * @throws NoSuchElementException if one of the 2 vertices is not in the graph
     * **/
    void addEdge(Vertex<T> a, Vertex<T> b, int weight);

    /**
     * returns weather or not the graph is empty
     * @return true if it is empty , false if it is not empty
     * **/
    boolean isEmpty();


    /**
     * removes a vertex completely from the graph.
     * I also removes all its adjacency and connections to all adjacency
     *
     * @param vertex is the vertex to be removed
     * **/
    void removeVertex(Vertex<T> vertex);

    /**
     *  Removes the connection between 2 nodes.
     *  I also removes the weights associated with the nodes if present
     *
     * **/
    void removeEdge(Vertex<T> a, Vertex<T> b);

    /**
     * checks if a vertex is inside the graph.
     * @return true if the vertex is in the graph, false if the vertex ix not in the graph
     * **/
    boolean contains(Vertex<T> vertex);

    /**
     * checks if a vertex is inside the graph. the check is preformed using the data
     * @return true if the vertex is in the graph, false if the vertex ix not in the graph
     * **/
    boolean contains(T data);

    /**
     *
     * returns the list of adjacency of a given vertex within the graph
     * @return a list of adjacency of the vertex, null if the vertex is not in the graph or the vertex is null
     *
     * **/

    List<Vertex<T>> getAdjacencies(Vertex<T> vertex);

    /**
     * checks if the graph is fully connected or not
     * @return true if the graph is fully connected, false if the graph is not fully connected.
     * **/
    boolean isConnected();

    /**
     *
     * returns the weights between 2 vertices
     * returns 0 if there is no connection or weight assigned between 2 nodes
     * @param a the first vertex
     * @param b the second vertex
     * @return the weight between two vertices
     * **/
    public int getWeight( Vertex<T> a , Vertex<T> b );
}