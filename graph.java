import java.util.*;
class Edge{String v;int w;Edge(String v,int w){this.v=v;this.w=w;}}
public class Graph{
    Map<String,List<Edge>> g=new HashMap<>();
    public void addEdge(String s,String d,int w){
        g.putIfAbsent(s,new ArrayList<>());
        g.putIfAbsent(d,new ArrayList<>());
        g.get(s).add(new Edge(d,w));
        g.get(d).add(new Edge(s,w));
    }
    public void dijkstra(String start){
        Map<String,Integer> dist=new HashMap<>();
        for(String k:g.keySet())dist.put(k,Integer.MAX_VALUE);
        dist.put(start,0);
        PriorityQueue<String> pq=new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);
        while(!pq.isEmpty()){
            String u=pq.poll();
            for(Edge e:g.get(u)){
                int nd=dist.get(u)+e.w;
                if(nd<dist.get(e.v)){
                    dist.put(e.v,nd);
                    pq.add(e.v);
                }
            }
        }
        for(String k:dist.keySet())System.out.println(k+" "+dist.get(k));
    }
    public void prim(){
        Set<String> vis=new HashSet<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        String start=g.keySet().iterator().next();
        vis.add(start);
        for(Edge e:g.get(start))pq.add(new int[]{start.hashCode(),e.v.hashCode(),e.w});
        int total=0;
        while(!pq.isEmpty()){
            int[] cur=pq.poll();
            String v=null;
            for(String k:g.keySet())if(k.hashCode()==cur[1])v=k;
            if(vis.contains(v))continue;
            vis.add(v);total+=cur[2];
            for(Edge e:g.get(v))if(!vis.contains(e.v))
                pq.add(new int[]{v.hashCode(),e.v.hashCode(),e.w});
        }
        System.out.println("MST weight "+total);
    }
}
