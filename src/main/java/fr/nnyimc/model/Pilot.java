package fr.nnyimc.model;

public class Pilot implements Flyable {
    private int hoursFlown;
    private  boolean instrumentFlightReader;

    @Override
    public void fly() {
        System.out.println("Prepare for takeoff");
    }

    public Pilot(int hoursFlown, boolean instrumentFlightReader) {
        this.hoursFlown = hoursFlown;
        this.instrumentFlightReader = instrumentFlightReader;
    }

    @Override
    public int getHoursFlown() {
        return hoursFlown;
    }

    @Override
    public void setHoursFlown(int hoursFlown) {
        this.hoursFlown = hoursFlown;
    }

    @Override
    public boolean isInstrumentFlightReader() {
        return instrumentFlightReader;
    }

    @Override
    public void setInstrumentFlightReader(boolean instrumentFlightReader) {
        this.instrumentFlightReader = instrumentFlightReader;
    }
}
