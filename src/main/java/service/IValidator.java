package service;

import model.Invoice;
import model.User;

public interface IValidator {
    void checkLimit(User user, Invoice invoice);
}
