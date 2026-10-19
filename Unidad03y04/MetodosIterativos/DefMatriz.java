package Ecuaciones_Lineales.Unidad03y04.MetodosIterativos;

public class DefMatriz {

    // ^v^ Matriz de coeficientes A del sistema 5x + y + z = 7 / x + 4y + z = 6 / x + 2y + 5z = 8 ^v^
    public static double[][] obtenerMatrizA() {
        return new double[][] {
                {5.0, 1.0, 1.0},
                {1.0, 4.0, 1.0},
                {1.0, 2.0, 5.0}
        };
    }

    // ^v^ Vector de términos independientes: lo que está del lado derecho de cada igualdad ^v^
    public static double[] obtenerVectorB() {
        return new double[] {7.0, 6.0, 8.0};
    }

    // ^v^ Aproximación inicial X(0) = [0, 0, 0]: desde aquí arrancan ambos métodos ^v^
    public static double[] obtenerVectorX0() {
        return new double[] {0.0, 0.0, 0.0};
    }

    // ^v^ Tolerancia de error ε = 0.0001 que define cuándo se detiene la iteración ^v^
    public static double obtenerTolerancia() {
        return 0.0001;
    }
}