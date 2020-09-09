package main;
import main.model.Fraction;
public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(8, 4);
        System.out.println(f1);
        System.out.println(f1.getExpandedForm(3));
        System.out.println(f1.getSimplestForm());
        System.out.println(f1.getExpandedForm(3).getSimplestForm());
    }
}