import java.io.*;

public class Sudoku {

    private int[][] gameBoard;

    public Sudoku() {}

    /**
     * Opens the game text file; it will throw exception
     *   if the file is unable to open or something is going wrong.
     * @param seed the [id] in the file name "game[id].text"
     */
    public void createGame(int seed) throws IOException {
        String filePath = ".\\demos\\game" + seed + ".txt";
        this.createGame(filePath);
    } // end createGame()

    /**
     * Opens the given text file if the path is found, then converts it
     *   to the game board formal and stores in gameBoard.
     *   Otherwise, throws exception.
     * @param filePath the path of the Sudoku game text file
     */
    public void createGame(String filePath) throws IOException {
        this.gameBoard = new int [9][9];
        int row = 0;
        int col = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (ch >= 48 && ch <= 57) {
                        this.gameBoard[row][col] = Character.getNumericValue(ch);
                        col++;
                    } // end if
                } // end for(ch)
                row++;
                col = 0;
            } // end while()
            reader.close();
        } catch (Exception e) {
            throw e;
        } // end try..catch

    } // end createGame()

    /**
     * Return true if num can be saved in gameBoard[x][y] which breaking
     *   the game rule; otherwise, return false.
     * @param num current number to check, range: [0, 9]
     * @param x   the x-coordinate index (or, the row index) in gameBoard[x][y]
     * @param y   the y-coordinate index (or, the column index) in gameBoard[x][y]
     * @return    Boolean
     */
    private boolean isSafe(int num, int x, int y) {
        for (int i = 0; i < 9; i++) {
            // check the row
            if (i != y && this.gameBoard[x][i] == num) {
                return false;
            } // end if

            // check the column
            if (i != x && this.gameBoard[i][y] == num) {
                return false;
            } // end if
        } // end for(col)

        int box_x = x / 3;
        int box_y = y / 3;
        for (int i = box_x * 3; i < box_x * 3 + 3; i++) {
            for (int j = box_y * 3; j < box_y * 3 + 3; j++) {
                if (i != x && j != y && this.gameBoard[i][j] == num) {
                    return false;
                } // end if
            } // end for(j)
        } // end for(i)

        return true;
    } //end isSafe()

    /**
     * Return the next position [row, col] for 0 in gameBoard
     *   or return [-1, -1] if there is not 0 in gameBoard
     * @return [row, col]
     */
    private int[] findZero() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (this.gameBoard[row][col] == 0) {
                    return new int[]{row, col};
                } // end if
            } // end for(col)
        } // end for(row)
        return new int[]{-1, -1}; // no 0 left
    } // end findZero()


    /**
     * Return true if the Sudoku game is done of solving;
     *   otherwise, return false. If the Sudoku game is
     *   unable to solve (no solution), also return false.
     * @return Boolean
     */
    public boolean solveSudoku() {
        // find the position of next 0 in gameBoard
        int zeroInt[] = this.findZero();
        int row = zeroInt[0];
        int col = zeroInt[1];

        if (row == -1 && col == -1) {
            return true; // no 0 left, solution found!
        } // end if

        for (int num = 1; num <= 9; num++) {
            if (isSafe(num, row, col)) {
                this.gameBoard[row][col] = num;
                if(solveSudoku()) {
                    return true;
                } // end if
            } // end if
            this.gameBoard[row][col] = 0; // failure, reset to 0
        } // end for(num)

        return false;
    } // end solveSudoku()

    /**
     * Display a 9*9 gameBoard in console.
     */
    public void printSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(this.gameBoard[row][col] + " ");
            } // end for(col)
            System.out.println();
        } // end for(row)
    } // end printSudoku()

} // end Sudoku
