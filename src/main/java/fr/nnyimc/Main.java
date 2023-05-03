package fr.nnyimc;

import fr.nnyimc.model.*;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {linesOfCodePerDay=2000,experienceYears=6,iq=120}
            Alta, Pedra, 1/1/1990, Programmer, {linesOfCodePerDay=1200,experienceYears=7,iq=110}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=230}
            Chimera, Byron, 2/1/1905, Manager, {orgSize=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=10}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=350}
            """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)\\}\\n";
        Pattern pattern = Pattern.compile(peopleRegex);
        Matcher matcher = pattern.matcher(people);


        int totalSalaries = 0;
        Employee employee = null;

        while (matcher.find()) {
            employee = Employee.createEmployee(matcher.group());
            if (employee != null) {
                System.out.println(employee);
                totalSalaries += employee.getSalary();
            }
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be: %s%n", currencyInstance.format(totalSalaries));

    }
}