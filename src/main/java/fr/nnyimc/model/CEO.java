package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee {


    private double avgStockPrice;

    private final String ceoRegex = "\\w+\\=(?<avgStockPrice>\\d+)";
    private final Pattern ceoPattern = Pattern.compile(ceoRegex);

    public CEO(String line) {
        super(line);
        Matcher ceoMatcher= ceoPattern.matcher(line);
        if (ceoMatcher.find()) {
            this.avgStockPrice = Double.parseDouble(ceoMatcher.group("avgStockPrice"));
        }
    }

    @Override
    public double getSalary() {
        return this.salary += Math.pow(salary/avgStockPrice, 3);
    }

    @Override
    public String toString() {
        return String.format("%s %s %n", super.toString(), avgStockPrice);
    }
}
