import java.io.*;
import java.util.Random;

public class Main {

    public static void openGameFile(Sudoku game, String filePath, int seed) {
        Boolean isOpened = false;
        try {
            if (seed == -1) {
                game.createGame(filePath);
            } else {
                game.createGame(seed);
            } // end if
            isOpened = true; // everything is fine
        } catch (FileNotFoundException e) {
            System.out.println("I cannot find the file path...");
        } catch (Exception e){
            System.out.println("Something is wrong here...");
        } // end try..catch

        if (!isOpened) { // something is wrong...
            System.out.println("Program End.");
            System.exit(1);
        } // end if
    } // end openGameFile()

    public static void main(String[] args){

        Sudoku game = new Sudoku();
        int numOfDemos = 3; // number of demos

        if (args.length > 1) {
            System.out.println("You can only input 0 or 1 argument.");
            System.exit(1);
        } else if (args.length == 1) {
            openGameFile(game, args[0], -1);
        } else { // no argument
            System.out.println("Running the demo Sudoku...");
            Random rand = new Random();
            int seed = rand.nextInt(numOfDemos); // seed = [0, numOfDemos)
            openGameFile(game, null, seed);
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
