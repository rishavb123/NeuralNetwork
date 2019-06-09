package io.bhagat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

//TODO finish
/**
 * A utility class for output and input streams
 * @author Bhagat
 */
public class StreamUtil {

	/**
	 * reads the string content from a reader
	 * @param reader the reader
	 * @return the string content
	 */
	public static String read(Reader reader)
	{
		try
		{
			BufferedReader bufferedReader = new BufferedReader(reader);
			String text = "";
			String s;
			while((s = bufferedReader.readLine())!=null)
			{
				text+=s+"\n";
			}
			bufferedReader.close();
			return text.substring(0, text.length() - 1);

		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * reads the string content from an input stream
	 * @param stream the steam
	 * @return the string content
	 */
	public static String readString(InputStream stream)
	{
		return read(new InputStreamReader(stream));
	}
	
	

}
