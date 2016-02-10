import java.io.*;
import java.util.*;

public class Main {
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

    public String[] run(String[] candidates, String[] ballots) {
        int[] firstOnes = getRatingsOf("first", ballots);
        int winner = getWinner(firstOnes, ballots.length);

        if (winner != 0) return getWinnersList(new int[] {winner}, candidates);
        else if (allEqual(firstOnes)) return candidates;
        else run(getNonLoosersList(candidates), ballots);
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

    public int getWinner(int[] firstOnes, int total) {
        if (firstOnes.length >= 1 && (total - firstOnes[1] < firstOnes[1])) return firstOnes[1];
        else return 0;
    }

    public boolean allEqual(int[] firstOnes) {
        if (firstOnes.length == 0) return false;
        int sample = firstOnes[1];
        for (int i = 2; i < firstOnes.length; i++)
            if (firstOnes[i] != sample) return false;
        return true;
    }

    public String[] getWinnersList(int[] winners, String[] candidates) {
        String[] list = new String[winners.length-1];
        for (int i = 0; i < winners.length; i++)
            list[i] = candidates[winners[i]];
        return list;
    }

    public String[] getNonLoosersList(String [] candidates) {
        String[] result;

        //return result;
    }
}