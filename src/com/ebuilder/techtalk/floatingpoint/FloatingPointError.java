package com.ebuilder.techtalk.floatingpoint;

/**
 * Created by IntelliJ IDEA.
 * User: Indika Liyanage
 * Date: Mar 27, 2012
 * Time: 6:29:57 PM
 */
public class FloatingPointError {
    public static void main(String[] args) {
        double sum = 0;
        for (int i=1; i <= 10000; i++) {
            sum = sum + 0.0001;
        }
        System.out.println("Total: " + sum);

        FloatingPointError.equalityError();
        FloatingPointError.subtractingAlmostEqualValues();
    }

    public static void equalityError() {
        double item1 = 69.82;
        double item2 = 69.20 + 0.62;

        System.out.println("Item 1 (69.82): " + item1);
        System.out.println("Item 2 (69.20 + 0.62): " + item2);
        
        if (item1 == item2) {
            System.out.println("Equality!");
        }

        if (item1 < 69.83 && item1 > 69.81) {
            System.out.println("Equal");
        }
    }

    public static void subtractingAlmostEqualValues() {
        System.out.println(0.1235 - 0.1234 + "");
    }
}
