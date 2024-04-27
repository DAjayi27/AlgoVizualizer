import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Example extends JFrame {

    public Example() {
        setTitle("Curved Line Example");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCurvedLine(g);
            }
        };

        add(panel);
    }

    private void drawCurvedLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int startX = 50;
        int startY = 150;
        int endX = 250;
        int endY = 10;
        int ctrlX = 150;
        int ctrlY = 140;

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new QuadCurve2D.Double(startX, startY, ctrlX, ctrlY, endX, endY));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Example example = new Example();
            example.setVisible(true);
        });
    }
}
