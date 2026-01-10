import java.util.Scanner;

public class Game {
    private String status;
    private String winnerPlayerName;
    private String playerNameA;
    private String playerNameB;
    private ConnectFour connectFour;
    private String currentPlayerName;

    public Game(String playerNameA, String playerNameB) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.connectFour = new ConnectFour();
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.currentPlayerName = playerNameA;
    }

    public String getStatus() {
        return status;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }

    public String getPlayerNameA() {
        return playerNameA;
    }

    public String getPlayerNameB() {
        return playerNameB;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public ConnectFour getConnectFour() {
        return connectFour;
    }

    public String play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("El juego comenzo entre " + playerNameA + " (X) y " + playerNameB + " (O).");

        while (status.equals("IN_PROGRESS")) {
            connectFour.printGrid();
            char currentSymbol = connectFour.getCurrentSymbol();
            currentPlayerName = (currentSymbol == 'X') ? playerNameA : playerNameB;

            System.out.println("Es turno de: "+currentPlayerName + " (" + currentSymbol + "). Ingrese columna (1-7):");

            int column = -1;
            boolean moveMade = false;
            while(!moveMade) {
                if (scanner.hasNextInt()) {
                    column = scanner.nextInt();
                    if (connectFour.makeMove(column)) {
                        moveMade = true;
                    } else {
                        System.out.println("Movimiento invalido (Columna llena o numero fuera de rango) intentelo de nuevo , " + currentPlayerName + " (1-7):");
                    }
                } else {
                    System.out.println("movimiento invalido, porfavor ingrese moviento (1-7).");
                    scanner.next();
                }
            }

            ConnectFour.GameStatus gameStatus = connectFour.isGameOver();
            switch (gameStatus.getState()) {
                case WIN:
                    status = "VICTORY";
                    char winningSymbol = gameStatus.getWinnerSymbol();
                    winnerPlayerName = (winningSymbol == 'X') ? playerNameA : playerNameB;
                    connectFour.printGrid();
                    System.out.println("Game Over! " + winnerPlayerName + " (" + winningSymbol + ") wins!");
                    break;
                case DRAW:
                    status = "DRAW";
                    winnerPlayerName = "";
                    connectFour.printGrid();
                    System.out.println("Game Over! It's a DRAW!");
                    break;
                case IN_PROGRESS:
                    break;
            }
        }
        return winnerPlayerName;
    }

    public double calcularPromedioEvalucaion(int index){
        //el metodo
        double promedio = 0;
        return promedio;
    }

    public double calcularvarianza(int index){
        //metodo
        double prom=calcularPromedioEvalucaion(index);

    }

}
