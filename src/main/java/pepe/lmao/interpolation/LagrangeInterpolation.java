package pepe.lmao.interpolation;

import lombok.Data;

@Data
public class LagrangeInterpolation {
    private double[] x;
    private double[] y;
    private double argument;
    private int n;
    private double res;
    private double l;

    public LagrangeInterpolation(double[] x, double[] y, double argument) {
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.argument = argument;
    }

    public void solve() {
        for (int i = 0; i < n; i++) {
            double numerator;
            double denominator = numerator = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    numerator *= (argument - x[j]);
                    denominator *= (x[i] - x[j]);
                }
            }
            l += numerator / denominator * y[i];
        }
        setRes(l);
    }
}
