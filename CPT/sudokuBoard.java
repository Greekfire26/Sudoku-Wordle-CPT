package CPT;

import javax.swing.*;

public class sudokuBoard {

    public int[][] board = new int[9][9];
    public sudokuBoard() {generateBoard();} // define sudokuBoard constructor, this function is run automatically whenever an instance of this class is created

    public void generateBoard() {

        for (int i = 0; i < 9; i += 3) {fillBox(i, i);}

        fillRemaining(0, 3);
        // randomly removes "count" numbers to give the user a board that they can attempt to fill
        int count = 50;
        while (count != 0) { // while there are cells still to make blank
            int randomNum = randomNum(9*9)-1; // create a random number

            int row = randomNum / 9; // get a row from the random number
            int column = randomNum % 9; // get a column from the random number

            if (column != 0){column -= 1;} // if the column is not zero set it to be one to the left

            if (board[row][column] != 0) { // if the random index is not already blank (blanks are 0s)
                count--;
                board[row][column] = 0; // set the current random index to 0 / blank
            }
        }
    }

    public boolean validBox(int row, int column, int num) {
        for (int i = 0; i < 3; i++){ // loop through the entire square
            for (int j = 0; j < 3; j++){
                if (board[row + i][column + j] == num){ // if current index of the board is equal to number
                    return false;
                }
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) { // loop through the whole box
            for (int j = 0; j < 3; j++) {
                do {
                    num = randomNum(9); // get a random number from 1-9
                }
                while (!validBox(row, col, num));

                board[row + i][col + j] = num; // set the current index to that random number
            }
        }
    }

    public int randomNum(int max) {return (int) Math.floor((Math.random()* max + 1));} // get a random number from 1 to the inputted max
    public boolean validCell(int row, int column, int num) {return (validRow(row, num) && validColumn(column, num) && validBox(row - row % 3, column - column % 3, num));} // if the row, square and column are all valid then the cell is valid
    public boolean validRow(int row, int num) {
        for (int j = 0; j < 9; j++){ // loop through the entire row
            if (board[row][j] == num){ // if current index of the board is equal to number
                return false;
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public boolean validColumn(int column, int num) {
        for (int i = 0; i < 9; i++){ // loop through the entire column
            if (board[i][column] == num){ // if current index of the board is equal to number
                return false;
            }
        }
        return true; // if none of the index's checked where the number, loop will exit without the return false statement being reached so return true
    }

    public boolean fillRemaining(int row, int column) {

        if (column >= 9 && row < 8) { // if the column is greater than or equal to 9 and the row is less than 8
            row += 1; // set row to be one below
            column = 0; // set column to 0
        }
        if (row >= 9 && column >= 9){return true;} // both column and row are greater than or equal to 9 return true to exit function

        if (row < 3 && column < 3) { // if row and column are greater than 3
            column = 3; // set column to 3
        } else if (row < 6) { // if row is greater than 6
            if (column == (row / 3) * 3){ // set column to the value of row divided by 3 times three
                column += 3; // set column to be 3 to the right
            }
        } else {
            if (column == 6) { // if column is 6
                row += 1; // set row to be one below
                column = 0; // set column to 0
                if (row >= 9){return true;} // if row is greater than or equal to 9 return true to exit function
            }
        }

        for (int num = 1; num <= 9; num++) { // loop 9 times
            if (validCell(row, column, num)) { // if the cell is valid
                board[row][column] = num; // set the current index to num
                if (fillRemaining(row, column + 1)){return true;} // recursively call fillremaining to loop through all indexes
                board[row][column] = 0; // if function reaches this point set current index to zero
            }
        }
        return false; // return false so recursive calls will stop once complete
    }
    public boolean fullBoard(int[][] board){
        for (int row = 0; row < board.length; row++){ // loop through board
            for (int column = 0; column < board[row].length; column++){
                if (board[row][column] == 0){ // current cell is equal to 0
                    return false; // return false as 0 represents blank cells
                }
            }
        }
        return true; // if none of the index's checked where 0, loop will exit without the return false statement being reached so return true
    }

    public int[][] createIntBoard(JLabel[][] labelArray){
        int[][] intBoard = new int[9][9]; // create a new 9x9 2d array of ints

        for (int row = 0; row < intBoard.length; row++){ // loop through intBoard
            for (int column = 0; column < intBoard[row].length; column++){
                if (!labelArray[row][column].getText().equals("")){ // if the cell is not blank
                    intBoard[row][column] = Integer.valueOf(labelArray[row][column].getText()); // set that index to the corresponding number
                } else {
                    intBoard[row][column] = 0; // set that index to zero
                }
            }
        }
        return intBoard; // return the intBoard array
    }
}