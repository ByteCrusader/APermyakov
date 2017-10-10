package ru.job4j.calculator;

public class Calculator {
    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    public void div(double first, double second) {
        try {
            this.result = first/second;
        } catch (Exception e) {
            //do some with exception (task is do not use System.out.println)
        }
    }

    public void multiple(double first, double second) {
        this.result = first*second;
    }

    public double getResult() {
        return this.result;
    }
}
