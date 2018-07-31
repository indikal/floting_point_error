
-   **Why does my floating point calculation are not accurate sometimes?**

-   **Rounding floating point numbers some times give visible calculation errors**

-   **How numbers represent in computers?**

-   **General floating point concept and common errors**

-   **Does Java has a solution for this?**

-   **How we handle this with Hibernate? **

**Let's take a simple arithmetic problem**

You'll be given a sub-total of an invoice and should calculate the total
value of the invoice with following conditions:

-   If the sub-total is above or equal \$100 the invoice will get a 10%
    discount

-   Invoice will have 5% sales tax on the discounted amount

Let's take few sample values:

---------------------------- -----------------------
***Invoice sub total***      *\$150.50*
***Discount***               *\$150.50 x 10%*
                             ***\$15.05***
***Total after discount***   *\$150.50 - \$15.05*
                             ***\$135.45***
***Sales tax***              *\$135.45 x 5%*
                             ***\$6.7725***
***Total***                  *\$135.45 + \$6.7725*
                             *\$142.2225*
                             ***\$142.22***
---------------------------- -----------------------

  ---------------------------- ------------------------
  ***Invoice sub total***      *\$100.05*
  ***Discount***               *\$100.05 x 10%*
                               ***\$10.005***
  ***Total after discount***   *\$100.05 - \$10.005*
                               ***\$90.045***
  ***Sales tax***              *\$90.045 x 5%*
                               ***\$4.50225***
  ***Total***                  *\$90.045 + \$4.50225*
                               *\$94.54725*
                               ***\$94.55***
  ---------------------------- ------------------------

**Let's write a simple Java application (Invoice Application) to salve
this problem**

*// create a Scanner object and start while loop*

*Scanner sc = new Scanner(System.in);*

*String choice = "y";*

*while (choice.equalsIgnoreCase("y")) {*

*// get the input from the user*

*System.out.print("Enter subtotal: ");*

*double subtotal = sc.nextDouble();*

*// calculate the results*

*double discountPercent = 0.0;*

*if (subtotal &gt;= 100) {*

*discountPercent = .1;*

*} else {*

*discountPercent = 0.0;*

*}*

*double discountAmount = subtotal \* discountPercent;*

*double totalBeforeTax = subtotal - discountAmount;*

*double salesTax = totalBeforeTax \* .05;*

*double total = totalBeforeTax + salesTax;*

*// format and display the results*

*NumberFormat currency =*

*NumberFormat.getCurrencyInstance();*

*NumberFormat percent = NumberFormat.getPercentInstance();*

*String message = *

*"Discount percent = " +*

*percent.format(discountPercent) + "\\n"*

*+ "Discount amount = " +*

*currency.format(discountAmount) + "\\n"*

*+ "Total before tax = " + *

*currency.format(totalBeforeTax) + "\\n"*

*+ "Sales tax = " + *

*currency.format(salesTax) + "\\n"*

*+ "Invoice total = " + *

*currency.format(total) + "\\n";*

*System.out.println(message);*

*// see if the user wants to continue*

*System.out.print("Continue? (y/n): ");*

*choice = sc.next();*

*System.out.println();*

*}*

**The console for the formatted Invoice application**

*Enter subtotal: 150.50*

*Discount percent = 10%*

*Discount amount = \$15.05*

*Total before tax = \$135.45*

*Sales tax = \$6.77*

*Invoice total = \$142.22*

**The math problems in the Invoice application**

Let's take few more sample values:

*Enter subtotal: 100.05*

*Discount percent = 10%*

*Discount amount = \$10.00*

*Total before tax = **\$90.04***

*Sales tax = \$4.50*

*Invoice total = **\$94.55***

Wait! The results for a subtotal entry of 100.05 don’t add up. If the
discount amount is \$10.00, the total before tax should be \$90.05, but
it’s \$90.04.

*Enter subtotal: .70*

