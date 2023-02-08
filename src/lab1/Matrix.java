package lab1;

import java.util.Iterator;
import java.util.List;

public class Matrix {
    private int n, m;

    private Complex[][] matrix;

    // square matrix
    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(int n, int m) {
        this.matrix = new Complex[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                this.matrix[i][j] = new Complex(false);
            }
        }
        this.n = n;
        this.m = m;
    }

    // constructor, that initializes matrix with a list of complex(or normal) numbers
    public Matrix(int n, int m, List<Complex> list) {
        this.matrix = new Complex[n][m];
        Iterator<Complex> iterator = list.iterator();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.matrix[i][j] = iterator.next();
            }
        }
        this.n = n;
        this.m = m;
    }

    public void add(Matrix toAdd) {
        if (this.n != toAdd.n || this.m != toAdd.m) {
            throw new ArithmeticException("Only matrices with equal size can be summed!");
        } else {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    this.matrix[i][j].setReal(this.matrix[i][j].getReal() + toAdd.matrix[i][j].getReal());
                    this.matrix[i][j].setImaginary(this.matrix[i][j].getImaginary() + toAdd.matrix[i][j].getImaginary());
                }
            }
        }
    }

    public void multiply(Matrix multiplyWith) {
        if (this.m != multiplyWith.n) {
            throw new ArithmeticException("Quantity of columns in first matrix must" +
                    " be the same with the quantity of rows in the second matrix.");
        } else {
            Matrix result = new Matrix(this.n, multiplyWith.m);
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < multiplyWith.m; j++) {
                    for (int k = 0; k < this.m; k++) {
                        Complex val = new Complex(matrix[i][k].getReal(), matrix[i][k].getImaginary());
                        result.matrix[i][j].add(matrix[i][k].multiply(multiplyWith.matrix[k][j]));
                        matrix[i][k] = val;
                    }
                }
            }
            this.matrix = result.matrix;
            this.n = result.n;
            this.m = result.m;
        }
    }

    // lab1.Matrix transponation
    public void transponate() {
        Matrix temp = new Matrix(this.m, this.n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp.matrix[i][j] = this.matrix[j][i];
            }
        }
        this.n = temp.n;
        this.m = temp.m;
        this.matrix = temp.matrix;
    }

    // for better prints
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Complex[] complexes : matrix) {
            for (Complex complex : complexes) {
                builder.append(complex + "   ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // just basic getters and setters, even if we don't need them in this case, they might come in handy later
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public Complex[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Complex[][] matrix) {
        this.matrix = matrix;
    }
}
