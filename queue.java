import java.util.*;
public class QueueDS{
    Queue<Package> q=new LinkedList<>();
    public void enqueue(Package p){q.add(p);}
    public Package dequeue(){return q.poll();}
    public boolean isEmpty(){return q.isEmpty();}
}
