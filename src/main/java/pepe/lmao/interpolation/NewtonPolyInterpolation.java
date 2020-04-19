package pepe.lmao.interpolation;

import lombok.Data;

@Data
public class NewtonPolyInterpolation {
    private double[] x;
    private double[] y;
    private double argument;
    private int n;

    private double[] node_x;
    private double[] node_y;
    private double res;

    public NewtonPolyInterpolation(double[] x, double[] y, double argument) {
        this.x = x;
        this.y = y;
        this.n = x.length;
        this.argument = argument;
    }


    public void solve() {

        for (int i = 0; i < n; i++) {

            if (argument < x[n - 1] && argument > x[n - 1 - 1] || argument > x[i] && (n - 1 - i) < 3) {
                node_x = new double[]{x[n - 1 - 2], x[n - 1 - 1], x[n - 1]};
                node_y = new double[]{y[n - 1 - 2], y[n - 1 - 1], y[n - 1]};
                res = calc() + calc();
            }
            if (argument > x[0] && argument < x[1] || argument > x[i] && (n - 1 - i) >= 3) {
                for (int j = 0; j < 1; j++) {
                    node_x = new double[]{x[i + j], x[i + j + 1], x[i + j + 2]};
                    node_y = new double[]{y[i + j], y[i + j + 1], y[i + j + 2]};
                    res += calc();
                }
            }
        }
        setRes(res/2);

    }

    private double calc() {
        return f(0) + f(0, 1) * (argument - node_x[0]) + f(0, 1, 2) * (argument - node_x[0]) * (argument - node_x[1]);
    }

    private double f(int a) {
        return node_y[a];
    }

    private double f(int a, int b) {
        return (f(b) - f(a)) / (node_x[b] - node_x[a]);
    }

    private double f(int a, int b, int c) {
        return (f(b, c) - f(a, b)) / (node_x[c] - node_x[a]);
    }


}
