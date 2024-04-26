package Algorithms;

import Datastructures.Graphs.DirectedGraph;
import Datastructures.Graphs.Graph;
import Datastructures.Graphs.UndirectedGraph;
import Datastructures.Graphs.Vertex;
import Datastructures.PriorityQueue;

import java.util.List;

public class GraphAlgorithms {

    public GraphAlgorithms(){}

    /***
     * Preforms Prims algorithm on the graph and returns a minimum spanning graph or tree
     * @params graph the graph to build the minimum spanning tree from.
     * @return a graph representation the minimum spanning tree.
     * **/

    public Graph prims(Graph graph){

        Graph minGraph  =  new UndirectedGraph();

        PriorityQueue<Integer, Vertex> queue = new PriorityQueue<>();

        Vertex v = (Vertex) graph.getVertices().getFirst();

        queue.enqueue(0,v);
        List<Vertex> adj = graph.getVertices();

        for (Vertex vertex : adj){
            vertex.distance = Integer.MAX_VALUE;
            minGraph.addVertex(new Vertex<>(vertex.getData()));
        }

        v.distance = 0;

        while (queue.getSize() > 0){

            Vertex graphVertex = queue.dequeue();

            if (!graphVertex.isVisited()){
                graphVertex.setVisited(true);
                if (graphVertex.parent !=null){
                    minGraph.addEdge(graphVertex.parent.data,graphVertex.data, graph.getWeight(graphVertex.parent,graphVertex));
                }
                for( Vertex v1 : (List<Vertex>)graphVertex.getAdjacencies()){
                    if (v1.distance > graph.getWeight(v1,graphVertex)){
                        v1.distance = graph.getWeight(v1,graphVertex);
                        v1.parent = graphVertex;
                        queue.enqueue(v1.distance, v1);
                    }
                }
            }

        }

        return minGraph;
    }
}
