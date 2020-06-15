package interfaces;

import model.Invoice;
import model.User;

public interface IValidation {
    void checkLimit(User user, Invoice invoice);
}
