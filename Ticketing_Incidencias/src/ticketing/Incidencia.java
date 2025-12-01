package ticketing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Representa una incidencia registrada en el sistema
public class Incidencia {

    private int id;
    private TipoIncidencia tipo;
    private Prioridad prioridad;
    private EstadoIncidencia estado;
    private String descripcion;
    private Equipo equipo;
    private Tecnico tecnicoAsignado;
    private LocalDate fechaCreacion;
    private LocalDate fechaResolucion;

    private List<Historial> historial;   // Lista de eventos de historial

    public Incidencia(int id, TipoIncidencia tipo, Prioridad prioridad,
                      String descripcion, Equipo equipo) {

        this.id = id;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.equipo = equipo;
        this.estado = EstadoIncidencia.PENDIENTE; // Estado inicial
        this.fechaCreacion = LocalDate.now();
        this.historial = new ArrayList<>();

        agregarHistorial(new Historial(fechaCreacion, estado, null, "Incidencia creada"));
    }

    public int getId() { return id; }
    public EstadoIncidencia getEstado() { return estado; }
    public TipoIncidencia getTipo() { return tipo; }
    public Prioridad getPrioridad() { return prioridad; }
    public String getDescripcion() { return descripcion; }
    public Equipo getEquipo() { return equipo; }
    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    public List<Historial> getHistorial() { return historial; }

    // Método para asignar un técnico
    public void asignarTecnico(Tecnico t) {
        this.tecnicoAsignado = t;
        agregarHistorial(new Historial(LocalDate.now(), estado, t, "Técnico asignado"));
    }

    // Método para cambiar el estado
    public void cambiarEstado(EstadoIncidencia nuevoEstado, Tecnico responsable) {
        this.estado = nuevoEstado;

        if (nuevoEstado == EstadoIncidencia.RESUELTA ||
            nuevoEstado == EstadoIncidencia.CERRADA) {
            this.fechaResolucion = LocalDate.now();
        }

        agregarHistorial(new Historial(LocalDate.now(), nuevoEstado, responsable, ""));
    }

    public void agregarHistorial(Historial h) {
        historial.add(h);
    }

    @Override
    public String toString() {
        String t = (tecnicoAsignado == null) ? "Sin asignar" : tecnicoAsignado.getNombre();
        return "ID:" + id + " | Tipo:" + tipo + " | Prioridad:" + prioridad +
               " | Estado:" + estado + " | Equipo:" + equipo + " | Técnico:" + t +
               "Desc: " + descripcion;
    }
}
