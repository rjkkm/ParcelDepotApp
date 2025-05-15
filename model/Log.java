package model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class Log {
    private static Log instance;
    private StringBuffer logBuffer;

    private Log() {
        logBuffer = new StringBuffer();
    }

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void logEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    public void writeLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(logBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
