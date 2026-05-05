# 🔵 FASE 1: Programación Orientada a Objetos (OOP Básico)

  > **🏠 [Proyecto Principal](https://github.com/Blancadum/ComandasSOL)**
  >
  > - 🔄 [Fase 1](https://github.com/Blancadum/ComandasSOL-fase1)
  > - [Fase 2](https://github.com/Blancadum/ComandasSOL-fase2)
  > - [Fase 3](https://github.com/Blancadum/ComandasSOL-fase3)
  > - [Fase 4](https://github.com/Blancadum/ComandasSOL-fase4)
  > - [Fase 5](https://github.com/Blancadum/ComandasSOL-fase5)

## 📚 Objetivo de Esta Fase

Aprender los **fundamentos de la Programación Orientada a Objetos** construyendo un **sistema simple de gestión de comandas de restaurante** usando:
- ✅ Clases y Objetos
- ✅ Atributos y Métodos
- ✅ Encapsulación (private/public)
- ✅ ArrayList (colecciones dinámicas)
- ✅ Métodos de utilidad

---

## 🎯 ¿Por Qué Este Proyecto?

Imaginemos que **eres un camarero** que debe:
1. **Anotar el menú** del restaurante (qué platos hay, cuánto cuesta cada uno)
2. **Tomar un pedido** (el cliente pide 2 ensaladas y 1 carne)
3. **Calcular el total** de lo que pidió
4. **Cambiar el estado** del pedido (pendiente → en preparación → listo)

Esto requiere **3 clases principales**:
- `Plato`: Un item del menú (nombre, precio, categoría)
- `Pedido`: Una comanda con múltiples platos
- `Restaurante`: Gestiona el menú y todas las comandas

---

## 📋 Las 4 Clases Principales

### 1. **Plato.java** - Un item del menú

```java
public class Plato {
    private int id;              // Identificador único (1, 2, 3...)
    private String nombre;       // "Ensalada Fresca"
    private String categoria;    // "Entrantes", "Platos Principales", etc
    private double precio;       // 8.50€

    // Constructor: crea un nuevo plato con validaciones
    public Plato(int id, String nombre, String categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getters: métodos para obtener los valores
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    // Método de utilidad para imprimir el plato
    public String getInfo() {
        return String.format("[%d] %s (%.2f€)", id, nombre, precio);
    }
}
```

**Conceptos clave:**
- `private`: Los atributos no se pueden acceder directamente desde fuera
- `public`: Los métodos getters permiten obtener los valores de forma controlada
- `Constructor`: Inicializa el objeto cuando se crea con `new`

---

### 2. **Pedido.java** - Una comanda (conjunto de platos)

```java
public class Pedido {
    private int id;                      // Número de comanda (1, 2, 3...)
    private ArrayList<Plato> items;      // Lista de platos pedidos
    private ArrayList<Integer> cantidades; // Cantidad de cada plato
    private String estado;               // "PENDIENTE", "EN_PREPARACION", "LISTO"
    private double subtotal;             // Precio total del pedido

    // Constructor: crea un nuevo pedido vacío
    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();       // ArrayList: crece dinámicamente
        this.cantidades = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.subtotal = 0.0;
    }

    // ➕ Agrega un plato a la comanda
    public void agregarItem(Plato plato, int cantidad) {
        items.add(plato);
        cantidades.add(cantidad);
        this.subtotal += plato.getPrecio() * cantidad;
    }

    // 🧮 Calcula el total del pedido
    public double calcularSubtotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    // 🔄 Cambia el estado (PENDIENTE → EN_PREPARACION → LISTO)
    public boolean cambiarEstado(String nuevoEstado) {
        if (nuevoEstado.equals("PENDIENTE") ||
            nuevoEstado.equals("EN_PREPARACION") ||
            nuevoEstado.equals("LISTO")) {
            this.estado = nuevoEstado;
            return true;
        }
        return false;
    }

    // Getters
    public int getId() { return id; }
    public String getEstado() { return estado; }
    public ArrayList<Plato> getItems() { return items; }
    public ArrayList<Integer> getCantidades() { return cantidades; }
}
```

**Conceptos clave:**
- `ArrayList<Plato>`: Colección dinámica que crece cuando agregamos platos
- `for (int i = 0; i < items.size(); i++)`: Itera sobre los items del pedido
- `items.add()`: Agrega un nuevo elemento al ArrayList
- Métodos con `boolean`: Retornan true/false para indicar éxito/fracaso

---

### 3. **Restaurante.java** - Gestiona menú y comandas

```java
public class Restaurante {
    private String nombre;              // "La Bella Italia"
    private String propietario;         // "Giuseppe"
    private ArrayList<Plato> menu;      // Todos los platos disponibles
    private ArrayList<Pedido> pedidos;  // Todas las comandas tomadas

    // Constructor: inicializa el restaurante
    public Restaurante(String nombre, String propietario) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // 📋 Agrega un plato al menú
    public void agregarPlato(Plato plato) {
        menu.add(plato);
    }

    // 🔍 Busca un plato por ID
    public Plato buscarPlato(int id) {
        for (Plato p : menu) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // No encontrado
    }

    // 📝 Registra una nueva comanda
    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // 💰 Calcula la recaudación total
    public double calcularRecaudacion() {
        double total = 0;
        for (Pedido p : pedidos) {
            if (p.getEstado().equals("LISTO")) {
                total += p.calcularSubtotal();
            }
        }
        return total;
    }
}
```

**Conceptos clave:**
- `for (Plato p : menu)`: Sintaxis mejorada de for (for-each)
- `new ArrayList<>()`: Crear colecciones dinámicas
- Métodos que actúan sobre colecciones (buscar, agregar, iterar)

---

### 4. **Main.java** - Interfaz interactiva

```java
public class Main {
    public static void main(String[] args) {
        // Crear el restaurante
        Restaurante miRestaurante = new Restaurante("La Bella Italia", "Giuseppe");

        // Agregar platos al menú
        miRestaurante.agregarPlato(new Plato(1, "Tabla de Quesos", "Entrantes", 10.00));
        miRestaurante.agregarPlato(new Plato(2, "Ensalada Fresca", "Entrantes", 8.50));
        miRestaurante.agregarPlato(new Plato(3, "Carne a la Parrilla", "Principales", 22.00));

        // Crear una comanda (pedido)
        Pedido comanda1 = new Pedido(1);

        // Agregar items a la comanda
        comanda1.agregarItem(miRestaurante.buscarPlato(2), 2); // 2 ensaladas
        comanda1.agregarItem(miRestaurante.buscarPlato(3), 1); // 1 carne

        // Mostrar detalles
        System.out.println("COMANDA #" + comanda1.getId());
        System.out.println("Total: " + comanda1.calcularSubtotal() + "€");
        System.out.println("Estado: " + comanda1.getEstado());

        // Cambiar estado
        comanda1.cambiarEstado("EN_PREPARACION");

        // Registrar la comanda en el restaurante
        miRestaurante.registrarPedido(comanda1);
    }
}
```

---

## 🎓 Conceptos OOP Explicados

### 📌 **Clase vs Objeto**

```
CLASE (Plato)           vs      OBJETO (Instancia)
┌─────────────────┐             ┌─────────────────┐
│ Plato           │             │ plato1          │
│ ─────────────   │             │ ─────────────   │
│ - id            │ ────────→   │ - id = 1        │
│ - nombre        │             │ - nombre = ...  │
│ - precio        │             │ - precio = 8.50 │
│ ─────────────   │             │ ─────────────   │
│ - getNombre()   │             │ new Plato(...)  │
│ - getPrecio()   │             │                 │
└─────────────────┘             └─────────────────┘

Una CLASE es el plano (como un molde de pastel).
Un OBJETO es la instancia (el pastel que salió del molde).
```

### 🔐 **Encapsulación**

```java
// ❌ SIN encapsulación (MALO):
public class Plato {
    public double precio;  // Cualquiera puede cambiar el precio
}

Plato p = new Plato();
p.precio = -100;  // ¡Precio negativo! ❌

// ✅ CON encapsulación (BIEN):
public class Plato {
    private double precio;  // Protegido

    public double getPrecio() {
        return precio;  // Solo lectura
    }
}

Plato p = new Plato();
p.getPrecio();  // Funciona ✅
p.precio = -100;  // ❌ ERROR: precio is private
```

**La encapsulación protege los datos de cambios indeseados.**

### 📦 **ArrayList (Colecciones Dinámicas)**

```java
// Array fijo (tamaño conocido)
int[] numeros = new int[5];  // Máximo 5 elementos
numeros[0] = 1;
numeros[1] = 2;
// ¿Qué pasa si quiero agregar un 6to elemento? ❌ ERROR

// ArrayList dinámico (crece automáticamente)
ArrayList<Integer> numeros = new ArrayList<>();
numeros.add(1);      // Tamaño: 1
numeros.add(2);      // Tamaño: 2
numeros.add(3);      // Tamaño: 3 (¡crecimiento automático!)
numeros.get(0);      // Obtener elemento
numeros.size();      // Saber cuántos elementos hay
```

**ArrayList es perfecto para "no sé cuántos platos/pedidos tendré".**

---

## ❓ Preguntas y Respuestas

### P1: ¿Qué es una clase?
**R:** Una clase es un plano o molde que define qué atributos (datos) y métodos (funcionalidades) tendrá un objeto. Por ejemplo, `Plato` es una clase que define que todo plato tiene `id`, `nombre`, `categoria` y `precio`.

### P2: ¿Cuál es la diferencia entre atributo y método?
**R:**
- **Atributo**: Un dato que guarda la clase (ej: `precio`). Responde "¿Qué datos tiene?"
- **Método**: Una función que realiza una acción (ej: `getPrecio()`). Responde "¿Qué puede hacer?"

### P3: ¿Por qué hacer atributos `private` y métodos `public`?
**R:** Para **controlar el acceso**. Si `precio` es public, cualquiera puede ponerle -100€. Si es private + getter, garantizamos que solo se acceda de forma controlada.

### P4: ¿Qué es un ArrayList?
**R:** Una colección dinámicamente redimensionable. A diferencia de un array (que tiene tamaño fijo), un ArrayList crece automáticamente cuando le agregas elementos. Es como una bolsa que se expande según lo necesites.

### P5: ¿Cuál es la diferencia entre `agregarItem()` y `getItems()`?
**R:**
- `agregarItem()`: Agrega un plato al pedido (modifica el estado)
- `getItems()`: Retorna la lista de platos (solo lectura)

### P6: ¿Cómo funciona el `for-each` en Java?
**R:** Itera automáticamente sobre cada elemento sin necesidad de índices:
```java
// For tradicional
for (int i = 0; i < items.size(); i++) {
    Plato p = items.get(i);
    System.out.println(p.getNombre());
}

// For-each (más legible)
for (Plato p : items) {
    System.out.println(p.getNombre());
}
```

### P7: ¿Qué pasa si busco un plato que no existe?
**R:** El método `buscarPlato()` retorna `null`. Es responsabilidad del código que llama a este método verificar si es null:
```java
Plato p = restaurante.buscarPlato(999);
if (p == null) {
    System.out.println("Plato no encontrado");
}
```

### P8: ¿Por qué el constructor no tiene tipo de retorno?
**R:** Porque los constructores siempre retornan una instancia de la clase. No necesitan declarar el tipo de retorno explícitamente.

### P9: ¿Cómo cambio el estado de un pedido?
**R:** Llamando al método `cambiarEstado()`:
```java
Pedido p = new Pedido(1);
p.cambiarEstado("EN_PREPARACION");  // Cambia a preparación
p.cambiarEstado("LISTO");            // Listo para servir
```

### P10: ¿Qué es mejor: tener muchos métodos pequeños o pocos métodos grandes?
**R:** **Muchos métodos pequeños**. Cada método debe hacer UNA cosa bien. Es más fácil de entender, probar y reutilizar.

---

## 🚀 Cómo Compilar y Ejecutar

### Paso 1: Compilar

```bash
cd /Users/admin/Desktop/Finales/Comandas/ComandasSOL/fase1-comandas--oop-basico/src
javac *.java
```

Esto genera archivos `.class` para cada clase.

### Paso 2: Ejecutar

```bash
java Main
```

Deberías ver un menú interactivo como:

```
╔══════════════════════════════════════╗
║    🍽️ SISTEMA DE COMANDAS - MENÚ     ║
╠══════════════════════════════════════╣
║ 1. Ver menú                          ║
║ 2. Crear nueva comanda               ║
║ 3. Ver comanda actual                ║
║ ...
```

---

## 🎮 Ejemplo Paso a Paso

```java
// 1️⃣ Crear restaurante
Restaurante rest = new Restaurante("La Bella Italia", "Giuseppe");

// 2️⃣ Agregar platos al menú
rest.agregarPlato(new Plato(1, "Ensalada Fresca", "Entrantes", 8.50));
rest.agregarPlato(new Plato(2, "Carne a la Parrilla", "Principales", 22.00));

// 3️⃣ Crear una comanda
Pedido comanda = new Pedido(1);

// 4️⃣ Buscar platos y agregarlos
Plato ensalada = rest.buscarPlato(1);  // Buscar por ID
Plato carne = rest.buscarPlato(2);

// 5️⃣ Agregar items a la comanda
comanda.agregarItem(ensalada, 2);  // 2 ensaladas
comanda.agregarItem(carne, 1);     // 1 carne

// 6️⃣ Ver el total
System.out.println("Total: " + comanda.calcularSubtotal() + "€");
// Output: Total: 39.00€  (2×8.50 + 1×22.00)

// 7️⃣ Cambiar estado
comanda.cambiarEstado("EN_PREPARACION");

// 8️⃣ Registrar en el restaurante
rest.registrarPedido(comanda);
```

---

## 📊 Diagrama Conceptual

```
┌──────────────────────────────────────────────┐
│         SISTEMA DE COMANDAS FASE 1           │
│              (OOP BÁSICO)                    │
└──────────────────────────────────────────────┘

┌──────────────┐      ┌──────────────┐      ┌──────────────┐
│  PLATO       │      │  PEDIDO      │      │  RESTAURANTE │
├──────────────┤      ├──────────────┤      ├──────────────┤
│ - id         │ ◄────│ - id         │      │ - nombre     │
│ - nombre     │      │ - items      │      │ - propietario│
│ - categoria  │      │ - cantidades │      │ - menu       │
│ - precio     │      │ - estado     │ ◄────│ - pedidos    │
├──────────────┤      │ - subtotal   │      ├──────────────┤
│ getPrecio()  │      ├──────────────┤      │ buscarPlato()│
│ getNombre()  │      │ agregarItem()│      │ registrar()  │
└──────────────┘      │ calcularSub()│      │ calcularRec()│
                      │ cambiarEst() │      └──────────────┘
                      └──────────────┘
                              ▲
                              │ contiene 1 o más
                              │
                      COMANDA REALIZADA
                      2×Ensalada (17€)
                      1×Carne (22€)
                      Total: 39€
```

---

## ✅ Checklist de Aprendizaje

Después de completar esta fase, deberías entender:

- ✅ Cómo crear una clase con atributos y métodos
- ✅ La diferencia entre `public` (visible) y `private` (oculto)
- ✅ Cómo usar getters para acceder a atributos privados
- ✅ Cómo funciona un constructor
- ✅ Cómo usar ArrayList para colecciones dinámicas
- ✅ El bucle `for-each` en Java
- ✅ Cómo buscar elementos en una colección
- ✅ La diferencia entre métodos que modifican estado y métodos de lectura

---

## 🔗 Conexión con Fase 2

En **Fase 2 - Excepciones**, aprenderemos:
- A validar operaciones robustamente con `try-catch`
- Crear excepciones personalizadas para errores del negocio
- Manejar casos donde un plato no existe o la cantidad es inválida

**Ejemplo avance:**
```java
// Fase 1 (retorna null)
Plato p = restaurante.buscarPlato(999);
if (p == null) { /* manejar error */ }

// Fase 2 (lanza excepción)
try {
    Plato p = restaurante.buscarPlato(999);  // Lanza PlatoNoEncontradoException
} catch (PlatoNoEncontradoException e) {
    System.out.println("Error: " + e.getMessage());
}
```

---

## 🎯 Ejercicios Propuestos

1. **Ejercicio 1:** Agrega un método `actualizarPrecio(double nuevoPrecio)` a la clase `Plato` que solo permita precios positivos.

2. **Ejercicio 2:** Crea un método en `Pedido` que retorne el nombre de todos los items de la comanda.

3. **Ejercicio 3:** Implementa un método en `Restaurante` que busque platos por categoría y retorne una lista.

4. **Ejercicio 4:** Agrega un método `eliminarItem(int idPlato)` a `Pedido` que quite un plato de la comanda.

---

## 📚 Recursos de Aprendizaje

- [Oracle: Classes and Objects (Inglés)](https://docs.oracle.com/javase/tutorial/java/concepts/index.html)
- [ArrayList Documentation (Inglés)](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- Documentación de Java: encapsulación y modificadores de acceso

---

## 💡 Tips Importantes

1. **Usa nombres descriptivos**: `agregarItem()` es mejor que `add()`
2. **Comenta el código**: Explica el "por qué", no el "qué"
3. **Prueba frecuentemente**: Compila y ejecuta después de cada cambio
4. **Busca nulos**: Siempre verifica si `buscarPlato()` retorna null
5. **Reutiliza código**: Usa métodos en lugar de repetir lógica

---
**¡Listo para pasar a Fase 2? ¡Aprenderemos a manejar excepciones!** 🚀
