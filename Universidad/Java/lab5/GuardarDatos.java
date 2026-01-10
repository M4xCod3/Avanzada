import java.io.*;
import java.util.List;
import java.util.Scanner;
public class GuardarDatos {
    public static final Scoreboard scoreboard = new Scoreboard();

    public static void SaveData(List<Player> listPlayers) {
        try (FileWriter fw = new FileWriter("BD.txt")) {
            for(Player p: listPlayers) {
                fw.write(p.ToString()+"\n");
            }
            fw.write("\n Jugadores totoales: "+listPlayers.size());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Leer()throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("BD.txt"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 4){
                    String name=parts[0].trim();
                    int wins=Integer.parseInt(parts[1].trim());
                    int draws=Integer.parseInt(parts[2].trim());
                    int losses=Integer.parseInt(parts[3].trim());

                    System.out.println("Nombre: "+name+" Ganados: "+wins+" Empates: "+draws+" Losses: "+losses);
                    Player p = new Player(name);
                    p.setWins(wins);
                    p.setDraws(draws);
                    p.setLosses(losses);
                    scoreboard.anadirPlayer_DB(name, p);
                }
                else{
                    System.out.println("Error: Formato incorrecto");
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
        catch (IOException e) {
            System.out.println("Error: IO Exception");
        }
    }

}