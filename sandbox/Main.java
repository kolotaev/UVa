import java.io.*;
import java.util.*;

public class Main {
    char[][] board;
    String answer;

    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));

            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();

            byte emptyRows = 0;
            int boardsCount = 0;
            String row;
            char [][] board = new char[8][8];

            while (emptyRows != 8) {
                emptyRows = 0;
                for (int i = 0; i < 8; i++) {
                    row = in.nextLine();
                    if (row.equals("........")) emptyRows++;
                    for (int j = 0; j < 8; j++) {
                        board[i][j] = row.charAt(j);
                    }
                }
                in.nextLine();
                if (emptyRows != 8) {
                    boardsCount++;
//                    if (boardsCount > 1) System.out.println();
                    aMain.run(board);
                    System.out.println("Game #" + boardsCount + ": " + aMain.answer + " king is in check.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void run(char[][] givenBoard) {
        board = givenBoard;
        answer = "no";
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 8; j++) {
                switch (board[i][j]) {
                    case 'q': case 'Q' : moveQueen(i, j); break;
                    case 'r': case 'R' : moveRook(i, j); break;
                    case 'b': case 'B' : moveBishop(i, j); break;
                    case 'n': case 'N' : moveKnight(i, j); break;
                    case 'p': case 'P' : movePawn(i, j); break;
                    case 'k': case 'K' : moveKing(i, j); break;
                }
                if (!answer.equals("no")) return;
            }
        }
    }

    public boolean check(int i, int j, char figure) {
        if (i >= 0 && i < 8 && j >= 0 && j < 8) {
            if (board[i][j] != '.' && board[i][j] != 'k' && board[i][j] != 'K')
                return false;

            if (Character.isUpperCase(figure) && board[i][j] == 'k')
                answer = "black";
            else if (Character.isLowerCase(figure) && board[i][j] == 'K')
                answer = "white";
        }
        return true;
    }

    public void movePawn(int i, int j) {
        char figure = board[i][j];
        if (figure == 'p') {
            check(i+1, j-1, figure);
            check(i+1, j+1, figure);
        } else {
            check(i-1, j-1, figure);
            check(i-1, j+1, figure);
        }
    }

    public void moveKing(int i, int j) {
        char figure = board[i][j];
        check(i, j+1, figure);
        check(i, j-1, figure);
        check(i+1, j, figure);
        check(i-1, j, figure);

        check(i-1, j-1, figure);
        check(i-1, j+1, figure);
        check(i+1, j+1, figure);
        check(i+1, j-1, figure);
    }

    public void moveRook(int i, int j) {
        char figure = board[i][j];

        for (int a = i+1; a < 8; a++)
            if (!check(a, j, figure)) break;

        for (int a = i-1; a >= 0; a--)
            if (!check(a, j, figure)) break;

        for (int a = j+1; a < 8; a++)
            if (!check(i, a, figure)) break;

        for (int a = j-1; a >= 0; a--)
            if (!check(i, a, figure)) break;
    }

    void moveBishop(int i, int j) {
        char figure = board[i][j];

        for (int a = i+1, b = j-1; (a < 8) && (b >= 0); a++, b--)
            if (!check(a, b, figure)) break;

        for (int a = i+1, b = j+1; (a < 8) && (b < 8); a++, b++)
            if (!check(a, b, figure)) break;

        for (int a = i-1, b = j-1; (a >= 0) && (b >= 0); a--, b--)
            if (!check(a, b, figure)) break;

        for (int a = i-1, b = j+1; (a >= 0) && (b < 8); a--, b++)
            if (!check(a, b, figure)) break;
    }

    void moveQueen(int i, int j) {
        moveRook(i, j);
        moveBishop(i, j);
    }

    void moveKnight(int i, int j) {
        char figure = board[i][j];
        check(i-2, j+1, figure);
        check(i-2, j-1, figure);
        check(i+2, j+1, figure);
        check(i+2, j-1, figure);

        check(i-1, j+2, figure);
        check(i-1, j-2, figure);
        check(i+1, j+2, figure);
        check(i+1, j-2, figure);
    }
}
