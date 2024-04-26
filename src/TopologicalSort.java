import Datastructures.Graphs.DirectedGraph;
import Algorithms.Sorts.Sorts;

import java.util.List;

public class TopologicalSort {

    public static void main(String[] args) {
        DirectedGraph<String>  test  = new DirectedGraph<>();

        for (int i = 0; i < 7; i++) {
            char ascciVal = 65;
            ascciVal += i;

            test.addVertex(Character.toString(ascciVal));
        }

        test.addEdge("A","B");
        test.addEdge("A","C");
        test.addEdge("B","F");
        test.addEdge("F","G");
        test.addEdge("C","D");
        test.addEdge("C","E");

        Sorts sort = new Sorts();

        List<String> list = sort.topSort(test);

        for (String element  : list){
            System.out.println(element);
        }

    }


}
