/**
 * FASE 1: OOP Básico
 * Restaurante - Gestiona el menú de platos y las comandas
 */
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nombre;
    private String propietario;
    private ArrayList<Plato> menu;
    private ArrayList<Pedido> pedidos;
    private int contadorPedidos;

    // Constructor
    public Restaurante(String nombre, String propietario) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.contadorPedidos = 0;
    }

    /**
     * Inicializa el menú con platos de ejemplo
     */
    public void inicializarMenu() {
        // ENTRANTES
        menu.add(new Plato(1, "Tabla de Quesos", Constants.CAT_ENTRANTES, 10.00));
        menu.add(new Plato(2, "Ensalada Fresca", Constants.CAT_ENTRANTES, 8.50));
        menu.add(new Plato(3, "Camarones al Ajillo", Constants.CAT_ENTRANTES, 12.00));

        // PLATOS PRINCIPALES
        menu.add(new Plato(4, "Carne a la Parrilla", Constants.CAT_PRINCIPALES, 22.00));
        menu.add(new Plato(5, "Salmón a la Mantequilla", Constants.CAT_PRINCIPALES, 20.00));
        menu.add(new Plato(6, "Pasta Fresca", Constants.CAT_PRINCIPALES, 15.00));

        // BEBIDAS
        menu.add(new Plato(7, "Agua", Constants.CAT_BEBIDAS, 1.50));
        menu.add(new Plato(8, "Refresco", Constants.CAT_BEBIDAS, 2.50));
        menu.add(new Plato(9, "Vino Blanco", Constants.CAT_BEBIDAS, 5.00));

        // POSTRES
        menu.add(new Plato(10, "Tiramisú", Constants.CAT_POSTRES, 8.00));
        menu.add(new Plato(11, "Flan Casero", Constants.CAT_POSTRES, 6.00));
        menu.add(new Plato(12, "Helado", Constants.CAT_POSTRES, 7.00));
    }

    /**
     * Muestra el menú por categorías
     */
    public void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║       📋 MENÚ DEL RESTAURANTE  ║");
        System.out.println("╚════════════════════════════════╝");

        String[] categorias = {Constants.CAT_ENTRANTES, Constants.CAT_PRINCIPALES,
                               Constants.CAT_BEBIDAS, Constants.CAT_POSTRES};
        for (String cat : categorias) {
            System.out.println("\n" + cat.toUpperCase());
            System.out.println("─".repeat(35));
            for (Plato p : menu) {
                if (p.getCategoria().equals(cat)) {
                    System.out.println(p.getInfo());
                }
            }
        }
        System.out.println();
    }

    /**
     * Busca un plato por ID
     */
    public Plato buscarPlato(int id) {
        for (Plato p : menu) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Crea una nueva comanda
     */
    public Pedido crearComanda() {
        contadorPedidos++;
        Pedido comanda = new Pedido(contadorPedidos);
        return comanda;
    }

    /**
     * Registra un pedido
     */
    public void registrarPedido(Pedido p) {
        if (p != null) {
            pedidos.add(p);
        }
    }

    /**
     * Obtiene una comanda por ID
     */
    public Pedido obtenerComanda(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtiene comandas por estado
     */
    public List<Pedido> obtenerPorEstado(String estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado().equals(estado.toUpperCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Muestra todas las comandas
     */
    public void mostrarComandas() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay comandas registradas");
            return;
        }
        System.out.println("\n=== TODAS LAS COMANDAS ===");
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    /**
     * Muestra comandas pendientes
     */
    public void verPedidosActivos() {
        List<Pedido> pendientes = obtenerPorEstado("PENDIENTE");
        if (pendientes.isEmpty()) {
            System.out.println("No hay pedidos activos");
            return;
        }
        System.out.println("\n=== PEDIDOS ACTIVOS ===");
        for (Pedido p : pendientes) {
            System.out.println(p);
        }
    }

    /**
     * Cambia el estado de un pedido
     */
    public void cambiarEstadoPedido(int id, String nuevoEstado) {
        Pedido p = obtenerComanda(id);
        if (p != null) {
            if (p.cambiarEstado(nuevoEstado)) {
                System.out.println("✅ Estado actualizado a: " + nuevoEstado);
            } else {
                System.out.println("❌ Estado inválido");
            }
        } else {
            System.out.println("❌ Comanda no encontrada");
        }
    }

    /**
     * Calcula la recaudación (solo comandas PAGADO)
     */
    public double calcularRecaudacion() {
        double total = 0;
        List<Pedido> pagados = obtenerPorEstado("PAGADO");
        for (Pedido p : pagados) {
            total += p.calcularSubtotal();
        }
        return total;
    }

    /**
     * Obtiene todas las comandas
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPropietario() {
        return propietario;
    }
}
