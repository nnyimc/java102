package fr.nnyimc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer {

    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private int linesOfCodePerDay;
    private int experienceYears;
    private int iq;

    private int salary = 3000;


    //     Flinstone, Fred, 1/1/1900, Programmer, {linesOfCodePerDay=2000,experienceYears=10,iq=120}
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

    public int getSalary() {
            salary += salary/(linesOfCodePerDay+(iq/experienceYears));
        return salary;
    }
}
