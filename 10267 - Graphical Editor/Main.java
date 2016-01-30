import java.io.*;
import java.util.*;

public class Main {
    char[][] image;
    int width;
    int height;

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] input = line.split("\\s");
                if (input[0].equals("X")) break;

                aMain.controller(input[0], Arrays.copyOfRange(input, 1, input.length));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void controller(String command, String[] args) {
        switch (command) {
            case "I": create(Integer.parseInt(args[0]), Integer.parseInt(args[1])); break;
            case "S": save(args[0]); break;
            case "C": clear(); break;
            case "L": setPixel(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2].charAt(0)); break;
            case "V":
                paintVerticalLine(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        args[3].charAt(0)
                ); break;
            case "H":
                paintHorizontalLine(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        args[3].charAt(0)
                ); break;
            case "K":
                paintRectangle(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]),
                        args[4].charAt(0)
                ); break;
            case "F":
                fillFromPosition(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        args[2].charAt(0),
                        getPixel(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
                ); break;
        }
    }

    public void create(int width, int height) {
        image = new char[height][width];
        this.width = width;
        this.height = height;
        clear();
    }

    public void save(String name) {
        System.out.println(name);
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                System.out.print(getPixel(j, i));
            }
            System.out.println();
        }
    }

    public void clear() {
        for (int i = 1; i <= height; i++)
            for (int j = 1; j <= width; j++)
                setPixel(j, i, 'O');
    }

    public void setPixel(int x, int y, char color) {
        image[y-1][x-1] = color;
    }

    public char getPixel(int x, int y) {
        return image[y-1][x-1];
    }

    public void paintVerticalLine(int x, int y1, int y2, char color) {
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int y = y1; y <= y2; y++)
            setPixel(x, y, color);
    }

    public void paintHorizontalLine(int x1, int x2, int y, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }

        for (int x = x1; x <= x2; x++)
            setPixel(x, y, color);
    }

    public void paintRectangle(int x1, int y1, int x2, int y2, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++)
                setPixel(x, y, color);
    }

    public void fillFromPosition(int x, int y, char color, char oldColor) {
        if (outOfBoundaries(x, y) || oldColor != getPixel(x, y) || color == oldColor) return;

        setPixel(x, y, color);

        fillFromPosition(x-1, y, color, oldColor);
        fillFromPosition(x+1, y, color, oldColor);
        fillFromPosition(x, y-1, color, oldColor);
        fillFromPosition(x, y+1, color, oldColor);
    }

    private boolean outOfBoundaries(int x, int y) {
        return x-1 < 0 || x-1 >= width || y-1 < 0 || y-1 >= height;
    }
}
