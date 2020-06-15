import model.Invoice;
import model.User;
import service.impl.ConsolePrinter;
import service.impl.LimitValidator;
import service.impl.UserInvoiceService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {


        Map<User, User> userMap = new HashMap<>();

        UserInvoiceService userInvoiceService = new UserInvoiceService(new ConsolePrinter(), new LimitValidator());

        User user1 = new User("John", "Doe", "john@doe.com");
        userInvoiceService.addInvoice(user1, new Invoice(new BigDecimal(200), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user1, new Invoice(new BigDecimal(190), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user1, new Invoice(new BigDecimal(200), "IPhone 8", "TR0001"));
        userMap.put(user1, user1);

        User user2 = new User("Jane", "Doe", "jane@doe.com");
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(230), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(2), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user2, new Invoice(new BigDecimal(2), "IPhone 8", "TR0001"));
        userMap.put(user2, user2);

        User user3 = new User("aysegul", "leyla", "john@doe.com");
        userInvoiceService.addInvoice(user3, new Invoice(new BigDecimal(2), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user3, new Invoice(new BigDecimal(250), "IPhone 8", "TR0001"));
        userInvoiceService.addInvoice(user3, new Invoice(new BigDecimal(2), "IPhone 8", "TR0001"));
        userMap.put(user3, user3);

        userInvoiceService.report();

        System.out.println("USER RAPOR :" + user3.toString());
        userInvoiceService.printUser(user3);


        String message = "Enter new accounting invoice or write 'X' for exit, 'R' for report : ";
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        while (!"X".equalsIgnoreCase(inputString)) {

            if ("R".equalsIgnoreCase(inputString)) {
                userInvoiceService.report();
                System.out.print(message);
                inputString = scanner.nextLine();
            } else {
                String[] splitted = inputString.split(",");
                if (splitted.length < 6) {
                    System.out.println("Please check invoice");
                } else {
                    User user = new User(splitted[0], splitted[1], splitted[2]);
                    userInvoiceService.addInvoice(userMap.getOrDefault(user, user), new Invoice(new BigDecimal(splitted[3]), splitted[4], splitted[5]));
                }
                System.out.print(message);
                inputString = scanner.nextLine();
            }
        }
    }
}
