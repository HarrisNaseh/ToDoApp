package toDoApp;


public class DoublyLinkedListTest extends student.TestCase{
    
    private DoublyLinkedList<String> list;
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }
    
    public void testAdd() {
        Exception exception = null;
        try {
            list.add(null);
            fail("add() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("peek() is throwing the wrong type of exceptions",
            exception instanceof IllegalArgumentException);
        
        Exception exception1 = null;
        try {
            list.add(-1, "list");
            fail("add() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception1 = e;
        }
        assertTrue("peek() is throwing the wrong type of exceptions",
            exception1 instanceof IndexOutOfBoundsException);
        
        
        list.add("Test");
        System.out.println(list.toString());
        list.add("Test2");
        System.out.println(list.toString());
        list.add(1, "test3");
        System.out.println(list.toString());
        assertEquals(3, list.size());
        assertTrue(list.contains("Test"));
        assertFalse(list.contains("g"));
        assertFalse(list.remove("Test3"));
        assertTrue(list.remove("test3"));
        assertTrue(list.remove("Test2"));
        list.add("Test2");
        list.add("Test3");
        assertTrue(list.remove(0));
        assertFalse(list.remove(3));
        
        System.out.println(list.toString());
        
              
        
    }
    

}
