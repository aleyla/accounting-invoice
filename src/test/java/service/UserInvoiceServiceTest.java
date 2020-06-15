package service;

import model.Invoice;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class UserInvoiceServiceTest {

    UserInvoiceService userInvoiceService;
    User user;

    @Before
    public void beforeAll() {
        userInvoiceService = new UserInvoiceService(new Printer(), new LimitValidation());
        user = new User("test name", "test surname", "test@email.com");

    }

    @Test
    public void should_add_invoices_for_different_users_total_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(190), "test product", "1"));

        User user2 = new User("test name2", "test surname", "test@email.com");
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(100), "test product", "1"));
        Assert.assertEquals("RAPOR : 4 fatura girişi yapıldı.0 fatura rededildi.", userInvoiceService.report());
    }

    @Test
    public void should_users_total_correct_after_adding_invoice() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(20), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(200), "test product", "1"));
        Assert.assertEquals(new BigDecimal(30), user.getTotalAmount());
    }

    @Test
    public void should_add_two_invoice_total_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(190), "test product", "1"));
        Assert.assertEquals("RAPOR : 2 fatura girişi yapıldı.0 fatura rededildi.", userInvoiceService.report());
    }

    @Test
    public void should_not_add_two_invoice_total_bigger_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(200), "test product", "1"));
        Assert.assertEquals("RAPOR : 2 fatura girişi yapıldı.1 fatura rededildi.", userInvoiceService.report());
    }

    @Test
    public void should_add_invoice_smaller_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        Assert.assertEquals("RAPOR : 1 fatura girişi yapıldı.0 fatura rededildi.", userInvoiceService.report());
    }

    @Test
    public void should_not_add_invoice_bigger_then_limit() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(300), "test product", "1"));
        Assert.assertEquals("RAPOR : 1 fatura girişi yapıldı.1 fatura rededildi.", userInvoiceService.report());
    }

    @Test
    public void should_invoice_print_correct() {
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(10), "test product", "1"));
        userInvoiceService.addInvoice(user, new Invoice(new BigDecimal(250), "test product", "1"));
        Assert.assertEquals("RAPOR : 2 fatura girişi yapıldı.1 fatura rededildi.", userInvoiceService.report());
    }
}