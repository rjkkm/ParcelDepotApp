package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Singleton class used to log events.
 * Records events in an internal buffer and writes to a file on demand.
 */
public class Log {
    private static Log instance;
    private StringBuilder logBuffer;

    private Log() {
        logBuffer = new StringBuilder();
    }

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void writeEvent(String event) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logBuffer.append("[").append(timestamp).append("] ").append(event).append("\n");
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(logBuffer.toString());
            writer.write("------ End of Log ------\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
