package CPT;

import javax.swing.*;
import java.awt.*;

public class sudokuPanel extends JPanel { // create our own custom class which inherits from JPanel

    public void paintComponent(Graphics g) { // override the paintComponent method, which is run automatically when the component is created
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // create a Graphics2D (needed to draw our thick lines)

        g.setColor(Color.WHITE); // set the color of the lines we will draw to be white
        // first two loops draw all the thin lines
        for (int x1 = 25, y1 = 25; x1 <= 600; x1 += 60, y1 += 60) {
            g.drawLine(x1, 1, y1, 541);
        }

        for (int x2 = 1, y2 = 1; x2 <= 600; x2 += 60, y2 += 60) {
            g.drawLine(25, x2, 565, y2);
        }
        
        g2.setStroke(new BasicStroke(5)); // set the stroke to 5 to draw our thick lines
        // next two loops draw all the thick lines
        for (int x1 = 25, y1 = 25; x1 <= 600; x1 += 180, y1 += 180) {
            g.drawLine(x1, 1, y1, 541);
        }

        for (int x2 = 1, y2 = 1; x2 <= 650; x2 += 180, y2 += 180) {
            g.drawLine(25, x2, 565, y2);
        }
    }
}