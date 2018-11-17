/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacciheap;

/**
 *
 * @author uzair
 */
public class Fibo_Heap {
    Doubly t1 = new Doubly();
    Fibo_Heap()
    {
        
    }
    //Yeh node ko insert karta hai root list mei aur H.min ko update kardeta
    // hai agar newnode H.min se chota ho tou
    void insert(Node H,Node newnode)
    {
        t1.insert(H, newnode);
        if(H.min==null || newnode.key<H.min.key)
        {
            H.min=newnode;
        }
        H.n=H.n+1;
    }
    
    Node find_min(Node H)
    {
        return H.min;
    }
    
    // 2 root lists ko mila kar ek root list bana deta hai
    Node union(Node H1,Node H2)
    {
        Node H = new Node();
       H=H1;
       t1.concatenate(H, H2);
       if(H1.min==null || (H2.min!=null && H2.min.key < H1.min.key))
            H.min=H2.min;
       else
           H.min=H1.min;
       
       H.n = H1.n+H2.n;
       H1=H2=null;
       return H;
    }
    
    // Yeh function root list ko consolidate kar deta hai matlab k root 
    // list mei koi bi aesa node nai hota jis ki degree same ho degree ka matlab 
    // hai number of children any node has is wajah se yeh data structure 
    // fibonacci heap kehlata hai kyun k iska structure fibonacci sequence 
    // se match karta hai
    void consolidate(Node H)
    {
        Node temp = H.head;
        Node flag;
        Node temp1;
        do
        {
            flag=temp.right;
            while(flag!=temp)
            {
                if(temp.degree==flag.degree && temp!=flag)
                {
                    if(temp.key<flag.key)
                    {
                        flag=flag.right;
                        Heap_Link(H,flag.left,temp);
                        flag=temp.right;
                    }
                    else
                    {   
                        /*//temp1=flag;
                        flag=flag.right;
                        Heap_Link(H,temp,flag.left);
                        temp=flag;
                        temp=temp.left;
                        //flag=flag.right;*/
                        temp1=flag;
                        flag=temp;
                        temp=temp1;
                        Heap_Link(H,flag,temp);
                        flag=temp.right;
                    }
                }
                else
                flag = flag.right;
            }
            
            temp=temp.right;
        }while(temp!=H.head);
        
        
    }
    
    //Yeh consolidate k sath call hota hai iska kaam yeh hai k yeh k jo node
    // chota ho uska barhey waley ka child bana do takey min heap property rahe
    // data structure ki aur yeh children jo add hotey hai unke links bi bana 
    // deta hai with respect to circular doubly linked list
    void Heap_Link(Node H,Node y,Node x)
    {
        //Node z = new Node(y.key);
        /*if(y==H.head)
        {
            H.head=y.right;
        }
        if(y==H.tail)
        {
            H.tail=y.left;
        }*/
        t1.remove(H,y);
        if(x.degree==0)
        {
          x.child=y;
          y.parent=x;
          y.left=y;
          y.right=y;
          
        }
        else if(x.degree==1)
        {
          y.parent=x;
          x.child.right=y;
          y.right=x.child;
          x.child.left=y;
          y.left=x.child;
        }
        else if(x.degree>1)
        {
            y.parent=x;
            y.right=x.child.right;
            x.child.right.left=y;
            y.left=x.child;
            x.child.right=y;
        }
        //t1.remove(H,y);  
        x.degree+=1;
        y.mark=0;
    }
    
    
    // sab se mushkil function yeh hi hai ismey H.min k saarey children ko
    // root list mei add karna hota hai phir H.min ko root list mei se nikalna 
    // hota hai phir consolidate call ho jata hai takey aur H.min ko bi 
    // update karna hota hai
    Node extract_min(Node H)
    {
        Node z = H.min;
        //int degree = H.min.degree;
        if(H.n==0)
        {
            System.out.println("Kiya delete karna chah rha hai ?");
        }
        else if(H.n==1 && H.min.child==null)
        {
            t1.remove(H, z);
            H.n-=1;
            System.out.println("There was only one root in the Heap which is now deleted.!");
            System.out.println("Minimum : "+z.key);
        }
        else
        {
            
        Node temp;
        if(H.min.child!=null)
        {
            temp=H.min.child;
            if(H.min.degree==1)
            {
                t1.insert(H, temp);
            }
            else
            {
                
            /*do
            {
                if(temp.right!=H.min.child)
                {
                temp=temp.right;
                t1.insert(H, temp.left);
                }
                else
                {
                    t1.insert(H, temp);
                    temp.parent=null;
                    temp=temp.right;
                }
                temp.parent=null;
               
            }while(temp!=H.min.child);*/
                 temp=H.min.child;
                for(int i=0;i<H.min.degree;i++)
                {
                    if(i==(H.min.degree)-1)
                    {
                        t1.insert(H, temp);
                        temp.parent=null;
                        break;
                    }
                   temp=temp.right;
                  t1.insert(H, temp.left);
                  temp.parent=null;
                }
            }
            t1.remove(H, z);
            consolidate(H);
            updatemin(H);
            H.n-=1;
        }
        else
        {
            t1.remove(H, z);
            consolidate(H);
            updatemin(H);
            H.n-=1;
        }
        }
        return z;
    }
    
