package Ecuaciones_Lineales.Unidad03y04.TrapecioCompuesto;

import Ecuaciones_Lineales.Unidad03y04.SimpsonCompuesto.SimpsonCompuesto;

import java.util.Locale;
/**
 * Programa principal: ejecuta Trapecio Compuesto y Simpson 1/3 Compuesto con
 * la misma función, intervalo y tolerancia, y compara su eficiencia.
 */
public class Main {

    public static void main(String[] args) {
        double a = 0.0;
        double b = 2.0;
        double tolerancia = 0.0001;

        System.out.println("==========================================================");
        System.out.println("   INTEGRACION NUMERICA - TRAPECIO vs SIMPSON 1/3");
        System.out.println("==========================================================");
        System.out.println("  Funcion    : f(x) = x^2");
        System.out.println("  Intervalo  : [" + a + ", " + b + "]");
        System.out.println("  Tolerancia : " + tolerancia);

        // Metodo 1: Trapecio Compuesto
        double resultadoTrapecio = TrapecioCompuesto.integrarConTolerancia(a, b, tolerancia);
        int nTrapecio = TrapecioCompuesto.ultimoN;
        int iterTrapecio = TrapecioCompuesto.ultimasIteraciones;

        // Metodo 2: Simpson 1/3 Compuesto
        double resultadoSimpson = SimpsonCompuesto.integrarConTolerancia(a, b, tolerancia);
        int nSimpson = SimpsonCompuesto.ultimoN;
        int iterSimpson = SimpsonCompuesto.ultimasIteraciones;

        // Resumen comparativo (valor exacto de x^2 en [0,2] = 8/3)
        double exacto = 8.0 / 3.0;
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("   RESUMEN COMPARATIVO");
        System.out.println("==========================================================");
        System.out.println("  +-----------+-------+-------+----------------+-----------+");
        System.out.println("  | Metodo    | Iter  |   n   |    Resultado   | Err. real |");
        System.out.println("  +-----------+-------+-------+----------------+-----------+");
        imprimirResumen("Trapecio", iterTrapecio, nTrapecio, resultadoTrapecio, exacto);
        imprimirResumen("Simpson", iterSimpson, nSimpson, resultadoSimpson, exacto);
        System.out.println("  +-----------+-------+-------+----------------+-----------+");
        System.out.println("  Valor exacto (8/3): "
                + String.format(Locale.US, "%.10f", exacto));
        System.out.println();
    }

    private static void imprimirResumen(String metodo, int iter, int n,
                                        double resultado, double exacto) {
        System.out.println(String.format(Locale.US,
                "  | %-9s | %5d | %5d | %14.10f | %9.2e |",
                metodo, iter, n, resultado, Math.abs(resultado - exacto)));
    }
}