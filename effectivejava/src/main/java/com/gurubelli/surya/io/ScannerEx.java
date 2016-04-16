package com.gurubelli.surya.io;


import java.util.Scanner;

public class ScannerEx {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a number:\n");
        int number = scanner.nextInt();

        int result = 1;


        for(int p=1; p <=number ;p++)  {
            result= result*p;

        }
        System.out.println("Factorial of a number is " + result);
    }


}