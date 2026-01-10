//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Stack;

public class Paciente implements Comparable<Paciente> {
    private String name;
    private String Apellido;
    private String run;
    private int category;
    private long TiempoLlegada;
    private String estado;
    private String area;
    private Stack<String> HistorialCambios = new Stack();

    public Paciente(String name, String apellido, String run, int category, long tiempoLlegada, String estado, String area) {
        this.name = name;
        this.Apellido = apellido;
        this.run = run;
        this.category = category;
        this.TiempoLlegada = tiempoLlegada / 60000L;
        this.estado = estado;
        this.area = area;
    }

    public int compareTo(Paciente p) {
        return 0;
    }

    public void RegistrarCambio(String desc) {
        this.HistorialCambios.add(desc);
    }

    public String ObtenerUltimoCambio() {
        return this.HistorialCambios.isEmpty() ? "no hay cambios" : (String)this.HistorialCambios.pop();
    }

    public Stack<String> getHistorialCambios() {
        return this.HistorialCambios;
    }

    public int getCantHis() {
        return this.HistorialCambios.size();
    }

    public String toString() {
        return "Paciente{nombre='" + this.name + " " + this.Apellido + "', categoria=" + this.category + ", Run= " + this.run + ", TiempoLlegada=" + this.TiempoLlegada + ", Area= " + this.area + ", Estado=" + this.estado + "}\n";
    }

    public String getName() {
        return this.name;
    }

    public String getApellido() {
        return this.Apellido;
    }

    public String getRun() {
        return this.run;
    }

    public int getCategory() {
        return this.category;
    }

    public long getTiempoLlegada() {
        return this.TiempoLlegada;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getArea() {
        return this.area;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