   void updatemin(Node H)
   {
       Node temp=H.head;
       H.min=temp;
       if(H.n!=0)
       {
           
       
       do
       {
           if(H.min.key>temp.key)
           {
               H.min=temp;
           }
           temp=temp.right;
       }while(temp!=H.head);
       }
   }
   
   //Yeh function simply x ko H.min-1 ki value assign karta hai phir cut ko call
   // karta hai jo usko root list mei add kar deta hai
   void decrease_key(Node H,Node x,int key)
   {
       if(key>x.key)
       {
           System.out.println("New key is greater than current key!");
           //System.exit(1);
       }
       else
       {
           x.key=key;
           Node y = x.parent;
           if(y!=null && x.key<y.key)
           {
               cut(H,x,y);
               cascadingcut(H,y);
           }
           
           if(x.key<H.min.key)
               H.min=x;
       }
   }
   
   // jo node delete karna hota hai uske parent se uske links nikaal detay hai
   // phir usko root list mei add kar detay hai
   void cut(Node H,Node x,Node y)
   {
       t1.removechild(y, x);
       y.degree-=1;
       t1.insert(H, x);
       x.parent=null;
       x.mark=0;
   }
   
   //Check karta hai k children tou remove nahi hue they pehley agar nahi hue
   // tou simply usko mark kar dega per agar marked hai pehley sy tou phir
   // cut call karey ga unmark kar k root list mei add kar dega aur recursively 
   void cascadingcut(Node H,Node y)
   {
       Node z = y.parent;
       if(z!=null)
       {
           if(y.mark==0 && t1.inrootlist(H, y)==0)
           {
               
               y.mark=1;
           }
           else
           {
               cut(H,y,z);
               cascadingcut(H,z);
           }
       }
   }
   
   //ismey wohi Min-Heap wala scene hai per poora same nahi hai ismey jis bi 
   // node ko humey delte karna hota hai usko hum minus-infinity k barabar le kar 
   // root list mei daal detay hai per yaha humney usko (H.min-1) k barabar liya
   // hai jo bilkul theek hai phir extract_min call kartey hai aur usko 
   //delte kardetay hai
   void delete(Node H,Node x)
   {
       if(x==null || H.min==null)
       {
           System.out.println("There is nothing to delete here.");
       }
       else if(x==H.min)
       {
           extract_min(H);
           updatemin(H);
       }
       else
       {   
        decrease_key(H,x,H.min.key-1);
        extract_min(H);
        updatemin(H);
       }
   }
}
