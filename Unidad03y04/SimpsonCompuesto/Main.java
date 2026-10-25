package Ecuaciones_Lineales.Unidad03y04.SimpsonCompuesto;

import java.util.Locale;

/**
 * Programa principal: ejecuta Simpson 1/3 Compuesto para f(x) = x^2 en [0, 2]
 * con la tolerancia indicada y muestra un resumen del resultado.
 */
public class Main {

    public static void main(String[] args) {
        double a = 0.0;
        double b = 2.0;
        double tolerancia = 0.0001;

        System.out.println("==========================================================");
        System.out.println("   INTEGRACION NUMERICA - SIMPSON 1/3 COMPUESTO");
        System.out.println("==========================================================");
        System.out.println("  Funcion    : f(x) = x^2");
        System.out.println("  Intervalo  : [" + a + ", " + b + "]");
        System.out.println("  Tolerancia : " + tolerancia);

        // Llamamos al metodo encapsulado
        double resultado = SimpsonCompuesto.integrarConTolerancia(a, b, tolerancia);

        double exacto = 8.0 / 3.0; // Valor exacto de la integral de x^2 en [0, 2]
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("   RESULTADO FINAL");
        System.out.println("==========================================================");
        System.out.println(String.format(Locale.US,
                "  Integral aproximada : %.10f", resultado));
        System.out.println(String.format(Locale.US,
                "  Valor exacto (8/3)  : %.10f", exacto));
        System.out.println(String.format(Locale.US,
                "  Error real          : %.2e", Math.abs(resultado - exacto)));
        System.out.println("  Subintervalos (n)   : " + SimpsonCompuesto.ultimoN);
        System.out.println("  Iteraciones         : " + SimpsonCompuesto.ultimasIteraciones);
        System.out.println();
    }
}