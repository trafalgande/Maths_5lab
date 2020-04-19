package pepe.lmao.interpolation;

import lombok.Data;
import org.apache.commons.math3.util.FastMath;

import java.util.Arrays;

@Data
public class NewtonInterpolation {
    private double[] x;
    private double[] y;
    private double argument;
    private int n;
    private double res;
    private double t;
    static int k;
    public NewtonInterpolation(double[] x, double[] y, double argument) {
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.argument = argument;
    }


    public void solve() {
        if (argument < x[0]) {
            t = (argument - x[0]) / (x[1] - x[0]);
            backward(t);
        } else if (argument > x[n - 1]) {
            t = (argument - x[n - 1]) / (x[1] - x[0]);
            forward(t);
        } else if (argument <= x[n / 2]) {
            for (int i = 0; i < n / 2 + 1; i++) {
                if (argument > x[i]) {
                    t = (argument - x[0]) / (x[1] - x[0]);
                    k = i;
                }
            }
            forward(t);
        } else {
            for (int i = n / 2; i < n; i++) {
                if (argument > x[i]) {
                    t = (argument - x[n - 1]) / (x[1] - x[0]);
                    k = i;
                }
            }
            backward(t);
        }
    }

    private void forward(double t) {
        double res = 0;
        double numerator = 1;
        for (int i = k; i < n; i++) {
            if (i == k) res = y[k] + t * delta(1);
            if (i >= k) {
                numerator *= t * (t - i);
                res += numerator / fact(i + 1) * delta(i + 1);
            }

        }
        setRes(res);
    }

    private void backward(double t) {
        double res = 0;
        double numerator = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0) res = y[n - 1] + t * delta(1, n - 2);
            if (i >= 1) {
                numerator *= t * (t + i);
                res += numerator / fact(i + 1) * delta(i + 1, n - i);
            }

        }
        setRes(res);
    }

    private double delta(int q) {
        return calcCurrentDelta(q)[k];
    }

    private double delta(int q, int p) {
        return calcCurrentDelta(q)[p];
    }


    private double[] calcCurrentDelta(int q) {
        double[] res = new double[n];
        if (q == 1) {
            res = new double[n];
            for (int i = 0; i < n - 1; i++) {
                res[i] = y[1 + i] - y[i];
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                double a = calcCurrentDelta(q - 1)[i + 1];
                double b = calcCurrentDelta(q - 1)[i];
                res[i] = a - b;
            }
        }
        return res;
    }

    int fact(int n) {
        if (n <= 1)
            return 1;
        else
            return n * fact(n - 1);
    }
}
