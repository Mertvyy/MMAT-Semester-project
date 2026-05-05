class SLLNode{
    Package data;SLLNode next;
    SLLNode(Package d){data=d;}
}
public class SLL{
    SLLNode head;
    public void addRecord(Package p){
        SLLNode n=new SLLNode(p);
        if(head==null){head=n;return;}
        SLLNode t=head;while(t.next!=null)t=t.next;
        t.next=n;
    }
    public void displayLog(){
        SLLNode t=head;
        while(t!=null){System.out.println(t.data);t=t.next;}
    }
}
