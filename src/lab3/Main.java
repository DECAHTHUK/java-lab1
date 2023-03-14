package lab3;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Please enter your initials in format:
                Surname Name Patronymic dd.mm.yyyy
                """);
        Scanner scanner = new Scanner(System.in);
        String surname = scanner.next();
        String name = scanner.next();
        String patronymic = scanner.next();
        String[] date = scanner.nextLine().trim().split("\\.");

        LocalDate now = LocalDate.now();
        LocalDate birth = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]));
        int age = Period.between(birth, now).getYears();

        char gender = calculateGender(patronymic, surname);

        System.out.printf("%s %c.%c. %c %d %s\n", surname, name.charAt(0), patronymic.charAt(0),
                gender, age, findEnding(age));
    }

    public static char calculateGender(String patronymic, String surname) {
        String ending = patronymic.substring(patronymic.length() - 2);
        if (ending.equals("ич") || ending.equals("лы")) {
            return 'М';
        } else if (ending.equals("на") || ending.equals("зы")) {
            return 'Ж';
        } else {
            if (surname.charAt(surname.length() - 1) == 'а') {
                return 'Ж' ;
            } else {
                return 'М';
            }
        }
    }

    public static String findEnding(int age) {
        if (age == 1) {
            return "год";
        } else if (age >= 2 && age <= 4) {
            return "года";
        } else if (age >= 5 && age <= 20 || age == 0) {
            return "лет";
        } else {
            if (age % 10 == 0) {
                return "лет";
            } else if (age % 10 == 1) {
                return "год";
            } else if (age % 10 < 5) {
                return "года";
            } else {
                return "лет";
            }
        }
    }
}
