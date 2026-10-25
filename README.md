# Integración numérica: Simpson 1/3 Compuesto vs. Trapecio Compuesto

Implementación en Java de la regla de **Simpson 1/3 Compuesta** y comparación con el **Trapecio Compuesto** para:

- Función: `f(x) = x²`
- Intervalo: `[0, 2]`
- Tolerancia: `0.0001` (criterio de paro: error absoluto entre dos aproximaciones sucesivas)
- Valor exacto: `8/3 ≈ 2.6666666667`

Ambos métodos parten de `n = 2` y duplican `n` en cada iteración hasta cumplir la tolerancia.

## Descripción de los métodos

**Trapecio Compuesto.** Divide el intervalo `[a, b]` en `n` subintervalos de ancho `h = (b − a) / n` y aproxima el área bajo la curva en cada uno con un trapecio (una recta que une dos puntos consecutivos). La suma de todas las áreas da la integral:

```
I ≈ (h/2) · [ f(x₀) + 2·f(x₁) + 2·f(x₂) + ... + 2·f(xₙ₋₁) + f(xₙ) ]
```

Acepta cualquier `n ≥ 1` y su error es de orden O(h²).

**Simpson 1/3 Compuesto.** Agrupa los subintervalos de dos en dos y aproxima la curva en cada pareja con una parábola que pasa por tres puntos consecutivos. Por eso `n` debe ser par. Los puntos de índice impar pesan 4 y los de índice par interior pesan 2:

```
I ≈ (h/3) · [ f(x₀) + 4·f(x₁) + 2·f(x₂) + 4·f(x₃) + ... + 4·f(xₙ₋₁) + f(xₙ) ]
```

Su error es de orden O(h⁴) y es exacto para polinomios de grado ≤ 3.

**Criterio de paro (ambos).** Se parte de `n = 2` y se duplica `n` en cada iteración hasta que el error absoluto entre dos aproximaciones sucesivas sea menor o igual que la tolerancia.

## Estructura del proyecto

```
Ecuaciones_Lineales/Unidad03y04/
├── TrapecioCompuesto/
│   ├── TrapecioCompuesto.java
│   └── Main.java        (ejecuta ambos métodos y muestra el resumen comparativo)
└── SimpsonCompuesto/
    ├── SimpsonCompuesto.java
    └── Main.java        (ejecuta solo Simpson 1/3 Compuesto)
```

## Requisitos

- JDK 17 o superior.
- Verificar en la terminal que `java -version` y `javac -version` muestren una versión instalada.

## Instrucciones paso a paso

### Opción A: desde la terminal

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Lalis-707/M-todos_Integraci-n_num-rica.git
   ```
2. Entrar a la carpeta del proyecto:
   ```bash
   cd M-todos_Integraci-n_num-rica
   ```
3. Compilar todas las clases (los `.class` se generan en la carpeta `out`):
   ```bash
   javac -d out Ecuaciones_Lineales/Unidad03y04/TrapecioCompuesto/TrapecioCompuesto.java Ecuaciones_Lineales/Unidad03y04/TrapecioCompuesto/Main.java Ecuaciones_Lineales/Unidad03y04/SimpsonCompuesto/SimpsonCompuesto.java Ecuaciones_Lineales/Unidad03y04/SimpsonCompuesto/Main.java
   ```
4. Ejecutar **ambos métodos** con el resumen comparativo:
   ```bash
   java -cp out Ecuaciones_Lineales.Unidad03y04.TrapecioCompuesto.Main
   ```
5. (Opcional) Ejecutar **solo Simpson 1/3 Compuesto**:
   ```bash
   java -cp out Ecuaciones_Lineales.Unidad03y04.SimpsonCompuesto.Main
   ```

### Opción B: desde un IDE (IntelliJ IDEA)

1. Clonar o descargar el repositorio.
2. En IntelliJ: `File > Open` y seleccionar la carpeta `M-todos_Integraci-n_num-rica`.
3. Clic derecho sobre la carpeta raíz > `Mark Directory as > Sources Root`, para que se resuelvan los paquetes `Ecuaciones_Lineales...`.
4. Configurar el JDK en `File > Project Structure > Project` (JDK 17 o superior).
5. Abrir `TrapecioCompuesto/Main.java` (ambos métodos) o `SimpsonCompuesto/Main.java` (solo Simpson).
6. Presionar el botón **Run** junto al método `main`.

## Ejemplo de salida en consola (ambos métodos, tolerancia 0.0001)

Resultado de ejecutar `Ecuaciones_Lineales.Unidad03y04.TrapecioCompuesto.Main`:

```
=========================================================
  INTEGRACION NUMERICA - TRAPECIO vs SIMPSON 1/3
=========================================================
  Funcion    : f(x) = x^2
  Intervalo  : [0.0, 2.0]
  Tolerancia : 1.0E-4

  TRAPECIO COMPUESTO - iteraciones
  +------+-------+------------------------+------------------------+
  | Iter |     n |        Integral        |     Error absoluto     |
  +------+-------+------------------------+------------------------+
  |    0 |     2 |         3.000000000000 |                      - |
  |    1 |     4 |         2.750000000000 |           0.2500000000 |
  |    2 |     8 |         2.687500000000 |           0.0625000000 |
  |    3 |    16 |         2.671875000000 |           0.0156250000 |
  |    4 |    32 |         2.667968750000 |           0.0039062500 |
  |    5 |    64 |         2.666992187500 |           0.0009765625 |
  |    6 |   128 |         2.666748046875 |           0.0002441406 |
  |    7 |   256 |         2.666687011719 |           0.0000610352 |
  +------+-------+------------------------+------------------------+

  SIMPSON 1/3 COMPUESTO - iteraciones
  +------+-------+------------------------+------------------------+
  | Iter |     n |        Integral        |     Error absoluto     |
  +------+-------+------------------------+------------------------+
  |    0 |     2 |         2.666666666667 |                      - |
  |    1 |     4 |         2.666666666667 |           0.0000000000 |
  +------+-------+------------------------+------------------------+

=========================================================
  RESUMEN COMPARATIVO
=========================================================
  +-----------+-------+-------+----------------+-----------+
  | Metodo    | Iter  |   n   |    Resultado   | Err. real |
  +-----------+-------+-------+----------------+-----------+
  | Trapecio  |     7 |  256  |  2.6666870117  |  2.03e-05 |
  | Simpson   |     1 |    4  |  2.6666666667  |  0.00e+00 |
  +-----------+-------+-------+----------------+-----------+
  Valor exacto (8/3): 2.6666666667
```

> El formato exacto de las líneas puede variar ligeramente según la versión del código, pero las cifras (iteraciones, `n`, resultados y errores) son las que produce el programa.

## Resultados

| Método | Iteraciones | n final | Resultado | Error real vs. 8/3 |
|---|---|---|---|---|
| Trapecio Compuesto | 7 | 256 | 2.6666870117 | 2.03 × 10⁻⁵ |
| Simpson 1/3 Compuesto | 1 | 4 | 2.6666666667 | 0 |

Simpson 1/3 es exacto desde `n = 2` porque su regla integra sin error polinomios de grado ≤ 3 y `f(x) = x²` es de grado 2. El Trapecio, con error O(h²), necesita llegar a `n = 256` para cumplir la tolerancia.
