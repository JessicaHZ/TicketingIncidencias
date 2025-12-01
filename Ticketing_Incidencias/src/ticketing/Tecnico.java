package ticketing;

// Clase Técnico, que hereda de Usuario y añade una especialidad
public class Tecnico extends Usuario {
    private String especialidad; // Tipo de soporte que maneja (hardware/software)

    public Tecnico(int id, String nombre, String especialidad) {
        super(id, nombre);  // Llamada al constructor de Usuario
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    // Un técnico cambia el estado de una incidencia a EN_PROCESO
    public void resolverIncidencia(Incidencia i) {
        if (i != null)
            i.cambiarEstado(EstadoIncidencia.EN_PROCESO, this);
    }

    @Override
    public String toString() {
        return super.toString() + " - Especialidad: " + especialidad;
    }
}
