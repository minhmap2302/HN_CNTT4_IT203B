package Bai6;

public class MIain {
    public static void main(String[] args) {
        User user=new User();
        try {
            user.setAge(-10);
        }catch (InvalidAgeException e){
            Logger.logError(e.getMessage());
        }
    }
}
