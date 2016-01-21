import java.io.*;
import java.util.*;

public class Main {
    char[][] image;
    int width;
    int height;

    public static void main(String[] args) {
        try {
            //System.setIn(new FileInputStream(args[0]));

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
                    setPixel(a, b, args[2].charAt(0));
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
                    fillFromPosition(a, b, args[2].charAt(0));
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

    public void setPixel(int x, int y, char color) {
        image[y-1][x-1] = color;
    }

    public char getPixel(int x, int y) {
        return image[y-1][x-1];
    }

    public void paintVerticalLine(int x, int y1, int y2, char color) {
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int i = y1; i <= y2; i++) {
            setPixel(x, i, color);
        }
    }

    public void paintHorizontalLine(int x1, int x2, int y, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }

        for (int i = x1; i <= x2; i++) {
            setPixel(i, y, color);
        }
    }

    public void paintRectangle(int x1, int x2, int y1, int y2, char color) {
        if (x1 > x2) { int tmp = x1; x1 = x2; x2 = tmp; }
        if (y1 > y2) { int tmp = y1; y1 = y2; y2 = tmp; }

        for (int i = y1; i <= y2; i++)
            for (int j = x1; j <= x2; j++)
                setPixel(j, i, color);
    }

    public void fillFromPosition(int x, int y, char color) {
        if (!withinSize(x-1, y-1)) return;
        char previousColor = getPixel(x, y);
        if (getPixel(x, y) != previousColor) return;
        setPixel(x, y, color);

            fillFromPosition(x-1, y, color);
            fillFromPosition(x+1, y, color);
            fillFromPosition(x, y-1, color);
            fillFromPosition(x, y+1, color);
    }

    private boolean withinSize(int x, int y) {
        return x >= 0 && x < image[0].length && y >= 0 && y < image.length;
    }

    private boolean isValidXY(int x, int y) {
        return x <= height && y <= width;
    }
}