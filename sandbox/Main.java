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
                    for (int j = 0; j < 8; j++) {
                        board[i][j] = row.charAt(j);
                        if (row.equals("........")) emptyRows++;
                    }
                }
                in.nextLine();
                if (emptyRows != 8) {
                    boardsCount++;
                    aMain.run(board);
                    System.out.print("Game #" + boardsCount + ": " + aMain.answer + " king is in check.");
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
                    case 'p': case 'P' : movePawn(i, j); break;
                    case 'k': case 'K' : moveKing(i, j); break;
                    case 'n': case 'N' : moveKnight(figure); break;
                    case 'b': case 'B' : moveBishop(figure); break;
                    case 'r': case 'R' : moveRook(figure); break;
                    case 'q': case 'Q' : moveQueen(figure); break;
                }
                if (!answer.equals("no")) return;
            }
        }
    }

    public boolean check(int i, int j, char figure) {
        if (i >= 0 && i < 8 && j >= 0 && i < 8) {
            if (Character.isUpperCase(figure) && board[i][j] == 'k')
                answer = "white";
            else if (Character.isLowerCase(figure) && board[i][j] == 'K')
                answer = "black";
            else if (board[i][j] != 'k' && board[i][j] == 'K' && board[i][j] == '.')
                return false;
        }
        return true;
    }

    public void movePawn(int i, int j) {
        char figure = board[i][j];
        if (figure == 'p') {
            check(i+1, j-1, figure);
            check(i+1, j+1, figure);
        } else {
            check(i+1, j+1, figure);
            check(i-1, j+1, figure);
        }
    }

    public void moveKing(int i, int j) {
        char figure = board[i][j];
        check(i, j+1, figure);
        check(i, j-1, figure);
        check(i+1, j, figure);
        check(i-1, j, figure);
    }
}

