import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            while (in.hasNextInt()) {
                int number = in.nextInt();
                int[] data = new int[number];
                for (int i = 0; i < number; i++) {
                    data[i] = in.nextInt();
                }
                System.out.println((aMain.run(data)) ? "Jolly" : "Not jolly");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public boolean run(int[] data) {
        Set<Integer> bag = new HashSet<>();

        for (int k = 1; k < data.length; k++)
            bag.add(k);

        for (int i = 0; i < data.length - 1; i++)
            bag.remove(Math.abs(data[i+1] - data[i]));

        return bag.size() == 0;
    }
}
