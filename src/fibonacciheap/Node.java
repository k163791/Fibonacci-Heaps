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
public class Node {
    int n;
    Node min;
    int key;
    int degree;
    Node parent;
    Node child;
    Node left;
    Node right;
    Node head;
    Node tail;
    int mark;
    Node(int data)
    {
        this.n=0;
        this.head=this.tail=null;
        //this.next=this.prev=null;
        this.key=data;
        this.min=this.parent=this.child=this.left=this.right=null;
        this.degree=0;
        this.mark=0;
    }
    
    Node()
    {
        this.n=0;
        this.head=this.tail=null;
        //this.next=this.prev=null;
        this.key=0;
        this.min=this.parent=this.child=this.left=this.right=null;
        this.degree=0;
        this.mark=0; 
    }
}
