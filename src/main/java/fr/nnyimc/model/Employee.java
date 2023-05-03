package fr.nnyimc.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee {

    protected String lastName;
    protected String firstName;
    protected LocalDate dateOfBirth;
    protected double salary = 3000;

    NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

    private final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})\\n";
    private final Pattern pattern = Pattern.compile(peopleRegex);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Employee (String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
        }
    }

    public double getSalary(){
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lastName, firstName, currencyInstance.format(getSalary()) );
    }
}
