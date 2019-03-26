package io.bhagat.androidneuralnetwork.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

//TODO finish and document

public class StreamUtil {

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
	
	public static String readString(InputStream stream)
	{
		return read(new InputStreamReader(stream));
	}
	
	

}
