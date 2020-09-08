package main.model;

import java.util.Objects;

public class Fraction {
    private int numerator;
    private int denominator;
    private boolean isInteger = false;

    public Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        if ((numerator / denominator) % 2 == 0) {
            isInteger = true;
        }
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        denominator = 1;
        isInteger = true;
    }

    public Fraction() {
        numerator = 0;
        denominator = 1;
        isInteger = true;
    }

    public Fraction(Fraction fraction) {
        numerator = fraction.numerator;
        denominator = fraction.denominator;
    }

    public Fraction add(Fraction fraction) {
        this.numerator += fraction.numerator;
        this.denominator += fraction.denominator;
        return this;
    }

    public Fraction sub(Fraction fraction) {
        this.numerator -= fraction.numerator;
        this.denominator -= fraction.denominator;
        return this;
    }

    public Fraction mul(Fraction fraction) {
        this.numerator *= fraction.numerator;
        this.denominator *= fraction.denominator;
        return this;
    }

    public Fraction div(Fraction fraction) {
        this.numerator /= fraction.numerator;
        this.denominator /= fraction.denominator;
        return this;
    }

    public Fraction pow(int power) {
        this.numerator = (int) Math.pow(this.numerator, power);
        this.denominator = (int) Math.pow(this.denominator, power);
        return this;
    }

    public Fraction opposite() {
        this.numerator = 0 - numerator;
        return this;
    }

    public Fraction reciprocal() {
        try {
            int temp = this.numerator;
            this.numerator = this.denominator;
            this.denominator = temp;
            return this;
        } catch (ArithmeticException e) {
            System.err.println("A tört reciproka nem kétezik");
            return null;
        }
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private void setDenominator(int denominator) {
        try {
            this.denominator = denominator;
        } catch (IllegalArgumentException e) {
            System.err.println("Hibás nevező");
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public boolean equals(Fraction fraction) {
        if (fraction == this) {
            return true;
        } else if (fraction == null) {
            return false;
        } else {
            Fraction f = (Fraction) fraction;
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.numerator, this.denominator);
    }


    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }




}
