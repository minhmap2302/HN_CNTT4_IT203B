package Bai5;



import java.util.Arrays;
import java.util.List;

public class Bai5 {
    public static void main(String[] args) {
        List<User> users= Arrays.asList(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@gmail.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );
        List<User> users1=users.stream().sorted((a,b)->b.username().length()-a.username().length()).limit(3).toList();
        users1.forEach(System.out::println);
    }
}
