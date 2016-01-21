import java.io.*;
import java.util.*;

public class Main {
    char[][] image;
    int width;
    int height;

    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));

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
        int a, b, c, d;
        switch (command) {
            case "I": create(Integer.parseInt(args[0]), Integer.parseInt(args[1])); break;
            case "S": save(args[0]); break;
            case "C": clear(); break;

            case "L":
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                if (isValidXY(a, b))
                    paintPixel(a, b, args[2].charAt(0));
                break;
            case "V":
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                c = Integer.parseInt(args[2]);
                if (isValidXY(a, b) && isValidXY(a, c))
                    paintVerticalLine(a, b, c, args[3].charAt(0));
                break;
            case "H":
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                c = Integer.parseInt(args[2]);
                if (isValidXY(a, c) && isValidXY(b, c))
                    paintHorizontalLine(a, b, c, args[3].charAt(0));
                break;
            case "K":
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                c = Integer.parseInt(args[2]);
                d = Integer.parseInt(args[3]);
                if (isValidXY(a, c) && isValidXY(b, d))
                    paintRectangle(a, b, c, d, args[4].charAt(0));
                break;
            case "F":
                a = Integer.parseInt(args[0]);
                b = Integer.parseInt(args[1]);
                if (isValidXY(a, b))
                    fillFromPosition(a-1, b-1, args[2].charAt(0));
                break;
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
    }

    public void clear() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                image[i][j] = 'O';
    }

    public void paintPixel(int x, int y, char color) {
        image[y-1][x-1] = color;
    }

    public void paintVerticalLine(int x, int y1, int y2, char color) {
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int i = y1-1; i <= y2-1; i++) {
            image[i][x-1] = color;
        }
    }

    public void paintHorizontalLine(int x1, int x2, int y, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }

        for (int i = x1-1; i <= x2-1; i++) {
            image[y-1][i] = color;
        }
    }

    public void paintRectangle(int x1, int x2, int y1, int y2, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int i = y1-1; i <= y2-1; i++)
            for (int j = x1-1; j <= x2-1; j++)
                image[i][j] = color;
    }

    public void fillFromPosition(int x, int y, char color) {
        char previousColor = image[y][x];
        image[y][x] = color;

//        int colorCount = 0;
//        for (int i = 0; i < height; i++)
//            for (int j = 0; j < width; j++)
//                if (image[i][j] == color) colorCount++;
//
//        if (colorCount == height * width || colorCount == height * width - 1) return;

//        if (color == previousColor)
//            fillFromPosition(x, y, '~');

        int[][] neighbourPixels = new int[4][2];
        neighbourPixels[0][0] = x - 1;
        neighbourPixels[0][1] = y;

        neighbourPixels[1][0] = x + 1;
        neighbourPixels[1][1] = y;

        neighbourPixels[2][0] = x;
        neighbourPixels[2][1] = y - 1;

        neighbourPixels[3][0] = x;
        neighbourPixels[3][1] = y + 1;

        for (int[] p : neighbourPixels)
            if (withinSize(p[0], p[1]) && image[p[1]][p[0]] == previousColor)
                fillFromPosition(p[0], p[1], color);
    }

    private boolean withinSize(int x, int y) {
        return x >= 0 && x < image[0].length && y >= 0 && y < image.length;
    }

    private boolean isValidXY(int x, int y) {
        return x <= height && y <= width;
    }
}
