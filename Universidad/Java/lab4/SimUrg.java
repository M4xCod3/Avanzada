//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class SimUrg {
    private static final Scanner sc;

    public static void SimUrg() {
        Hospital hospital = new Hospital();
        Timestamp timeStampIni = new Timestamp(System.currentTimeMillis());
        long timestapInicio = timeStampIni.getTime();
        int cantp = 0;
        int cantat = 0;
        int duracion = 1440;
        int bus = 30;

        for(int minutActual = 1; minutActual <= duracion; ++minutActual) {
            Timestamp timeStampActual = new Timestamp(timeStampIni.getTime() + (long)(minutActual * '\uea60'));
            long timeActual = timeStampActual.getTime();
            if (minutActual % 10 == 0) {
                Paciente p = GeneradorPaciente.GenerarPaciente(timestapInicio, timeActual);
                hospital.RegistrarPaciente(p);
                System.out.println("Paciente registrado en el minuto " + minutActual);
                System.out.println(p);
                ++cantp;
            }

            if (minutActual % 5 == 0) {
                PrintStream var10000 = System.out;
                int var10001 = hospital.CantPacientes();
                var10000.println("Hay: " + var10001 + " Pacientes, en el minuto " + minutActual);
            }

            if (minutActual % 15 == 0) {
                Paciente p = hospital.SiguentePaciente();
                hospital.AtenderSiguente(minutActual);
                System.out.println("Paciente atendido en el minuto " + minutActual);
                System.out.println(p);
                ++cantat;
            }

            if (hospital.CantPacientes() >= 3) {
                for(int i = 0; i < hospital.CantPacientes(); ++i) {
                }

                hospital.AtenderSiguente(minutActual);
                System.out.println("Paciente atendido en el minuto " + minutActual + " por mas de 3 pacientes");
                ++cantat;
            }

            if (minutActual > 10 && minutActual % bus == 0) {
                System.out.println("Busqueda de paciente? (si/no/fn(finalizar simulacin))");
                String res = sc.nextLine();
                if (res.toLowerCase().equals("si")) {
                    System.out.println("Go to PacienteDetils");
                    PacienteDetails.PacienteDetails(hospital);
                }

                if (res.toLowerCase().equals("fn")) {
                    bus = 1441;
                }
            }
        }

        System.out.println("se registraron: " + cantp + " pacientes\n");
        System.out.println("se atendieron: " + cantat + " pacientes\n");

        for(int cat = 1; cat < 6; ++cat) {
            System.out.println("Pacientes por categoria C" + cat);
            System.out.println(hospital.obtenerPacientesPorCategoria(cat).toString());
            System.out.println("\n");
        }

        System.out.println("Busqueda de paciente? (si/no)");
        String res = sc.nextLine();
        if (res.toLowerCase().equals("si")) {
            System.out.println("Go to PacienteDetils");
            PacienteDetails.PacienteDetails(hospital);
        }

        List<Paciente> pc = hospital.PacientesAtendidos;
        GuardarDatos.SaveData(pc, "Pacientes-Atendidos");

        for(int cat = 1; cat < 6; ++cat) {
            List<Paciente> pcToCat = hospital.obtenerPacientesPorCategoria(cat);
            GuardarDatos.SaveData(pcToCat, "Pacientes-Categoria-" + cat);
        }

    }

    static {
        sc = new Scanner(System.in);
    }
}
