import database.ProductDatabase;
import entity.Product;
import factory.ProductFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("----- Quan ly San Pham -----");
            System.out.println("1. Them moi");
            System.out.println("2. Xem danh sach");
            System.out.println("3. Cap nhap");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Thoat");
            System.out.print("Pick: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap loai (1, 2): ");
                    int type = sc.nextInt(); sc.nextLine();
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble();
                    Product p;
                    if (type == 1) {
                        System.out.print("Trong Luong: ");
                        double weight = sc.nextDouble();
                        p = ProductFactory.createPhysicalProduct(id, name, price, weight);
                    } else {
                        System.out.print("Dung Luong(MB): ");
                        int size = sc.nextInt();
                        p = ProductFactory.createDigitalProduct(id, name, price, size);
                    }
                    db.addProduct(p);
                    break;
                case 2:
                    System.out.println("----- Danh sach san pham -----");
                    for (Product prod : db.getAllProducts()) {
                        prod.displayInfo();
                    }
                    break;
                case 3:
                    System.out.print("Nhap ID: ");
                    String updateId = sc.nextLine();
                    System.out.print("Loai (1-Physical, 2-Digital): ");
                    int typeU = sc.nextInt(); sc.nextLine();
                    System.out.print("Ten moi: ");
                    String nameU = sc.nextLine();
                    System.out.print("Gia moi: ");
                    double priceU = sc.nextDouble();

                    Product newP;
                    if (typeU == 1) {
                        System.out.print("Trong luong moi: ");
                        double weightU = sc.nextDouble();
                        newP = ProductFactory.createPhysicalProduct(updateId, nameU, priceU, weightU);
                    } else {
                        System.out.print("Dung luong moi (MB): ");
                        int sizeU = sc.nextInt();
                        newP = ProductFactory.createDigitalProduct(updateId, nameU, priceU, sizeU);
                    }

                    db.updateProduct(updateId, newP);
                    break;

                case 4:
                    System.out.print("Nhap ID: ");
                    String delId = sc.nextLine();
                    db.deleteProduct(delId);
                    break;
                case 5:
                    System.out.println("Exit.");
                    return;
                default:
                    System.out.println("lua chon ko hop le");
            }
        }
    }
}