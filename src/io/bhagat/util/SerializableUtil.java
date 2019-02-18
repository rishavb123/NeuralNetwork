package io.bhagat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A class that will use static methods to serialize and deserialize objects
 * @author Bhagat
 *
 */
public class SerializableUtil {

	/**
	 * will serialize an object into a file
	 * @param obj the object to serialize
	 * @param filename the filename of where to store the object
	 * @throws IOException if it is thrown by writing the object
	 */
	public static void serialize(Serializable obj, String filename) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream("objects/" + filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(obj);
		
		objectOutputStream.close();
		fileOutputStream.close();
	}
	
	/**
	 * retrieves a serialized object from the disk
	 * @param filename the filename where the object is stored
	 * @param <T>x the type of the object
	 * @return the object
	 * @throws ClassNotFoundException the retrieved object is not of a known class
	 * @throws IOException if an IOException is thrown during the reading of the object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String filename) throws ClassNotFoundException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream("objects/" + filename); 
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
        Object obj = objectInputStream.readObject();
        
        objectInputStream.close();
        fileInputStream.close();
        
		return (T) obj;
	}
	
}
