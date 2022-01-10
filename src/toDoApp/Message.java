package toDoApp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Comparable<Message> {

    private LocalDateTime time;
    private String message;
    private boolean complete;
    
    public Message(String mess) {
        time = LocalDateTime.now();
        message = mess;
        complete = false;
    }
    
    public void setMessage(String newMessage) {
        message = newMessage;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getDate() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = time.format(myFormatObj);
        return formattedDate;
    }
    
    public LocalDateTime getTime() {
        return time;
    }
    
    public boolean isComplete() {
        return complete;
    }
    
    public void complete() {
        complete = true;
    }

    @Override
    public int compareTo(Message o) {
        
        return time.compareTo(o.getTime());
    }
    
    public String toString() {
        return message;
    }

}
