/**
 * FASE 1: OOP Básico
 * Clase que centraliza constantes y mensajes del sistema
 */
public class Constants {
    // Títulos
    public static final String APP_TITULO = "\n🍽️  === SISTEMA DE COMANDAS - GESTIÓN DE RESTAURANTE ===";

    // Menú principal
    public static final String MENU_OPCION_1 = "1. 📖 Ver menú";
    public static final String MENU_OPCION_2 = "2. 🆕 Crear nueva comanda";
    public static final String MENU_OPCION_3 = "3. 📝 Ver pedidos activos";
    public static final String MENU_OPCION_4 = "4. 📋 Ver todas las comandas";
    public static final String MENU_OPCION_5 = "5. 💰 Ver recaudación";
    public static final String MENU_OPCION_6 = "6. 🎓 Realizar examen";
    public static final String MENU_OPCION_7 = "7. 🚪 Salir";

    // Prompts
    public static final String PROMPT_SELECCION = "👉 Elige opción: ";
    public static final String PROMPT_MESA = "📍 Número de mesa: ";
    public static final String PROMPT_ID_PLATO = "🆔 ID del plato (0 para terminar): ";
    public static final String PROMPT_CANTIDAD = "🔢 Cantidad: ";

    // Categorías
    public static final String CAT_ENTRANTES = "Entrantes";
    public static final String CAT_PRINCIPALES = "Platos Principales";
    public static final String CAT_BEBIDAS = "Bebidas";
    public static final String CAT_POSTRES = "Postres";

    // Estados
    public static final String ESTADO_PENDIENTE = "PENDIENTE";
    public static final String ESTADO_PREPARANDO = "EN_PREPARACION";
    public static final String ESTADO_LISTO = "LISTO";

    // Cabeceras
    public static final String CABECERA_COMANDAS = "\n📋 --- COMANDAS EN CURSO ---";
    public static final String CABECERA_CAJA = "\n💵 --- RESUMEN DE CAJA ---";
    public static final String TICKET_TOTAL = "TOTAL RECAUDADO: ";

    // Mensajes
    public static final String MSG_EXITO_REGISTRO = "✅ Comanda registrada.";
    public static final String ERROR_OPCION_INVALIDA = "❌ Opción no válida.";
    public static final String ERROR_INPUT_NUMERICO = "❌ Debes ingresar un número.";
    public static final String ERROR_PLATO_NO_ENCONTRADO = "❌ Plato no encontrado.";
}
