package model;

import java.math.BigDecimal;

public class Invoice {

    private BigDecimal amount;
    private String productName;
    private String billNo;
    private Boolean added;

    public Invoice(BigDecimal amount, String productName, String billNo) {
        this.amount = amount;
        this.productName = productName;
        this.billNo = billNo;
        added = false;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }
}
