package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static User inputUser(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the firstName: ");
        String firstName = scanner.next();
        System.out.println("Enter the lastName: ");
        String lastName = scanner.next();
        System.out.println("Enter the age: ");
        int age = scanner.nextInt();
        System.out.println("Enter the country: ");
        String country = scanner.next();
        return new User(id, firstName, lastName, age, country);
    }

    public static void printAllSortedByLastname(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        Stream<User> stream = users.stream();
        stream.sorted(Comparator.comparing(User::getLastName)).forEach(System.out::println);
    }

    public static void printAllSortedByAge(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        Stream<User> stream = users.stream();
        stream.sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);
    }

    public static boolean checkAge(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users.");
            return true;
        }
        return users.stream().filter(user -> user.isOlder(7)).count() == users.size();
    }

    public static double averageAge(List<User> users) {
        OptionalDouble average = users.stream().mapToInt(User::getAge).average();
        if (average.isPresent()) {
            return average.getAsDouble();
        }
        System.out.println("No users.");
        return 0;
    }

    public static void printNumberOfCountries(List<User> users) {
        System.out.println(users.stream().collect(Collectors.groupingBy(User::getCountry, Collectors.counting())).size());
    }

    public static void printAll(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        users.forEach(System.out::println);
    }

    public static void menu() {
        System.out.println("Menu:");
        System.out.println("1. Add user.");
        System.out.println("2. Remove user.");
        System.out.println("3. Print all users sorted by lastname.");
        System.out.println("4. Print all users sorted by age.");
        System.out.println("5. Check if all users are over 7 years old.");
        System.out.println("6. Calculate average age.");
        System.out.println("7. Output the number of different countries.");
        System.out.println("8. Print all users.");
        System.out.println("9. Finish program.");
        System.out.println("Input number of command: ");
    }

    public static User selectUser(List<User> users) {
        printAll(users);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select student (enter id): ");
        int number = scanner.nextInt();
        for (User user : users) {
            if (user.getId() == number) {
                return user;
            }
        }
        System.out.println("Wrong id.");
        return null;
    }

    public static void main(String[] args) {
        boolean flag = true;
        List<User> users = new ArrayList<>();
        int lastID = 0;
        Scanner scanner = new Scanner(System.in);
        int command;
        User user;
        while (flag) {
            menu();
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    user = inputUser(++lastID);
                    users.add(user);
                    break;
                case 2:
                    user = selectUser(users);
                    users.remove(user);
                    break;
                case 3:
                    printAllSortedByLastname(users);
                    break;
                case 4:
                    printAllSortedByAge(users);
                    break;
                case 5:
                    System.out.println(checkAge(users));
                    break;
                case 6:
                    System.out.println(averageAge(users));
                    break;
                case 7:
                    printNumberOfCountries(users);
                    break;
                case 8:
                    printAll(users);
                    break;
                case 9:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong command, try again.");
            }
        }
    }
}