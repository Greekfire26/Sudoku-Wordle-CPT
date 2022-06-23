package CPT;

import javax.swing.*;
import java.awt.*;

public class wordleHowTo {

    public static JPanel wordleHowToPanel;
    private JLabel titleLabel;
    private JLabel textLabel;
    private JButton backButton;

    public wordleHowTo(){ // define wordleHowTo constructor, this function is run automatically whenever an instance of this class is created
        wordleHowToPanel = new JPanel(); // create new JPanel
        GUI.frame.setContentPane(wordleHowToPanel); // make our JPanel visible on the screen
        wordleHowToPanel.setBackground(Color.DARK_GRAY); // set the background of our panel to dark gray
        wordleHowToPanel.setLayout(null); // set our layout type to null (this allows the use of x, y cords to set the location of components on the screen)

        draw(); // call the draw method
    }

    private void draw(){
        titleLabel = new JLabel("How to Play Wordle"); // create a header label
        titleLabel.setForeground(new Color(255, 255, 255)); // set the color of our header label to white
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // set the font and size of our header label
        wordleHowToPanel.add(titleLabel); // add the header label to the panel
        titleLabel.setBounds(150, 5, titleLabel.getPreferredSize().width, titleLabel.getPreferredSize().height); // set the location and size of the header label

        backButton = GUI.createButton("Back"); // create a new back button
        wordleHowToPanel.add(backButton); // add back button to the panel
        backButton.setBounds(270, 550, backButton.getPreferredSize().width, backButton.getPreferredSize().height); // set the size and location of the back button

        textLabel = new JLabel(); // create a new JLabel
        textLabel.setText("<html>To win guess the 5 letter word. You have 6 guesses<br/>after each guess the letters in your guess will<br/>change color. Green means that letter is in the<br/>same spot in the target word. Yellow means it is in<br/>the target word but not in that spot. And grey<br/>means it is not in the word at all. Once you win or<br/>run out of guesses press space to play again.</html>"); // set the text of the new Label
        textLabel.setForeground(new Color(255, 255, 255)); // set the color of the text label
        textLabel.setFont(new Font("Verdana", Font.BOLD, 20)); // set the font and size of the text label
        wordleHowToPanel.add(textLabel); // add the text label to the panel
        textLabel.setBounds(5, 40, textLabel.getPreferredSize().width, textLabel.getPreferredSize().height); // set the location and size of the text label
    }
}