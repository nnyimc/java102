package fr.nnyimc.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements Flyable {


    private double avgStockPrice;

    private final String ceoRegex = "\\w+\\=(?<avgStockPrice>\\d+)";
    private final Pattern ceoPattern = Pattern.compile(ceoRegex);

    private Pilot pilot = new Pilot(1000, true);

    public CEO(String line) {
        super(line);
        Matcher ceoMatcher= ceoPattern.matcher(line);
        if (ceoMatcher.find()) {
            this.avgStockPrice = Double.parseDouble(ceoMatcher.group("avgStockPrice"));
        }
    }

    @Override
    public double getSalary() {
        return this.salary += Math.pow(salary/avgStockPrice, 3);
    }

    public void fly() {
        pilot.fly();
    }

    public int getHoursFlown() {
        return pilot.getHoursFlown();
    }

    public void setHoursFlown(int hoursFlown) {
        pilot.setHoursFlown(hoursFlown);
    }

    public boolean isInstrumentFlightReader() {
        return pilot.isInstrumentFlightReader();
    }

    public void setInstrumentFlightReader(boolean instrumentFlightReader) {
        pilot.setInstrumentFlightReader(instrumentFlightReader);
    }

    @Override
    public String toString() {
        return String.format("%s %s %n", super.toString(), avgStockPrice);
    }
}
