import java.io.*;
import java.util.*;

public class Main {
    private int count, currentPosition;
    private int[] ram, registers;

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            int cycles = Integer.parseInt(in.nextLine());
            in.nextLine();
            for (int i=0; i < cycles; i++) {
                Main aMain = new Main(in);
                aMain.run();
                if (i < cycles-1) System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public Main(Scanner in) {
        ram = new int[1000];
        registers = new int[10];
        count = 0;
        currentPosition = 0;
        String line;
        for (int i = 0; in.hasNextLine(); i++) {
            line = in.nextLine();
            if (line.equals("")) break;
            ram[i] = Integer.parseInt(line);
        }
    }

    public void run() {
        while (currentPosition < ram.length) {
            int previous = currentPosition;
            execute(ram[currentPosition]);
            count++;
            if (previous == currentPosition) currentPosition++;
        }

        System.out.println(count);
    }

    public void execute(int instruction) {
        int command = instruction / 100;
        int a = instruction / 10 % 10;
        int b = instruction % 10;

        switch (command) {
            case 1: halt(); break;
            case 2: setFromValue(a, b); break;
            case 3: addFromValue(a, b); break;
            case 4: multiplyFromValue(a, b); break;
            case 5: setFromRegister(a, b); break;
            case 6: addFromRegister(a, b); break;
            case 7: multiplyFromRegister(a, b); break;
            case 8: setFromRAM(a, b); break;
            case 9: setRamFromRegister(a, b); break;
            case 0: jumpToRamAddress(a, b); break;
        }
    }

    public void halt() {
        currentPosition = ram.length + 100;
    }

    public void setFromValue(int a, int value) {
        registers[a] = value;
    }

    public void addFromValue(int a, int value) {
        registers[a] = (registers[a] + value) % 1000;
    }

    public void multiplyFromValue(int a, int value) {
        registers[a] = (registers[a] * value) % 1000;
    }

    public void setFromRegister(int a, int b) {
        registers[a] = registers[b];
    }

    public void addFromRegister(int a, int b) {
        registers[a] = (registers[a] + registers[b]) % 1000;
    }

    public void multiplyFromRegister(int a, int b) {
        registers[a] = (registers[a] * registers[b]) % 1000;
    }

    public void setFromRAM(int a, int b) {
        registers[a] = ram[registers[b]];
    }

    public void setRamFromRegister(int a, int b) {
        ram[registers[b]] = registers[a];
    }

    public void jumpToRamAddress(int a, int b) {
        if (registers[b] != 0) currentPosition = registers[a];
    }
}
