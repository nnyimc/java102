package fr.nnyimc;

import fr.nnyimc.model.Programmer;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String people = """
            Flinstone, Fred, 1/1/1900, Programmer, {linesOfCodePerDay=2000,experienceYears=10,iq=120}
            Alta, Pedra, 1/1/1990, Programmer, {linesOfCodePerDay=1200,experienceYears=7,iq=110}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=230}
            Chimera, Byron, 2/1/1905, Manager, {orgSize=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=10}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=350}
            """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)\\}\\n";
        Pattern pattern = Pattern.compile(peopleRegex);
        Matcher matcher = pattern.matcher(people);

        String programmerRegex = "\\w+=(?<linesOfCodePerDay>\\w+),\\w+=(?<experienceYears>\\w+),\\w+=(?<iq>\\w+)";
        Pattern programmerPattern = Pattern.compile(programmerRegex);

        String managerRegex = "\\w+\\=(?<orgSize>\\d+)";
        Pattern managerPattern = Pattern.compile(managerRegex);

        String analystRegex = "\\w+\\=(?<projectCount>\\d+)";
        Pattern analystPattern = Pattern.compile(analystRegex);

        String ceoRegex = "\\w+\\=(?<avgStockPrice>\\d+)";
        Pattern ceoPattern = Pattern.compile(ceoRegex);

        


        int totalSalaries = 0;
        while (matcher.find()) {
            System.out.printf("%s %s %s %s %s %n", matcher.group("firstName"), matcher.group("lastName"), matcher.group("dob"), matcher.group("role"),  matcher.group("details"));
            float salary = 3000f;
            totalSalaries += switch (matcher.group("role")) {
                case "Programmer" -> {
                    Programmer programmer = new Programmer(matcher.group());
                    yield programmer.getSalary();
                }
                case "Manager" -> {
                    Matcher managerMatcher = managerPattern.matcher(matcher.group("details"));
                    if (managerMatcher.find()) {
                        int organizationSize = Integer.parseInt(managerMatcher.group("orgSize"));
                        System.out.printf("Manager details : %n organization size: %s%n", organizationSize);
                        salary += Math.pow(salary/organizationSize,2);
                    }
                    yield salary;
                }
                case "Analyst" -> {
                    Matcher analystMatcher = analystPattern.matcher(matcher.group("details"));
                    if (analystMatcher.find()) {
                        int projectCount = Integer.parseInt(analystMatcher.group("projectCount"));
                        salary += Math.pow(salary/projectCount,2.25);
                    }
                    yield salary;
                }
                case "CEO" -> {
                    Matcher ceoMatcher = ceoPattern.matcher( matcher.group("details"));
                    if (ceoMatcher.find()) {
                        float avgStockPrice = Float.parseFloat(ceoMatcher.group("avgStockPrice"));
                        salary += Math.pow(salary/avgStockPrice, 3);
                    }
                    yield 5000;
                }
                default -> 0;
            };
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be: %s%n", currencyInstance.format(totalSalaries));

    }
}