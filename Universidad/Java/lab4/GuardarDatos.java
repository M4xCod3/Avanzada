//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GuardarDatos {
    public static void SaveData(List<Paciente> Pacientes, String name) {
        int i = 10;
        List<String> exclusivo = List.of("Pacientes-Categoria-1", "Pacientes-Categoria-2", "Pacientes-Categoria-3", "Pacientes-Categoria-4", "Pacientes-Categoria-5");

        try (FileWriter fw = new FileWriter(name + ".txt")) {
            for(Paciente pc : Pacientes) {
                fw.write(pc.toString());
                i += 10;
                if (i % 60 == 0 && !exclusivo.contains(name)) {
                    fw.write("hora= " + i / 60 + "\n");
                }
            }

            fw.write("Pacientes tot: " + Pacientes.size() + "\n");
            System.out.println("datos guardados correctamente en: " + name + ".txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo" + e.getMessage());
            e.printStackTrace();
        }

    }
}
