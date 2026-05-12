import java.io.*;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class DataStore{

    public List<PackageItem> packages=new ArrayList<>();

    public DataStore() throws Exception{
        loadPackages();
    }

    public void loadPackages() throws Exception{

        InputStream is=getClass()
        .getClassLoader()
        .getResourceAsStream("packageData.txt");

        BufferedReader br=new BufferedReader(
        new InputStreamReader(is));

        String line;

        while((line=br.readLine())!=null){

            if(line.startsWith("#")||line.trim().isEmpty())
                continue;

            String[] p=line.split(" ");

            packages.add(
                new PackageItem(p[0],p[1])
            );
        }
    }
}
