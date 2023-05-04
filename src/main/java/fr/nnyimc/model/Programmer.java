package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee implements Cook {

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

    public double getLinesOfCodePerDay() {
        return linesOfCodePerDay;
    }

    public void setLinesOfCodePerDay(double linesOfCodePerDay) {
        this.linesOfCodePerDay = linesOfCodePerDay;
    }

    public double getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(double experienceYears) {
        this.experienceYears = experienceYears;
    }

    public double getIq() {
        return iq;
    }

    public void setIq(double iq) {
        this.iq = iq;
    }

    @Override
    public double getSalary() {
            return this.salary += salary/(linesOfCodePerDay+(iq/experienceYears));
    }

    @Override
    public String toString() {
        return String.format(" %s %s %s %n",  super.toString(), experienceYears, linesOfCodePerDay);
    }
}
