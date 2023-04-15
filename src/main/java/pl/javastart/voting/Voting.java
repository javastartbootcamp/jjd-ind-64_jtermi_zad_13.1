package pl.javastart.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Voting {

    private static final String FOR = "z";
    private static final String AGAINST = "p";

    public static void main(String[] args) {
        List<String> voters = new ArrayList<>();

        voters.add("Jan Kowalski");
        voters.add("Zigniew Siobro");
        voters.add("Zbyszek Stonoga");

        Voting voting = new Voting();

        VotingResult votingResult = voting.executeVoting(voters, new Scanner(System.in));
        votingResult.printResults();
        votingResult.printVoteForVoter("Zigniew Siobro");
    }

    /**
     * Uzupełnij metodę metodę, ale nie zmieniaj jej sygnatury! (typu tego, co przyjmuje i zwraca).
     * do wczytywania danych od użytkownika użyj scannera z parametru
     * Metoda powinna pobrać głos dla każdego przekazanego głosującego i zapisać wyniki głosowania do VotingResult
     */

    VotingResult executeVoting(List<String> voters, Scanner scanner) {
        VotingResult votingResult = new VotingResult();
        for (String voter : voters) {
            Boolean voting = readVote(scanner, voter);
            votingResult.addVoter(voter, voting);
        }
        return votingResult;
    }

    private Boolean readVote(Scanner scanner, String voter) {

        System.out.println("Jak głosuje " + voter + "? (z - za, p - przeciw, w - wstrzymanie się)");
        Boolean result;
        String validVote = "";
        String vote;

        while (validVote.equals("")) {
            vote = scanner.nextLine();
            if ((vote.equals("z")) || (vote.equals("p")) || (vote.equals("w"))) {
                validVote = vote;
            } else {
                System.out.println("nie ma takiej opcji, proszę spóbować jeszcze raz");
            }
        }
        switch (validVote) {
            case FOR -> result = true;
            case AGAINST -> result = false;
            default -> result = null;
        }
        return result;
    }
}

