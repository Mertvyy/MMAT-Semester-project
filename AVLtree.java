class AVLNode{
    String key,val;int h;AVLNode l,r;
    AVLNode(String k,String v){key=k;val=v;h=1;}
}
public class AVLTree{
    AVLNode root;
    int h(AVLNode n){return n==null?0:n.h;}
    int bal(AVLNode n){return n==null?0:h(n.l)-h(n.r);}
    AVLNode rR(AVLNode y){
        AVLNode x=y.l,t=x.r;
        x.r=y;y.l=t;
        y.h=Math.max(h(y.l),h(y.r))+1;
        x.h=Math.max(h(x.l),h(x.r))+1;
        return x;
    }
    AVLNode lR(AVLNode x){
        AVLNode y=x.r,t=y.l;
        y.l=x;x.r=t;
        x.h=Math.max(h(x.l),h(x.r))+1;
        y.h=Math.max(h(y.l),h(y.r))+1;
        return y;
    }
    AVLNode insert(AVLNode n,String k,String v){
        if(n==null)return new AVLNode(k,v);
        if(k.compareTo(n.key)<0)n.l=insert(n.l,k,v);
        else n.r=insert(n.r,k,v);
        n.h=1+Math.max(h(n.l),h(n.r));
        int b=bal(n);
        if(b>1&&k.compareTo(n.l.key)<0)return rR(n);
        if(b<-1&&k.compareTo(n.r.key)>0)return lR(n);
        if(b>1&&k.compareTo(n.l.key)>0){n.l=lR(n.l);return rR(n);}
        if(b<-1&&k.compareTo(n.r.key)<0){n.r=rR(n.r);return lR(n);}
        return n;
    }
    public void insert(String k,String v){root=insert(root,k,v);}
    public AVLNode search(AVLNode n,String k){
        if(n==null||n.key.equals(k))return n;
        return k.compareTo(n.key)<0?search(n.l,k):search(n.r,k);
    }
    public String search(String k){
        AVLNode n=search(root,k);
        return n==null?null:n.val;
    }
}
