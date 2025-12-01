package ticketing;

// Representa un equipo físico del laboratorio
public class Equipo {
    private String idEquipo;   // Identificador del equipo (Ej: PC-12)
    private String ubicacion;  // Ubicación física del equipo

    public Equipo(String idEquipo, String ubicacion) {
        this.idEquipo = idEquipo;
        this.ubicacion = ubicacion;
    }

    public String getIdEquipo() { return idEquipo; }
    public String getUbicacion() { return ubicacion; }

    @Override
    public String toString() {
        return idEquipo + " (" + ubicacion + ")";
    }
}
