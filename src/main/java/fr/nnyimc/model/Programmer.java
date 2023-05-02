package fr.nnyimc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer implements Employee {

    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private double linesOfCodePerDay;
    private double experienceYears;
    private double iq;

    private double salary = 3000;

    private final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})\\n";
    private final Pattern pattern = Pattern.compile(peopleRegex);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    private final String programmerRegex = "\\w+\\=(?<linesOfCodePerDay>\\w+)\\,\\w+\\=(?<experienceYears>\\w+)\\,\\w+\\=(?<iq>\\w+)";
    private final Pattern programmerPattern = Pattern.compile(programmerRegex);

    public Programmer(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
            Matcher programmerMatcher = programmerPattern.matcher(line);
            if (programmerMatcher.find()) {
                this.linesOfCodePerDay = Integer.parseInt(programmerMatcher.group("linesOfCodePerDay"));
                this.experienceYears = Integer.parseInt(programmerMatcher.group("experienceYears"));
                this.iq = Integer.parseInt(programmerMatcher.group("iq"));
            }
        }
    }

    public double getSalary() {
            return this.salary += salary/(linesOfCodePerDay+(iq/experienceYears));
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %n", lastName, firstName, experienceYears, linesOfCodePerDay, getSalary());
    }
}
