import Datastructures.Graphs.Graph;
import Datastructures.Graphs.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GraphVisualizer extends JPanel {

    private Graph vizGraph;

    public <T extends Comparable<T>> GraphVisualizer(Graph graph, int horizontalSize, int verticalSize ){
        this.setPreferredSize(new Dimension(horizontalSize,verticalSize));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);

        vizGraph = graph;
    }

    @Override
    public void paint(Graphics g) {

        //btw the y coordinates are flipped  in a JPanel
        final int DIVISION_CONST = 10000;
        int verticalBound =  this.getHeight();
        int horizontalBound =  this.getWidth();
        int adjustedVertexSize =  (verticalBound*horizontalBound) / DIVISION_CONST;
        populatePanel(g, horizontalBound, verticalBound, adjustedVertexSize);



    }

    /**
     * Called to populate the panel with the vertices in a graph
     * Draws a circle on the j panel for each vertex in the graph.
     * Meaning if there are 5 vertices in the graph, 5 circles are drawn.
     * @param g the graphics object drawn to
     * @param horizontalBound the horizontal bound of the j panel
     * @param verticalBound the vertical bound of the j panel
     * @param adjustedVertexSize the size of ech vertex adjusted for panel size and number of vertices
     * **/
    private void populatePanel(Graphics g, int horizontalBound, int verticalBound, int adjustedVertexSize) {
        List<Vertex> adjacencies = vizGraph.getVertices();

        Random rand =  new Random(System.currentTimeMillis());

        for(Vertex vertex : adjacencies){


            int xPosition =  rand.nextInt(horizontalBound-adjustedVertexSize)  ;
            int yPosition = rand.nextInt(verticalBound-adjustedVertexSize) ;

            int adjustedTextXPosition =  xPosition + (adjustedVertexSize / 2);
            int adjustedTextYPosition = yPosition + (adjustedVertexSize / 2);

            g.drawOval(xPosition,yPosition, adjustedVertexSize, adjustedVertexSize);
            g.drawString(vertex.getData().toString(),adjustedTextXPosition,adjustedTextYPosition);

        }
    }
}
