import java.io.*;
import java.util.*;

public class Main {
    private TreeMap<Integer, String> activeCandidates;
    private String[] ballots;
    private int initialCandidatesSize;

    public static void main(String[] args) {
        try {
            System.setIn(new FileInputStream(args[0]));
            Scanner in = new Scanner(new BufferedInputStream(System.in));
            int blocks = Integer.parseInt(in.nextLine());
            int block = 0;
            if (in.hasNextLine()) in.nextLine();
            while (block < blocks) {
                int candidatesNumber = Integer.parseInt(in.nextLine());
                TreeMap<Integer, String> allCandidates = new TreeMap<>();
                TreeMap<Integer, ArrayList<String>> ratings = new TreeMap<>();
                ArrayList<String> ballots = new ArrayList<>();
                ArrayList<String> candidateNames = new ArrayList<>();
                String nextBallot = "";

                for (int i = 1; i <= candidatesNumber; i++) {
                    String name = in.nextLine();
                    allCandidates.put(i, name);
                    candidateNames.add(name);
                }
                ratings.put(0, candidateNames);

                if (in.hasNextLine()) nextBallot = in.nextLine();
                while (!nextBallot.equals("")) {
                    ballots.add(nextBallot);
                    nextBallot = (in.hasNextLine()) ? in.nextLine() : "";
                }

                Main aMain = new Main(allCandidates, ballots.toArray(new String[ballots.size()]));
                ArrayList<String> result = aMain.run(ratings);
                for (String name : result) {
                    System.out.println(name);
                }
                System.out.println();
                block++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public Main(TreeMap<Integer, String> allCandidates, String[] givenBallots) {
        activeCandidates = allCandidates;
        ballots = givenBallots;
        initialCandidatesSize = allCandidates.size();
    }

    public ArrayList<String> run(TreeMap<Integer, ArrayList<String>> ratings) {
        processBallots(ratings);
        ArrayList<String> result = getWinners(ratings);
        if (result.size() == 0) {
            eliminateLastOnes(ratings);
            result = run(ratings);
        }

        return result;
    }

    public void processBallots(TreeMap<Integer, ArrayList<String>> ratings) {
        for (String ballot : ballots) {
            String[] votes = ballot.split("\\s");
            for (int v = 0; v < votes.length; v++) {
                int winner = Integer.parseInt(ballot.split("\\s")[v]);
                if (activeCandidates.containsKey(winner)) {
                    String winnerName = activeCandidates.get(winner);
                    int winnerIdxInTree = 0;

                    for (Map.Entry<Integer, ArrayList<String>> entry : ratings.entrySet()) {
                        winnerIdxInTree = entry.getKey();
                        ArrayList<String> positionCandidates = entry.getValue();
                        int pos = positionCandidates.indexOf(winnerName);
                        if (pos != -1) {
                            positionCandidates.remove(pos);
                            ratings.put(winnerIdxInTree, positionCandidates);
                            break;
                        }
                    }

                    if (ratings.get(winnerIdxInTree).size() == 0) ratings.remove(winnerIdxInTree);
                    winnerIdxInTree++;
                    ArrayList<String> winnerSiblings = ratings.get(winnerIdxInTree);
                    if (winnerSiblings == null) winnerSiblings = new ArrayList<>();
                    winnerSiblings.add(winnerName);
                    ratings.put(winnerIdxInTree, winnerSiblings);
                    break;
                }
            }
        }
    }

    public ArrayList<String> getWinners(TreeMap<Integer, ArrayList<String>> ratings) {
        int winnerVotes = ratings.lastKey();
        ArrayList<String> winners = ratings.lastEntry().getValue();

        if (winners.size() == activeCandidates.size()) return winners;
        else if (winners.size() == 1 && (initialCandidatesSize - winnerVotes < winnerVotes)) return winners;
        else return new ArrayList<>();
    }

    public void eliminateLastOnes(TreeMap<Integer, ArrayList<String>> ratings) {
        ArrayList<String> lostOnes = ratings.firstEntry().getValue();
        ratings.remove(ratings.firstKey());

        for (Map.Entry<Integer, String> entry : activeCandidates.entrySet()) {
            int key = entry.getKey();
            String name = entry.getValue();
            if (lostOnes.contains(name)) activeCandidates.remove(key);
        }
    }
}
