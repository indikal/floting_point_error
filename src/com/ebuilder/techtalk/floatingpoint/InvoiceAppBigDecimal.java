package com.ebuilder.techtalk.floatingpoint;

import java.text.NumberFormat;
import java.util.Scanner;
import java.math.*; // imports all classes and enumerations in java.math

/**
 * Created by IntelliJ IDEA.
 * User: Indika Liyanage
 * Date: Mar 28, 2012
 * Time: 1:00:28 PM
 */
public class InvoiceAppBigDecimal {
        public static void main(String[] args) {
        // create a Scanner object and start while loop
        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            // get the input from the user
            System.out.print("Enter subtotal: ");
            double subtotal = sc.nextDouble();

            // calculate the results

            // convert subtotal and discount percent to BigDecimal
            BigDecimal decimalSubtotal = new BigDecimal(Double.toString(subtotal));
            decimalSubtotal = decimalSubtotal.setScale(2, RoundingMode.HALF_UP);

            BigDecimal decimalDiscountPercent;
            if (decimalSubtotal.compareTo(new BigDecimal("100.00")) >= 0) {
                decimalDiscountPercent = new BigDecimal("0.1");
            } else {
                decimalDiscountPercent = new BigDecimal("0.0");
            }
            // calculate discount amount
            BigDecimal discountAmount = decimalSubtotal.multiply(decimalDiscountPercent);
            discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);

            // calculate total before tax, sales tax, and total
            BigDecimal totalBeforeTax = decimalSubtotal.subtract(discountAmount);
            BigDecimal salesTaxPercent = new BigDecimal(".05");
            BigDecimal salesTax = salesTaxPercent.multiply(totalBeforeTax);
            salesTax = salesTax.setScale(2, RoundingMode.HALF_UP);
            
            BigDecimal total = totalBeforeTax.add(salesTax);

            String message =
                      "Discount percent = " + decimalDiscountPercent + "\n"
                    + "Discount amount  = " + discountAmount + "\n"
                    + "Total before tax = " + totalBeforeTax + "\n"
                    + "Sales tax        = " + salesTax + "\n"
                    + "Invoice total    = " + total + "\n";

            System.out.println(message);
            // see if the user wants to continue
            System.out.print("Continue? (y/n): ");
            choice = sc.next();
            System.out.println();
        }
    }
}
