package service;

import model.Invoice;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.impl.ConsolePrinter;
import service.impl.LimitValidator;
import service.impl.UserInvoiceService;

import java.math.BigDecimal;

public class IUserInvoiceServiceTest {

    UserInvoiceService userInvoiceService;
    User user;

    @Before
    public void beforeAll() {
        userInvoiceService = new UserInvoiceService(new ConsolePrinter(), new LimitValidator());
        user = new User("test name", "test surname", "test@email.com");

    }

    @Test
    public void should_add_invoices_for_different_users_total_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(190), "test product", "1"));

        User user2 = new User("test name2", "test surname", "test@email.com");
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(100), "test product", "1"));

        Assert.assertEquals(2, user.getInvoices().stream().filter(Invoice::getAdded).count());
        Assert.assertEquals(2, user2.getInvoices().stream().filter(Invoice::getAdded).count());
    }

    @Test
    public void should_users_total_correct_after_adding_invoice() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(20), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(200), "test product", "1"));
        Assert.assertEquals(3, user.getInvoices().size());
        Assert.assertEquals(new BigDecimal(30), user.getTotalAmount());
        Assert.assertEquals(2, user.getInvoices().stream().filter(Invoice::getAdded).count());

    }

    @Test
    public void should_add_two_invoice_total_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(190), "test product", "1"));
        Assert.assertEquals(2, user.getInvoices().size());
        Assert.assertEquals(new BigDecimal(200), user.getTotalAmount());
    }

    @Test
    public void should_not_add_two_invoice_total_bigger_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(200), "test product", "1"));
        Assert.assertEquals(new BigDecimal(10), user.getTotalAmount());
        Assert.assertEquals(2, user.getInvoices().size());

    }

    @Test
    public void should_add_invoice_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        Assert.assertEquals(new BigDecimal(10), user.getTotalAmount());
        Assert.assertEquals(1, user.getInvoices().size());
    }

    @Test
    public void should_not_add_invoice_bigger_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(300), "test product", "1"));
        Assert.assertEquals(new BigDecimal(0), user.getTotalAmount());
        Assert.assertEquals(1, user.getInvoices().size());    }

    @Test
    public void should_invoice_print_correct() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(250), "test product", "1"));
        Assert.assertEquals("RAPOR : 2 fatura girişi yapıldı.1 fatura rededildi.", userInvoiceService.report());
    }
}