package toDoApp;

public class MessageTest extends student.TestCase {
    
    private Message message;
    
    public void setUp() {
        message = new Message("Clean room");
    }
    
    public void testgetMessage() {
        assertTrue(message.getMessage().equals("Clean room"));
        message.setMessage("Clean house");
        assertTrue(message.getMessage().equals("Clean house"));
        System.out.println(message.getDate());
        Message mess = new Message("Mess");
        System.out.println(message.compareTo(mess));
        System.out.println(mess.compareTo(message));
        
    }

}
