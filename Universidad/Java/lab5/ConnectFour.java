public class ConnectFour {
    private static final int Filas = 6;
    private static final int Columnas = 7;
    private char[][] grid;
    private char currentSymbol; // 'X' o 'O'

    public ConnectFour() {
        grid = new char[Filas][Columnas];
        for (int i = 0; i < Filas; i++) {
            for (int j = 0; j < Columnas; j++) {
                grid[i][j] = ' ';
            }
        }
        currentSymbol = 'X';
    }

    public char getCurrentSymbol() {
        return currentSymbol;
    }

    public char[][] getGrid() {
        char[][] gridCopy = new char[Filas][Columnas];
        for (int i = 0; i < Filas; i++) {
            System.arraycopy(grid[i], 0, gridCopy[i], 0, Columnas);
        }
        return gridCopy;
    }

    public void printGrid() {
        System.out.println(" 1 2 3 4 5 6 7");
        System.out.println("---------------");
        for (int i = 0; i < Filas; i++) {
            System.out.print("|");
            for (int j = 0; j < Columnas; j++) {
                System.out.print(grid[i][j] + "|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println();
    }

    public boolean makeMove(int col) {
        if (col < 1 || col > Columnas) {
            return false;
        }
        int actualCol = col - 1;

        for (int i = Filas - 1; i >= 0; i--) {
            if (grid[i][actualCol] == ' ') {
                grid[i][actualCol] = currentSymbol;
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
                return true;
            }
        }
        return false;
    }

    public static class GameStatus {
        public enum State { IN_PROGRESS, WIN, DRAW }
        State state;
        char winnerSymbol;

        GameStatus(State state, char winnerSymbol) {
            this.state = state;
            this.winnerSymbol = winnerSymbol;
        }

        public State getState() { return state; }
        public char getWinnerSymbol() { return winnerSymbol; }
    }

    public GameStatus isGameOver() {
        char winner = checkWin();
        if (winner != ' ') {
            return new GameStatus(GameStatus.State.WIN, winner);
        }


        if (isBoardFull()) {
            return new GameStatus(GameStatus.State.DRAW, ' ');
        }

        return new GameStatus(GameStatus.State.IN_PROGRESS, ' ');
    }

    private char checkWin() {
        for (int i = 0; i < Filas; i++) {
            for (int j = 0; j <= Columnas - 4; j++) {
                if (grid[i][j] != ' ' &&
                        grid[i][j] == grid[i][j+1] &&
                        grid[i][j] == grid[i][j+2] &&
                        grid[i][j] == grid[i][j+3]) {
                    return grid[i][j];
                }
            }
        }

        for (int j = 0; j < Columnas; j++) {
            for (int i = 0; i <= Filas - 4; i++) {
                if (grid[i][j] != ' ' &&
                        grid[i][j] == grid[i+1][j] &&
                        grid[i][j] == grid[i+2][j] &&
                        grid[i][j] == grid[i+3][j]) {
                    return grid[i][j];
                }
            }
        }

        for (int i = 3; i < Filas; i++) {
            for (int j = 0; j <= Columnas - 4; j++) {
                if (grid[i][j] != ' ' &&
                        grid[i][j] == grid[i-1][j+1] &&
                        grid[i][j] == grid[i-2][j+2] &&
                        grid[i][j] == grid[i-3][j+3]) {
                    return grid[i][j];
                }
            }
        }

        for (int i = 0; i <= Filas - 4; i++) {
            for (int j = 0; j <= Columnas - 4; j++) {
                if (grid[i][j] != ' ' &&
                        grid[i][j] == grid[i+1][j+1] &&
                        grid[i][j] == grid[i+2][j+2] &&
                        grid[i][j] == grid[i+3][j+3]) {
                    return grid[i][j];
                }
            }
        }
        return ' ';
    }

    private boolean isBoardFull() {
        for (int j = 0; j < Columnas; j++) {
            if (grid[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }
}
