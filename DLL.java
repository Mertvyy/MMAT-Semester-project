class DLLNode{
    Package data;DLLNode prev,next;
    DLLNode(Package d){data=d;}
}
public class DLL{
    DLLNode head,tail;
    public void insertAtTail(Package p){
        DLLNode n=new DLLNode(p);
        if(head==null){head=tail=n;return;}
        tail.next=n;n.prev=tail;tail=n;
    }
    public Package removeFromHead(){
        if(head==null)return null;
        Package d=head.data;
        head=head.next;
        if(head!=null)head.prev=null;
        else tail=null;
        return d;
    }
}
