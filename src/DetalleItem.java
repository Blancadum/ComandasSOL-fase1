/**
 * FASE 1: OOP Básico
 * DetalleItem - Representa un item en un pedido
 * Relaciona: 1 Plato + cantidad en un Pedido
 */
public class DetalleItem {
    private Plato plato;
    private int cantidad;

    // Constructor
    public DetalleItem(Plato plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }

    // Getters
    public Plato getPlato() {
        return plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters
    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Calcula el subtotal de este item (precio * cantidad)
     */
    public double calcularSubtotal() {
        return plato.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return cantidad + "x " + plato.getNombre() + " = " + calcularSubtotal() + "€";
    }
}
