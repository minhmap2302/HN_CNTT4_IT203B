package Bai6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void logError(String message) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(format);
        System.out.println("[ERROR] " + time + " - " + message);
    }

}
