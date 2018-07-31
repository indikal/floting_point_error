package com.ebuilder.techtalk.floatingpoint.hibernate;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Indika Liyanage
 * Date: Mar 29, 2012
 * Time: 12:28:39 PM
 */
public class InvoiceApp {
    private Long invoiceId;
    private BigDecimal subTotal;
    private BigDecimal discount;
    private BigDecimal salesTax;
    private BigDecimal total;

    public InvoiceApp() {
    }

    public InvoiceApp(Long invoiceId, BigDecimal subTotal, BigDecimal discount, BigDecimal salesTax, BigDecimal total) {
        this.invoiceId = invoiceId;
        this.subTotal = subTotal;
        this.discount = discount;
        this.salesTax = salesTax;
        this.total = total;
    }

    public InvoiceApp(BigDecimal subTotal, BigDecimal discount, BigDecimal salesTax, BigDecimal total) {
        this.subTotal = subTotal;
        this.discount = discount;
        this.salesTax = salesTax;
        this.total = total;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
