import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            aMain.run(in);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    private void run(Scanner in) {
        while (in.hasNext()) {
            int count = in.nextInt();

            if (count == 0) break;

            int[] trip = new int[count];
            for (int i = 0; i < count; i++) {
                trip[i] = (int) Math.round(in.nextDouble() * 100);
            }

            System.out.printf("$%.02f%n", (double) solve(trip) / 100);
        }
    }

    private int solve(int[] data) {
        long total = 0, avg = 0;
        int given = 0, taken = 0;

        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }
        avg = (long) Math.round((double)total/data.length);

        for (int i = 0; i < data.length; i++) {
            if (data[i] > avg) {
                taken += data[i] - avg;
            } else {
                given += avg - data[i];
            }
        }

        return (given < taken) ? given : taken;
    }
}
