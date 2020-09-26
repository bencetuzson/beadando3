package main;

import main.model.Fraction;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String path = "src/resources/input.txt";
        final String path2 = "src/resources/output.txt";
        String next;

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
            Fraction fraction = new Fraction();
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                next = sc.next();
                next = next.replace(String.valueOf(next.charAt(next.length()-1)), "");

                switch (next.split(" ").length) {
                    case 1:
                        fraction = new Fraction(Integer.parseInt(next.split("/")[0]), Integer.parseInt(next.split("/")[1]));
                        break;
                    case 2:
                        switch (next.split(" ")[0]) {
                            case "+":
                                switch (next.split(" ")[1].split("/").length) {
                                    case 2 -> fraction.add(new Fraction(Integer.parseInt(next.split(" ")[1].split("/")[0]), Integer.parseInt(next.split(" ")[1].split("/")[1])));
                                    case 1 -> fraction.add(Objects.requireNonNull(Fraction.parseString(next.split(" ")[1])));
                                }
                                break;
                            case "-":
                                switch (next.split(" ")[1].split("/").length) {
                                    case 2 -> fraction.sub(new Fraction(Integer.parseInt(next.split(" ")[1].split("/")[0]), Integer.parseInt(next.split(" ")[1].split("/")[1])));
                                    case 1 -> fraction.sub(Objects.requireNonNull(Fraction.parseString(next.split(" ")[1])));
                                }
                                break;
                            case "*":
                                switch (next.split(" ")[1].split("/").length) {
                                    case 2 -> fraction.mul(new Fraction(Integer.parseInt(next.split(" ")[1].split("/")[0]), Integer.parseInt(next.split(" ")[1].split("/")[1])));
                                    case 1 -> fraction.mul(Objects.requireNonNull(Fraction.parseString(next.split(" ")[1])));
                                }
                                break;
                            case "/":
                                switch (next.split(" ")[1].split("/").length) {
                                    case 2 -> fraction.div(new Fraction(Integer.parseInt(next.split(" ")[1].split("/")[0]), Integer.parseInt(next.split(" ")[1].split("/")[1])));
                                    case 1 -> fraction.div(Objects.requireNonNull(Fraction.parseString(next.split(" ")[1])));
                                }
                                break;
                        }
                }
            }
            try (FileWriter fw = new FileWriter(path2)) {
                fw.write(fraction.toString() + "\n" + (double) fraction.getNumerator()/fraction.getDenominator());
            } catch (IOException e) {
                System.err.println("Error while writing into file!");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }
}