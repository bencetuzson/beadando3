package main.model;

import java.util.Objects;

import static main.model.MathUtil.*;

@SuppressWarnings("ConstantConditions")
public class Fraction implements Comparable<Fraction> {
    //2-tes szint
    private int numerator;
    private int denominator;
    private boolean isInteger = false;
    final private static Fraction pi = new Fraction(355, 113);

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        if (this.numerator % this.denominator == 0) {
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
        if (simplify) {
            this.simplify();
        } else {
            isInteger = this.numerator % this.denominator == 0;
        }
    }

    public Fraction(Fraction fraction, boolean simplify) {
        numerator = fraction.numerator;
        denominator = fraction.denominator;
        if (simplify) {
            this.simplify();
        } else {
            isInteger = this.numerator % this.denominator == 0;
        }
    }

    public Fraction(Fraction fraction) {
        numerator = fraction.numerator;
        denominator = fraction.denominator;
        isInteger = fraction.isInteger;
    }

    public Fraction add(Fraction fraction) {
        Fraction ret = new Fraction();
        ret.denominator = LCM(denominator, ret.denominator, fraction.denominator);
        setNumerator(ret.numerator += ret.denominator / fraction.denominator * fraction.numerator + ret.denominator / denominator * numerator);
        setDenominator(ret.denominator);
        this.simplify();
        return this;
    }

    public Fraction sub(Fraction fraction) {
        Fraction ret = new Fraction();
        ret.denominator = LCM(denominator, ret.denominator, fraction.denominator);
        setNumerator(ret.numerator -= ret.denominator / fraction.denominator * fraction.numerator - ret.denominator / denominator * numerator);
        setDenominator(ret.denominator);
        this.simplify();
        return this;
    }

    public Fraction mul(Fraction fraction) {
        numerator *= fraction.numerator;
        denominator *= fraction.denominator;
        this.simplify();
        return this;
    }

    public Fraction div(Fraction fraction) {
        numerator *= fraction.denominator;
        denominator *= fraction.numerator;
        this.simplify();
        return this;
    }

    public Fraction pow(int power) {
        setNumerator((int) Math.pow(numerator, power));
        setDenominator((int) Math.pow(denominator, power));
        this.simplify();
        return this;
    }

    public Fraction opposite() {
        return new Fraction(-numerator, denominator).getSimplestForm();
    }

    public Fraction reciprocal() {
        try {
            return new Fraction(denominator, numerator).getSimplestForm();
        } catch (ArithmeticException e) {
            System.err.println("The fraction's reciprocal doesn't exist!");
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
            System.err.println("Illegal denominator!");
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public boolean equals(Fraction fraction) {
        if (fraction.getSimplestForm().numerator == getSimplestForm().numerator && fraction.getSimplestForm().denominator == getSimplestForm().denominator) {
            return true;
        } else if (fraction == null) {
            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (isInteger) {
            return Integer.toString((int) evaluate());
        } else {
            return numerator + "/" + denominator;
        }
    }

    //3-mas szint

    private void simplify() {
        int GCD = GCD(numerator, denominator);
        denominator /= GCD;
        numerator /= GCD;
        isInteger = numerator % denominator == 0;
    }

    private void expand(int n) {
        numerator += denominator*n;
        isInteger = numerator % denominator == 0;
    }

    public Fraction getSimplestForm() {
        Fraction ret = new Fraction(numerator, denominator);
        ret.simplify();
        return ret;
    }

    public Fraction getExpandedForm(int n) {
        Fraction ret = new Fraction(numerator, denominator);
        ret.expand(n);
        return ret;
    }

    //4-es szint

    public static Fraction add(Fraction ...fraction) {
        Fraction ret = new Fraction();
        for (Fraction num : fraction) {
            ret.denominator = LCM(num.denominator, ret.denominator);
        }
        for (Fraction num : fraction) {
            ret.numerator += ret.denominator / num.denominator * num.numerator;
        }
        return ret.getSimplestForm();
    }

    public static Fraction sub(Fraction ...fraction) {
        Fraction ret = new Fraction();
        for (Fraction num : fraction) {
            ret.denominator = LCM(num.denominator, ret.denominator);
        }
        fraction[0] = fraction[0].opposite();
        for (Fraction num : fraction) {
            ret.numerator -= ret.denominator / num.denominator * num.numerator;
        }
        return ret.getSimplestForm();
    }

    public static Fraction mul(Fraction ...fraction) {
        Fraction ret = new Fraction(1, 1);
        for (Fraction num : fraction) {
            ret.numerator *= num.numerator;
            ret.denominator *= num.denominator;
        }
        return ret.getSimplestForm();
    }

    public static Fraction div(Fraction ...fraction) {
        Fraction ret = new Fraction(1, 1);
        fraction[0] = fraction[0].reciprocal();
        for (Fraction num : fraction) {
            ret.numerator *= num.denominator;
            ret.denominator *= num.numerator;
        }
        return ret.getSimplestForm();
    }

    public Fraction pow(Fraction fraction, int power) {
        fraction.numerator = (int) Math.pow(numerator, power);
        fraction.denominator = (int) Math.pow(denominator, power);
        return fraction.getSimplestForm();
    }

    //5-ös szint

    public double evaluate() {
        return (double) numerator/denominator;
    }

    public static Fraction parseDouble(Double raw) {
        if (raw == Math.floor(raw)) {
            return new Fraction((int) Math.floor(raw), 1).getSimplestForm();
        } else if (raw == Math.PI){
            return pi;
        } else {
            final int numLength = (int) Math.pow(10, raw.toString().split("\\.")[1].length());
            return new Fraction((int) (raw*numLength), numLength).getSimplestForm();
        }
    }

    public static Fraction parseString(String raw) {
        try {
            return parseDouble(Double.parseDouble(raw));
        } catch (IllegalArgumentException e) {
            System.err.println("String couldn't be converted into fraction!");
            return null;
        }
    }

    @Override
    public int compareTo(Fraction o) {
        if (o.equals(this)) {
            return 0;
        } else if (evaluate() < (double) (o.numerator/o.denominator)) {
            return -1;
        } else if (evaluate() > (double) (o.numerator/o.denominator)) {
            return 1;
        } else {
            return evaluate() < (double) (o.numerator/o.denominator) ? -1 : 1;
        }
    }
}
