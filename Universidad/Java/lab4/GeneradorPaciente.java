//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;

public class GeneradorPaciente {
    private static final Random rd = new Random();

    public static String GenerarNombre() {
        String[] Nombres = new String[]{"Ana", "Juan", "Pedro", "Lucía", "Carlos", "Marta", "Diego", "Valentina", "Sofia", "Max", "Jose", "Franz"};
        String nombre = Nombres[rd.nextInt(Nombres.length)];
        return nombre;
    }

    public static String GenerarApellido() {
        String[] Apll = new String[]{"Pérez", "González", "López", "Sánchez", "Ramírez", "Flores", "Antonelli", "Verstappen", "Hermann", "Miranda", "Russel"};
        String apellido = Apll[rd.nextInt(Apll.length)];
        return apellido;
    }

    public static String GenerarArea() {
        String[] Ars = new String[]{"SAPU", "U-AD", "U-INF"};
        String area = Ars[rd.nextInt(Ars.length)];
        return area;
    }

    private static int generarRun() {
        Random rd = new Random();
        int dig = rd.nextBoolean() ? 7 : 8;
        int min = (int)Math.pow((double)10.0F, (double)(dig - 1));
        int max = (int)Math.pow((double)10.0F, (double)dig) - 1;
        return rd.nextInt(max - min + 1) + min;
    }

    public static char DigitoVerificador(int Run) {
        int suma = 0;
        int factor = 2;

        for(int tempRut = Run; tempRut > 0; tempRut /= 10) {
            int digit = tempRut % 10;
            suma += digit * factor;
            factor = factor == 7 ? 2 : factor + 1;
        }

        int dv = 11 - suma % 11;
        if (dv == 11) {
            return '0';
        } else if (dv == 10) {
            return 'K';
        } else {
            return (char)(dv + 48);
        }
    }

    public static String generarRunComp() {
        int nr = generarRun();
        char v = DigitoVerificador(nr);
        return String.format("%d-%c", nr, v);
    }

    public static int GenerarCategoria() {
        int probabilidad = rd.nextInt(100) + 1;
        if (probabilidad <= 10) {
            return 1;
        } else if (probabilidad <= 25) {
            return 2;
        } else if (probabilidad <= 43) {
            return 3;
        } else {
            return probabilidad <= 70 ? 4 : 5;
        }
    }

    public static Paciente GenerarPaciente(long timeLLegada, long timeActual) {
        String name = GenerarNombre();
        String apellido = GenerarApellido();
        String area = GenerarArea();
        String run = generarRunComp();
        int cat = GenerarCategoria();
        long timepoLlegada = (timeLLegada + timeActual) / 60000L;
        Paciente p = new Paciente(name, apellido, run, cat, timepoLlegada, "Espera", area);
        return p;
    }
}
