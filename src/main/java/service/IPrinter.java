package service;

import model.Invoice;
import model.User;

import java.util.List;

public interface IPrinter {

    String report(List<Invoice> invoices);

    void printAddedInvoice(User user, Invoice invoice);

    void printUserInvoices(User user);
}
