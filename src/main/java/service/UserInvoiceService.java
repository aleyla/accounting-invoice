package service;

import interfaces.IPrinter;
import interfaces.IUserInvoiceService;
import interfaces.IValidation;
import model.Invoice;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserInvoiceService implements IUserInvoiceService {

    private List<Invoice> allInvoices;
    private IPrinter printer;
    private IValidation validation;

    public UserInvoiceService(IPrinter printer, IValidation validation) {
        this.printer = printer;
        this.validation = validation;
        allInvoices = new ArrayList<>();
    }

    public void addInvoice(User user, Invoice invoice) {
        validation.checkLimit(user, invoice);
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
