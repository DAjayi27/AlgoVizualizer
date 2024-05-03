import Datastructures.Graphs.Graph;
import Datastructures.Graphs.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GraphVisualizer extends JPanel {

    private Graph vizGraph;
    private HashMap<Vertex,Point> vertexPositions;

    public <T extends Comparable<T>> GraphVisualizer(Graph graph, int horizontalSize, int verticalSize ){
        this.setPreferredSize(new Dimension(horizontalSize,verticalSize));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        this.vertexPositions = new HashMap<>();

        vizGraph = graph;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        //btw the y coordinates are flipped  in a JPanel
        final int DIVISION_CONST = 10000;
        int verticalBound =  this.getHeight();
        int horizontalBound =  this.getWidth();
        int adjustedVertexSize =  (verticalBound*horizontalBound) / DIVISION_CONST;
        populatePanel(graphics2D, horizontalBound, verticalBound, adjustedVertexSize);
        drawAdjacencies(graphics2D,adjustedVertexSize);



    }


    /***
     * Adds the Adjacencies of each vertex in the graph to the j panel.
     * @param graphics the object being drawn to.
     * ***/
    private void drawAdjacencies(Graphics2D graphics, int adjustedVertexSize) {
        List<Vertex> adjacencies = vizGraph.getVertices();
        for (Vertex vertex : adjacencies){
            List<Vertex> nestedAdjacency = vertex.getAdjacencies();
            for (Vertex adj: nestedAdjacency){

                Point startingPosition = vertexPositions.get(vertex);
                Point endPosition = vertexPositions.get(adj);

                int associatedWeight =  vertex.getWeight(adj);

                drawCurve(graphics,startingPosition,endPosition,adjustedVertexSize,associatedWeight);

            }
        }
    }

    /***
     * Draws a curve / line between 2 vertex points.
     * @param g the graphics object drawn to.
     * @param startingPosition the starting position of the curve
     * @param endPosition the end position of the curve.
     * **/
    private void drawCurve(Graphics2D g,Point startingPosition, Point endPosition,int adjustedVertexSize,int weight){
        double controlPointX = (startingPosition.x + endPosition.x)/2.0;
        double controlPointY = ((startingPosition.y + endPosition.y)/2.0) -30;

        int startX = startingPosition.x ;
        int startY = startingPosition.y;

        int endX = endPosition.x;
        int endY = endPosition.y;

        generateOptimalStartingAndEndPoints(startX,startY,endX,endY);



        QuadCurve2D curve = new QuadCurve2D.Double(startX,startY,controlPointX,controlPointY,endX,endY);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.draw(curve);
        drawWeight(g,startX,startY,endX,endY,weight);
        drawDirection(g,startX,startY,adjustedVertexSize);

    }

    private void generateOptimalStartingAndEndPoints(int startX, int startY, int endX, int endY) {
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
    private void
    populatePanel(Graphics2D g, int horizontalBound, int verticalBound, int adjustedVertexSize) {
        List<Vertex> adjacencies = vizGraph.getVertices();

        Random rand =  new Random(System.currentTimeMillis());

        for(Vertex vertex : adjacencies){

            int xPosition ;
            int yPosition ;

            int adjustedTextXPosition;
            int adjustedTextYPosition;

            boolean vertexHasNoPosition = vertexPositions.get(vertex) == null;
            if (vertexHasNoPosition){

                 xPosition =  rand.nextInt(horizontalBound-adjustedVertexSize)  ;
                 yPosition = rand.nextInt(verticalBound-adjustedVertexSize) ;
                 vertexPositions.put(vertex,new Point(xPosition,yPosition));
            }
            else{
                Point position =  vertexPositions.get(vertex);
                xPosition = (int)position.getX();
                yPosition = (int)position.getY();
            }

            adjustedTextXPosition =  xPosition + (adjustedVertexSize / 2);
            adjustedTextYPosition = yPosition + (adjustedVertexSize / 2);




            g.drawOval(xPosition,yPosition, adjustedVertexSize, adjustedVertexSize);
            g.drawString(vertex.getData().toString(),adjustedTextXPosition,adjustedTextYPosition);

        }
    }

    /***
     * Draws the weights between 2 vertexes onto the graph object
     * The weighst are drawn at the mid point of the curve connection 2 verticies
     * @param graphics the graphics object being drawn to
     * @param startX the starting x position of the curve drawn
     * @param startY the starting y position of the curve drawn
     * @param endX the ending x position of the curve drawn
     * @param endY the ending y position of the curve drawn
     * @param weight the weight value being drawn.
     */
    public void drawWeight(Graphics2D graphics, int startX, int startY, int endX, int endY,int weight){

        int weightXPosition  = (startX + endX) /2;
        int weightYPosition =  (startY + endY) / 2;
        graphics.drawString(Integer.toString(weight),weightXPosition,weightYPosition);

    }

    /**
     * Draws an arrow representing the direction of the connection between 2 verticies in a grapg
     * @param g the graphics object being drawn to
     * @param startX the stating x position where the arrow is being drawn.
     * @param startY the stating y position where the arrow is being drawn.
     * @param vertexSize used to offset the starting position to draw the arrow from
     * **/
    private void drawDirection(Graphics2D g, int startX, int startY,int vertexSize) {



        g.drawLine(startX,startY,startX-10, startY + 10);

        g.drawLine(startX,startY,startX+10, startY + 10);


    }

}
