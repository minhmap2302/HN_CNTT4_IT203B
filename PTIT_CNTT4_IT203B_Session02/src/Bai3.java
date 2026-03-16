public class Bai3 {
    public static void main(String[] args) {
        String encrypted = Authenticatable.encrypt("123456");
        System.out.println(encrypted);
    }
}
@FunctionalInterface
interface Authenticatable{
    String getPassword();
    default Boolean  isAuthenticated(){
        return getPassword() != null && !getPassword().isEmpty();
    }
    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword;
    }
}
