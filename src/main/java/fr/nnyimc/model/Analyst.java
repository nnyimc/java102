package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee {
    private double projectCount;
    private final String analystRegex = "\\w+\\=(?<projectCount>\\d+)";
    private final Pattern analystPattern = Pattern.compile(analystRegex);

    public Analyst(String line) {
        super(line);
        Matcher analystMatcher = analystPattern.matcher(line);
        if (analystMatcher.find()) {
            this.projectCount = Double.parseDouble(analystMatcher.group("projectCount"));
        }

    }

    public double getSalary() {
       return  this.salary += Math.pow(salary/projectCount,1.25);
    }

    @Override
    public String toString() {
        return String.format("%s %s %n",  super.toString(), projectCount);
    }
}
