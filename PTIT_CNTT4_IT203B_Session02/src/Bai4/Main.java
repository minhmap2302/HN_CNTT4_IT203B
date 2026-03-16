package Bai4;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<User> create=User::new;
        User n=create.get();
        Function<User,String> get=User::getName;
        Consumer<String>print=System.out::println;
        print.accept(get.apply(n));
    }
}
