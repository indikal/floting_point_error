package com.ebuilder.techtalk.floatingpoint;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.ebuilder.techtalk.floatingpoint.hibernate.InvoiceApp;


/**
 * Created by IntelliJ IDEA.
 * User: Indika Liyanage
 * Date: Mar 29, 2012
 * Time: 12:47:14 PM
 */
public class InvoiceAppHibernate {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void storeInvoice(String subtotal) {
        try {
            InvoiceApp invoice = null;

            // convert subtotal and discount percent to BigDecimal
            BigDecimal decimalSubtotal = new BigDecimal(subtotal);
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

            invoice = new InvoiceApp(decimalSubtotal, discountAmount, salesTax, total);

            Session session = getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void removeInvoice(Long invoiceID) {
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();
        InvoiceApp invoice = new InvoiceApp();
        invoice.setInvoiceId(invoiceID);
        session.delete(invoice);
        session.getTransaction().commit();
    }

    public List listInvoices() {
        List result = null;
        try {
            Session session = getSessionFactory().getCurrentSession();
            session.beginTransaction();
            result = session.createQuery("from InvoiceApp").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return result;
    }

    public InvoiceApp getInvoice(Long invoiceId) {
        InvoiceApp invoice = null;
        try {
            Session session = getSessionFactory().getCurrentSession();
            session.beginTransaction();

            invoice = (InvoiceApp) session.load(InvoiceApp.class, invoiceId);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return invoice;
    }

    public static void main(String[] args) {
        InvoiceAppHibernate app = new InvoiceAppHibernate();
        Scanner sc = new Scanner(System.in);
        String choice = "L";
        while (!choice.equalsIgnoreCase("E")) {
            // get the input from the user
            System.out.println("[C] Create new | [L] List | [V] View | [D] Delete | [E] Exit");
            //read choice
            System.out.print("Enter choice: ");
            choice = sc.next();

            if (choice.equalsIgnoreCase("C")) {
                System.out.print("Enter subtotal: ");
                String subtotal = sc.next();
                try {
                    if (Double.valueOf(subtotal).doubleValue() > 0) {
                        app.storeInvoice(subtotal);
                        System.out.println("Successfully saved invoice!\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Data entered is not a valid amount!\n");
                }
            } else if (choice.equalsIgnoreCase("L")) {
                List list = app.listInvoices();
                if (null != list && list.size() > 0) {
                    System.out.println("Inv ID, Subtotal, Discount, Sales tax, Total");
                    for (Object aList : list) {
                        InvoiceApp invoice = (InvoiceApp) aList;
                        System.out.println(invoice.getInvoiceId() + ", " + invoice.getSubTotal() + ", " + invoice.getDiscount() + ", " + invoice.getSalesTax() + ", " + invoice.getTotal());
                    }
                } else {
                    System.out.println("No invoices found!");
                }
                System.out.println("");
            } else if (choice.equalsIgnoreCase("V")) {
                System.out.print("Enter Invoice ID: ");
                try {
                    Long invId = sc.nextLong();
                    InvoiceApp invoice = app.getInvoice(invId);
                    System.out.println("Inv ID    : " + invoice.getInvoiceId());
                    System.out.println("Subtotal  : " + invoice.getSubTotal());
                    System.out.println("Discount  : " + invoice.getDiscount());
                    System.out.println("Sales tax : " + invoice.getSalesTax());
                    System.out.println("Total     : " + invoice.getTotal());
                } catch (Exception e) {
                    sc.next();
                    System.out.println("Sorry! No invoice found for the given ID!");
                }
                System.out.println("");
            } else if (choice.equalsIgnoreCase("D")) {
                System.out.print("Enter Invoice ID: ");
                try {
                    Long invId = sc.nextLong();
                    app.removeInvoice(invId);
                    System.out.println("Invoice (ID: " + invId + ") removed successfully!\n");
                } catch (Exception e) {
                    sc.next();
                    System.out.println("Sorry! No invoice found for the given ID!");
                }
                System.out.println("");
            } else if (choice.equalsIgnoreCase("E")) {
                break;
            } else {
                System.out.println("Invalid choice!\n");
            }
        }
    }
}
