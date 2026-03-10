package Bai4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Bai4 {
    public static void main(String[] args) {
        List<User> users= Arrays.asList(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@gmail.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );
        List<User>setUser = new ArrayList<>(users.stream().collect(Collectors.toMap(User::username, u->u,(u1, u2)->u1)).values());
        Consumer<User> print =System.out::println;
        setUser.forEach(print);
    }
}
