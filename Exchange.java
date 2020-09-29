import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.*;

class linkedlist
{
	node front,rear;
	
	int size;
	Boolean isEmpty() {return (front==null);}
	void insert(node k) 
		 {
		 	node newnode = new node(k);
		 	if(front==null)
		 	{
		 		front = newnode; 
		 		rear = newnode;
		 	}
		 	else
		 	{
		 		rear.link=newnode;
		 		rear = rear.link;
		 	}
		 	size++;
		 }
	void remove(node n)
		 {     if(isEmpty()) throw new NoSuchElementException("Underflow Exception");
		 		
		 		/*node l = new node(front);
		 		int i=size;
		 		if(l.name.equals(n.name))
		 		{
		 			front = l.link;
		 		}
		 		else while(l!=null)
		 			{
		 				if(l.link.name.equals(n.name))
		 				{
		 					l.link=l.link.link;
		 					size--;
		 					break;
		 				}
		 				l=l.link;
		 			}*/
		 			if(front==n){
		 				front=front.link;
		 				return;
		 			}
		 		node l=front;
		 		while(!(l.link==n))
		 		{
		 			l=l.link;
		 		}
		 		l.link=l.link.link;


		 		
		 }
	void modify(node z, int x)
	{	node l = new node(front);
		int i=size;
		 		while(i>0)
		 			{
		 				if(l.name.equals(z.name))
		 				{
		 					l.qty=l.qty-x;
		 					break;
		 				}
		 				i--;
		 			}

	}

}
public class Exchange {
	//match orders
	public static linkedlist s = new linkedlist();
	public static linkedlist b = new linkedlist();
	public static int profit=0;

	public static void dealb(node z, node n)
	{  
		 if(z.qty==n.qty)   //partial or non partial don't matter
			{	profit=profit+(z.qty*(z.price-n.price));
				b.modify(z,z.qty);
				//z.qty=0;
				n.qty=0;
			}
		 else if(z.qty<n.qty )
			{
				 profit=profit+(z.qty*(z.price-n.price));
				 b.modify(z,z.qty);
				 //z.qty=0;
				 n.qty=n.qty-z.qty;
			}
		 else if(z.qty>n.qty)
			{
				profit=profit+(n.qty*(z.price-n.price));
				b.modify(z,n.qty);
				//z.qty-=n.qty;
				n.qty=0;
			}
	}

	public static void deals(node z, node n)
	{  
		 if(z.qty==n.qty)   //partial or non partial don't matter
			{	profit=profit+(z.qty*(z.price-n.price));
				s.modify(z,z.qty);
				//z.qty=0;
				n.qty=0;
			}
		 else if(z.qty<n.qty )
			{
				 profit=profit+(z.qty*(z.price-n.price));
				 s.modify(z,z.qty);
				 //z.qty=0;
				 n.qty=n.qty-z.qty;
			}
		 else if(z.qty>n.qty )
			{
				profit=profit+(n.qty*(z.price-n.price));
				s.modify(z,n.qty);
				//z.qty-=n.qty;
				n.qty=0;
			}
	}

	

}
