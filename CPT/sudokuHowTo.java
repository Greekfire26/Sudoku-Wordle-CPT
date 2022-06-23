package CPT;

import javax.swing.*;
import java.awt.*;

public class sudokuHowTo {

    public static JPanel sudokuHowToPanel;
    private JLabel titleLabel;
    private JLabel textLabel;
    private JButton backButton;

    public sudokuHowTo(){ // define sudokuHowTo constructor, this function is run automatically whenever an instance of this class is created
        sudokuHowToPanel = new JPanel(); // create new JPanel
        GUI.frame.setContentPane(sudokuHowToPanel); // make our JPanel visible on the screen
        sudokuHowToPanel.setBackground(Color.DARK_GRAY); // set the background of our panel to dark gray
        sudokuHowToPanel.setLayout(null); ;

        draw(); // call the draw method
    }

    private void draw(){
        titleLabel = new JLabel("How to Play Sudoku"); // create a new header label
        titleLabel.setForeground(new Color(255, 255, 255)); // set the color of our header label to white
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // set the font and size of our header label
        sudokuHowToPanel.add(titleLabel); // add our header label to the panel
        titleLabel.setBounds(150, 5, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height); // set the location and size of the label

        backButton = GUI.createButton("Back"); // create a new back button
        sudokuHowToPanel.add(backButton); // add the back button to the panel
        backButton.setBounds(270, 550, backButton.getPreferredSize().width, backButton.getPreferredSize().height); // set the location and size of the button

        textLabel = new JLabel(); // create a new JLabel
        textLabel.setText("<html>Use the arrow keys to move, green indicates the<br/>current selected cell. To win each row, column and<br/>square (9 spaces each) needs to be filled out with<br/>the numbers 1-9, without repeating any numbers<br/>within the row, column or square. Once you win, <br/>press space to play again.</html>"); // set the text of the new label
        textLabel.setForeground(new Color(255, 255, 255)); // set the color of the text label
        textLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // set the font and size of the text label
        sudokuHowToPanel.add(textLabel); // add the text label to the panel
        textLabel.setBounds(5, 40, textLabel.getPreferredSize().width, textLabel.getPreferredSize().height); // set the location and size of the text label
    }
}