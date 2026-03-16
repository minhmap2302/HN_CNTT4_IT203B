package Bai5;

public class SuperAdmin implements UserActions,AdminActions{
//    Java sẽ không biết phải dùng logActivity() của interface nào vì cả hai đều có default method giống nhau.
    @Override
    public void logActivity(String Activity) {
        System.out.println("SuperAdmin log: " + Activity);
    }
}
