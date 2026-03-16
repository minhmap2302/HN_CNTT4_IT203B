package Bai2.Main;

public class UserService {
    public boolean  checkRegistrationAge(int age){
        if( age<0){
            throw new IllegalArgumentException("tuoi ko hop le");
        }
        if(age<18){
            return false;
        }

        return true;
    }
}
