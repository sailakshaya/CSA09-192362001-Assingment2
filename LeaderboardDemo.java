import java.util.Comparator;

public class LeaderboardDemo {
    public static void main(String[] args) {
        Comparator<Team> byRank =
            Comparator.comparingInt((Team t) -> t.points).reversed()
                .thenComparing(
                    Comparator.comparingInt((Team t) -> t.goalDiff).reversed()
                )
                .thenComparing(t -> t.name);

        Leaderboard<Team> board = new Leaderboard<>(byRank);
        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println(board.ranking());
    }
}
