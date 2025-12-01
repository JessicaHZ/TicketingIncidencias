package ticketing;

import java.util.List;
import java.util.Scanner;

// Clase con el menú principal del sistema
public class SistemaTicketing {

    private static GestorIncidencias gestor = new GestorIncidencias();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        gestor.agregarTecnico(new Tecnico(1, "Luis Pérez", "Hardware"));
        gestor.agregarTecnico(new Tecnico(2, "María López", "Software"));

        boolean salir = false;

        while (!salir) {
            mostrarMenu();

            int opcion = leerInt("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarIncidencia();
                case 2 -> listarIncidencias();
                case 3 -> asignarTecnico();
                case 4 -> cambiarEstado();
                case 5 -> generarReporte();
                case 0 -> salir = true;
                default -> System.out.println("Opción inválida");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Ticketing ---");
        System.out.println("1. Registrar incidencia");
        System.out.println("2. Listar incidencias");
        System.out.println("3. Asignar técnico");
        System.out.println("4. Cambiar estado");
        System.out.println("5. Generar reporte");
        System.out.println("0. Salir");
    }

    private static int leerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Entrada inválida. " + msg);
        }
        return sc.nextInt();
    }

    private static String leerTexto(String msg) {
        System.out.print(msg);
        sc.nextLine();
        return sc.nextLine();
    }

    private static void registrarIncidencia() {
        System.out.println("--- Registrar incidencia ---");

        System.out.println("1-HARDWARE  2-SOFTWARE  3-RED");
        int tipoOp = leerInt("Tipo: ");

        TipoIncidencia tipo = switch (tipoOp) {
            case 1 -> TipoIncidencia.HARDWARE;
            case 2 -> TipoIncidencia.SOFTWARE;
            default -> TipoIncidencia.RED;
        };

        System.out.println("1-ALTA  2-MEDIA  3-BAJA");
        int prioOp = leerInt("Prioridad: ");

        Prioridad prioridad = switch (prioOp) {
            case 1 -> Prioridad.ALTA;
            case 2 -> Prioridad.MEDIA;
            default -> Prioridad.BAJA;
        };

        String desc = leerTexto("Descripción: ");
        String idEq = leerTexto("ID de equipo: ");
        String ubic = leerTexto("Ubicación: ");

        Equipo e = new Equipo(idEq, ubic);

        Incidencia i = gestor.registrarIncidencia(tipo, prioridad, desc, e);

        System.out.println("Incidencia creada con ID: " + i.getId());
    }

    private static void listarIncidencias() {
        System.out.println("--- Lista de incidencias ---");

        List<Incidencia> lista = gestor.getIncidencias();

        if (lista.isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }

        for (Incidencia i : lista) {
            System.out.println(i);
        }
    }

    private static void asignarTecnico() {
        System.out.println("--- Asignar técnico ---");

        int id = leerInt("ID de incidencia: ");

        System.out.println("Técnicos disponibles:");
        for (Tecnico t : gestor.getTecnicos()) {
            System.out.println(t.getId() + " - " + t.getNombre());
        }

        int idt = leerInt("ID de técnico: ");

        Tecnico elegido = gestor.getTecnicos().stream()
                .filter(t -> t.getId() == idt)
                .findFirst()
                .orElse(null);

        if (elegido == null) {
            System.out.println("Técnico no encontrado.");
            return;
        }

        boolean ok = gestor.asignarTecnico(id, elegido);
        System.out.println(ok ? "Técnico asignado." : "Incidencia no encontrada.");
    }

    private static void cambiarEstado() {
        System.out.println("--- Cambiar estado ---");

        int id = leerInt("ID incidencia: ");

        System.out.println("1-PENDIENTE 2-EN_PROCESO 3-RESUELTA 4-CERRADA");
        int op = leerInt("Estado: ");

        EstadoIncidencia e = switch (op) {
            case 1 -> EstadoIncidencia.PENDIENTE;
            case 2 -> EstadoIncidencia.EN_PROCESO;
            case 3 -> EstadoIncidencia.RESUELTA;
            default -> EstadoIncidencia.CERRADA;
        };

        boolean ok = gestor.cambiarEstado(id, e, null);
        System.out.println(ok ? "Estado actualizado." : "Incidencia no encontrada.");
    }

    private static void generarReporte() {
        System.out.println("--- Reporte del sistema ---");
        System.out.println(gestor.generarReporte());
    }
}
