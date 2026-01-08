package com.belajar.java;


import java.io.FileWriter;
import java.io.IOException;

public class SimpleWrite {
    static void main() {
       try {
           FileWriter writer = new FileWriter("output.txt", true);
           writer.write("Baris ketiga\n");

           writer.close();
       }catch (IOException e){
           System.err.println("Error writing file: "+e.getMessage());
       }
    }
}
