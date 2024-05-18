package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Constants.AUDIT_LOG_FILE;

public class AuditService
{
    public static void logAction(String actionName)
    {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = String.format("%s,%s%n",actionName,timeStamp);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(AUDIT_LOG_FILE,true)))
        {
            bufferedWriter.write(logEntry);
        }
        catch (IOException e)
        {
            System.err.println("Error writing to audit log: " + e.getMessage());
        }
    }
}
