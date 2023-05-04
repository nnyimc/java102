package fr.nnyimc.model;

public interface Cook {

    default void cook(String meal) {
        System.out.printf("%s %s %n", "I'm cooking", meal);
    }

    default String cleanUp() {
        return "I'm done cleaning up";
    }

    default void scream(String orders) {
        System.out.printf("%s %s %n", "Hey", orders + "!!!1");
    }
}
