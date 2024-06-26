import Datastructures.Graphs.Graph;
import Datastructures.Graphs.UndirectedGraph;

import javax.swing.*;
import java.util.Random;

public class VizTests {
    public static void main(String[] args) throws InterruptedException {
        JFrame testEnv = new JFrame("Test Env");

        testEnv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testEnv.setLocationRelativeTo(null);

        Graph<String> testGraph =  new UndirectedGraph<>();


        GraphVisualizer testViz  = new GraphVisualizer(testGraph,500,500);

        testEnv.add(testViz);
        testEnv.pack();
        testEnv.setVisible(true);
        testEnv.setVisible(true);

        for (int i = 0; i < 20; i++) {

            int ascii = 97 + i;
            char letter = (char) ascii;

            testGraph.addVertex(Character.toString(letter));
            testViz.repaint();
            Thread.sleep(200);
        }

        Random random =  new Random();

        for (int i = 0; i < 30; i++) {

           int vertexAAscii = random.nextInt(19) + 97;
           int vertexBAscii = random.nextInt(19) + 97;

           String vertexA = Character.toString((char) vertexAAscii);
           String vertexB = Character.toString((char) vertexBAscii);

           testGraph.addEdge(vertexA,vertexB);
            testViz.repaint();
            Thread.sleep(200);
        }




    }
}
