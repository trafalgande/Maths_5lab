package pepe.lmao;

import pepe.lmao.interpolation.Interpolation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static final double[] x = new double[7];
    static final double[] y = new double[7];
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        readFromFile("table_1.txt");
        System.out.println("Set argument for Lagrange Interpolation: ");
        Interpolation.solveByLagrange(x,y,scanner.nextDouble());
        System.out.println("Set argument for Newton Polynomial Interpolation: ");
        Interpolation.solveByNewtonPoly(x,y,scanner.nextDouble());
        readFromFile("table_2.txt");
        System.out.println("Set argument for Newton Interpolation: ");
        Interpolation.solveByNewton(x,y,scanner.nextDouble());
    }

    public static void readFromFile(String filename) throws IOException {
        String row;
        String[] data;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int i = 0;
        while ((row = reader.readLine()) != null) {
            data = row.split(" ");
            x[i] = Double.parseDouble(data[0]);
            y[i] = Double.parseDouble(data[1]);
            i++;
        }
    }
}
