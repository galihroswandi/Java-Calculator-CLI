package com.belajar.looparray;

import java.util.Scanner;

public class StudentGradeSystem {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("══════ STUDENT GRADE SYSTEM ══════");
        System.out.println("Enter amount student: ");
        byte amountStudent = scanner.nextByte();

        String[] students = new String[amountStudent];
        int[] marks = new int[amountStudent];

        for(int i = 0; i < amountStudent; i++){
            System.out.println("Enter name and value (format: name,value): ");
            String inputFromUser = scanner.next();

            String[] nameAndValue = inputFromUser.split(",");
            if(nameAndValue.length <2){
                System.err.println("Incorrect Format: Use comma (,)");
                amountStudent++;
            }

            int valueStudent = Integer.parseInt(nameAndValue[1]);

            students[i] = nameAndValue[0];
            marks[i] = valueStudent;
        }

        // calculate max value
        int marksMax = marks[0];
        int indexMaxMark = 0;
        for(int i = 0; i < marks.length; i++){
            if(marks[i] > marksMax){
                marksMax = marks[i];
                indexMaxMark = i;
            }
        }

        // calculate min value
        int marksMin = marks[0];
        int indexMinMark = 0;
        for(int i = 0; i < marks.length; i++){
            if(marks[i] < marksMin){
                marksMin = marks[i];
                indexMinMark = i;
            }
        }

        // calculate average
        int sum = 0;
        for(int mark: marks){
            sum += mark;
        }
        float average = (float) sum / amountStudent;

        // total student graduated
        int totalGraduated = 0;
        for(int mark: marks){
            if(mark >= 70){
                totalGraduated++;
            }
        }


        System.out.println("\n═══════════ STUDENTS ═══════════");
        System.out.println("List of student: ");
        for (int i = 0; i < amountStudent; i++){
            String displayStudent = String.format("%d. %s nilai: %d", (i+1), students[i], marks[i]);
            System.out.println(displayStudent);
        }

        System.out.printf("\n%s with the highest score: %d\n", students[indexMaxMark], marks[indexMaxMark]);
        System.out.printf("%s with the lowest score: %d\n", students[indexMinMark], marks[indexMinMark]);
        System.out.printf("Average grade in class: %f\n", average);
        System.out.println("Total Graduated: "+totalGraduated);

        System.out.println("════════════════════════════════");
    }
}
