import java.io.*;
import java.util.*;

public class Main {
    private char[][] board;
    private String answer;

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();

            byte emptyRows = 0;
            int boardsCount = 0;
            String row;
            char [][] board = new char[8][8];

            while (emptyRows < 8) {
                emptyRows = 0;
                for (int i = 0; i < 8; i++) {
                    row = in.nextLine();
                    if (row.equals("........")) emptyRows++;
                    for (int j = 0; j < 8; j++) {
                        board[i][j] = row.charAt(j);
                    }
                }
                if (in.hasNextLine()) in.nextLine();
                if (emptyRows < 8) {
                    boardsCount++;
                    String answer = aMain.run(board);
                    System.out.println("Game #" + boardsCount + ": " + answer + " king is in check.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public String run(char[][] givenBoard) {
        board = givenBoard;
        answer = "no";
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                switch (board[i][j]) {
                    case 'q': case 'Q': moveQueen(i, j); break;
                    case 'r': case 'R': moveRook(i, j); break;
                    case 'b': case 'B': moveBishop(i, j); break;
                    case 'n': case 'N': moveKnight(i, j); break;
                    case 'p': case 'P': movePawn(i, j); break;
                    // case k - Kings' moves in this challenge doesn't matter.
                }
                if (!answer.equals("no")) return answer;
            }
        }
        return answer;
    }

    public boolean check(int i, int j, char piece) {
        boolean goOn = false;

        if (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (Character.isUpperCase(piece) && board[i][j] == 'k') answer = "black";
            else if (Character.isLowerCase(piece) && board[i][j] == 'K') answer = "white";
            else if (board[i][j] == '.') goOn = true;
        }

        return goOn;
    }

    public void movePawn(int i, int j) {
        char piece = board[i][j];
        if (Character.isLowerCase(piece)) {
            check(i+1, j-1, piece);
            check(i+1, j+1, piece);
        } else {
            check(i-1, j-1, piece);
            check(i-1, j+1, piece);
        }
    }

    public void moveRook(int i, int j) {
        char piece = board[i][j];

        for (int a = i+1; a < 8; a++)
            if (!check(a, j, piece)) break;

        for (int a = i-1; a >= 0; a--)
            if (!check(a, j, piece)) break;

        for (int a = j+1; a < 8; a++)
            if (!check(i, a, piece)) break;

        for (int a = j-1; a >= 0; a--)
            if (!check(i, a, piece)) break;
    }

    void moveBishop(int i, int j) {
        char piece = board[i][j];

        for (int a = i+1, b = j-1; (a < 8) && (b >= 0); a++, b--)
            if (!check(a, b, piece)) break;

        for (int a = i+1, b = j+1; (a < 8) && (b < 8); a++, b++)
            if (!check(a, b, piece)) break;

        for (int a = i-1, b = j-1; (a >= 0) && (b >= 0); a--, b--)
            if (!check(a, b, piece)) break;

        for (int a = i-1, b = j+1; (a >= 0) && (b < 8); a--, b++)
            if (!check(a, b, piece)) break;
    }

    void moveQueen(int i, int j) {
        moveRook(i, j);
        moveBishop(i, j);
    }

    void moveKnight(int i, int j) {
        char piece = board[i][j];
        check(i-2, j+1, piece);
        check(i-2, j-1, piece);
        check(i+2, j+1, piece);
        check(i+2, j-1, piece);

        check(i-1, j+2, piece);
        check(i-1, j-2, piece);
        check(i+1, j+2, piece);
        check(i+1, j-2, piece);
    }
}
