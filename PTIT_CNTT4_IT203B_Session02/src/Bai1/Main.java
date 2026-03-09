package Bai1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<User> createUser=()->new User("An","USER");
        User defaultuser=createUser.get();
        User adminUser = new User("admin01", "ADMIN");
        Predicate<User> check=User->User.getRole().equalsIgnoreCase("Admin");
        Function<User,String> getUserName=user -> user.getName();
        Consumer<User> printuser=user->{
            System.out.println("Username: " + user.getName());
            System.out.println("Role: " + user.getRole());
            System.out.println("------------------");
        };
        System.out.println("Default");
        System.out.println(defaultuser);
        System.out.println("Admin User:");
        printuser.accept(adminUser);
        System.out.println("Is adminUser Admin? " + check.test(adminUser));

        System.out.println("Username of adminUser: " + getUserName.apply(adminUser));

    }
}
