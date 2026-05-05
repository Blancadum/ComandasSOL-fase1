/**
 * FASE 1: OOP Básico
 * Main - Interfaz interactiva del sistema de comandas
 */
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Restaurante miRestaurante = new Restaurante("La Bella Italia", "Giuseppe");
        miRestaurante.inicializarMenu();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println(Constants.APP_TITULO);
            System.out.println(Constants.MENU_OPCION_1);
            System.out.println(Constants.MENU_OPCION_2);
            System.out.println(Constants.MENU_OPCION_3);
            System.out.println(Constants.MENU_OPCION_4);
            System.out.println(Constants.MENU_OPCION_5);
            System.out.println(Constants.MENU_OPCION_6);
            System.out.println(Constants.MENU_OPCION_7);
            System.out.print(Constants.PROMPT_SELECCION);

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        miRestaurante.mostrarMenu();
                        break;
                    case 2:
                        gestionarNuevaComanda(miRestaurante, scanner);
                        break;
                    case 3:
                        miRestaurante.verPedidosActivos();
                        break;
                    case 4:
                        listarTodosPedidosRealizados(miRestaurante);
                        break;
                    case 5:
                        verEstadisticasRestaurante(miRestaurante);
                        break;
                    case 6:
                        System.out.println("Examen no disponible en Fase 1");
                        break;
                    case 7:
                        salir = true;
                        System.out.println("👋 ¡Hasta luego!");
                        break;
                    default:
                        System.out.println(Constants.ERROR_OPCION_INVALIDA);
                }
            } catch (NumberFormatException e) {
                System.out.println(Constants.ERROR_INPUT_NUMERICO);
            }
        }
        scanner.close();
    }

    private static void gestionarNuevaComanda(Restaurante restaurante, Scanner scanner) {
        System.out.print(Constants.PROMPT_MESA);
        try {
            int mesa = Integer.parseInt(scanner.nextLine());
            Pedido nuevoPedido = new Pedido(mesa);

            restaurante.mostrarMenu();

            boolean agregarMas = true;
            while (agregarMas) {
                System.out.print(Constants.PROMPT_ID_PLATO);
                int idPlato = Integer.parseInt(scanner.nextLine());

                if (idPlato == 0) {
                    agregarMas = false;
                    break;
                }

                System.out.print(Constants.PROMPT_CANTIDAD);
                int cantidad = Integer.parseInt(scanner.nextLine());

                Plato p = restaurante.buscarPlato(idPlato);
                if (p != null) {
                    if (nuevoPedido.agregarItem(p, cantidad)) {
                        System.out.println("✅ Agregado: " + cantidad + "x " + p.getNombre());
                    } else {
                        System.out.println("❌ No se pudo agregar el item");
                    }
                } else {
                    System.out.println(Constants.ERROR_PLATO_NO_ENCONTRADO);
                }
            }

            if (!nuevoPedido.getItems().isEmpty()) {
                restaurante.registrarPedido(nuevoPedido);
                System.out.println(Constants.MSG_EXITO_REGISTRO);
                System.out.println(nuevoPedido.getDetalles());
            }
        } catch (NumberFormatException e) {
            System.out.println(Constants.ERROR_INPUT_NUMERICO);
        }
    }

    private static void verEstadisticasRestaurante(Restaurante restaurante) {
        System.out.println(Constants.CABECERA_CAJA);
        System.out.println(Constants.TICKET_TOTAL + restaurante.calcularRecaudacion() + "€");
    }

    private static void listarTodosPedidosRealizados(Restaurante restaurante) {
        List<Pedido> todosPedidos = restaurante.getPedidos();

        if (todosPedidos == null || todosPedidos.isEmpty()) {
            System.out.println("\n❌ No hay pedidos registrados");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║      📊 TODOS LOS PEDIDOS REALIZADOS       ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Total de pedidos: " + todosPedidos.size());
        System.out.println("╠════════════════════════════════════════════╣");

        int contador = 1;
        for (Pedido p : todosPedidos) {
            System.out.println("\n[" + contador + "] " + p.getNumeroComanda() + " - " + p.getEstado());
            System.out.println("    Items: " + p.getItems().size());
            System.out.println("    Total: " + String.format("%.2f€", p.calcularSubtotal()));
            contador++;
        }

        System.out.println("\n╚════════════════════════════════════════════╝");
    }
}
