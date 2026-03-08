package Bai3;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setAge(-1);
        user.setAge(19);
        System.out.println(user.getAge());
    }
}
