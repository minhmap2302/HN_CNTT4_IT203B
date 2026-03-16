import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("moi ban nhap so nguoi: ");
            int nguoi=sc.nextInt();
            sc.nextLine();
            System.out.print("moi bna nhap so nhoms: ");
            int nhom=sc.nextInt();
            System.out.println("1 nhom co so nguoi la :"+nguoi/nhom);
        }catch (ArithmeticException e){
            System.out.println("Không thể chia cho 0!");
        }finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
