package pl.javastart.voting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VotingResult {

    private BigDecimal percentageOfForVoters;
    private BigDecimal percentageOfAgainstVoters;
    private BigDecimal percentageOfAbstainedVoters;
    private int numberOfForVoters = 0;
    private int numberOfAgainstVoters = 0;
    private int numberOfAbstainedVoters = 0;
    private final List<Vote> voteList = new ArrayList<>();

    public void printResults() {
        calculateResults();
        System.out.printf("Wstrzymało się: %.2f%%\n", percentageOfAbstainedVoters);
        System.out.printf("Głosów za: %.2f%%\n", percentageOfForVoters);
        System.out.printf("Głosów przeciw: %.2f%%\n\n", percentageOfAgainstVoters);

        for (Vote value : voteList) {
            String voter = value.getVoter();
            Boolean vote = value.getVote();
            printVoterResult(voter, vote);
        }

        System.out.println();
    }

    private void printVoterResult(String voter, Boolean vote) {
        System.out.print(voter + ": ");
        if (vote == null) {
            System.out.println("WSTRZYMAŁ SIĘ");
        } else if (!vote) {
            System.out.println("PRZECIW");
        } else {
            System.out.println("ZA");
        }
    }

    private void calculateResults() {
        fillNumberOfVotes();
        fillVotesPersentage();
    }

    private void fillVotesPersentage() {

        BigDecimal numberOfVoters = BigDecimal.valueOf(voteList.size());

        percentageOfForVoters = BigDecimal.valueOf(numberOfForVoters)
                .divide((numberOfVoters), 5, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        percentageOfAgainstVoters = BigDecimal.valueOf(numberOfAgainstVoters)
                .divide((numberOfVoters), 5, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        percentageOfAbstainedVoters = BigDecimal.valueOf(100)
                .subtract(percentageOfForVoters)
                .subtract(percentageOfAgainstVoters);
    }

    private void fillNumberOfVotes() {
        for (Vote value : this.voteList) {
            Boolean vote = value.getVote();
            if (vote == null) {
                numberOfAbstainedVoters++;
            } else if (!vote) {
                numberOfAgainstVoters++;
            } else {
                numberOfForVoters++;
            }
        }
    }

    /**
     * Metoda przyjmuje imię i nazwisko głosującego, np "Zigniew Siobro".
     * Uzupełnij tę metodę, żeby drukowała informację w formie:
     * Zigniew Siobro: ZA
     * Nie zmieniaj sygnatury tej metody!
     */
    public void printVoteForVoter(String voterName) {

        for (Vote vote : voteList) {
            String voter = vote.getVoter();
            if (voterName.equals(voter)) {
                printVoterResult(voter, vote.getVote());
            }
        }
    }

    public void addVoter(String voter, Boolean vote) {
        Vote newVote = new Vote(voter, vote);
        this.voteList.add(newVote);
    }
}
