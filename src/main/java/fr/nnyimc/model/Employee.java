package fr.nnyimc.model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements Employable {

    protected String lastName;
    public String firstName;
    protected LocalDate dateOfBirth;
    protected double salary = 3000;

    NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

    private static final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})\\n";
    public static final Pattern pattern = Pattern.compile(peopleRegex);
    protected final  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    protected final Matcher matcher;

    protected Employee() {
       matcher  = null;
       firstName = "John";
       lastName = "Doe";
       dateOfBirth = null;
    }

    public Employee (String line) {
        matcher = pattern.matcher(line);
        if (matcher.find()) {
            this.lastName = matcher.group("lastName");
            this.firstName = matcher.group("firstName");
            this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(matcher.group("dob")));
        }
    }

    public static Employee createEmployee(String line) {
        Matcher matcher = Employee.pattern.matcher(line);
        if (matcher.find()) {
            return switch (matcher.group("role")) {
                case "Programmer" -> new Programmer(matcher.group());
                case "Manager" -> new Manager(matcher.group());
                case "Analyst" -> new Analyst(matcher.group());
                case "CEO" -> new CEO(matcher.group());
                default -> new FakeEmployee();
            };
        } else {
            // Consider throwing an exception instead
            return new FakeEmployee();
        }

    }

    @Override
    public abstract double getSalary();


    @Override
    public String toString() {
        return String.format("%s %s %s", lastName, firstName, currencyInstance.format(getSalary()) );
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (! (object instanceof Employee) )  {
            return false;
        }
        Employee employee = (Employee)  object;
        return lastName.equals(employee.lastName)
                //
               && firstName.equals(employee.firstName)
               //
               && dateOfBirth.equals(employee.dateOfBirth);


    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dateOfBirth);
    }

    private static final class FakeEmployee extends Employee implements Employable {

        @Override
        public double getSalary() {
            return 0;
        }
    }
}
