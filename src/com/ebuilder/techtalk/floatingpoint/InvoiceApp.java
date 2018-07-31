package com.ebuilder.techtalk.floatingpoint;

import java.util.Scanner;
import java.text.NumberFormat;

public class InvoiceApp {
    public static void main(String[] args) {
        // create a Scanner object and start while loop
        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            // get the input from the user
            System.out.print("Enter subtotal: ");
            double subtotal = sc.nextDouble();
            // calculate the results
            double discountPercent = 0.0;
            if (subtotal >= 100) {
                discountPercent = .1;
            } else {
                discountPercent = 0.0;
            }
            double discountAmount = subtotal * discountPercent;
            double totalBeforeTax = subtotal - discountAmount;
            double salesTax = totalBeforeTax * .05;
            double total = totalBeforeTax + salesTax;
            // format and display the results
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat percent = NumberFormat.getPercentInstance();
            String message =
                      "Discount percent = " + percent.format(discountPercent) + "\n"
                    + "Discount amount  = " + currency.format(discountAmount) + "\n"
                    + "Total before tax = " + currency.format(totalBeforeTax) + "\n"
                    + "Sales tax        = " + currency.format(salesTax) + "\n"
                    + "Invoice total    = " + currency.format(total) + "\n";

            System.out.println(message);
            // see if the user wants to continue
            System.out.print("Continue? (y/n): ");
            choice = sc.next();
            System.out.println();
        }
    }
}