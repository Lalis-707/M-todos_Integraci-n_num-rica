package Ecuaciones_Lineales.Unidad03y04.SimpsonCompuesto;

import Ecuaciones_Lineales.Unidad03y04.TrapecioCompuesto.TrapecioCompuesto;

import java.util.Locale;

/**
 * Integración numérica por la regla de Simpson 1/3 Compuesta.
 *
 * <p>Cada par de subintervalos se aproxima con una parábola, por lo que n debe
 * ser par. Reutiliza la función f(x) y el cálculo de h de TrapecioCompuesto.</p>
 */
public class SimpsonCompuesto {

    /** Número de subintervalos con el que terminó la última integración. */
    public static int ultimoN = 0;

    /** Número de iteraciones (duplicaciones de n) de la última integración. */
    public static int ultimasIteraciones = 0;

    /**
     * Aproxima la integral con Simpson 1/3 Compuesto para un n par fijo.
     *
     * <p>Fórmula: I = (h/3) * [ f(a) + 4*sum(impares) + 2*sum(pares) + f(b) ]</p>
     */
    private static double calcularSimpson(double a, double b, int n) {
        double h = TrapecioCompuesto.calcularH(a, b, n);
        double sumaImpares = 0.0; // Puntos x1, x3, x5, ...  (coeficiente 4)
        double sumaPares = 0.0;   // Puntos x2, x4, x6, ...  (coeficiente 2)

        for (int k = 1; k < n; k++) {
            double xk = a + k * h;

            // Índice impar -> coeficiente 4 (centro de una parábola)
            // Índice par   -> coeficiente 2 (frontera entre dos parábolas)
            if (k % 2 != 0) {
                sumaImpares += TrapecioCompuesto.funcion(xk);
            } else {
                sumaPares += TrapecioCompuesto.funcion(xk);
            }
        }

        double sumaTotal = TrapecioCompuesto.funcion(a) + 4 * sumaImpares
                + 2 * sumaPares + TrapecioCompuesto.funcion(b);

        return (h / 3) * sumaTotal;
    }

    /**
     * Aplica el criterio de paro por error absoluto duplicando n en cada vuelta
     * (n parte de 2 y siempre se mantiene par) y muestra la tabla de avance.
     *
     * @param a          límite inferior.
     * @param b          límite superior.
     * @param tolerancia error absoluto máximo permitido entre iteraciones.
     * @return la aproximación final de la integral.
     */
    public static double integrarConTolerancia(double a, double b, double tolerancia) {
        int n = 2;
        int iteraciones = 0;
        double integralAnterior = calcularSimpson(a, b, n);
        double errorAbsoluto = 999.0; // Valor grande para entrar al while

        System.out.println();
        System.out.println("  SIMPSON 1/3 COMPUESTO - iteraciones");
        imprimirSeparador();
        System.out.println("  | Iter |     n |        Integral        |     Error absoluto     |");
        imprimirSeparador();
        imprimirFila(0, n, integralAnterior, Double.NaN);

        while (errorAbsoluto > tolerancia) {
            n *= 2;                 // h se reduce a la mitad; n sigue siendo par
            iteraciones++;
            double integralNueva = calcularSimpson(a, b, n);

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