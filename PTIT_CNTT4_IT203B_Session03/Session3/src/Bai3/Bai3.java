package Bai3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Bai3 {
    public static void main(String[] args) {
        List<User> users= Arrays.asList(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@gmail.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );
        Optional<User> user = users.stream().filter(e->e.username().equalsIgnoreCase("alice")).findFirst();
        if(user.isPresent()){
            System.out.println("Welcome alice");
        }else {
            System.out.println("Guest login");
        }
    }
}
