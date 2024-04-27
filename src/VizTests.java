import Datastructures.Graphs.Graph;
import Datastructures.Graphs.UndirectedGraph;

import javax.swing.*;

public class VizTests {
    public static void main(String[] args) {
        JFrame testEnv = new JFrame("Test Env");

        testEnv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testEnv.setLocationRelativeTo(null);

        Graph<String> testGraph =  new UndirectedGraph<>();

        testGraph.addVertex("A");
        testGraph.addVertex("B");
        testGraph.addVertex("C");
        testGraph.addVertex("D");
        testGraph.addVertex("E");

        GraphVisualizer testViz  = new GraphVisualizer(testGraph,500,500);

        testEnv.add(testViz);
        testEnv.pack();
        testEnv.setVisible(true);




    }
}
