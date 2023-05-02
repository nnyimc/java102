package fr.nnyimc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager implements Employee {

    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private int organizationSize;

    String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)\\}\\n";
    Pattern pattern = Pattern.compile(peopleRegex);

    private double salary = 3000;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    String managerRegex = "\\w+\\=(?<organizationSize>\\d+)";
    Pattern managerPattern = Pattern.compile(managerRegex);

    public Manager(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
            Matcher managerMatcher = managerPattern.matcher(line);
            if (managerMatcher.find()) {
                this.organizationSize = Integer.parseInt(managerMatcher.group("organizationSize"));
            }
        }
    }

    public double getSalary() {
       return salary += Math.pow(salary/organizationSize,2);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %n", lastName, firstName, organizationSize, getSalary());
    }

}
