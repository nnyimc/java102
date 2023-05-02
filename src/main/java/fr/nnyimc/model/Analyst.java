package fr.nnyimc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst {
    private double projectCount;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    private double salary = 3000;

    private final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})\\n";
    private final Pattern pattern = Pattern.compile(peopleRegex);
    private final String analystRegex = "\\w+\\=(?<projectCount>\\d+)";
    private final Pattern analystPattern = Pattern.compile(analystRegex);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Analyst(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
            Matcher analystMatcher = analystPattern.matcher(line);
            if (analystMatcher.find()) {
                this.projectCount = Double.parseDouble(analystMatcher.group("projectCount"));
            }
        }

    }

    public double getSalary() {
        this.salary += Math.pow(salary/projectCount,1.25);
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %n", lastName, firstName, projectCount, salary);
    }
}
