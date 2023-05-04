package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {

    private int organizationSize;

    private double salary = 3000;
    String managerRegex = "\\w+\\=(?<organizationSize>\\d+)";
    Pattern managerPattern = Pattern.compile(managerRegex);

    public Manager(String line) {
        super(line);
        Matcher managerMatcher = managerPattern.matcher(line);
        if (managerMatcher.find()) {
            this.organizationSize = Integer.parseInt(managerMatcher.group("organizationSize"));
        }
    }

    @Override
    public double getSalary() {
       return salary += Math.pow(salary/organizationSize,2);
    }

    public int getOrganizationSize() {
        return organizationSize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %n", super.toString(), organizationSize);
    }

}
