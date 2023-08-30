package seatingchart;

import javax.swing.*;
import java.awt.*;

public class NumberInSquare {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Number in Square");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new DrawingPanel());
            frame.setSize(300, 300);
            frame.setVisible(true);
        });
    }
}

class DrawingPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = 50;
        int numRows = 5;
        int numCols = 8;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int number = row * numCols + col + 1;
                int x = col * squareSize;
                int y = row * squareSize;
                
                g.drawRect(x, y, squareSize, squareSize);
                g.drawString(String.valueOf(number), x + squareSize / 2, y + squareSize / 2);
            }
        }
    }
}