*Discount percent = 0%*

*Discount amount = \$0.00*

*Total before tax = \$0.70*

*Sales tax = **\$0.03***

*Invoice total = **\$0.74***

Similarly, the sales tax for a subtotal entry of .70 is shown as \$0.03,
so the invoice total should be \$0.73, but it’s shown as is \$0.74.

**What’s going on?**

If you look at the unformatted results for the first entry (100.05), you
can easily see what’s going on.

Because of the way NumberFormat rounding works, the discount amount
value of 10.005 and the total before tax value of 90.045 aren’t rounded
up.

However, the invoice total value of 94.54725 is rounded up.

With this extra information, you know that everything is working the way
it’s supposed to, even though you’re not getting the results you want.

*Enter subtotal: 100.05*

*UNFORMATTED RESULTS*

*Discount percent = 0.1*

*Discount amount = 10.005*

*Total before tax = 90.045*

*Sales tax = 4.50225*

*Invoice total = 94.54725*

*Continue? (y/n): y*

*Enter subtotal: .70*

*UNFORMATTED RESULTS*

*Discount percent = 0.0*

*Discount amount = 0.0*

*Total before tax = 0.7*

*Sales tax = 0.034999999999999996*

*Invoice total = 0.735*

*Continue? (y/n): n*

**Decimal and Binary Number Systems**

-   Normally, we count things in base 10.

-   The base is completely arbitrary. The only reason that people have
    traditionally used base 10 is that they have 10 fingers, which have
    made handy counting tools.

The number 532.25 in decimal (base 10) means:

(5 \* 10\^2) + (3 \* 10\^1) + (2 \* 10\^0) + (2 \* 10\^-1) + (5 \*
10\^-2)

*500 +* 30 + 2 + 2/10 + 5/100

= 532.25

-   In the binary number system (base 2), each column represents a power
    of 2 instead of 10.

For example, the number 101.01 means the following:

(1 \* 2\^2) + (0 \* 2\^1) + (1 \* 2\^0) + (0 \* 2\^-1) + (1 \* 2\^-2)

*4 +* 0 + 1 + 0 + 1/4

= 5.25 Decimal

**How Integers Are Represented in PCs**

Because there is no fractional part to an integer, its machine
representation is much simpler than it is for floating-point values.
Normal integers on personal computers (PCs) are 2 bytes (16 bits) long
with the most significant bit indicating the sign. Long integers are 4
bytes long. Positive values are straightforward binary numbers.

For example:

1 Decimal = 1 Binary

2 Decimal = 10 Binary

22 Decimal = 10110 Binary, etc.

However, negative integers are represented using the **two's
complement** scheme.

To get the two's complement representation for a negative number, take
the binary representation for the number's absolute value and then flip
all the bits and add 1.

For example:

4 Decimal = 0000 0000 0000 0100

1111 1111 1111 1011 Flip the Bits

-4 = 1111 1111 1111 1100 Add 1

**Floating-Point Complications**

Every decimal integer can be exactly represented by a binary integer;
however, this is not true for fractional numbers. In fact, every number
that is irrational in base 10 will also be irrational in any system with
a base smaller than 10.

For binary, in particular, only fractional numbers that can be
represented in the form p/q, where q is an integer power of 2, can be
expressed exactly, with a finite number of bits.

Even common decimal fractions, such as decimal 0.0001, cannot be
represented exactly in binary. (0.0001 is a repeating binary fraction
with a period of 104 bits!)

Simple example:

*double sum = 0;*

*for (int i=1; i &lt;= 10000; i++) {*

*sum = sum + 0.0001;*

*}*

*System.out.println("Total: " + sum);*

For the same reason, you should always be very cautious when making
comparisons on real numbers. The following example illustrates a common
programming error:

*double item1 = 69.82;*

*double item2 = 69.20 + 0.62;*

*if (item1 == item2) {*

*System.out.println("Equality!");*

*}*

