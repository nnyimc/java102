package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {

    private double linesOfCodePerDay;
    private double experienceYears;
    private double iq;

    private final String programmerRegex = "\\w+\\=(?<linesOfCodePerDay>\\w+)\\,\\w+\\=(?<experienceYears>\\w+)\\,\\w+\\=(?<iq>\\w+)";
    private final Pattern programmerPattern = Pattern.compile(programmerRegex);

    public Programmer (String line) {
        super(line);
        Matcher programmerMatcher = programmerPattern.matcher(line);
        if (programmerMatcher.find()) {
            this.linesOfCodePerDay = Integer.parseInt(programmerMatcher.group("linesOfCodePerDay"));
            this.experienceYears = Integer.parseInt(programmerMatcher.group("experienceYears"));
            this.iq = Integer.parseInt(programmerMatcher.group("iq"));
        }
    }

    public double getSalary() {
            return this.salary += salary/(linesOfCodePerDay+(iq/experienceYears));
    }

    @Override
    public String toString() {
        return String.format(" %s %s %s %n",  super.toString(), experienceYears, linesOfCodePerDay);
    }
}
