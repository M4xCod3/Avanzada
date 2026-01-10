public class Player {
    private String playerName;
    private int wins;
    private int draws;
    private int losses;

    public Player(String playerName) {
        this.playerName = playerName;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public void addWin() {
        this.wins++;
    }

    public void addDraw() {
        this.draws++;
    }

    public void addLoss() {
        this.losses++;
    }

    public double winRate() {
        int totalGames = this.wins + this.draws + this.losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) this.wins / totalGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerName.equals(player.playerName);
    }

    @Override
    public int hashCode() {
        return playerName.hashCode();
    }
    public String ToString(){
        return playerName+","+wins+","+draws+","+losses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

}
