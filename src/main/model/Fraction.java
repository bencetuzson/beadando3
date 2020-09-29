package main.model;

import java.util.Objects;

import static main.model.MathUtil.*;

public class Fraction implements Comparable<Fraction> {
    //2-tes szint
    private int numerator;
    private int denominator;
    private boolean isInteger = false;
    final public static Fraction PI = new Fraction(355, 113);

    public Fraction(int numerator, int denominator) {
        if (denominator != 0) {
            this.numerator = numerator;
            this.denominator = denominator;
            isInteger = isInteger();

            this.simplify();
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
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

    public Fraction(int numerator, int denominator, boolean simplify) {
        if (denominator != 0) {
            this.numerator = numerator;
            this.denominator = denominator;
            if (simplify) {
                this.simplify();
            } else {
                isInteger = isInteger();
            }
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
        }
    }

    public Fraction(Fraction fraction, boolean simplify) {
        numerator = fraction.numerator;
        denominator = fraction.denominator;
        if (simplify) {
            this.simplify();
        } else {
            isInteger = isInteger();
        }
    }

    public Fraction(Fraction fraction) {
        numerator = fraction.numerator;
        denominator = fraction.denominator;
        isInteger = fraction.isInteger;
    }

    public Fraction add(Fraction fraction) {
        if (denominator != 0 || fraction.denominator != 0) {
            Fraction ret = new Fraction();
            ret.denominator = LCM(denominator, ret.denominator, fraction.denominator);
            setNumerator(ret.numerator += ret.denominator / fraction.denominator * fraction.numerator + ret.denominator / denominator * numerator);
            setDenominator(ret.denominator);
            this.simplify();
            return this;
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
        }
    }

    public Fraction sub(Fraction fraction) {
        if (denominator != 0 || fraction.denominator != 0) {
            Fraction ret = new Fraction();
            ret.denominator = LCM(denominator, ret.denominator, fraction.denominator);
            setNumerator(ret.numerator -= ret.denominator / fraction.denominator * fraction.numerator - ret.denominator / denominator * numerator);
            setDenominator(ret.denominator);
            this.simplify();
            return this;
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
        }
    }

    public Fraction mul(Fraction fraction) {
        if (denominator != 0 || fraction.denominator != 0) {
            numerator *= fraction.numerator;
            denominator *= fraction.denominator;
            this.simplify();
            return this;
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
        }
    }

    public Fraction div(Fraction fraction) {
        if (denominator != 0 || fraction.denominator != 0) {
            numerator *= fraction.denominator;
            denominator *= fraction.numerator;
            this.simplify();
            return this;
        } else {
            throw new ArithmeticException("Denominator cannot be 0!");
        }
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
        if (denominator != 0) {
            return new Fraction(denominator, numerator).getSimplestForm();
        } else {
            throw new ArithmeticException("The fraction's reciprocal doesn't exist!");
        }
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private void setDenominator(int denominator) {
        if (denominator != 0) {
            this.denominator = denominator;
        } else {
            throw new IllegalArgumentException("Denominator cannot be 0!");
        }
    }

    private void setIsInteger(boolean isInteger) {
        this.isInteger = isInteger;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public boolean getIsInteger() {
        return isInteger;
    }

    @Override
    public boolean equals(Object o) {
        if (((Fraction) o).getSimplestForm().numerator == getSimplestForm().numerator && ((Fraction) o).getSimplestForm().denominator == getSimplestForm().denominator) {
            return true;
        } else if (o == null) {
            return false;
        } else {
            return false;
        }
    }

    @Override
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
        setIsInteger(isInteger());
    }

    private void expand(int n) {
        numerator += denominator*n;
        setIsInteger(isInteger());
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

    public static Fraction pow(Fraction fraction, int power) {
        fraction.numerator = (int) Math.pow(fraction.numerator, power);
        fraction.denominator = (int) Math.pow(fraction.denominator, power);
        return fraction.getSimplestForm();
    }

    //5-ös szint

    public double evaluate() {
        return (double) numerator/denominator;
    }

    public static Fraction parseDouble(Double raw) {
        if (raw == Math.floor(raw)) {
            Fraction ret = new Fraction((int) Math.floor(raw), 1);
            ret.setIsInteger(ret.isInteger());
            return ret;
        } else if (raw == Math.PI){
            return PI;
        } else {
            final int numLength = (int) Math.pow(10, raw.toString().split("\\.")[1].length());
            return new Fraction((int) (raw*numLength), numLength).getSimplestForm();
        }
    }

    public static Fraction parseString(String raw) {
        try {
            String[] ret = raw.split("/");
            if (ret.length == 0 || ret.length > 2) {

                System.err.println("Input isn't fraction");
                return null;
            } else {
                return new Fraction(Integer.parseInt(ret[0]), Integer.parseInt(ret[1]));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("String couldn't be converted into fraction!");
            return null;
        }
    }

    private boolean isInteger() {
        return numerator % denominator == 0;
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