This will NOT PRINT "Equality!" because 69.82 cannot be represented
exactly in binary.

In practice, you should always code such comparisons in such a way as to
allow for some tolerance.

*if (item1 &lt; 69.83 && item1 &gt; 69.81) {*

*System.out.println("Equal");*

*}*

**General Floating-Point Concepts**

-   It is very important to realize that any binary floating-point
    system can represent only a finite number of floating-point values
    in exact form.

-   All other values must be approximated by the closest representable
    value.

-   The IEEE standard (IEEE 754) specifies the method for rounding
    values to the "closest" representable value.

**Other Common Floating-Point Errors**

-   **Round-off error**: This error results when all of the bits in a
    binary number cannot be used in a calculation.

<!-- -->

-   []{#__DdeLink__5_1153060037 .anchor}**Subtracting two almost equal
    values**: Decimal fractions, such as decimal 0.0001, cannot be
    represented exactly in binary

<!-- -->

-   **Overflow and underflow**: This occurs when the result is too large
    or too small to be represented by the data type.

<!-- -->

-   **Quantizing error**: This occurs with those numbers that cannot be
    represented in exact form by the floating-point standard.

<!-- -->

-   **Division by a very small number**: This can trigger a "divide by
    zero" error or can produce bad results

<!-- -->

-   **Output error**: This type of error occurs when the output
    functions alter the values they are working with.

**How to avoid Invoice error**

-   One solution is to write your own code that does the rounding so you
    don’t need to use the NumberFormat class to do the rounding for you.

Eg: In PA, when splitting the total invoice amount among account rows,
the difference of

account row sum and tatal invoice amount has been added to one of the
account rows.

-   However, that still doesn’t deal with the fact that some decimal
    fractions can’t be accurately represented by floating-point numbers.

Eg: Iraqi Scud missile to slip through Patriot missile defenses a year
ago and hit U.S. Army barracks in

Dhahran, Saudi Arabia, killing 28 servicemen.

-   To solve that problem as well as the other data problems, the best
    solution is to use the **BigDecimal** class.

**How to use the BigDecimal class**

The BigDecimal class is designed to solve two types of problems that are
associated with floating-point numbers.

-   the BigDecimal class can be used to exactly represent decimal
    numbers.

-   it can be used to work with numbers that have more than 16
    significant digits.

If you haven’t ever used this class, it’s one that you should master and
use for many business applications.

**Constructors of the BigDecimal class**

  -------------------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  **Constructor**      **Description**
  BigDecimal(int)      Creates a new BigDecimal object with the specified int value.
  BigDecimal(double)   Creates a new BigDecimal object with the specified double value.
  BigDecimal(long)     Creates a new BigDecimal object with the specified long value.
  BigDecimal(String)   Creates a new BigDecimal object with the specified String object. Because of the limitations of floating-point numbers, it’s often best to create BigDecimal objects from strings.
  -------------------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**Methods of the BigDecimal class most useful in business applications**

  ------------------------------------- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
  **Methods**                           **Description**
  add(value)                            Returns the value of this BigDecimal object after the specified BigDecimal value has been added to it.
  compareTo(value)                      Compares the value of the BigDecimal object with the value of the specified BigDecimal object and returns -1 if less, 0 if equal, and 1 if greater.
  divide(value, scale, rounding-mode)   Returns the value of this BigDecimal object divided by the value of the specified BigDecimal object, sets the specified scale, and uses the specified rounding mode.
  multiply(value)                       Returns the value of this BigDecimal object multiplied by the specified BigDecimal value.
  setScale(scale, rounding-mode)        Sets the scale and rounding mode for the BigDecimal object.
  subtract(value)                       Returns the value of this BigDecimal object after the specified BigDecimal value has been subtracted from it.
  toString()                            Converts the BigDecimal value to a string.
  ------------------------------------- ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

-   Because floating-point numbers are limited to 16 significant digits
    and does not always represent decimal numbers exactly, it’s often
    best to construct BigDecimal objects from strings rather than
    doubles.

-   Once you create a BigDecimal object, you can use its methods to work
    with the data.

-   setScale() method lets you set the number of decimal places (scale)
    for the value in a BigDecimal object as well as the rounding mode.

-   For example, you can use the setScale() method to return a number
    that’s rounded to two decimal places like this:

*salesTax = salesTax.setScale(2,*

*RoundingMode.HALF\_UP);*

**How to use BigDecimal arithmetic in the Invoice application**

*// get the input from the user*

*System.out.print("Enter subtotal: ");*

*double subtotal = sc.nextDouble();*

*// calculate the results*

*// convert subtotal and discount percent to BigDecimal*

*BigDecimal decimalSubtotal = *

*new BigDecimal(Double.toString(subtotal));*

*decimalSubtotal = *

*decimalSubtotal.setScale(2, RoundingMode.HALF\_UP);*

*BigDecimal decimalDiscountPercent;*

*if (decimalSubtotal.compareTo(*

*new BigDecimal("100.00")) &gt;= 0) {*

*decimalDiscountPercent = new BigDecimal("0.1");*

*} else {*

*decimalDiscountPercent = new BigDecimal("0.0");*

*}*

*// calculate discount amount*

*BigDecimal discountAmount =*

*decimalSubtotal.multiply(decimalDiscountPercent);*

*discountAmount = *

*discountAmount.setScale(2, RoundingMode.HALF\_UP);*

*// calculate total before tax, sales tax, and total*

*BigDecimal totalBeforeTax =*

*decimalSubtotal.subtract(discountAmount);*

*BigDecimal salesTaxPercent = new BigDecimal(".05");*

*BigDecimal salesTax =*

*salesTaxPercent.multiply(totalBeforeTax);*

*salesTax = salesTax.setScale(2, RoundingMode.HALF\_UP);*

*BigDecimal total = totalBeforeTax.add(salesTax);*

*String message =*

*"Discount percent = " + decimalDiscountPercent + "\\n"*

*+ "Discount amount = " + discountAmount + "\\n"*

*+ "Total before tax = " + totalBeforeTax + "\\n"*

*+ "Sales tax = " + salesTax + "\\n"*

*+ "Invoice total = " + total + "\\n";*

*System.out.println(message);*

*// see if the user wants to continue*

*System.out.print("Continue? (y/n): ");*

*choice = sc.next();*

*System.out.println();*

**Let's see the result for our sample inputs:**

*Enter subtotal: 150.50*

*Discount percent = 0.1*

*Discount amount = 15.05*

*Total before tax = 135.45*

*Sales tax = 6.77*

*Invoice total = 142.22*

*Continue? (y/n): y*

*Enter subtotal: 100.05*

*Discount percent = 0.1*

*Discount amount = 10.01*

*Total before tax = 90.04*

*Sales tax = 4.50*

*Invoice total = 94.54*

*Continue? (y/n): y*

*Enter subtotal: .70*

*Discount percent = 0.0*

*Discount amount = 0.00*

*Total before tax = 0.70*

*Sales tax = 0.04*

*Invoice total = 0.74*

**BigDecimal support in Hibernate**

With Hibernate it is possible to map your BigDecimal objects to a
database column.

This way you can write your BigDecimal Java objects directly to the DB
and read them back as BigDecimal values.

org.hibernate.type.BigDecimalType

Maps a java.math.BigDecimal to a JDBC NUMERIC

You can have a simple Hibernate mapping like this:

*&lt;property*

*name="total"*

*type="big\_decimal"*

*column="TOTAL"*

*&gt;*

**Let's see our invoice problem saved to the DB with Hibernate**

DB table:

***Column name Data type Nullable***

*INVOICE\_ID NUMBER No*

*SUBTOTAL NUMBER(10,2) Yes*

*DISCOUNT NUMBER(10,2) Yes*

*SALES\_TAX NUMBER(10,2) Yes*

*TOTAL NUMBER(10,2) Yes*

**Mapping file:**

*&lt;?xml version="1.0"?&gt;*

*&lt;!DOCTYPE hibernate-mapping PUBLIC*

*"-//Hibernate/Hibernate Mapping DTD 3.0//EN"*

*"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd" &gt;*

*&lt;hibernate-mapping&gt;*

*&lt;class *

*name="com.ebuilder.techtalk.floatingpoint.hibernate.InvoiceApp"*

*table="INVOICEAPP"&gt;*

*&lt;id*

*name="invoiceId"*

*type="long"*

*column="INVOICE\_ID" &gt;*

*&lt;generator class="sequence"&gt;*

*&lt;param name="sequence"&gt;SEQ\_EMP\_ID&lt;/param&gt;*

*&lt;/generator&gt;*

*&lt;/id&gt;*

***&lt;property***

***name="subTotal"***

***type="big\_decimal"***

***column="SUBTOTAL"&gt;***

***&lt;/property&gt;***

*&lt;!-- other properties --&gt;*

*&lt;/class&gt;*

*&lt;/hibernate-mapping&gt;*

**POJO class**

*public class InvoiceApp {*

*private Long invoiceId;*

***private BigDecimal subTotal;***

*private BigDecimal discount;*

*private BigDecimal salesTax;*

*private BigDecimal total;*

*public InvoiceApp() {*

*}*

*public InvoiceApp(Long invoiceId, BigDecimal subTotal,*

*BigDecimal discount, BigDecimal salesTax,*

*BigDecimal total) {*

*//set values*

*}*

*//other constructors*

*public Long getInvoiceId() {*

*return invoiceId;*

*}*

*public void setInvoiceId(Long invoiceId) {*

*this.invoiceId = invoiceId;*

*}*

***public BigDecimal getSubTotal() {***

***return subTotal;***

***}***

***public void setSubTotal(BigDecimal subTotal) {***

***this.subTotal = subTotal;***

***}***

*//rest of the getter/setters*

*}*

**Save an invoice**

*public void storeInvoice(String subtotal) {*

*try {*

*InvoiceApp invoice = null;*

*// convert subtotal and discount percent to BigDecimal*

*BigDecimal decimalSubtotal = new BigDecimal(subtotal);*

*decimalSubtotal = decimalSubtotal.setScale(2,*

*RoundingMode.HALF\_UP);*

*BigDecimal decimalDiscountPercent;*

*if (decimalSubtotal.compareTo(new BigDecimal("100.00")) &gt;= 0) {*

*decimalDiscountPercent = new BigDecimal("0.1");*

*} else {*

*decimalDiscountPercent = new BigDecimal("0.0");*

*}*

*// calculate discount amount*

*BigDecimal discountAmount =*

*decimalSubtotal.multiply(decimalDiscountPercent);*

*discountAmount = discountAmount.setScale(2,*

*RoundingMode.HALF\_UP);*

*// calculate total before tax, sales tax, and total*

*BigDecimal totalBeforeTax =*

*decimalSubtotal.subtract(discountAmount);*

*BigDecimal salesTaxPercent = new BigDecimal(".05");*

*BigDecimal salesTax = salesTaxPercent.multiply(totalBeforeTax);*

*salesTax = salesTax.setScale(2, RoundingMode.HALF\_UP);*

*BigDecimal total = totalBeforeTax.add(salesTax);*

***invoice = new InvoiceApp(decimalSubtotal, discountAmount,***

***salesTax, total);***

***Session session = getSessionFactory().getCurrentSession();***

***session.beginTransaction();***

***session.save(invoice);***

***session.getTransaction().commit();***

*} catch (HibernateException e) {*

*e.printStackTrace();*

*}*

*}*
