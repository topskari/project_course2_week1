import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCartApp {
    public double calculateCost(double price, int quantity) {
        return price * quantity;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a language:");
        System.out.println("1. Swedish");
        System.out.println("2. Finnish");
        System.out.println("3. Japanese");

        int choice = scanner.nextInt();
        Locale locale;

        switch (choice){
            case 1:
                locale = new Locale("sv", "SE");
                break;
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("ja", "JP");
                break;
            default:
                System.out.println("Invalid choice. Using English as default.");
                locale = new Locale("en", "US");
        }
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            System.out.println("Invalid locale. Using English as default.");
            rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        }

        System.out.println(rb.getString("products"));
        int amount = scanner.nextInt();

        double totalCost = 0;

        for (int i = 0; i < amount; i++) {
            System.out.println(MessageFormat.format(rb.getString("price"), i + 1));
            double price = scanner.nextDouble();

            System.out.println(MessageFormat.format(rb.getString("amount"), i + 1));
            int quantity = scanner.nextInt();

            totalCost += price * quantity;

        }

        System.out.printf(rb.getString("total")+" %.2f", totalCost);

        scanner.close();
    }
}