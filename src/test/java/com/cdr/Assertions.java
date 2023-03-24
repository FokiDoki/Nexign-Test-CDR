package com.cdr;

public class Assertions {
public static void assertEquals(Object expected, Object actual, String message) {
        if (expected.equals(actual)) {
            System.out.println("Test passed: " + message);
        } else {
            System.out.println("Test failed: " + message);
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            throw new AssertionError();
        }
    }
}
