import java.util.Random;

public class Main {

    public static void unreadable() {
        System.out.println("Program End.");
        System.exit(1);
    }

    public static void main(String[] args) {

        Sudoku game = new Sudoku();
        int numOfDemos = 3; // number of demos

        if (args.length > 1) {
            System.out.println("You can only input 0 or 1 argument.");
            System.exit(1);
        } else if (args.length == 1) {
            boolean isRead = game.createGame(args[0]);
            if (!isRead) { // unable to open the file
                unreadable();
            } // end if
        } else { // no argument
            System.out.println("Running the demo Sudoku...");
            Random rand = new Random();
            int seed = rand.nextInt(numOfDemos); // seed = [0, numOfDemos)
            boolean isRead = game.createGame(2);
            if (!isRead) { // unable to open the file
                unreadable();
            } // end if
        } // end if..else

        System.out.println("Before Running the Solver...");
        game.printSudoku();
        System.out.println();
        System.out.println("After Running the Solver...");
        if (game.solveSudoku()) {
            game.printSudoku();
            System.out.println();
        } else {
            System.out.println("Solution does not exist!");
        } // end if..else

    } // end main()

} // end class Main
