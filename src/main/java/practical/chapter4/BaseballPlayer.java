package practical.chapter4;

public class BaseballPlayer implements Comparable<BaseballPlayer> {
    private String teamName;
    private String playerName;
    private String position;
    private int ranking;

    public BaseballPlayer(){}
    public BaseballPlayer(String playerName) {
        this.playerName = playerName;
    }
    public String getTeamName() {
        return teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public int compareTo(BaseballPlayer baseballPlayer) {
        return playerName.compareTo(baseballPlayer.getPlayerName());
    }
}
