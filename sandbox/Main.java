import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();

            byte emptyRows = 0;
            String row = "";
            char [][] board = new char[8][8];

            while (emptyRows != 8) {
                for (int i = 0; i < 8; i++) {
                    row = in.nextLine();
                    for (int j = 0; j < row.length(); j++)
                        board[i][j] = row.charAt(j);
                }
                if (row.equals("........")) emptyRows++;
                in.nextLine();
                aMain.run(board);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void run(char[][] board) {

    }
}
