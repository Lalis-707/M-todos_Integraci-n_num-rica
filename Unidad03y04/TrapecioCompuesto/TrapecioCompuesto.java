package Ecuaciones_Lineales.Unidad03y04.TrapecioCompuesto;
/**
 * Integración numérica por la regla del Trapecio Compuesto.
 *
 * <p>Criterio de paro: se duplica el número de subintervalos (n -> 2n) en cada
 * iteración hasta que el error absoluto entre dos aproximaciones sucesivas sea
 * menor o igual que la tolerancia indicada.</p>
 */

import java.util.Locale;

public class TrapecioCompuesto {
    /** Número de subintervalos con el que terminó la última integración. */
    public static int ultimoN = 0;

    /** Número de iteraciones (duplicaciones de n) de la última integración. */
    public static int ultimasIteraciones = 0;

    /**
     * Función matemática f(x) que se desea integrar.
     *
     * @param x valor de la variable independiente.
     * @return f(x) evaluada en x.
     */
    public static double funcion(double x) {
        return Math.pow(x, 2); // Ejemplo: f(x) = x^2
    }

    /**
     * Calcula el ancho de cada subintervalo: h = (b - a) / n.
     * Es public static para que SimpsonCompuesto lo reutilice directamente.
     *
     * @param a límite inferior.
     * @param b límite superior.
     * @param n número de subintervalos.
     * @return el paso h.
     */
    public static double calcularH(double a, double b, int n) {
        return (b - a) / n;
    }

    /**
     * Aproxima la integral con la regla del Trapecio Compuesta para un n fijo.
     *
     * <p>Fórmula: I = (h/2) * [ f(a) + 2 * sum(f(x_k)) + f(b) ],  k = 1 .. n-1</p>
     */
    private static double calcularTrapecio(double a, double b, int n) {
        double h = calcularH(a, b, n);
        double suma = funcion(a) + funcion(b); // Extremos (coeficiente 1)

        // Puntos interiores: cada uno es compartido por dos trapecios (coeficiente 2)
        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            suma += 2 * funcion(xk);
        }

        return (h / 2) * suma;
    }

    /**
     * Aplica el criterio de paro por error absoluto duplicando n en cada vuelta
     * y mostrando una tabla con el avance de la convergencia.
     *
     * @param a          límite inferior.
     * @param b          límite superior.
     * @param tolerancia error absoluto máximo permitido entre iteraciones.
     * @return la aproximación final de la integral.
     */
    public static double integrarConTolerancia(double a, double b, double tolerancia) {
        int n = 2;                  // Número inicial de subintervalos
        int iteraciones = 0;
        double integralAnterior = calcularTrapecio(a, b, n);
        double errorAbsoluto = 999.0; // Valor grande para entrar al while

        System.out.println();
        System.out.println("  TRAPECIO COMPUESTO - iteraciones");
        imprimirSeparador();
        System.out.println("  | Iter |     n |        Integral        |     Error absoluto     |");
        imprimirSeparador();
        imprimirFila(0, n, integralAnterior, Double.NaN);

        while (errorAbsoluto > tolerancia) {
            n *= 2;                 // h se reduce a la mitad
            iteraciones++;
            double integralNueva = calcularTrapecio(a, b, n);

            errorAbsoluto = Math.abs(integralNueva - integralAnterior);
            imprimirFila(iteraciones, n, integralNueva, errorAbsoluto);

            integralAnterior = integralNueva;
        }
        imprimirSeparador();

        ultimoN = n;
        ultimasIteraciones = iteraciones;
        return integralAnterior;
    }

    // ---------- Utilidades de presentación (solo afectan la consola) ----------

    private static void imprimirSeparador() {
        System.out.println("  +------+-------+------------------------+------------------------+");
    }

    private static void imprimirFila(int iter, int n, double integral, double error) {
        String errorTxt = Double.isNaN(error)
                ? "-"
                : String.format(Locale.US, "%.10f", error);
        System.out.println(String.format(Locale.US,
                "  | %4d | %5d | %22.12f | %22s |", iter, n, integral, errorTxt));
    }
}