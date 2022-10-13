package prereqchecker;

public class Node {
    private String data;
    private Node next;

    public Node(String d){
        data = d;
        next = null;
    }

    public String getData(){ return data; }

    public void setData(String d){ data = d; }

    public Node getNext(){ return next; }

    public void setNext(Node n){ next = n; }

    public boolean hasNext(){ return next != null; }

    public boolean hasNextNext(){ return next.next != null; }

}