package pepe.lmao.interpolation;

public class Interpolation {

    public static void solveByLagrange(double[] x, double[] y, double argument) {
        LagrangeInterpolation lagrangeInterpolation = new LagrangeInterpolation(x, y, argument);
        lagrangeInterpolation.solve();

        System.out.println("-->Lagrange Interpolation result: " + lagrangeInterpolation.getRes() + "\n");
    }

    public static void solveByNewtonPoly(double[] x, double[] y, double argument) {
        NewtonPolyInterpolation newtonPolyInterpolation = new NewtonPolyInterpolation(x,y,argument);
        newtonPolyInterpolation.solve();

        System.out.println("-->Newton Polynomial Interpolation result: " + newtonPolyInterpolation.getRes() + "\n");
    }

    public static void solveByNewton(double[] x, double[] y, double argument) {
        NewtonInterpolation newtonInterpolation = new NewtonInterpolation(x,y,argument);
        newtonInterpolation.solve();

        System.out.println("-->Newton Interpolation result: " + newtonInterpolation.getRes() + "\n");
    }
}
