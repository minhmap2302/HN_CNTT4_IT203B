import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String namsinh=sc.nextLine();
        try {
             int tuoi=Integer.parseInt(namsinh);
             tuoi=2026-tuoi;
            System.out.println("tuoi cua bn : "+tuoi);
        }catch (NumberFormatException e){
            System.out.println("Lỗi: Bạn phải nhập năm sinh bằng số. Ví dụ: 2003");

        }finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}
