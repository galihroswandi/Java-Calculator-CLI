package com.belajar.exception_file_i_o.practice.agevalidator;

public class InvalidAgeException extends Exception {
    private int age;

    public InvalidAgeException(int age) {
        super("Invalid age: " + age);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
