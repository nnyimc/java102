package fr.nnyimc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO {

    private double salary = 3000;
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    private double avgStockPrice;

    private final  String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})\\n";
    private final Pattern pattern = Pattern.compile(peopleRegex);
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private final String ceoRegex = "\\w+\\=(?<avgStockPrice>\\d+)";
    private final Pattern ceoPattern = Pattern.compile(ceoRegex);

    public CEO(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
            Matcher ceoMatcher= ceoPattern.matcher(line);
            if (ceoMatcher.find()) {
                this.avgStockPrice = Double.parseDouble(ceoMatcher.group("avgStockPrice"));
            }
        }
    }

    public double getSalary() {
        this.salary += Math.pow(salary/avgStockPrice, 3);
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %n", lastName, firstName,  salary);
    }
}
