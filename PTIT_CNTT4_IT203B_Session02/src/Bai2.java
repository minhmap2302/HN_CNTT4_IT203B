import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
      passwordvalidate check= new passwordvalidate() {
          @Override
          public boolean isvalid(String pass) {
              if(pass.length()>=8){
                  return true;
              }
              return false;
          }
      };
        System.out.printf("moi ban nhap pass: ");
        Scanner sc = new Scanner(System.in);
        String temp= sc.nextLine();
        System.out.println(check.isvalid(temp));

    }
}
@FunctionalInterface
interface passwordvalidate{
    boolean isvalid(String pass);
}

