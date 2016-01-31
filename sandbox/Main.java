import java.io.*;
import java.util.*;

public class Main {
    private int count;
    private int[] ram = new int[1000];
    private int[] registers = new int[10];

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            int cycles = in.nextInt();
            int address;
            for (int i=0; i < cycles; i++) {
                address = 0;
                while (in.hasNextInt()) {
                    aMain.setRamAt(address++, in.nextInt());
                }
                aMain.run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public void setRamAt(int at, int value) {
        ram[at] = value;
    }

    public void run() {
        count = 0;
        for (int a = 0, a < ram.length; a++)
            execute(ram[a]);
    }

    public void execute(int instruction) {
        count++;
        int command = instruction / 100;
        int a = instruction / 10 % 10;
        int b = instruction % 100;


        switch (command) {
            case 1: getInstructionsCount(); break;
            case 2: setFromValue(a, b); break;
            case 3: addFromValue(a, b); break;
            case 4: multiplyFromValue(a, b); break;
            case 5: setFromRegister(a, b); break;
            case 6: addFromRegister(a, b); break;
            case 7: multiplyFromRegister(a, b); break;
            case 8: setFromRAM(a, b); break;
            case 9: setRamFromRegister(a, b); break;
            case 0: goToRamAddress(a, b); break;
        }
    }

    public void getInstructionsCount() {
        System.out.println(count);
    }

    public void setFromValue(int a, int value) {
        registers[a] = value;
    }

    public void addFromValue(int a, int value) {
        registers[a] += value;
    }

    public void multiplyFromValue(int a, int value) {
        registers[a] *= value;
    }

    public void setFromRegister(int a, int b) {
        registers[a] = registers[b];
    }

    public void addFromRegister(int a, int b) {
        registers[a] += registers[b];
    }

    public void multiplyFromRegister(int a, int b) {
        registers[a] *= registers[b];
    }

    public void setFromRAM(int a, int b) {
        int idx = registers[b];
        registers[a] = ram[idx];
    }

    public void setRamFromRegister(int a, int b) {
        int idx = registers[a];
        ram[idx] = registers[b];
    }

    public void goToRamAddress(int a, int b) {

    }
}
