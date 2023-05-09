package fr.nnyimc;

import fr.nnyimc.model.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {linesOfCodePerDay=2000,experienceYears=6,iq=120}
            Alta, Pedra, 1/1/1990, Programmerzzzz, {linesOfCodePerDay=1200,experienceYears=7,iq=110}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=230}
            Chimera, Byron, 2/1/1905, Manager, {orgSize=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=10}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=350}
            """;


        Matcher matcher = Employee.pattern.matcher(people);
        int totalSalaries = 0;
        Employable employee = null;
        List<Employable> employees = new LinkedList<>();

        while (matcher.find()) {
            employee = Employee.createEmployee(matcher.group());
            employees.add(employee);
            if (employee != null  && employee instanceof Programmer)  {
                System.out.printf("%s %s %n", "IQ:",  ((Programmer) employee).getIq());
            } else if (employee != null && employee instanceof CEO) {
                System.out.printf("%s %s %n", "Hours flown:",  ((CEO) employee).getHoursFlown());
            } else if (employee != null && employee instanceof Analyst) {
                System.out.printf("%s %s %n", "Project count:",  ((Analyst) employee).getProjectCount());
            } else if (employee != null && employee instanceof Manager) {
                System.out.printf("%s %s %n", "Organization size:", (((Manager) employee).getOrganizationSize()));
            }
            totalSalaries += employee.getSalary();
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be: %s%n", currencyInstance.format(totalSalaries));

        List<String> firedStaff = new ArrayList<>();
        firedStaff.add("Pedra");
        firedStaff.add("Byron");


        for (Iterator<Employable> iterator = employees.iterator(); iterator.hasNext();) {
            Employable employable = iterator.next();
            if (employable instanceof Employee) {
                Employee worker = (Employee) employable;
                if (firedStaff.contains(worker.firstName)) {
                    iterator.remove();
                }
            }
        }



        Flyable flyer = new CEO("");
        flyer.fly();

        Programmer coder = new Programmer("");
        coder.cook("hamburgers");
        coder.scream("sauté that ");
    }
}