package Bai4;

import java.io.IOException;

public class Bai4 {
    public static void main(String[] args) throws IOException {
        try{
            FileService.processUserData();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }
}
