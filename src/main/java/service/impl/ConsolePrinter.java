package service.impl;

import model.Invoice;
import model.User;
import service.IPrinter;

import java.util.List;

public class ConsolePrinter implements IPrinter {

    @Override
    public String report(List<Invoice> invoices) {
        long count = invoices.stream().filter(Invoice::getAdded).count();
        String report = "RAPOR : " + invoices.size() + " fatura girişi yapıldı." + (invoices.size() - (int) count) + " fatura rededildi.";
        System.out.println(report);
        return report;
    }

    @Override
    public void printAddedInvoice(User user, Invoice invoice) {
        if (invoice.getAdded()) {
            System.out.println("FATURA GİRİŞ : " + formatUser(user, invoice) + "->" + "KABUL EDILDI");
        } else {
            System.out.println("FATURA GİRİŞ : " + formatUser(user, invoice) + "->" + "RED EDILDI");
        }
    }

    @Override
    public void printUserInvoices(User user) {
        user.getInvoices().forEach(invoice -> {
            System.out.println(formatUser(user, invoice) + "->" + (invoice.getAdded() ? "KABUL EDILDI" : "RED EDILD"));
        });
    }

    private String formatUser(User user, Invoice invoice) {
        return user.getName() + "," +
                user.getSurname() + "," +
                user.getEmail() + "," +
                invoice.getAmount() + "," +
                invoice.getProductName() + "," +
                invoice.getBillNo();

    }
}
