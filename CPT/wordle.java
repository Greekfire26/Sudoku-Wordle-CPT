package CPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

public class wordle {

    private targetWord targetword;
    private JLabel guessLabel;
    private JLabel spaceLabel;
    public static wordlePanel wordlePanel;
    private JButton backButton;
    private JButton howToButton;
    private int currentRow;

    public wordle() { // define wordle constructor, this function is run automatically whenever an instance of this class is created
        wordlePanel = new wordlePanel(); // create new instance of wordlePanel (a custom panel class)
        GUI.frame.setContentPane(wordlePanel); // make our wordle panel visible on the screen
        wordlePanel.setBackground(Color.DARK_GRAY); // set our panels background to dark gray
        wordlePanel.setLayout(null); // set our layout type to null (this allows use to use x, y cords to determine where an element appears on screen)

        play(); // call the play function
    }

    private void play() {
        targetword = new targetWord(); // create a new targword

        guessLabel = new JLabel("Guesses Left: " + targetword.attempts); // create new JLabel to display the attempts left
        guessLabel.setForeground(new Color(255, 255, 255)); // set the color of the guess label to white
        guessLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // set the font and size of the guess label
        wordlePanel.add(guessLabel); // add the guess label to the panel
        guessLabel.setBounds(195, 525, 250, 100); // set the location and size of the guess label

        backButton = GUI.createButton("Back"); // create a new back button
        wordlePanel.add(backButton); // add the back button to the panel
        backButton.setBounds(490, 560, backButton.getPreferredSize().width, backButton.getPreferredSize().height); // set the size and location of the back button

        howToButton = GUI.createButton("How To"); // create a new how to button
        wordlePanel.add(howToButton); // add the how to button to the panel
        howToButton.setBounds(15, 560, howToButton.getPreferredSize().width, howToButton.getPreferredSize().height); // set the size and location of the back button

        JLabel[][] boxLabels = new JLabel[6][6]; // create a 6x6 2d array of JLabel objects
        Font labelFont = new Font("Verdana", Font.BOLD, 64); // create an instance of the font class to use for our labels
        Color labelColor = new Color(255, 255, 255); // create an instance of the color class to set our labels to

        for (int col = 0, y = 40; col < boxLabels.length; col++, y += 85) { // loop through our 2d array
            for (int x = 98, row = 0; row < boxLabels[0].length; row++, x += 85) {
                boxLabels[col][row] = new JLabel(); // create a new JLabel object at the current index
                boxLabels[col][row].setFont(labelFont); // set the font of the current label
                boxLabels[col][row].setForeground(labelColor); // set the color of the current label
                boxLabels[col][row].setBounds(x, y, 75, 75); // set the size and location of the current label
                wordlePanel.add(boxLabels[col][row]); // add the current label to the panel
            }
        }
        // define our colors
        Color yellow = new Color(255, 234, 0);
        Color green = new Color(0, 128, 0);
        Color grey = new Color(105, 105, 105);

        ArrayList<Character> current_letters = new ArrayList<>(); // create a new array list to store the letters currently on screen in the active row
        GUI.frame.addKeyListener(new KeyListener() { // create a key listener to have the program respond to the keyboard being used

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) { //if the enter key is pressed
                    int[] compareArray = targetword.checkGuess(current_letters); // create an array of the comparison and set it equal to the return value of the check guess function
                    if (current_letters.size() == 5 && targetword.attempts > 0) { // if 5 letters have been inputted and the user has guesses left
                        int currentColumn = 0;
                        for (int x : compareArray) { // loop through the compare array
                            switch (x) {
                                case 1: // if the current compare value is 1
                                    boxLabels[currentRow][currentColumn].setForeground(grey); // set the color of the letter to be gray
                                    break;
                                case 2:
                                    boxLabels[currentRow][currentColumn].setForeground(yellow); // set the color of the letter to be yellow
                                    break;
                                case 3:
                                    boxLabels[currentRow][currentColumn].setForeground(green); // set the color of the letter to be green
                                    break;
                            }
                            currentColumn++;
                        }

                        currentRow += 1;
                        targetword.attempts -= 1;
                        guessLabel.setText("Guesses Left: " + targetword.attempts); // update the guesses label
                        current_letters.clear(); // clear the current letters array as the active row moves down
                        
                        int[] greenArray = {3, 3, 3, 3, 3};
                        if (Arrays.equals(compareArray, greenArray)){ // if all letters are green
                            guessLabel.setText("You Win! Nice!"); // update the guess label
                            targetword.attempts = 0; // set the attempts var to 0 (this ends the game)
                        }
                        if (targetword.attempts == 0){ // if no more attempts (either they won or ran out of guesses)
                            spaceLabel = new JLabel("Press space to play again"); // create new Jlabel to prompt user to play again
                            spaceLabel.setForeground(new Color(255, 255, 255)); // set color of space label to white
                            spaceLabel.setFont(new Font("Verdana", Font.BOLD, 14)); // set size and font of space label
                            wordlePanel.add(spaceLabel); // add space label to panel
                            spaceLabel.setBounds(195, 550, 250, 100); // set location and size of space label
                        }
                    }
                    
                } else if (Character.isAlphabetic(e.getKeyChar())) { // if any letter key is pressed
                    if (current_letters.size() < 5 && targetword.attempts > 0) { // if row is not full and player has guesses left
                        current_letters.add(e.getKeyChar()); // add inputted letter to current letters array list
                        boxLabels[currentRow][current_letters.size() - 1].setText(Character.toString(e.getKeyChar()).toUpperCase()); // set corresponding JLabel to display the letter
                    }
                } else if (e.getKeyCode() == 8 && targetword.attempts > 0) { // if delete key is pressed and game is not over
                    if (current_letters.size() > 0) { // if any letters exist to delete
                        current_letters.remove(current_letters.size() - 1); // delete the last letter
                        boxLabels[currentRow][current_letters.size()].setText(""); // set the corresponding JLabel to be blank
                    }
                } else if (e.getKeyCode() == 32 && targetword.attempts == 0){ // if space pressed and game is over
                    new wordle(); // create new instance of wordle class
                    GUI.refresh();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}