package tictactoe;
import java.util.Scanner;


public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    private static final char[] symbols = input("_________");

    public static void main(String[] args) {

        String result = "Game not finished";

        output(); // print field

        char playersSign = 'X';
        boolean playerX = true;

        while ("Game not finished".equals(result)) {

            oneMove(playersSign); // one move

            output();// print field

            if (playerX) {
                playersSign = 'O';
                playerX = false;
            } else {
                playersSign = 'X';
                playerX = true;
            }

            result = check(); // give current result
        }



        System.out.println(result); // print result
    }

    public static char[] input(String line) {
        char[] symbols = new char[9];

        for (int i = 0; i < symbols.length ; i++) {
            symbols[i] = line.charAt(i);
        }

        return symbols;
    }


    public static void output() {

        System.out.println("---------");
        int index = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(symbols[index++] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String check() {

        int amountX = 0;
        int amountO = 0;

        int xInLine = 0;
        int oInLine = 0;

        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == 'X') {
                amountX++;

                if ((i % 3 == 0 && (symbols[i + 1] == 'X' && symbols[i + 2] == 'X')) ||
                        (i < 3 && (symbols[i + 3] == 'X' && symbols[i + 6] == 'X'))) {
                    xInLine++;
                }

            } else if (symbols[i] == 'O') {
                amountO++;

                if ((i % 3 == 0 && (symbols[i + 1] == 'O' && symbols[i + 2] == 'O')) ||
                        (i < 3 && (symbols[i + 3] == 'O' && symbols[i + 6] == 'O'))) {

                    oInLine++;
                }
            }
        }

        if ((symbols[0] == 'X' && symbols[4] == 'X' && symbols[8] == 'X') ||
                (symbols[2] == 'X' && symbols[4] == 'X' && symbols[6] == 'X')) {
            xInLine++;
        } else if ((symbols[0] == 'O' && symbols[4] == 'O' && symbols[8] == 'O') ||
                (symbols[2] == 'O' && symbols[4] == 'O' && symbols[6] == 'O')){
            oInLine++;
            System.out.println("oInLine Diagonal:" + oInLine);
        }

        String result;

        if (Math.abs(amountX - amountO) >= 2) {
            result = "Impossible";
        } else if (xInLine > 0 && oInLine > 0) {
            result = "Impossible";
        } else if (xInLine > 0) {
            result = "X wins";
        } else if (oInLine > 0) {
            result = "O wins";
        } else if (amountX + amountO < symbols.length) {
            result = "Game not finished";
        } else {
            result = "Draw";
        }

        return result;
    }

    public static void oneMove(char playersSign) {

        int a;
        int b;
        int index;
        boolean rightCoordinates = false;

        System.out.print("Enter the coordinates: ");
        String coordinates = scanner.nextLine();

        while (!rightCoordinates) {
            try {
                if (coordinates.length() == 3) {
                    a = Integer.parseInt(String.valueOf(coordinates.charAt(0)));
                    b = Integer.parseInt(String.valueOf(coordinates.charAt(2)));

                    index = Math.abs(b - 3) * 3 + (a - 1);

                    if (a > 3 || a < 1 || b > 3 || b < 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (symbols[index] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        symbols[index] = playersSign;
                        rightCoordinates = true;
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("You should enter numbers!");
            }

            if (!rightCoordinates) {
                System.out.print("Enter the coordinates: ");
                coordinates = scanner.nextLine();
            }
        }
    }
}
