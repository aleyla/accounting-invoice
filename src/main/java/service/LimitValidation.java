package service;

import interfaces.IValidation;
import model.Invoice;
import model.User;

import java.math.BigDecimal;

public class LimitValidation implements IValidation {

    private static final BigDecimal LIMIT = new BigDecimal(200);

    public void checkLimit(User user, Invoice invoice) {
        BigDecimal totalAmount = user.getTotalAmount().add(invoice.getAmount());
        if (LIMIT.compareTo(totalAmount) >= 0) {
            invoice.setAdded(true);
            user.setTotalAmount(totalAmount);
        }
        user.getInvoices().add(invoice);
    }
}
