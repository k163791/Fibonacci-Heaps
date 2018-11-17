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
public class Doubly {
    Doubly()
    {
        
    }
    //Iska maqsad iske naam se zahir hai yeh insert kar deta hai root list
    // mei koi sa bi node
    void insert(Node H,Node newnode)
    {
        if(H.n==0)
        {
            H.head=newnode;
            H.head.left=H.head;
            H.head.right=H.head;
            H.head=H.head;
            H.tail=H.head;
        }
        else if(H.head.right==H.head)
        {
            newnode.left=H.head;
            H.head.right=newnode;
            newnode.right=H.head;
            H.head.left=newnode;
            H.tail=newnode;
        }
        else
        {
            newnode.right=H.head.right;
            H.head.right.left=newnode;
            H.head.right=newnode;
            newnode.left=H.head;
        }
    }
    
    //Yeh function union k liye istemaal hota hai for example 2 root lists
    // ko merge kr k ek root list bana deta hai
    void concatenate(Node x,Node y)
    {
       x.tail.right=y.head;
       y.head.left=x.tail;
       y.tail.right=x.head;
       x.head.left=y.tail;
       x.tail=y.tail;
        
    }
    
    //Yeh function root list mei se kisi bi node ko remove kardeta hai
    void remove(Node H,Node t)
    {
        if(t.right==t)
        {
            H.head=H.tail=H.min=H.min.child;
        }
        else if(t==H.head)
        {
            H.head=t.right;
            H.head.left=H.tail;
            H.tail.right=H.head;
        }
        else if(t==H.tail)
        {
            H.tail=t.left;
            H.tail.right=H.head;
            H.head.left=H.tail;
        }
        else
        {
            t.left.right=t.right;
            t.right.left=t.left;
        }
    }
    
    
    // Yeh deletion k time pe child remove kardeta hai
    // for example jis bi node ko delete karna hota hai us ke parent mei sy uske 
    //child pointer ko remove kardeta hai
    void removechild(Node y,Node x)
    {
        if(y.degree==1)
        {
            y.child=null;
        }
        else if(y.degree>1)
        {
            if(y.child==x)
            {
                x.right.left=y.child.left;
                y.child.left.right=x.right;
                y.child=x.right;
            }
            else
            {
                Node temp=y.child;
                while(temp!=x)
                {
                    temp=temp.right;
                }
                temp.right.left=temp.left;
                temp.left.right=temp.right;
                y.child=temp.right;
                
            }
            
        }
    }
    
    // Yeh function check kr k batata hai k jis node ko hum mark karne jaa rhe 
    // hai vo root list mei tou nahi hai kyun k agar vo root list mei hoga tou
    // usko mark nhi karey gey
    int inrootlist(Node H,Node x)
    {
        Node temp = H.head;
        do
        {
            if(x==temp)
                return 1;
            temp=temp.right;
        }while(temp!=H.head);
        return 0;
    }
}
