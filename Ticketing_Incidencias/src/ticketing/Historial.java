package ticketing;

import java.time.LocalDate;

// Registra un cambio de estado en la incidencia
public class Historial {
    private LocalDate fecha;            // Fecha del cambio
    private EstadoIncidencia estado;    // Nuevo estado asignado
    private Tecnico tecnico;            // Técnico responsable del cambio
    private String nota;                // Comentario opcional

    public Historial(LocalDate fecha, EstadoIncidencia estado, Tecnico tecnico, String nota) {
        this.fecha = fecha;
        this.estado = estado;
        this.tecnico = tecnico;
        this.nota = nota;
    }

    @Override
    public String toString() {
        String t = (tecnico == null) ? "Sin técnico" : tecnico.getNombre();
        return fecha + " - " + estado + " - " + t +
               (nota != null && !nota.isEmpty() ? " - " + nota : "");
    }
}
