package service.impl;

import service.IUserInvoiceService;
import service.IValidator;
import model.Invoice;
import model.User;
import service.IPrinter;

import java.util.ArrayList;
import java.util.List;

public class UserInvoiceService implements IUserInvoiceService {

    private List<Invoice> allInvoices;
    private IPrinter printer;
    private IValidator validator;

    public UserInvoiceService(IPrinter printer, IValidator validator) {
        this.printer = printer;
        this.validator = validator;
        allInvoices = new ArrayList<>();
    }

    public void addInvoice(User user, Invoice invoice) {
        validator.checkLimit(user, invoice);
        printer.printAddedInvoice(user, invoice);
        allInvoices.add(invoice);
    }

    public void printUser(User user) {
        printer.printUserInvoices(user);
    }

    public String report() {
        return printer.report(allInvoices);
    }


}
