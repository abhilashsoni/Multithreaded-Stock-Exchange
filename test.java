import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class test implements Runnable{
//Thread wrapper class

	public void run()
	{ 
		System.out.println(Thread.currentThread().getName());
		if(Thread.currentThread().getName().equals("Thread-0"))
		{
			//here order thread operation
			String[] arr = checker.r.act.split("\\s+");
			node a = new node();
			try
			{
				try
				{
				a.t0=(float)Integer.parseInt(arr[0]);
				a.name=arr[1];
				a.texp=(float)Integer.parseInt(arr[2]);
				a.type=arr[3];
				a.qty=Integer.parseInt(arr[4]);
				a.st=arr[5];
				a.price=Integer.parseInt(arr[6]);
				a.partial=arr[7];
				a.link=null;
				if(a.t0>=((System.currentTimeMillis()/1000.0f)-checker.abst))		 
					{
						try{int t=(int)((a.t0-((System.currentTimeMillis()/1000.0f)-checker.abst))*1000);
						Thread.sleep(t);  
                     	}catch(Exception e){System.out.println("Exception"+"  "+e.getClass().getName());}
                    }
                     //insert if with expiry condn here
                 if(a.t0+a.texp<=((System.currentTimeMillis()/1000.0f)-checker.abst));
					checker.r.q.enqueue(a);
					

					try{
						FileOutputStream oso = new FileOutputStream("order.out",true);
						PrintStream p=new PrintStream(oso);
						p.println(a.t0 +"  "+ a.name +"  "+a.texp+"  "+a.type+"  "+a.qty+"  "+a.st+"  "+a.price+"  "+a.partial);
						System.out.println(a.t0 +"  "+ a.name +"  "+a.texp+"  "+a.type+"  "+a.qty+"  "+a.st+"  "+a.price+"  "+a.partial);
						//oso.close();
						}
						catch(FileNotFoundException e)
						{System.out.println(e);
							e.printStackTrace();
						}

      	    		
				}
				catch(Exception e)
				{//e.printStackTrace();

				try{
					FileOutputStream oso = new FileOutputStream("order.out",true);
					PrintStream p=new PrintStream(oso);
					p.println("Exception"+"  "+e.getClass().getName());
					System.out.println("Exception"+"  "+e.getClass().getName());
					//oso.close();
					}
					catch(FileNotFoundException ie)
					{System.out.println(ie);
						ie.printStackTrace();
					}
				}
			}
			catch(NumberFormatException e)
			{System.out.println(e);
				e.printStackTrace();
				try{
					FileOutputStream oso = new FileOutputStream("order.out",true);
					PrintStream p=new PrintStream(oso);
					p.println("Exception"+"  "+" input mismatch exception");
					System.out.println("Exception"+"  "+" input mismatch exception");
					//oso.close();
					}
					catch(FileNotFoundException ie)
					{System.out.println(ie);
						ie.printStackTrace();
					}
			}
			
			/*if(a.t0>=((System.currentTimeMillis()/1000.0f)-checker.abst))		 
			{
				//threado.sleep(a.t0-((System.currentTimeMillis()/1000)-checker.abst));

				checker.r.q.enqueue(a);
					

				try{
					FileOutputStream oso = new FileOutputStream("order.out",true);
					PrintStream p=new PrintStream(oso);
					p.println(a.t0 +"  "+ a.name +"  "+a.texp+"  "+a.type+"  "+a.qty+"  "+a.st+"  "+a.price+"  "+a.partial);
					System.out.println(a.t0 +"  "+ a.name +"  "+a.texp+"  "+a.type+"  "+a.qty+"  "+a.st+"  "+a.price+"  "+a.partial);
					//oso.close();
					}catch(FileNotFoundException e)
					{System.out.println(e);
						e.printStackTrace();
					}

      	    }*/		
		}
	 

		else if(Thread.currentThread().getName().equals("Thread-2"))
		{   
		 if(!Exchange.b.isEmpty())
			{
				node u = new node(Exchange.b.front);
				//here cleanup thread operation
				while(u!=null)
				{
					float t=(((float)System.currentTimeMillis()/1000.0f)-checker.abst);
					if(u.qty==0 || u.texp+u.t0<t)
					{if(u==null) break;
					Exchange.b.remove(u);
					}
					try{
						FileOutputStream osc = new FileOutputStream("cleanup.out",true);
						PrintStream p = new PrintStream(osc);
						p.println(u.t0 +"  "+ u.name +"  "+u.texp+"  "+u.type+"  "+u.qty+"  "+u.st+"  "+u.price+"  "+u.partial);
						}catch(FileNotFoundException e){System.out.println(e);
										e.printStackTrace();}
					u=u.link;
				}
			}
			if(!Exchange.s.isEmpty())
			{
				node u = new node(Exchange.s.front);
				//here cleanup thread operation
				while(u!=null)
				{
					float t=(((float)System.currentTimeMillis()/1000.0f)-checker.abst);
					if(u.qty==0 || u.texp+u.t0<t)
					{
						if(u==null) break;
					Exchange.s.remove(u);
					}
					try{
						FileOutputStream osc = new FileOutputStream("cleanup.out",true);
						PrintStream p = new PrintStream(osc);
						p.println(u.t0 +"  "+ u.name +"  "+u.texp+"  "+u.type+"  "+u.qty+"  "+u.st+"  "+u.price+"  "+u.partial);
						}catch(FileNotFoundException e){System.out.println(e);
										e.printStackTrace();}
					u=u.link;
				}
			}
			
		}

		else 
		{	
			if(!checker.r.q.isEmpty())
			{node n =stock.q.dequeue();
			
			//here exchange thread operation
			//while(n!=null
				System.out.println("1");
				if(n.type.equalsIgnoreCase("sell"))
				{  
					if(!Exchange.b.isEmpty())
					{	node k=new node(Exchange.b.front);
						System.out.println("f");
						
						{if(n.texp+n.t0>(System.currentTimeMillis()/1000.0f-checker.abst))
							{
								int quan;
								int nn=0,zz;
								node z = new node();
								z.price = 0;
								while(k!=null)
								{ 	
									if(k.st.equals(n.st) && k.price>=n.price && k.texp+k.t0>(System.currentTimeMillis()/1000.0f-checker.abst))
										{
										 if(k.qty==n.qty)   //partial or non partial don't matter
											{
												if(k.price>=z.price)
												 {z=k; 
												nn=k.qty;
												zz=k.qty;}
											}
										 else if(k.qty<n.qty && n.partial.equals("T"))
											{
												if(k.price>=z.price)
												 {z=k; 
												  nn=k.qty;
												  zz=k.qty;
											}
										 else if(k.qty>n.qty && k.partial.equals("T"))
										 	{
												if(k.price>=z.price)
												 {z=k;
												 nn=n.qty;
												 zz=k.qty;
												 } 
											}
										}
										k=k.link;
										System.out.println("Called deal b and returned!");
								}
								//deal gets done //make the maximization better
								Exchange.dealb(z,n);
								//System.out.println("Called deal b and returned!");
								float time= System.currentTimeMillis()/1000.0f-checker.abst;
								try{
									FileOutputStream ose = new FileOutputStream("Exchange.out",true);
									PrintStream p = new PrintStream(ose);
									p.println("T"+"  "+time+"  "+nn+"  "+/*insert stautus here*/z.t0 +"  "+ z.name +"  "+z.texp+"  "+z.type+"  "+z.qty+"  "+z.st+"  "+z.price+"  "+z.partial);
									p.println("T"+"  "+time+"  "+nn+"  "+n.t0 +"  "+ n.name +"  "+n.texp+"  "+n.type+"  "+n.qty+"  "+n.st+"  "+n.price+"  "+n.partial);
					
									}catch(FileNotFoundException e){System.out.println(e);
										e.printStackTrace();}
							}
						}
					}
					if(n.qty!=0)
					Exchange.s.insert(n);
				}
				else 
				{ // int d=0;
					if(!Exchange.s.isEmpty())
					{	node k=new node(Exchange.s.front);
						System.out.println("fs");
						
						{if(n.texp+n.t0>(System.currentTimeMillis()/1000-checker.abst))
							{	int nn=0;
								node z = new node();
								z.price = 0;
								while(k!=null)
								{	
									if(k.st==n.st && k.price>n.price && k.texp+k.t0>(System.currentTimeMillis()/1000-checker.abst))
										{ 
										 if(k.qty==n.qty)   //partial or non partial don't matter
											{
												if(k.price<=z.price)
												 {z=k; 
												 	nn=k.qty;
												 }
											}
										 else if(k.qty<n.qty && n.partial.equals("T"))
											{
												if(k.price<=z.price)
												 {z=k;
												 	nn=k.qty;
												 }

											}
										 else if(k.qty>n.qty && k.partial.equals("T"))
										 	{
												if(k.price<=z.price)
												 {z=k;
												 nn=n.qty;
												 } 
											}
										}
										k=k.link;
										System.out.println("Called deal s and returned!");
								}
								//deal gets done //make the maximization better
								Exchange.deals(z,n);
								//System.out.println("Called deals and returned!");
								float time= System.currentTimeMillis()/1000.0f-checker.abst;
								try{
									FileOutputStream ose = new FileOutputStream("Exchange.out",true);
									PrintStream p = new PrintStream(ose);
									p.println(/*insert log char ,timestamp,status here*/"T"+"  "+time+"  "+nn+"  "+z.t0 +"  "+ z.name +"  "+z.texp+"  "+z.type+"  "+z.qty+"  "+z.st+"  "+z.price+"  "+z.partial);
									p.println("T"+"  "+time+"  "+nn+"  "+n.t0 +"  "+ n.name +"  "+n.texp+"  "+n.type+"  "+n.qty+"  "+n.st+"  "+n.price+"  "+n.partial);
					
									}catch(Exception e){System.out.println(e);}
							}
						}
					}
					if(n.qty!=0)
					Exchange.s.insert(n);
				}
				if(checker.r.q.isEmpty()) 
					{
						n=null;
						//break;
					}
				else n=checker.r.q.dequeue();
			}
			//}
			
			
		}
	}
}
	public static void order()
	{
		test t = new test();
		Thread threado = new Thread(t);
		
		Thread threade = new Thread(t);
		Thread threadc = new Thread(t);
		threado.setName("Thread-0");
		threade.setName("Thread-1");
		threadc.setName("Thread-2");

		threado.start();
		try{threado.join();}
		catch(InterruptedException e){e.printStackTrace();}
		threade.start();
		try{threade.join();}
		catch(InterruptedException e){e.printStackTrace();}
		threadc.start();
		try{threadc.join();}
		catch(InterruptedException e){e.printStackTrace();}
		

	}
}
