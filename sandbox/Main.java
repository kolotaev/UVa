import java.io.*;
import java.util.*;

public class Main {
    private String[] candidates;

    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            int blocks = Integer.parseInt(in.nextLine());
            int block = 0;
            if (in.hasNextLine()) in.nextLine();
            while (block < blocks) {
                String[] candidates = new String[Integer.parseInt(in.nextLine())];
                String[] ballots = new String[1000];
                int ballotNum = 0;
                for (int i = 0; i < candidates.length; i++) {
                    candidates[i] = in.nextLine();
                }
                String nextBallot = "";
                if (in.hasNextLine()) nextBallot = in.nextLine();
                while (!nextBallot.equals("")) {
                    ballots[ballotNum] = nextBallot;
                    nextBallot = (in.hasNextLine()) ? in.nextLine() : "";
                    ballotNum++;
                }
                System.out.println(aMain.run(candidates, ballots));
                block++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    public String run(String[] candidates, String[] ballots) {
        return "2";
    }
}