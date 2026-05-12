import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.io.*;

@RestController
@CrossOrigin("*")
public class CargoController {

    DLL buffer=new DLL();
    SLL log=new SLL();
    AVLTree avl=new AVLTree();
    Graph graph=new Graph();

    public CargoController() throws Exception {

        List<Package> pkgs=
        FileManager.readPackages("packageData.txt");

        for(Package p:pkgs){
            buffer.insertAtTail(p);
            log.addRecord(p);
            avl.insert(p.destination,p.id);
        }

        graph=FileManager.readMap("mapData.txt");
    }

    @GetMapping("/shipment/all")
    public List<Package> all(){

        List<Package> out=new ArrayList<>();

        DLLNode t=buffer.head;

        while(t!=null){
            out.add(t.data);
            t=t.next;
        }

        return out;
    }

    @PostMapping("/shipment/add")
    public void add(@RequestBody Package p)throws Exception{

        buffer.insertAtTail(p);
        log.addRecord(p);
        avl.insert(p.destination,p.id);

        FileWriter fw=
        new FileWriter("packageData.txt",true);

        fw.write("\n"+p.id+" "+p.destination);

        fw.close();
    }

    @PostMapping("/shipment/delete")
    public void delete(@RequestBody Map<String,String> body){

        String id=body.get("id");

        Package removed=null;

        DLLNode t=buffer.head;

        while(t!=null){

            if(t.data.id.equals(id)){
                removed=t.data;
                break;
            }

            t=t.next;
        }

        if(removed!=null)
            buffer.removePackage(removed);
    }

    @GetMapping("/address/search")
    public String search(@RequestParam String key){

        String res=avl.search(key);

        if(res==null)
            return "Address not found";

        return "Shipment ID: "+res;
    }

    @GetMapping("/route/optimal")
    public String route(){

        return graph.getMSTText();
    }
}
