import java.util.Arrays;
import java.util.List;

public class Bai2 {
    public static void main(String[] args) {
        List<String> email = Arrays.asList("alice@gmail.com",
                "bob@yahoo.com"
                ,
                "charlie@gmail.com");
        email.stream().filter(e->e.endsWith("gmail.com")).forEach(System.out::println);
    }
}
