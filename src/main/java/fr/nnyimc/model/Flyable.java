package fr.nnyimc.model;

public interface Flyable {
    void fly();

    int getHoursFlown();

    void setHoursFlown(int hoursFlown);

    boolean isInstrumentFlightReader();

    void setInstrumentFlightReader(boolean instrumentFlightReader);
}
