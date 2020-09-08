package main;
import main.model.Fraction;
public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(5, 4);
        System.out.println(f1.hashCode());
    }

}