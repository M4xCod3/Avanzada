import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Scoreboard {
    private BST<Integer, String> winTree;
    private HashST<String, Player> players;
    private List<Player> playerList;
    private int playedGames;

    public Scoreboard() {
        this.winTree = new BST<>();
        this.players = new HashST<>();
        this.playedGames = 0;
        this.playerList = new ArrayList<>();
    }

    public void registerPlayer(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return;
        }
        if (!players.contains(playerName)) {
            Player newPlayer = new Player(playerName);
            players.put(playerName, newPlayer);
            winTree.put(0, playerName);
            playerList.add(newPlayer);
        }
    }
    public void anadirPlayer_DB(String playerName, Player p) {
        players.put(playerName, p);
        winTree.put(0, playerName);
        playerList.add(p);
    }

    public boolean checkPlayer(String playerName) {
        if (playerName == null) {
            return false;
        }
        return players.contains(playerName);
    }

    public void addGameResult(String winnerPlayerName, String loserPlayerName, boolean draw) {
        if (!checkPlayer(winnerPlayerName)) {
            registerPlayer(winnerPlayerName);
        }
        if (!checkPlayer(loserPlayerName)) {
            registerPlayer(loserPlayerName);
        }

        Player winner = players.get(winnerPlayerName);
        Player loser = players.get(loserPlayerName);

        if (winner == null || loser == null) {
            System.err.println("Error: Objeto jugador no encontrado");
            return;
        }

        if (draw) {
            winner.addDraw();
            loser.addDraw();
        } else {
            winner.addWin();
            loser.addLoss();
            winTree.put(winner.getWins(), winner.getPlayerName());
        }
        playedGames++;
    }

    public Player[] winRange(int lo, int hi) {
        ArrayList<Player> resultList = new ArrayList<>();
        for (String playerName : players.keys()) {
            Player p = players.get(playerName);
            if (p != null && p.getWins() >= lo && p.getWins() <= hi) {
                resultList.add(p);
            }
        }
        Collections.sort(resultList, Comparator.comparing(Player::getWins).thenComparing(Player::getPlayerName));
        return resultList.toArray(new Player[0]);
    }

    public Player[] winSuccessor(int winsInput) {
        Integer successorWins = null;
        Iterable<Integer> sortedWinCounts = winTree.keys();

        for (Integer currentWins : sortedWinCounts) {
            if (currentWins > winsInput) {
                successorWins = currentWins;
                break;
            }
        }

        ArrayList<Player> resultList = new ArrayList<>();
        if (successorWins != null) {
            for (String playerName : players.keys()) {
                Player p = players.get(playerName);
                if (p != null && p.getWins() == successorWins) {
                    resultList.add(p);
                }
            }
        }
        Collections.sort(resultList, Comparator.comparing(Player::getPlayerName));
        return resultList.toArray(new Player[0]);
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void displayScoreboard() {
        System.out.println("--- Scoreboard ---");
        System.out.println("Total de Juegos jugados: " + playedGames);
        List<Player> allPlayers = new ArrayList<>();
        for (String playerName : players.keys()) {
            if (players.get(playerName) != null) {
                allPlayers.add(players.get(playerName));
            }
        }
        Collections.sort(allPlayers, Comparator.comparing(Player::getWins, Comparator.reverseOrder())
                .thenComparing(Player::getPlayerName));

        System.out.println("Jugadores (" + allPlayers.size() + "):");
        for (Player p : allPlayers) {
            System.out.printf("  %s: Wins=%d, Draws=%d, Losses=%d, WinRate=%.2f%%",p.getPlayerName(), p.getWins(), p.getDraws(), p.getLosses(), p.winRate() * 100);
        }
        System.out.println("--- End Scoreboard ---");
    }

    public Player getPlayer(String playerName) {
        if(playerName == null || playerName.trim().isEmpty()) {
            return null;
        }
        return players.get(playerName);
    }

    public List<Player> getPlayers() {
        return playerList;
    }
}
