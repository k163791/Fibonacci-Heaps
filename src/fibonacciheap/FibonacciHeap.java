/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacciheap;
import java.*;
/**
 *
 * @author uzair
 */
public class FibonacciHeap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*FIRST CASE :
        - Only One root
        - Extract min 
        - Insert and Delete Operations
        */
        Fibo_Heap t1 = new Fibo_Heap();
        /*Node H1 = new Node();
        t1.insert(H1, new Node(1));
        t1.consolidate(H1);
        t1.extract_min(H1);
        t1.extract_min(H1);
        Node temp = H1.head;
        do
        {
            if(temp!=null)
            {
                System.out.println(temp.key);
                temp=temp.right;   
            }
            
        }while(temp!=H1.head);
        */
        
        /*
        THIRD CASE :
        - Bohat Saarey Trees ka Union kia
        - Phir unko Consolidate kiya
        - Phir Unmey se Extract-Min aur Deletion perform ki
        */
        
        Node H3 = new Node();
        t1.insert(H3, new Node(-111));
        t1.insert(H3, new Node(-14));
        t1.insert(H3, new Node(-123000));
        t1.insert(H3, new Node(333));
        
        Node H2 = new Node();
        t1.insert(H2, new Node(2));
        t1.insert(H2, new Node(-12));
        t1.insert(H2, new Node(98));
        t1.insert(H2, new Node(10));
       
        Node H4 = new Node();
        t1.insert(H4, new Node(798));
        t1.insert(H4, new Node(-8765));
        t1.insert(H4, new Node(544));
        t1.insert(H4, new Node(1));
        Node H = new Node();
        H = t1.union(H3, H2);
        H = t1.union(H, H4);
        t1.consolidate(H);
        
        
        t1.extract_min(H);
        t1.delete(H, H.min);
        t1.extract_min(H);
        System.out.println(H.min.child.key);
        t1.delete(H, H.min.child);
        System.out.println(H.min.child.key);
        Node temp = H.head;
        //System.out.println(H.min.child.key);
        Node flag;
       do
        {
            if(temp!=null)
            {
                System.out.println(temp.key);
           
            temp=temp.right;
            }
       }while(temp!=H.head);
        
    }
    
}
