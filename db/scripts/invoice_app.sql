-- create the invoiceapp table

CREATE TABLE INVOICEAPP
(
  INVOICE_ID NUMBER NOT NULL,
  SUBTOTAL NUMBER(10, 2),
  DISCOUNT NUMBER(10, 2),
  SALES_TAX NUMBER(10, 2),
  TOTAL NUMBER(10, 2),
  CONSTRAINT INVOICEAPP_PK PRIMARY KEY (
    INVOICE_ID
  )
  ENABLE
);
/
CREATE OR REPLACE TRIGGER TRG_SET_INVOICE_ID BEFORE
  INSERT ON INVOICEAPP FOR EACH ROW WHEN (NEW.INVOICE_ID IS NULL) BEGIN
  SELECT SEQ_EMP_ID.NEXTVAL INTO :NEW.INVOICE_ID FROM DUAL;
END;
/
ALTER TRIGGER TRG_SET_INVOICE_ID ENABLE;