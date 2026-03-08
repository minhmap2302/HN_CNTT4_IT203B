package Bai5;

public class Main  {
    public static void main(String[] args) {
        User u = new User();
        try {
            u.setAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
