import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();
        boolean Running = true;
        GuardarDatos.Leer();

        System.out.println("Bienvenido a Connect Four!");

        while(Running) {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar jugador");
            System.out.println("2. Jugar");
            System.out.println("3. Mostrar marcador (Todos los jugadores por porcentaje de victoria)");
            System.out.println("4. Mostrar estadisticas del jugador");
            System.out.println("5. Salir");
            System.out.print("Elija una opcion: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Registrar Jugador");
                    System.out.println("---------------");
                    System.out.println("Ingrese el nombre del jugador: ");
                    String name = sc.next();
                    if(name.trim().isEmpty()) {
                        System.out.println("el nombre del jugador no puede estar vacio");
                    }
                    else if(scoreboard.checkPlayer(name)) {
                        System.out.println("El jugador "+name+" ya existe");
                    }
                    else {
                        scoreboard.registerPlayer(name);
                        System.out.println("El jugador "+name+" fue regiustrado con exito");
                    }
                    break;
                case 2:
                    String PlayerA = "";
                    String PlayerB = "";
                    System.out.println("Elija una opcion: ");
                    System.out.println("1. Jugar con Nombre ya Registrado");
                    System.out.println("2. Registrar y jugar");
                    int option = sc.nextInt();
                    boolean jugar = true;
                    switch (option) {
                        case 1:
                            System.out.println("Ingrese el nombre del jugador A(X): ");
                            PlayerA = sc.next();
                            if(!scoreboard.checkPlayer(PlayerA)) {
                                System.out.println("El jugador "+PlayerA+" no existe, porfavor registrelo antes de jguar");
                                jugar = false;
                                break;
                            }
                            System.out.println("Ingrese el nombre del jugador B(O): ");
                            PlayerB = sc.next();
                            if(!scoreboard.checkPlayer(PlayerB)) {
                                System.out.println("El jugador "+PlayerB+" no existe, porfavor registrelo antes de jguar");
                                jugar = false;
                                break;
                            }
                            if(PlayerA.equals(PlayerB)) {
                                System.out.println("Los jugadores no pueden ser las mismas personas");
                                jugar = false;
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("Registrar PlayerA(X)");
                            System.out.println("---------------");
                            System.out.println("Ingrese el nombre del jugador: ");
                            name = sc.next();
                            if(name.trim().isEmpty()) {
                                System.out.println("el nombre del jugador no puede estar vacio");
                            }
                            else if(scoreboard.checkPlayer(name)) {
                                System.out.println("El jugador "+name+" ya existe");
                            }
                            else {
                                scoreboard.registerPlayer(name);
                                System.out.println("El jugador "+name+" fue regiustrado con exito");
                            }
                            PlayerA = name;
                            System.out.println("Registrar PlayerB(O)");
                            System.out.println("---------------");
                            System.out.println("Ingrese el nombre del jugador: ");
                            name = sc.next();
                            if(name.trim().isEmpty()) {
                                System.out.println("el nombre del jugador no puede estar vacio");
                            }
                            else if(scoreboard.checkPlayer(name)) {
                                System.out.println("El jugador "+name+" ya existe");
                            }
                            else {
                                scoreboard.registerPlayer(name);
                                System.out.println("El jugador "+name+" fue regiustrado con exito");
                            }
                            PlayerB = name;
                            break;
                        default:
                            System.out.println("Opcion no valida ");
                            break;
                    }
                    if(jugar) {
                        System.out.println("Iniciando el juego con: "+PlayerA+"(X) y "+PlayerB+"(O)");
                        Game game = new Game(PlayerA, PlayerB);
                        String winnerName=game.play();

                        boolean IsDraw=winnerName.isEmpty();
                        if(IsDraw) {
                            System.out.println("El juego termino en empate");
                            scoreboard.addGameResult(PlayerA,PlayerB,true);
                        }
                        else{
                            System.out.println("Winner: "+winnerName);
                            if(winnerName.equals(PlayerA)) {
                                scoreboard.addGameResult(PlayerA,PlayerB,true);
                            }
                            else{
                                scoreboard.addGameResult(PlayerB,PlayerA,false);
                            }
                        }
                        System.out.println("Resultados del juegos registrado en el marcador. ");
                        break;
                    }
                    break;
                case 3:
                    System.out.println("\n------Scoreboard-----------");
                    Player[] allPlayer=scoreboard.winRange(0,Integer.MAX_VALUE);
                    if(allPlayer.length==0) {
                        System.out.println("No existen jugadores o no se han jugado partidas");
                    }
                    else{
                        System.out.println("Partidas jugadas: "+scoreboard.getPlayedGames());
                        System.out.println("Player"+ "Wins"+ "Loss"+"Draws"+"Win Rate");
                        System.out.println("-".repeat(55));
                        for(Player p:allPlayer) {
                            System.out.println(p.ToString());
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n------Player Stats-----------");
                    System.out.println("Ingresa el nombre del que desea buscar las estadisticas del jugador: ");
                    String playerName = sc.next();
                    Player p=scoreboard.getPlayer(playerName);
                    if(p!=null) {
                        System.out.println("Stats de: "+p.getPlayerName());
                        System.out.println("Wins: "+p.getWins());
                        System.out.println("Losses: "+p.getLosses());
                        System.out.println("Draws: "+p.getDraws());
                        System.out.println("Win Rate: "+p.winRate()+"%");
                    }
                    else{
                        System.out.println("No existen jugadores o no se han jugado");
                    }
                    break;

                case 5:
                    System.out.println("--------Exit---------");
                    List<Player> players=scoreboard.getPlayers();
                    GuardarDatos.SaveData(players);
                    Running = false;
                    break;
                default:
                    System.out.println("Opcion invalida. porfavor intentelo de nuevo");
                    break;
            }
        }
    }
}