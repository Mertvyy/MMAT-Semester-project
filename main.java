import java.util.*;
public class Main{
    public static void main(String[] args)throws Exception{
        List<Package> pkgs=FileManager.readPackages("packageData.txt");
        Graph g=FileManager.readMap("mapData.txt");

        DLL buffer=new DLL();
        QueueDS q=new QueueDS();
        StackDS st=new StackDS();
        SLL log=new SLL();
        AVLTree avl=new AVLTree();

        for(Package p:pkgs){
            buffer.insertAtTail(p);
            avl.insert(p.destination,p.id);
        }

        Package p;
        while((p=buffer.removeFromHead())!=null){
            q.enqueue(p);
        }

        while(!q.isEmpty()){
            st.push(q.dequeue());
        }

        while((p=st.pop())!=null){
            log.addRecord(p);
        }

        log.displayLog();
        System.out.println("---Dijkstra from Meydan---");
        g.dijkstra("Meydan");
        System.out.println("---MST---");
        g.prim();
    }
}
