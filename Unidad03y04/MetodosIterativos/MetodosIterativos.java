package Ecuaciones_Lineales.Unidad03y04.MetodosIterativos;

public class MetodosIterativos {

    // ^v^ Guardan en qué iteración se detuvo el último llamado de cada método ^v^
    public static int iteracionesJacobi = 0;
    public static int iteracionesGaussSeidel = 0;

    /**
     * ^v^ Resuelve Ax = b con el método de Jacobi: usa un vector auxiliar para los valores nuevos. ^v^
     *
     * @param A         matriz de coeficientes (cuadrada)
     * @param b         vector de términos independientes
     * @param x0        aproximación inicial
     * @param tol       error máximo permitido para detenerse
     * @param maxIter   tope de iteraciones
     * @return          aproximación a la solución del sistema
     */
    public static double[] jacobi(double[][] A, double[] b, double[] x0, double tol, int maxIter) {
        final int n = b.length;

        // ^v^ x guarda la aproximación vigente; xNuevo es el auxiliar donde se arma la siguiente ^v^
        double[] x = x0.clone();
        double[] xNuevo = new double[n];

        iteracionesJacobi = 0;

        // ^v^ Cada vuelta calcula una aproximación completa; el tope evita ciclos si no converge ^v^
        for (int k = 1; k <= maxIter; k++) {
            iteracionesJacobi = k;

            // ^v^ Cada incógnita se calcula usando solo valores de la iteración anterior (x) ^v^
            for (int i = 0; i < n; i++) {

                // ^v^ Suma de a_ij * x_j de todas las columnas menos la diagonal ^v^
                double acumulado = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        acumulado += A[i][j] * x[j];
                    }
                }

                // ^v^ Despeje x_i = (b_i - suma) / a_ii, guardado en el auxiliar y no en x ^v^
                xNuevo[i] = (b[i] - acumulado) / A[i][i];
            }

            // ^v^ Si el cambio máximo entre x y xNuevo es menor a tol, ya convergió ^v^
            if (calcularError(x, xNuevo) < tol) {
                return xNuevo;
            }

            // ^v^ Todavía hay error grande: xNuevo pasa a ser la aproximación vigente ^v^
            x = xNuevo.clone();
        }

        // ^v^ Se agotaron las iteraciones sin llegar a la tolerancia; se regresa la última aproximación ^v^
        return xNuevo;
    }

    /**
     * ^v^ Resuelve Ax = b con el método de Gauss-Seidel: cada valor nuevo se usa de inmediato. ^v^
     *
     * @param A         matriz de coeficientes (cuadrada)
     * @param b         vector de términos independientes
     * @param x0        aproximación inicial
     * @param tol       error máximo permitido para detenerse
     * @param maxIter   tope de iteraciones
     * @return          aproximación a la solución del sistema
     */
    public static double[] gaussSeidel(double[][] A, double[] b, double[] x0, double tol, int maxIter) {
        final int n = b.length;

        // ^v^ Copia de x0: el arreglo original no se modifica y x se actualiza sobre sí mismo ^v^
        double[] x = x0.clone();

        iteracionesGaussSeidel = 0;

        // ^v^ Cada vuelta es una iteración completa; el límite evita ciclos infinitos si no converge ^v^
        for (int k = 1; k <= maxIter; k++) {
            iteracionesGaussSeidel = k;

            // ^v^ Foto de x antes de actualizarlo, para medir después cuánto cambió ^v^
            double[] previo = x.clone();

            // ^v^ Se recorre cada ecuación del sistema, de la primera a la última ^v^
            for (int i = 0; i < n; i++) {

                // ^v^ Suma de a_ij * x_j de todas las incógnitas distintas a x_i ^v^
                double acumulado = 0.0;
                for (int j = 0; j < n; j++) {
                    // ^v^ x[j] ya trae el valor nuevo si j < i; esa es la diferencia clave con Jacobi ^v^
                    if (j != i) {
                        acumulado += A[i][j] * x[j];
                    }
                }

                // ^v^ Se despeja x_i = (b_i - suma) / a_ii y se guarda de inmediato en x ^v^
                x[i] = (b[i] - acumulado) / A[i][i];
            }

            // ^v^ Si la diferencia entre la iteración anterior y la actual es menor a tol, ya convergió ^v^
            if (calcularError(previo, x) < tol) {
                return x;
            }
        }

        // ^v^ Se agotaron las iteraciones sin llegar a la tolerancia; se regresa la última aproximación ^v^
        return x;
    }

    /**
     * ^v^ Error entre dos aproximaciones: la mayor diferencia absoluta entre sus componentes (norma infinito). ^v^
     */
    public static double calcularError(double[] xAnt, double[] xNuevo) {
        double max = 0.0;
        for (int i = 0; i < xAnt.length; i++) {
            max = Math.max(max, Math.abs(xNuevo[i] - xAnt[i]));
        }
        return max;
    }
}