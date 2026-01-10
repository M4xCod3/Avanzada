//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AreaAtencion {
    private String name;
    private static PriorityQueue<Paciente> pacientesHeap;
    public List<Paciente> pacientesArea;
    private static int CapacidadMax;

    public AreaAtencion(String name, int CapacidadMax) {
        this.name = name;
        AreaAtencion.CapacidadMax = CapacidadMax;
        pacientesHeap = new PriorityQueue();
        this.pacientesArea = new ArrayList();
    }

    public void IngresarPaciente(Paciente p) {
        this.pacientesArea.add(p);
        pacientesHeap.add(p);
    }

    public Paciente AtenderPaciente() {
        return (Paciente)pacientesHeap.poll();
    }

    public static boolean EstaSaturado() {
        return pacientesHeap.size() >= CapacidadMax;
    }

    public List<Paciente> ObtenerPacientesHeapSort() {
        List<Paciente> pacientes = new ArrayList(pacientesHeap);
        pacientes.sort((Comparator)null);
        return pacientes;
    }

    public List<Paciente> getpacientesArea() {
        return this.pacientesArea;
    }
}
