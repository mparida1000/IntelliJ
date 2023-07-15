package com.scaler.queue;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Problem Description
Given an integer A, you have to find the Ath Perfect Number.

A Perfect Number has the following properties:

It comprises only 1 and 2.

The number of digits in a Perfect number is even.

It is a palindrome number.

For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.
 */
public class PerfectNumber {
    public static String findAthPerfectNumber(int A) {
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");
        queue.offer("2");
        while (!queue.isEmpty()) {
            String num = queue.poll();
            if (num.length() % 2 == 0 && num.equals(new StringBuilder(num).reverse().toString())) {
                    count++;
                    if (count == A) {
                        return num;
                    }
            }
            if (num.length() < 2 * A) {
                queue.offer(num + "1");
                queue.offer(num + "2");
            }
        }
        return "-1";
    }
    public static void main(String[] args) {
        int A = 10000;
        String athPerfectNumber = findAthPerfectNumber(A);
        System.out.println("The " + A + "th Perfect Number is: " + athPerfectNumber);
    }
}
