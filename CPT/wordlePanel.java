package CPT;

import javax.swing.*;
import java.awt.*;

public class wordlePanel extends JPanel { // create our own custom class which inherits from JPanel
    public void paintComponent(Graphics g){ // override the paintComponent method, which is run automatically when the component is created
        super.paintComponent(g);
        // these two loops draw the boxes
        for (int y = 40; y <= 500; y += 85){
            g.setColor(Color.DARK_GRAY); // set color we are drawing with to dark gray
            for (int x = 85; x <= 425; x += 85){
                g.fillRect(x, y, 75, 75);
            }
            g.setColor(Color.WHITE); // set color we are drawing with to white
            for (int x = 85; x <= 425; x += 85){
                g.drawRect(x, y, 75, 75);
            }
        }
    }
}