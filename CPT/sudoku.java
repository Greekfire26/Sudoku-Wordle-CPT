package CPT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;

public class sudoku {

    public static JPanel sudokuPanel;
    private sudokuBoard sudokuBoard;
    private JButton backButton;
    private JButton howToButton;
    private JLabel winLabel;
    private int xCord = 0, yCord = 0, lastXCord, lastYCord;
    private boolean gameover = false;

    public sudoku() { // define sudoku constructor, this function is run automatically whenever an instance of this class is created
        sudokuPanel = new sudokuPanel(); // create new instance of a sudokuPanel (a custom panel class)
        GUI.frame.setContentPane(sudokuPanel); // make our sudoku panel visible on the screen
        sudokuPanel.setBackground(Color.DARK_GRAY); // set our panel's background to dark gray
        sudokuPanel.setLayout(null); // set our layout type to null (this allows use to use x, y cords to determine where an element appears on screen)

        play(); // call the play function
    }

    private void play() {
        sudokuBoard = new sudokuBoard(); // create a new sudokuBoard (a partly filled completable board the user can attempt to finish)

        try {
            String wins = String.format("%02d", getWins()); // set String var "wins" to be equal to the return value of getWins formatted to  be zero padded once
            winLabel = new JLabel("Total Wins: " + wins); // create a new label to display wins
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        winLabel.setForeground(new Color(255, 255, 255)); // set the color of our win label
        winLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // set the font and size of our win label
        sudokuPanel.add(winLabel); // add our win label to the panel
        winLabel.setBounds(195, 530, 250, 100); // set the size and location of our win label

        backButton = GUI.createButton("Back"); // create a new back button
        sudokuPanel.add(backButton); // add the back button to the panel
        backButton.setBounds(490, 560, backButton.getPreferredSize().width, backButton.getPreferredSize().height); // set the size and location of our back button

        howToButton = GUI.createButton("How To"); // create a new how to button
        sudokuPanel.add(howToButton); // add the how to button to the panel
        howToButton.setBounds(15, 560, howToButton.getPreferredSize().width, howToButton.getPreferredSize().height); // set the size and location of our back button

        JLabel[][] boxLabels = new JLabel[9][9]; // create a 9x9 2d array of JLabel objects
        Font labelFont = new Font("Verdana", Font.BOLD, 32); // create an instance of the font class we will set our labels to

        for (int col = 0, y = 1; col < boxLabels.length; col++, y += 60) { // loop through our 2d array
            for (int x = 43, row = 0; row < boxLabels[0].length; row++, x += 60) {
                boxLabels[col][row] = new JLabel(); // create a new JLabel object in the current index
                boxLabels[col][row].setFont(labelFont); // set the font and size our current label
                boxLabels[col][row].setForeground(Color.GRAY); // set the color of our current label
                boxLabels[col][row].setBounds(x, y, 60, 60); // set the size and location of our current label

                if (sudokuBoard.board[col][row] != 0) { // if the current label is not a 0 in the board
                    boxLabels[col][row].setText(Integer.toString(sudokuBoard.board[col][row])); // display the value of the current cell in it's label
                }
                sudokuPanel.add(boxLabels[col][row]); // add the current label to the panel
            }
        }
        
        GUI.frame.addKeyListener(new KeyListener() { // create a key listener to have the program respond to the keyboard being used

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                lastXCord = xCord; lastYCord = yCord;

                if (e.getKeyCode() == 38 && yCord > 0) { //  if Up arrow key is pressed and yCord isnt currently at the min
                    yCord -= 1; // set yCord to be one cell higher
                } else if (e.getKeyCode() == 39 && xCord < 8) { // if Right arrow key is pressed and xCord isnt currently at the max
                    xCord += 1; // set xCord to be one cell to the right
                } else if (e.getKeyCode() == 40 && yCord < 8) { // if Down arrow key is pressed and yCord isnt currently at the max
                    yCord += 1; // set yCord to be one cell lower
                } else if (e.getKeyCode() == 37 && xCord > 0) { // if Left arrow key is pressed and xCord isnt currently at the min
                    xCord -= 1; // set yCord to be one cell the left
                }
                if (sudokuBoard.board[lastYCord][lastXCord] == 0){ // if the last cell is a blank in the generated board
                    boxLabels[lastYCord][lastXCord].setForeground(Color.WHITE); // set it to white when the user moves to another cell
                } else {
                     boxLabels[lastYCord][lastXCord].setForeground(Color.GRAY); // set it to gray when the user moves to another cell
                }
                boxLabels[yCord][xCord].setForeground(Color.GREEN); // set the cell the user moved onto to be green
                
                if (Character.isDigit(e.getKeyChar()) && e.getKeyCode() != 48 && sudokuBoard.board[yCord][xCord] == 0 && !gameover){ // if the key pressed is a number, the number is not zero, the current cell is blank in the generated board and the game is not over
                    int[][] currentBoard = sudokuBoard.createIntBoard(boxLabels); // create a 2d int array of the values currently on the screen

                    if (validRow(currentBoard, yCord, Character.getNumericValue(e.getKeyChar())) &&
                        validColumn(currentBoard, xCord, Character.getNumericValue(e.getKeyChar())) &&
                        validBox(currentBoard, yCord - yCord % 3, xCord - xCord % 3, Character.getNumericValue(e.getKeyChar()))){ // if number is a valid input and not a duplicate in the current row, column or square

                        boxLabels[yCord][xCord].setForeground(Color.WHITE); // set the color of the label the number was inserted into to white
                        boxLabels[yCord][xCord].setText(String.valueOf(e.getKeyChar())); // set the text of the label the number was inserted into to that number

                        currentBoard[yCord][xCord] = Character.getNumericValue(e.getKeyChar()); // place the number in our array of current values
                        if (sudokuBoard.fullBoard(currentBoard)){ // if the board is full
                            gameover = true; // set game over to be true, because we check if the board is valid after every new insertion we can assume a full board is valid
                            try {
                                addWin(); // add one win the current count
                            } catch (IOException ex) {;
                                throw new RuntimeException(ex);
                            }
                            try {
                                String wins = String.format("%02d", getWins()); // set String var "wins" to be equal to the return value of getWins formatted to  be zero padded once
                                winLabel.setText("Total Wins: " + wins); // update win label to display current win count
                            } catch (FileNotFoundException exc) {
                                throw new RuntimeException(exc);
                            }
                        }
                    }
                }
                
                if (e.getKeyCode() == 8 && sudokuBoard.board[yCord][xCord] == 0  && !gameover){ // if delete button is pressed and current cell isnt prefilled and game is still going
                    boxLabels[yCord][xCord].setText(""); // set the current label to be blank
                }
                if (e.getKeyCode() == 32 && gameover){ // if space pressed and the game is over
                    new sudoku(); // create new instance of sudoku class
                    GUI.refresh();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public boolean validRow(int[][] board, int row, int num) {
        for (int j = 0; j < 9; j++){ // loop through the entire row
            if (board[row][j] == num){ // if current index of the board is equal to number
                return false;
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public boolean validColumn(int[][] board, int column, int num) {
        for (int i = 0; i < 9; i++){ // loop through the entire column
            if (board[i][column] == num){ // if current index of the board is equal to number
                return false;
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public boolean validBox(int[][] board, int row, int column, int num) {
        for (int i = 0; i < 3; i++){ // loop through the entire square
            for (int j = 0; j < 3; j++){
                if (board[row + i][column + j] == num){ // if current index of the board is equal to number
                    return false;
                }
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public int getWins() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/CPT/sudokuWins.txt")); // create new scanner instance
        int wins = scanner.nextInt(); // set var wins to the int found in the file
        return wins; // return that wins var
    }

    public void addWin() throws IOException {
        int wins = getWins() + 1; // call getWins and add one to the number
        FileWriter writer = new FileWriter("src/CPT/sudokuWins.txt", false); // create new filewriter instance
        PrintWriter pw = new PrintWriter(writer); // create new printwriter instance
        pw.print(wins); // override text in the file to be equal to wins var
        pw.close(); // close the print writer stream
    }
}