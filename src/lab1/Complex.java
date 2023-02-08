package lab1;

import java.util.Scanner;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Normal number constructor(not complex)
    public Complex(double real) {
        this(real, 0);
    }

    // Initializing through console(if specified argument is true)
    public Complex(boolean consoleInit) {
        this(0, 0);
        if (consoleInit) {
            Scanner scanner = new Scanner(System.in);
            this.real = scanner.nextDouble();
            this.imaginary = scanner.nextDouble();
        }
    }


    public void add(Complex c) {
        this.real += c.real;
        this.imaginary += c.imaginary;
    }

    public Complex multiply(Complex c) {
        double img = this.real * c.imaginary + this.imaginary * c.real;
        this.real = this.real * c.real - this.imaginary * c.imaginary;
        this.imaginary = img;
        return this;
    }

    public String trigonometricForm() {
        if (this.real != 0 && this.imaginary != 0) {
            return String.format("z = %.3f * (%.3f + i * %.2f)",
                    Math.sqrt(this.real * this.real + this.imaginary * this.imaginary),
                    Math.cos(Math.atan(this.imaginary / this.real)),
                    Math.sin(Math.atan(this.imaginary / this.real)));
        } else {
            return "0";
        }
    }

    //basic getters and setters
    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    // toString for better print
    @Override
    public String toString() {
        return "" + this.real + "+" + this.imaginary + "i";
    }
}
