import java.io.*;
import java.util.*;
import java.math.*;

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

    private void run(Scanner in) {
        while (in.hasNext()) {
            int count = in.nextInt();

            if (count == 0) break;

            int[] trip = new int[count];
            for (int i = 0; i < count; i++) {
                trip[i] = Math.round(in.nextFloat() * 100);
            }

            System.out.printf("$%.02f%n", (float) solve(trip) / 100);
        }
    }

    private double solve(int[] data) {
        double sumCeiling, sumFloor;

        sumCeiling = getTransfer(data, "ceil");
        sumFloor = getTransfer(data, "foor");

        return sumFloor < sumCeiling ? sumFloor : sumCeiling;
    }

    private int getTransfer(int[] data, String type) {
        int sum = 0, avg = getAverage(data, type);
        for (int i = 0; i < data.length; i++) {
            if (data[i] > avg) {
                sum += data[i] - avg;
            }
        }
        return sum;
    }

    private int getAverage(int[] data, String type) {
        int total = 0;

        for (int i = 0; i < data.length; i++) {
            total += data[i];
        }

        double avg = (double) total / (double) data.length;
        avg = (type == "ceil") ? Math.ceil(avg) : Math.floor(avg);

        return (int) avg;
    }
}
