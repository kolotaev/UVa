import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            aMain.run(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void run(Scanner in) {
        while (in.hasNext()) {
            byte scale = Byte.parseByte(in.next());
            int number = Integer.parseInt(in.next());

            if (scale == 0 && number == 0) break;

            displayLCD(scale, number);
        }
    }

    public void displayLCD(byte scale, int number) {
        System.out.println(scale * number);
    }
}
