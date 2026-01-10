//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PacienteDetails {
    private static final Scanner sc;

    public static void PacienteDetails(Hospital hospital) {
        System.out.println("-----Bienvenido a la busqueda de detalles de pacientes--------");

        String res;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("Como desea Buscar al paciente atendidos?");
            System.out.println("1. Por Nombre");
            System.out.println("2. Por Run");
            System.out.println("3. Por Categoria");
            System.out.println("4. Por Area");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nombre del paciente");
                    sc.nextLine();
                    res = sc.nextLine();
                    List<Paciente> PacientesName = new ArrayList();

                    for(Paciente paciente : hospital.getPacientesAtencion()) {
                        if (paciente.getName().equals(res)) {
                            PacientesName.add(paciente);
                        }
                    }

                    for(Paciente paciente : PacientesName) {
                        System.out.println(paciente.toString());
                    }

                    System.out.println("Ingrese el Run del paciente que desea Rastrear: ");
                    String rn = sc.next();
                    Paciente paciente2 = (Paciente)hospital.getPacientesTot().get(rn);
                    Stack<String> histl = paciente2.getHistorialCambios();
                    System.out.println("nc: " + paciente2.getCantHis());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Su historial: ");
                    System.out.println(String.valueOf(histl) + "\n");
                    break;
                case 2:
                    System.out.println("Ingrese el Run del paciente que desea Rastrear: ");
                    String run = sc.next();
                    Paciente paciente = (Paciente)hospital.getPacientesTot().get(run);
                    Stack<String> historial = paciente.getHistorialCambios();
                    System.out.println("nc: " + paciente.getCantHis());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Su historial: ");
                    System.out.println(String.valueOf(historial) + "\n");
                    break;
                case 3:
                    System.out.println("Ingrese que categoria desea revisar paciente: ");
                    int cat = sc.nextInt();
                    if (cat <= 5 && cat > 0) {
                        System.out.println("Pacientes por categoria C " + cat);
                        System.out.println(hospital.obtenerPacientesPorCategoria(cat).toString());
                        System.out.println("\n");
                        System.out.println("Ingrese el Run del paciente que desea revisar: ");
                        String catRun = sc.next();
                        Paciente paciente3 = (Paciente)hospital.getPacientesTot().get(catRun);
                        Stack<String> hist = paciente3.getHistorialCambios();
                        System.out.println("nc: " + paciente3.getCantHis());
                        System.out.println("--------------------------------------------------------");
                        System.out.println("Su historial: ");
                        System.out.println(String.valueOf(hist) + "\n");
                        break;
                    }

                    System.out.println("Categoria no valido");
                    break;
                case 4:
                    System.out.println("Ingrese en que Area Desea Buscar");
                    System.out.println("SAPU/U-AD/U-INF");
                    String area = sc.next();
                    Map<String, AreaAtencion> areas = hospital.getAreasAtencion();
                    AreaAtencion areaAtencion = (AreaAtencion)areas.get(area);
                    if (areaAtencion != null) {
                        for(Paciente pacciente : areaAtencion.getpacientesArea()) {
                            System.out.println(pacciente.toString());
                        }
                    } else {
                        System.out.println("No existe el Area");
                    }

                    System.out.println("Ingrese el Run del paciente que desea revisar: ");
                    String AreaRun = sc.next();
                    Paciente paciente4 = (Paciente)hospital.getPacientesTot().get(AreaRun);
                    Stack<String> hst = paciente4.getHistorialCambios();
                    System.out.println("nc: " + paciente4.getCantHis());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Su historial: ");
                    System.out.println(String.valueOf(hst) + "\n");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida ");
            }

            System.out.println("Desa hacer otra busqueda? s/n");
            res = sc.next();
        } while(!res.toLowerCase().equals("n"));

    }

    static {
        sc = new Scanner(System.in);
    }
}
