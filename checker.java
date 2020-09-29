import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.*;

public class checker
{
	public static float abst = 0.0f;
	public static stock r = new stock();
	public static boolean exhausted = false;
	public static void main ( String args[])
	{	  abst = (float)(System.currentTimeMillis()/1000.0f); 
		BufferedReader br = null;
		 //public static stock r = new stock();
		//float abst = (float)(System.currentTimeMillis()/1000.0f); 
		try 
		{
			 String actionString;
			br = new BufferedReader(new FileReader("input.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
			exhausted=true;
		}
		 catch (IOException e) 
		 {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
