package ticketing;

import java.util.ArrayList;
import java.util.List;

// Gestiona todas las incidencias del sistema
public class GestorIncidencias {

    private List<Incidencia> incidencias = new ArrayList<>();
    private List<Tecnico> tecnicos = new ArrayList<>();
    private int siguienteId = 1; // Generador automático de IDs

    public List<Incidencia> getIncidencias() { return incidencias; }
    public List<Tecnico> getTecnicos() { return tecnicos; }

    // Crea una incidencia nueva
    public Incidencia registrarIncidencia(TipoIncidencia tipo, Prioridad prioridad,
                                         String descripcion, Equipo equipo) {

        Incidencia i = new Incidencia(siguienteId++, tipo, prioridad, descripcion, equipo);
        incidencias.add(i);
        return i;
    }

    // Asignar técnico
    public boolean asignarTecnico(int idInc, Tecnico t) {
        Incidencia i = buscarPorId(idInc);
        if (i == null) return false;

        i.asignarTecnico(t);
        return true;
    }

    // Cambiar estado
    public boolean cambiarEstado(int idInc, EstadoIncidencia e, Tecnico resp) {
        Incidencia i = buscarPorId(idInc);
        if (i == null) return false;

        i.cambiarEstado(e, resp);
        return true;
    }

    // Buscar incidencia por ID
    public Incidencia buscarPorId(int id) {
        return incidencias.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Registrar técnico
    public void agregarTecnico(Tecnico t) {
        tecnicos.add(t);
    }

    // Reporte general
    public String generarReporte() {
        return "Total incidencias: " + incidencias.size();
    }
}
