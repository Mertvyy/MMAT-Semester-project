import java.util.*;
public class StackDS{
    Stack<Package> s=new Stack<>();
    public void push(Package p){s.push(p);}
    public Package pop(){return s.isEmpty()?null:s.pop();}
}
