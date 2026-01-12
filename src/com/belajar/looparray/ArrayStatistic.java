package com.belajar.looparray;

import java.util.Scanner;

public class ArrayStatistic {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("════════ ARRAY STATISTIC ════════");
        System.out.println("Enter a size of array: ");
        byte arraySize = scanner.nextByte();

        byte[] values = new byte[arraySize];

        for (byte i = 0; i < arraySize; i++){
            System.out.println("Enter a value "+(i+1)+": ");
            byte newNumber = scanner.nextByte();

            values[i] = newNumber;
        }

        // calculate sum
        int sum = 0;
        for(int value: values){
            sum += value;
        }

        // calculate average
        float average = (float) sum / arraySize;

        // calculate min value
        int minValue = values[0];
        for(int value: values){
            if(value<minValue){
                minValue = value;
            }
        }

        // calculate max value
        int maxValue = values[0];
        for(int value: values){
            if(value > maxValue){
                maxValue = value;
            }
        }

        // calculate of even and odd
        int countEven = 0;
        int countOdd = 0;
        for(int value: values){
            if(value % 2 == 0){
                countEven++;
            }else {
                countOdd++;
            }
        }

        System.out.println("\n════════ YOUR STATISTIC ════════");
        System.out.println("SUM : "+sum);
        System.out.println("AVERAGE: "+average);
        System.out.println("MIN: "+minValue);
        System.out.println("MAX: "+maxValue);
        System.out.println("EVEN: "+countEven);
        System.out.println("ODD: "+countOdd);
        System.out.println("══════════════════════════════════");
    }
}
