package be.ehb.kameelrace;

import java.util.ArrayList;

public class KameelAankomstSingleton {

	private static ArrayList<String> kamelen;
	
	private KameelAankomstSingleton()
	{
		kamelen = new ArrayList<String>();
	}
	
	public static void addKameel(String naam)
	{
		if(kamelen == null)new KameelAankomstSingleton();
		
		kamelen.add(naam);
	}
	
	public static void newRace()
	{		
		if(kamelen == null)new KameelAankomstSingleton();
		
		kamelen.clear();
	}

	public static String aankomst() 
	{
		if(kamelen == null)new KameelAankomstSingleton();
		
		String result ="";
		int lengteLijst = kamelen.size();
		
		for(int i = 0; i < lengteLijst; i++)
		{
			result += (i+1) + " =>  "+kamelen.get(i)+"\n";
		}
		
		return result;
	}

	
	
	
	
}
