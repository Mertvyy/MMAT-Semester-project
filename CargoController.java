import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CargoController{

    private final DataStore ds;

    public CargoController(DataStore ds){
        this.ds=ds;
    }

    @GetMapping("/shipment/all")
    public List<PackageItem> all(){
        return ds.packages;
    }

    @PostMapping("/shipment/add")
    public void add(@RequestBody PackageItem p){
        ds.packages.add(p);
    }

    @PostMapping("/shipment/delete")
    public void delete(@RequestBody Map<String,String> body){

        String id=body.get("id");

        ds.packages.removeIf(p->p.id.equals(id));
    }
}
