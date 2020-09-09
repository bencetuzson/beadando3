package main.model;

import java.util.Objects;

import static main.model.MathUtil.GCD;


public class Fraction {
    //2-tes szint
    private int numerator;
    private int denominator;
    private boolean isInteger = false;
    final private static int pi = 355/113;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        if ((numerator / denominator) % 2 == 0) {
            isInteger = true;
        }
        this.simplify();
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

    public Fraction(int numerator, int denominator, boolean simplify) {
        this.numerator = numerator;
        this.denominator = denominator;
        if ((numerator / denominator) % 2 == 0) {
            isInteger = true;
        }
        if (simplify) {
            this.simplify();
        }
    }

    public Fraction(Fraction fraction, boolean simplify) {
        this.numerator = fraction.numerator;
        this.denominator = fraction.denominator;
        if (simplify) {
            this.simplify();
        }
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

    public Fraction add(Fraction newFraction, Fraction fraction) {
        newFraction.numerator += fraction.numerator;
        newFraction.denominator += fraction.denominator;
        return newFraction;
    }

    public Fraction sub(Fraction newFraction, Fraction fraction) {
        newFraction.numerator -= fraction.numerator;
        newFraction.denominator -= fraction.denominator;
        return newFraction;
    }

    public Fraction mul(Fraction newFraction, Fraction fraction) {
        newFraction.numerator *= fraction.numerator;
        newFraction.denominator *= fraction.denominator;
        return newFraction;
    }

    public Fraction div(Fraction newFraction, Fraction fraction) {
        newFraction.numerator /= fraction.numerator;
        newFraction.denominator /= fraction.denominator;
        return newFraction;
    }

    public Fraction pow(Fraction fraction, int power) {
        fraction.numerator = (int) Math.pow(this.numerator, power);
        fraction.denominator = (int) Math.pow(this.denominator, power);
        return fraction;
    }

    public Fraction opposite() {
        this.numerator = -numerator;
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
        this.simplify();
        if (fraction == this) {
            return true;
        } else if (fraction == null) {
            return false;
        } else {
            //Fraction f = (Fraction) fraction;
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

    //3-mas szint

    private void simplify() {
        int GCD = GCD(this.numerator, this.denominator);
        denominator /= GCD;
        numerator /= GCD;
    }

    private void expand(int i) {
        numerator += denominator*i;
    }

    public Fraction getSimplestForm() {
        this.simplify();
        return this;
    }

    public Fraction getExpandedForm(int i) {
        this.expand(i);
        return this;
    }

    //4-es szint




}
