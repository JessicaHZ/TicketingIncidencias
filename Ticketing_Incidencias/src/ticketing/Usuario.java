package ticketing;

// Clase base que representa a un usuario general del sistema
public class Usuario {
    protected int id;           // Identificador único del usuario
    protected String nombre;    // Nombre del usuario

    // Constructor que inicializa un usuario con id y nombre
    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre + " (ID:" + id + ")";
    }
}
