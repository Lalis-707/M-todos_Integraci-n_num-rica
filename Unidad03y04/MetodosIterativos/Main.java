package Ecuaciones_Lineales.Unidad03y04.MetodosIterativos;

public class Main {

    public static void main(String[] args) {
        // ^v^ Obtiene el sistema desde DefMatriz: matriz A, vector b, aproximación inicial y tolerancia ^v^
        double[][] A = DefMatriz.obtenerMatrizA();
        double[] b = DefMatriz.obtenerVectorB();
        double[] x0 = DefMatriz.obtenerVectorX0();
        double tol = DefMatriz.obtenerTolerancia();

        // ^v^ Tope de iteraciones para que ningún método se cicle si no converge ^v^
        int maxIter = 100;

        // ^v^ Ejecuta ambos métodos con los mismos datos; cada uno recibe su propia copia de x0 ^v^
        double[] solJacobi = MetodosIterativos.jacobi(A, b, x0.clone(), tol, maxIter);
        double[] solGauss = MetodosIterativos.gaussSeidel(A, b, x0.clone(), tol, maxIter);

        // ^v^ Encabezado con los datos de entrada: el sistema, la aproximación inicial y la tolerancia ^v^
        System.out.println("^v^  METODOS ITERATIVOS: JACOBI Y GAUSS-SEIDEL  ^v^");
        System.out.println();
        System.out.println("Sistema de ecuaciones:");
        imprimirSistema(A, b);
        System.out.println();
        System.out.println("Aproximacion inicial X(0) = " + formatearVector(x0));
        System.out.println("Tolerancia                = " + tol);
        System.out.println("Maximo de iteraciones     = " + maxIter);

        // ^v^ Muestra la solución y el número de iteraciones de cada método ^v^
        imprimirResultado("JACOBI", solJacobi, MetodosIterativos.iteracionesJacobi, maxIter);
        imprimirResultado("GAUSS-SEIDEL", solGauss, MetodosIterativos.iteracionesGaussSeidel, maxIter);

        // ^v^ Compara cuántas vueltas necesitó cada método para cumplir la misma tolerancia ^v^
        System.out.println();
        System.out.println("COMPARACION CON TOLERANCIA " + tol + ":");
        System.out.println("   Jacobi:       " + MetodosIterativos.iteracionesJacobi + " iteraciones");
        System.out.println("   Gauss-Seidel: " + MetodosIterativos.iteracionesGaussSeidel + " iteraciones");
    }

    // ^v^ Muestra el nombre del método, cada incógnita con su valor y el total de iteraciones ^v^
    private static void imprimirResultado(String metodo, double[] solucion, int iteraciones, int maxIter) {
        System.out.println();
        System.out.println("SOLUCION APROXIMADA POR " + metodo + ":");
        for (int i = 0; i < solucion.length; i++) {
            System.out.printf("   x[%d] = %.6f%n", i, solucion[i]);
        }
        System.out.println("Iteraciones realizadas: " + iteraciones);
        if (iteraciones < maxIter) {
            System.out.println("Estado: convergio dentro de la tolerancia ^v^");
        } else {
            System.out.println("Estado: se alcanzo el maximo de iteraciones (posible falta de convergencia)");
        }
    }

    // ^v^ Imprime cada renglón de la matriz como una ecuación: a1*x0 + a2*x1 + ... = b ^v^
    private static void imprimirSistema(double[][] A, double[] b) {
        for (int i = 0; i < A.length; i++) {
            StringBuilder renglon = new StringBuilder("   ");
            for (int j = 0; j < A[i].length; j++) {
                if (j > 0) {
                    renglon.append(A[i][j] < 0 ? " - " : " + ");
                } else if (A[i][j] < 0) {
                    renglon.append("-");
                }
                renglon.append(String.format("%.1f*x%d", Math.abs(A[i][j]), j));
            }
            renglon.append(String.format(" = %.1f", b[i]));
            System.out.println(renglon);
        }
    }

    // ^v^ Convierte un vector a texto con el formato [ v1, v2, v3 ] ^v^
    private static String formatearVector(double[] v) {
        StringBuilder texto = new StringBuilder("[ ");
        for (int i = 0; i < v.length; i++) {
            texto.append(String.format("%.1f", v[i]));
            if (i < v.length - 1) texto.append(", ");
        }
        return texto.append(" ]").toString();
    }
}