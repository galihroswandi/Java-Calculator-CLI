package com.belajar.collectionframework.practice.uniquestudentid;

import java.util.*;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> students = new HashMap<>();

        System.out.println("\n╔═════════════════════════╗");
        System.out.println("║ UNIQUE STUDENT ID       ║");
        System.out.println("╚═════════════════════════╝");
        System.out.print("Enter multiple student with coma(,): ");
        ArrayList<String> inputStudent = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));

        HashSet<String> filtered = new HashSet<>(inputStudent);

        ArrayList<String> studentsIDs = new ArrayList<>();
        for(String filter: filtered){
            if(!filter.isEmpty()){
                studentsIDs.add(filter);
            }
        }

        System.out.println(studentsIDs.toString());
    }
}
