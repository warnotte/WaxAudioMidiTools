package org.warnotte.waxaudiomiditools.Tool;


import java.io.*;

public class es
{
	/**
	 * Reading of a Character.
	 */
	public static char readChar()
	{
		return readChar("");
	}
	/**
	 * Reading of a String.
	 * @param message Message to be printed before the reading of the value
	 */
	public static char readChar(String message)
	{
		String s = readString("");
		if(s != null && s.length() >= 1)
			return s.charAt(0);
		return (char)-1;
	}
	
	/**
	 * Reading of a String.
	 */
	public static String readString()
	{
		return readString("");
	}
	/**
	 * Reading of a String.
	 * @param message Message to be printed before the reading of the value
	 */
	public static String readString(String msg)
	{  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		if(msg != "")
			System.out.print(msg);
		
		String s = "";
		try
		{
			s = br.readLine();
		}
		catch(IOException ioe)
		{
			System.err.println("\n es.java / readString - Error of reading !");
		}
		
		return s;
	}
	
	/**
	 * Reading of an integer
	 */
	public static int readInt() throws NumberFormatException
	{
		return readInt("");
	}
	/**
	 * Reading of an integer
	 * @param message Message to be printed before the reading of the value
	 */
	public static int readInt(String message) throws NumberFormatException
	{
		int entier = Integer.MIN_VALUE;
		
		String read = readString(message);
		entier = Integer.parseInt(read);
		
		return entier;
	}
	
	/**
	 * Same than readInt() but catch the exceptions.
	 * In case of an exception caught, this method return Integer.MIN_VALUE
	 * @return Integer.MIN_VALUE when an exception happens.
	 */
	public static int readInt_()
	{
		return readInt_("");
	}
	/**
	 * Same than readInt(String) but catch the exceptions.
	 * In case of an exception caught, this method return Integer.MIN_VALUE
	 * @return Integer.MIN_VALUE when an exception happens.
	 */
	public static int readInt_(String message)
	{
		int value = Integer.MIN_VALUE;
		try
		{
			value = es.readInt(message);
		}
		catch(NumberFormatException nfe)
		{
			System.err.println("\n es.java / readInt_ - Error of reading !");
		}
		return value;
	}
}