import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class node
{
	public float t0;
	public String name;
	public float texp;
	public String type; 
	public int qty;
	public String st; //stock
	public int price;
	public String partial;
	public node link;

	public node(float a, String b, float c, String d, int e, String f, int g, String h)
	{
	t0=a;
	name=b;
	texp=c;
	type=d;
	qty=e;
	st=f;
	price=g;
	partial=h;
	link=null;	
	}
	public node getlink() 
	{
		return link;
	}
	public node(node k)
	{
	t0=k.t0;
	name=k.name;
	texp=k.texp;
	type=k.type;
	qty=k.qty;
	st=k.st;
	price=k.price;
	partial=k.partial;
	link=k.link;
	}
	public node()
	{
	t0=0.0f;
	name="null";
	texp=0.0f;
	type="sell";
	qty=0;
	st="a";
	price=0;
	partial="T";
	link=null;	
	}
}

class queue
{
	node front = new node();
	node rear = new node();
		 int size;
		 Boolean isEmpty() {return front==null;}
		 /*void enqueue(int a, String b, int c, String d, int e, String f, int g, String h) 
		 {
		 	node newnode = new node(int a, String b, int c, String d, int e, String f, int g, String h);
		 	if(rear==null)
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
		 }*/
		  void enqueue(node a) 
		 {
		 	node newnode = new node(a);
		 	if(front==null)
		 	{
		 		front = newnode;

		 		front.link=null;
		 		rear=front;
		 	}
		 	else
		 	{
		 		rear.link=newnode;
		 		rear = rear.link;
		 	}
		 	size++;
		 }
		 node dequeue()
		 {
		 		
		 		//	throw new NoSuchElementEception("Underflow Exception");
		 		node k = front;
		 			front = k.link;
		 			size--;
		 			return k;
		 		
		 }
}


public class stock
{
	//Perform I/O operation
	public static queue q=new queue();                           //queue for maintaining the orders
	public static String act;	 
	//void performAction(String actionString) //i m changing it to return type
	void performAction(String actionString)	 
	{	act = actionString;
		test.order();
		/*test t = new test();
		Thread threado = new Thread(t);
		Thread threadc = new Thread(t);
		Thread threade = new Thread(t);
		threado.start();
		threadc.start();
		threade.start();*/	
	}
		
}
