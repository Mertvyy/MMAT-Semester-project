import java.io.*;import java.util.*;
public class FileManager{
    public static List<Package> readPackages(String f)throws Exception{
        List<Package> l=new ArrayList<>();
        BufferedReader br=new BufferedReader(new FileReader(f));
        String s;
        while((s=br.readLine())!=null){
            if(s.startsWith("#")||s.trim().isEmpty())continue;
            String[] p=s.split(" ");
            l.add(new Package(p[0],p[1]));
        }
        return l;
    }
    public static Graph readMap(String f)throws Exception{
        Graph g=new Graph();
        BufferedReader br=new BufferedReader(new FileReader(f));
        String s;
        while((s=br.readLine())!=null){
            if(s.startsWith("#")||s.trim().isEmpty())continue;
            String[] p=s.split(" ");
            g.addEdge(p[0],p[1],Integer.parseInt(p[2]));
        }
        return g;
    }
}
