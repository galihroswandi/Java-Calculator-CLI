package com.belajar.exception_file_i_o.learning.handlecsv;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Person {
    String name;
    int age;
    String city;
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path CSV_FILE = DATA_DIR.resolve("person.csv");

    public static void main(String[] args) {
        // // Create to CSV
        // ArrayList<Person> persons = new ArrayList<>();
        // persons.add(new Person("John", 25, "NYC"));
        // persons.add(new Person("Jane", 30, "London"));

        // try (PrintWriter writer = new PrintWriter(CSV_FILE.toFile())) {
        // writer.println("Name,Age,City");
        // for (Person p : persons) {
        // writer.println(p.toCsv());
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // // Read from CSV
        // ArrayList<Person> personLoad = new ArrayList<>();
        // try (BufferedReader reader = new BufferedReader(new
        // FileReader("person.csv"))) {
        // reader.readLine(); // lewati header

        // String line;

        // while ((line = reader.readLine()) != null) {
        // Person p = Person.fromCsv(line);
        // personLoad.add(p);
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // for (Person p : personLoad) {
        // System.out.println("Name: " + p.name);
        // System.out.println("Age: " + p.name);
        // System.out.println("City: " + p.city);
        // System.out.println("---");
        // }
    }

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // Convert to CSV format
    public String toCsv() {
        return name + "," + age + "," + city;
    }

    // Create from line CSV
    public static Person fromCsv(String csvLine) {
        String[] bagian = csvLine.split(",");
        return new Person(bagian[0], Integer.parseInt(bagian[1]), bagian[2]);
    }
}
