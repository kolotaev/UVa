import java.io.*;
import java.util.*;

public class Main {
    private PrintStream o;
    
    public static void main(String[] args) {
        try {
//            System.setIn(new FileInputStream(args[0]));
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            aMain.run(in, System.out);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void run(Scanner in, PrintStream out) {
        o = out;
        while (in.hasNext()) {
            int scale = Integer.parseInt(in.next());
            int number = Integer.parseInt(in.next());

            if (scale == 0 && number == 0) break;

            displayLCD(scale, number);
        }
    }

    public void displayLCD(int scale, int number) {
        char[] digits = Integer.toString(number).toCharArray();

        if  (scale > 0) {
            for (char digit : digits) {
                printRow("top", digit, scale);
            }
            o.println();
        }

        for (int i = 1; i <= scale; i++) {
            for (char digit : digits) {
                printRow("preMiddle", digit, scale);
            }
            o.println();
        }

        if  (scale > 0) {
            for (char digit : digits) {
                printRow("middle", digit, scale);
            }
            o.println();
        }

        for (int i = 1; i <= scale; i++) {
            for (char digit : digits) {
                printRow("postMiddle", digit, scale);
            }
            o.println();
        }

        for (char digit : digits) {
            printRow("bottom", digit, scale);
        }
        o.println();
    }

    private void printRow(String part, char number, int scale) {
        if (scale == 0) {
            return;
        }
        switch (part) {
            case "top": top(number, scale); break;
            case "preMiddle": preMiddle(number, scale); break;
            case "middle": middle(number, scale); break;
            case "postMiddle": postMiddle(number, scale); break;
            case "bottom": bottom(number, scale); break;
        }
        o.print(" ");
    }

    private void top(char n, int scale) {
        o.print(" ");
        for (int i = 1; i <= scale; i++) {
            if (n == '1' || n == '4') {
                o.print(" ");
            } else {
                o.print("-");
            }
        }
        o.print(" ");
    }

    private void preMiddle(char n, int scale) {
        if (n == '1' || n == '2' || n == '3' || n == '7') {
            for (int i = 1; i <= 1 + scale; i++) {
                o.print(" ");
            }
            o.print("|");
        } else if (n == '5' || n == '6') {
            o.print("|");
            for (int i = 1; i <= 1 + scale; i++) {
                o.print(" ");
            }
        } else {
            o.print("|");
            for (int i = 1; i <= scale; i++) {
                o.print(" ");
            }
            o.print("|");
        }
    }

    private void middle(char n, int scale) {
        o.print(" ");
        for (int i = 1; i <= scale; i++) {
            if (n == '1' || n == '7' || n == '0') {
                o.print(" ");
            } else {
                o.print("-");
            }
        }
        o.print(" ");
    }

    private void postMiddle(char n, int scale) {
        if (n == '6' || n == '8' || n == '0') {
            o.print("|");
            for (int i = 1; i <= scale; i++) {
                o.print(" ");
            }
            o.print("|");
        } else if (n == '2') {
            o.print("|");
            for (int i = 1; i <= 1 + scale; i++) {
                o.print(" ");
            }
        } else {
            for (int i = 1; i <= 1 + scale; i++) {
                o.print(" ");
            }
            o.print("|");
        }
    }

    private void bottom(char n, int scale) {
        o.print(" ");
        for (int i = 1; i <= scale; i++) {
            if (n == '1' || n == '4' || n == '7') {
                o.print(" ");
            } else {
                o.print("-");
            }
        }
        o.print(" ");
    }
}
