import java.io.*;
import java.util.*;

public class Main {
    private PrintStream o;
    // d -> '-'; s -> ''; r -> '| '; l -> ' |'; w -> '| |'
    private static char[][] templates = {
            {'d', 'w', 's', 'w', 'd'}, // 0
            {'s', 'r', 's', 'r', 's'}, // 1
            {'d', 'r', 'd', 'l', 'd'}, // 2
            {'d', 'r', 'd', 'r', 'd'}, // 3
            {'s', 'w', 'd', 'r', 's'}, // 4
            {'d', 'l', 'd', 'r', 'd'}, // 5
            {'d', 'l', 'd', 'w', 'd'}, // 6
            {'d', 'r', 's', 'r', 's'}, // 7
            {'d', 'w', 'd', 'w', 'd'}, // 8
            {'d', 'w', 'd', 'r', 'd'}, // 9
    };

    public static void main(String[] args) {
        try {
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

        for (int i = 0; scale > 0 && i < 5; i++) {
            int times = (i%2 == 0) ? 1 : scale;
            for (int j = 1; j <= times; j++) {
                for (byte d = 0; d < digits.length; d++) {
                    char[] tpl = templates[Character.getNumericValue(digits[d])];
                    if (i%2 == 0) {
                        dash(tpl[i], scale);
                    } else {
                        pipe(tpl[i], scale);
                    }
                    if (d < digits.length - 1) o.print(" ");
                }
                o.println();
            }
        }
        o.println();
    }

    private void dash(char format, int scale) {
        o.print(" ");
        for (int i = 1; i <= scale; i++) {
            if (format == 's') {
                o.print(" ");
            } else {
                o.print("-");
            }
        }
        o.print(" ");
    }

    private void pipe(char format, int scale) {
        if (format == 'l') {
            o.print("|");
            for (int i = 1; i <= scale + 1; i++) {
                o.print(" ");
            }
        } else if (format == 'r') {
            for (int i = 1; i <= scale + 1; i++) {
                o.print(" ");
            }
            o.print("|");
        } else {
            o.print("|");
            for (int i = 1; i <= scale; i++) {
                o.print(" ");
            }
            o.print("|");
        }
    }
}
