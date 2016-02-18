import java.io.*;
import java.util.*;

public class Main {
    private TreeMap<Integer, String> activeCandidates;
    private int[][] ballots;

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            Main aMain = new Main();
            int blocks = Integer.parseInt(in.nextLine());
            int block = 0;
            if (in.hasNextLine()) in.nextLine();
            while (block < blocks) {
                int candidatesNumber = Integer.parseInt(in.nextLine());
                TreeMap<Integer, String> allCandidates = new TreeMap<>();
                ArrayList<String> ballots = new ArrayList<>();
                String nextBallot = "";

                for (int i = 1; i <= candidatesNumber; i++) {
                    allCandidates.put(i, in.nextLine());
                }

                if (in.hasNextLine()) nextBallot = in.nextLine();
                while (!nextBallot.equals("")) {
                    ballots.add(nextBallot);
                    nextBallot = (in.hasNextLine()) ? in.nextLine() : "";
                }
                if (block > 0) System.out.println();
                TreeMap<Integer, String> result = aMain.run(allCandidates, ballots.toArray(new String[ballots.size()]));
                for (Map.Entry<Integer, String> entry : result.entrySet()) {
                    System.out.println(entry.getValue());
                }
                block++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public TreeMap<Integer, String> run(TreeMap<Integer, String> allCandidates, String[] givenBallots) {
        activeCandidates = allCandidates;
        ballots = ballotsToDigits(givenBallots);

        TreeMap<Integer, TreeMap<Integer, String>> ratings = new TreeMap<>();
        TreeMap<Integer, String> result;

        do {
            ratings.put(0, (TreeMap<Integer, String>)allCandidates.clone());
            processBallots(ratings);
            result = getWinners(ratings);
            eliminateLastOnes(ratings);
        } while (result.size() == 0);

        return result;
    }

    public void processBallots(TreeMap<Integer, TreeMap<Integer, String>> ratings) {
        for (int[] ballot : ballots) {
            int winnerKey = ballot[0];

            String winnerName = activeCandidates.get(winnerKey);
            int winnerIdxInRatings = 0;

            for (Map.Entry<Integer, TreeMap<Integer, String>> entry : ratings.entrySet()) {
                winnerIdxInRatings = entry.getKey();
                TreeMap<Integer, String> positionCandidates = entry.getValue();
                if (positionCandidates.get(winnerKey) != null) {
                    positionCandidates.remove(winnerKey);
                    ratings.put(winnerIdxInRatings, positionCandidates);
                    break;
                }
            }

            if (ratings.get(winnerIdxInRatings).size() == 0) ratings.remove(winnerIdxInRatings);
            winnerIdxInRatings++;
            TreeMap<Integer, String> winnerSiblings = ratings.get(winnerIdxInRatings);
            if (winnerSiblings == null) winnerSiblings = new TreeMap<>();
            winnerSiblings.put(winnerKey, winnerName);
            ratings.put(winnerIdxInRatings, winnerSiblings);
        }
    }

    public TreeMap<Integer, String> getWinners(TreeMap<Integer, TreeMap<Integer, String>> ratings) {
        int winnerVotes = ratings.lastKey();
        TreeMap<Integer, String> winners = ratings.lastEntry().getValue();

        if (winners.size() == activeCandidates.size()) return winners;
        else if (winners.size() == 1 && (winnerVotes > ballots.length - winnerVotes)) return winners;
        else return new TreeMap<>();
    }

    public void eliminateLastOnes(TreeMap<Integer, TreeMap<Integer, String>> ratings) {
        TreeMap<Integer, String> lostOnes = ratings.firstEntry().getValue();
        ratings.remove(ratings.firstKey());
        TreeMap<Integer, String> copy = (TreeMap<Integer, String>)activeCandidates.clone();

        for (Map.Entry<Integer, String> entry : copy.entrySet()) {
            int key = entry.getKey();
            if (lostOnes.containsKey(key)) activeCandidates.remove(key);
        }

        int[][] newBallots = new int[ballots.length][];
        for (int b = 0; b < ballots.length; b++) {
            int[] votes = ballots[b];
            ArrayList<Integer> newVotes = new ArrayList<>();
            for (int vote : votes) {
                if (activeCandidates.containsKey(vote)) newVotes.add(vote);
            }
            int[] newVotesInt = new int[newVotes.size()];
            for (int j = 0; j < newVotes.size(); j++)
                newVotesInt[j] = newVotes.get(j);
            newBallots[b] = newVotesInt;
        }
        ballots = newBallots;
    }

    private int[][] ballotsToDigits(String[] ballots) {
        int[][] result = new int[ballots.length][];
        for (int b = 0; b < ballots.length; b++) {
            String[] votes = ballots[b].split("\\s");
            int[] votesInt = new int[votes.length];
            for (int i = 0; i < votes.length; i++) {
                votesInt[i] = Integer.parseInt(votes[i]);
            }
            result[b] = votesInt;
        }
        return result;
    }
}
