package Bai2.Test;

import Bai2.Main.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class TestUserService {
    UserService test=new UserService();
    @Test
    void check1(){
        boolean result=test.checkRegistrationAge(18);
        assertTrue(result);
    }
    @Test
    void check2(){
        boolean result=test.checkRegistrationAge(17);
        assertFalse(result);
    }
    @Test
    void check3(){
        int age =-1;
        assertThrows(IllegalArgumentException.class,()->{
            test.checkRegistrationAge(age);
        });
    }
}
