package toDoApp;


/**
 * 
 * @author Harris Naseh
 * @version 2022.01.01
 *
 * @param <T>
 */
public class DoublyLinkedList<T>{
    
    private class Node<T>{
        private Node<T> next;
        private Node<T> previous;
        private T data;
        
        public Node(T data, Node<T> next, Node<T> previous) {
            this(data);
            this.next = next;
            this.previous = previous;
        }
        
        public Node(T data) {
            this.data = data;
        }
        
        public Node<T> getNext(){
            return next;
        }
        
        public Node<T> getPrevious(){
            return previous;
        }
        
        public T getData() {
            return data;
        }
        
        public void setNext(Node<T> newNext) {
            next = newNext;
        }
        
        public void setPrevious(Node<T> newPrevious) {
            previous = newPrevious;
        }
    }
    
    private int entries;
    private Node<T> tail;
    private Node<T> head;
    
    public DoublyLinkedList() {
        init();  
        
    }
    
    private void init() {
        tail = new Node<T>(null);
        head = new Node<T>(null);
        entries = 0;
        head.setNext(tail);
        tail.setPrevious(head);
    }
    
    public void clear() {
        init();
    }
    
    public void add(T data) {
        add(entries, data);
       
    }
    
    public int size() {
        return entries;
    }
    
    public void add(int index, T data) {
        if(index < 0 || entries < index) {
            throw new IndexOutOfBoundsException();
        }
        
        if(data == null) {
            throw new IllegalArgumentException("Entry is null");
        }
        Node<T> afterNode;
        if(entries == index) {
            afterNode = tail;
        }
        else {
            afterNode = getNodeAt(index);
        }
        Node<T> add = new Node<T>(data);
        add.setPrevious(afterNode.getPrevious());
        add.setNext(afterNode);
        afterNode.getPrevious().setNext(add);
        afterNode.setPrevious(add);
        entries++;
    }
    
    private Node<T> getNodeAt(int pos){
        if(pos < 0 || entries <= pos) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> curr = head.next;
        for(int i = 0; i < pos; i++) {
            curr = curr.next;
        }
        
        return curr;
    }
    
    public String toString() {
        StringBuilder build = new StringBuilder();
        Node<T> curr = head.next;
        while(curr.getNext() != null) {
            build.append(curr.getData());
            if(curr.next != tail) {
                build.append(", ");
            }
            curr = curr.getNext();
        }
        
        return build.toString();
    }
    
    public boolean contains(T data) {
        if(data == null) {
            throw new IllegalArgumentException("Null Entry");
        }
        
        Node<T> curr = head.next;
        while(curr.getNext() !=null) {
            if(curr.getData().equals(data)) {
                return true;
            }
            curr = curr.getNext();
        }
        
        return false;
    }
    
    public T get(int pos) {
        return getNodeAt(pos).getData();
    }
    
    
    public int indexOf(T data) {
        if(data == null) {
            throw new IllegalArgumentException();
        }
        
        Node<T> curr = head.next;
        int index = 0;
        while(curr.getNext() !=null) {
            if(curr.getData().equals(data)) {
                return index;
            }
            curr = curr.getNext();
            index++;
        }
        
        return -1;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public boolean remove(T data) {
        if(indexOf(data) < 0) {
            return false;
        }
        return remove(indexOf(data));
                
    }
    
    public boolean remove(int index) {
        if(index >= size()) {
            return false;
        }
        Node<T> remove = getNodeAt(index);
        if(remove == null) {
            return false;
        }
        
        remove.getNext().setPrevious(remove.getPrevious());
        remove.getPrevious().setNext(remove.getNext());
        remove.setNext(null);
        remove.setPrevious(null);
        entries--;
        return true;
    }
    
    

}
