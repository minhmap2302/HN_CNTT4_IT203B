package Bai4;

import java.io.IOException;

public class FileService {
    static void savefile()throws IOException{
        throw new IOException("Lỗi khi ghi dữ liệu vào file!");
    }
    static void processUserData() throws IOException{
        savefile();
    }
}
