package interfaces;

import model.Invoice;
import model.User;

public interface IUserInvoiceService {
    void addInvoice(User user, Invoice invoice);
}
