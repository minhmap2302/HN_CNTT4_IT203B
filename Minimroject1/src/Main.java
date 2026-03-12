import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== PRODUCT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add product");
            System.out.println("2. Display product list");
            System.out.println("3. Update quantity");
            System.out.println("4. Delete products");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int quantity = sc.nextInt(); sc.nextLine();
                        System.out.print("Category: ");
                        String category = sc.nextLine();
                        manager.addProduct(new Product(id, name, price, quantity, category));
                        break;
                    case 2:
                        manager.displayProducts();
                        break;
                    case 3:
                        System.out.print("ID to update: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter quantity: ");
                        int newQuantity = sc.nextInt();
                        manager.updateQuantity(updateId, newQuantity);
                        break;
                    case 4:
                        manager.deleteOutOfStock();
                        System.out.println("Deleted products.");
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InvalidProductException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}