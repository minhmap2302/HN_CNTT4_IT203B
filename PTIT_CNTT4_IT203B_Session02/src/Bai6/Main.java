package Bai6;

public class Main {
    public static void main(String[] args) {
       UserProcessor userProcessor=UserUtils::convertToUpperCase;
       User n=new User(12,"an");
        System.out.println(userProcessor.process(n));
    }
}
@FunctionalInterface
interface UserProcessor {
    String process(User U);
}
