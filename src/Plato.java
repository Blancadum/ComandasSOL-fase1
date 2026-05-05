/**
 * FASE 1: OOP Básico
 * Plato - Representa un item del menú del restaurante
 */
public class Plato {
    private int id;
    private String nombre;
    private String categoria;
    private double precio;

    // Constructor
    public Plato(int id, String nombre, String categoria, double precio) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del plato no puede estar vacío");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    // Método de utilidad para mostrar información
    public String getInfo() {
        return String.format("[%d] %s (%.2f€)", id, nombre, precio);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
