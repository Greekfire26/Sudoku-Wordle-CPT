package CPT;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GUI {

    static public JFrame frame;
    private static JPanel menuPanel;
    private static JLabel label;
    private static JPanel buttons;
    private static JButton wordleButton;
    private static JButton sudokuButton;
    private static JButton quitButton;


    public GUI(){ // define GUI constructor, this function is run automatically whenever an instance of this class is created
        frame = new JFrame(); // create new JFrame, this is basically the window our graphics are displayed on
        frame.setTitle("Nicholas' Arcade - CS CPT"); // set the title
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // set frame to exit the program when the close button of our window is pressed
        frame.setSize(600, 650); // set the size of the window
        frame.setLocationRelativeTo(null); // have the window appear in the center of the user's monitor
        frame.setResizable(false); // make the window a permanent size
        frame.setVisible(true); // display our window on the screen
    }

    public static void menu(){
        menuPanel = new JPanel(); // create new JPanel
        frame.add(menuPanel, BorderLayout.CENTER); // add our new JPanel to the center of our frame
        frame.setContentPane(menuPanel); // make our JPanel visible on the screen
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // set a border of 10 pixels around the whole JPanel
        menuPanel.setLayout(new GridBagLayout()); // set our JPanel's layout, these are pre made templates for our panels

        GridBagConstraints gbc = new GridBagConstraints(); // create new instance of GridBagConstraints class
        gbc.gridwidth = GridBagConstraints.REMAINDER; // set our grid to take up as much space as possible within what remains
        gbc.anchor = GridBagConstraints.NORTH; // set our grid to start at the top of our panel

        label = new JLabel("<html><h1><strong><i>Nicholas' Arcade!</i></strong></h1><hr></html>"); // create our header text
        menuPanel.add(label, gbc); // add our header text to the panel

        gbc.anchor = GridBagConstraints.CENTER; // set our grid to start at the center of our panel
        gbc.fill = GridBagConstraints.HORIZONTAL; // set our grid to stretch horizontally

        buttons = new JPanel(new GridBagLayout()); // create a new JPanel

        wordleButton = createButton("Wordle"); // create a new button for our wordle game
        buttons.add(wordleButton, gbc); // add our new button to our new panel

        sudokuButton = createButton("Sudoku"); // create a new button for our sudoku game
        buttons.add(sudokuButton, gbc); // add our new button to our new panel

        quitButton = createButton("Quit"); // create a new button to allow the user to quit and exit
        buttons.add(quitButton, gbc); // add our new button to our new panel

        gbc.weighty = 1;
        menuPanel.add(buttons, gbc); // add our buttons panel to the menu panel
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text); // create a new JButton with text passed as a parameter
        button.setFocusable(false); // set our button to not be focusable (can't hold click)
        button.setFont(new Font("SansSerif", Font.PLAIN, 24)); // set the font and size of the buttons text

        button.addActionListener(new ActionListener(){ // add a new action listener to have our buttons do something when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Wordle")) { // if wordle button clicked
                    new wordle(); // create new instance of wordle class
                    refresh();
                } else if (e.getActionCommand().equals("Sudoku")){ // if sudoku button clicked
                    new sudoku(); // create new instance of sudoku class
                    refresh();
                }  else if (e.getActionCommand().equals("Back")){ // if back button clicked
                    if(frame.getContentPane() == sudokuHowTo.sudokuHowToPanel){ // if in the sudoku how to
                        frame.setContentPane(sudoku.sudokuPanel); // return to the sudoku game
                    } else if (frame.getContentPane() == wordleHowTo.wordleHowToPanel){ // if in the wordle how to
                        frame.setContentPane(wordle.wordlePanel); // return to the wordle game
                    } else {
                        frame.setContentPane(menuPanel); // return to the main menu
                    }
                    refresh();
                    
                    for (KeyListener kl : frame.getKeyListeners()) { // loop through all key listeners and remove them (prevents mutliple responses to one button click)
                        frame.removeKeyListener(kl);
                    }
                    
                }  else if (e.getActionCommand().equals("How To")){ // if how to button clicked
                    if(frame.getContentPane() == sudoku.sudokuPanel){ // if in sudoku game
                        new sudokuHowTo(); // create instance of sudokuHowTo class
                        frame.setContentPane(sudokuHowTo.sudokuHowToPanel); // display sudoku how to
                    } else if (frame.getContentPane() == wordle.wordlePanel){ // if in wordle game
                        new wordleHowTo(); // create instance of wordleHowTo class
                        frame.setContentPane(wordleHowTo.wordleHowToPanel); // display wordle how to
                    }
                    refresh();
                }  else if (e.getActionCommand().equals("Quit")){ // if quit button clicked
                    frame.dispose(); // exit program
                }
            }
        });
        return button; // return our new button
    }

    public static void refresh(){ // function to refresh the screen and show changes
        frame.invalidate();
        frame.validate();
    }
}