/**
 * FASE 1: OOP Básico
 * Pedido - Representa una comanda (conjunto de platos pedidos)
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<DetalleItem> items;
    private String estado;
    private LocalDateTime fecha;
    private String numeroComanda;

    // Constructor
    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.fecha = LocalDateTime.now();
        this.numeroComanda = String.format("COMANDA-%03d", id);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNumeroComanda() {
        return numeroComanda;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<DetalleItem> getItems() {
        return items;
    }

    /**
     * Agrega un plato a la comanda
     */
    public boolean agregarItem(Plato plato, int cantidad) {
        if (plato == null || cantidad <= 0) {
            return false;
        }

        // Verificar si el plato ya existe
        for (DetalleItem item : items) {
            if (item.getPlato().getId() == plato.getId()) {
                // Aumentar cantidad si ya existe
                item.setCantidad(item.getCantidad() + cantidad);
                return true;
            }
        }

        // Si no existe, agregar nuevo
        items.add(new DetalleItem(plato, cantidad));
        return true;
    }

    /**
     * Calcula el subtotal de la comanda
     */
    public double calcularSubtotal() {
        double total = 0;
        for (DetalleItem item : items) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /**
     * Cambia el estado de la comanda
     */
    public boolean cambiarEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isEmpty()) {
            return false;
        }

        String upper = nuevoEstado.toUpperCase();

        if (upper.equals("PENDIENTE") || upper.equals("EN_PREPARACION") ||
            upper.equals("LISTO") || upper.equals("PAGADO")) {
            this.estado = upper;
            return true;
        }

        return false;
    }

    /**
     * Obtiene los detalles formateados de la comanda
     */
    public String getDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("╔════════════════════════════╗\n"));
        sb.append(String.format("║  %s - COMANDA %d  ║\n", numeroComanda, id));
        sb.append(String.format("╠════════════════════════════╣\n"));

        for (DetalleItem item : items) {
            sb.append(String.format("║ %dx %-18s %6.2f€║\n",
                item.getCantidad(), item.getPlato().getNombre(), item.calcularSubtotal()));
        }

        sb.append(String.format("╠════════════════════════════╣\n"));
        sb.append(String.format("║ SUBTOTAL: %18.2f€║\n", calcularSubtotal()));
        sb.append(String.format("║ Estado: %-19s║\n", estado));
        sb.append(String.format("╚════════════════════════════╝"));

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("COMANDA %d - %s - Total: %.2f€", id, estado, calcularSubtotal());
    }
}
