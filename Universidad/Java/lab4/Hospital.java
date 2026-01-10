//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Hospital {
    private Map<String, Paciente> PacientesTot = new HashMap();
    private PriorityQueue<Paciente> ColaAtencion = new PriorityQueue();
    private Map<String, AreaAtencion> areasAtencion = new HashMap();
    public List<Paciente> PacientesAtendidos = new ArrayList();

    public Hospital() {
        this.areasAtencion.put("SAPU", new AreaAtencion("SAPU", 10000));
        this.areasAtencion.put("U-AD", new AreaAtencion("U-AD", 100000));
        this.areasAtencion.put("U-INF", new AreaAtencion("U-INF", 10000));
    }

    public void RegistrarPaciente(Paciente p) {
        this.ColaAtencion.add(p);
        this.PacientesTot.put(p.getRun(), p);
    }

    public void ReasignarCategoria(String Run, int Categoria) {
        Paciente Pac = (Paciente)this.PacientesTot.get(Run);
        Pac.setCategory(Categoria);
        String cambio = "New categoria: " + Pac.getCategory();
        Pac.RegistrarCambio(cambio);
    }

    public Paciente SiguentePaciente() {
        Paciente p = (Paciente)this.ColaAtencion.peek();
        return p;
    }

    public Paciente AtenderSiguente(int min) {
        Paciente p = (Paciente)this.ColaAtencion.poll();
        if (p != null) {
            p.RegistrarCambio("Fue atendido");
            p.RegistrarCambio("atendido en minuto: " + min);
            p.setEstado("atendido");
            this.PacientesAtendidos.add(p);
            AreaAtencion area = (AreaAtencion)this.areasAtencion.get(p.getArea());
            if (area != null) {
                area.IngresarPaciente(p);
            }
        }

        return p;
    }

    public List<Paciente> obtenerPacientesPorCategoria(int categoria) {
        List<Paciente> PacientesPorCat = new ArrayList();

        for(Paciente p : this.PacientesAtendidos) {
            if (p.getCategory() == categoria) {
                PacientesPorCat.add(p);
            }
        }

        return PacientesPorCat;
    }

    public AreaAtencion obtenerArea(String nombre) {
        return (AreaAtencion)this.areasAtencion.get(nombre);
    }

    public boolean HayPacientes() {
        return !this.ColaAtencion.isEmpty();
    }

    public int CantPacientes() {
        return this.ColaAtencion.size();
    }

    public Map<String, Paciente> getPacientesTot() {
        return this.PacientesTot;
    }

    public List<Paciente> getPacientesAtencion() {
        return this.PacientesAtendidos;
    }

    public Map<String, AreaAtencion> getAreasAtencion() {
        return this.areasAtencion;
    }
}
