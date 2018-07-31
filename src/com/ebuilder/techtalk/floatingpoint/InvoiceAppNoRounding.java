package com.ebuilder.techtalk.floatingpoint;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Indika Liyanage
 * Date: Mar 27, 2012
 * Time: 6:27:55 PM
 */
public class InvoiceAppNoRounding {
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

            // debugging statements that display the unformatted fields
            // these are added before displaying the formatted results
            String debugMessage = "\nUNFORMATTED RESULTS\n"
            + "Discount percent = " + discountPercent + " [" + percent.format(discountPercent) + "]\n"
            + "Discount amount  = " + discountAmount + " [" + currency.format(discountAmount) + "]\n"
            + "Total before tax = " + totalBeforeTax + " [" + currency.format(totalBeforeTax) + "]\n"
            + "Sales tax        = " + salesTax + " [" + currency.format(salesTax) + "]\n"
            + "Invoice total    = " + total + " [" + currency.format(total) + "]\n";
            System.out.println(debugMessage);

            // see if the user wants to continue
            System.out.print("Continue? (y/n): ");
            choice = sc.next();
            System.out.println();
        }
    }
}
