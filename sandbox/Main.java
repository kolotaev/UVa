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
            e.printStackTrace();
            System.out.println();
        }
    }

    public String run(String[] candidates, String[] ballots) {
        int[] firstOnes = getRatingsOf("first", ballots);
        int[] lastOnes = getRatingsOf("last", ballots);
        return "2";
    }

    public int[] getRatingsOf(String type, String[] ballots) {
        int[] result = new int[ballots[0].split("\\s").length + 1];

        for (String ballot : ballots) {
            if (ballot == null) break;
            String[] votes = ballot.split("\\s");
            String candidate = (type.equals("first")) ? votes[0] : votes[votes.length-1];
            result[Integer.parseInt(candidate)]++;
        }

        return result;
    }
}