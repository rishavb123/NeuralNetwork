package io.bhagat.server;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import io.bhagat.util.StreamUtil;

//TODO finish this, document, and make a streamHolder class and then an arraylist so that each connection can be stored
/**
 * @author Bhagat
 *
 */
public class Server extends Thread{

	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	private Reader reader;
	private PrintStream printStream;
	
	private ServerSocket server;
	private Socket socket;
	
	private int port;
	private int maxPeople;
	
	private boolean logs;
	
	public Server(int port, int maxPeople)
	{
		this.port = port;
		this.maxPeople = maxPeople;
		logs = false;
	}
	
	public Server(int port, int maxPeople, boolean logs)
	{
		this.port = port;
		this.maxPeople = maxPeople;
		this.logs = logs;
	}
	
	public void run()
	{
		try {
			server = new ServerSocket(port, maxPeople);
			while(true)
			{
				try {
					log("Waiting to connect . . .");
					socket = server.accept();
					log("Connected to "+socket.getInetAddress().getHostName());
					log("Setting up streams . . .");
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.flush();
					objectInputStream = new ObjectInputStream(socket.getInputStream());
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					printStream = new PrintStream(socket.getOutputStream());
					log("Streams are now set up");
				} catch (EOFException eof) {
					log("done");
				} finally {
					log("Closing Connections");
					try {
						objectInputStream.close();
						objectOutputStream.close();
						reader.close();
						printStream.close();
						socket.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String msg)
	{
		printStream.println(msg);
	}
	
	public String read()
	{
		return StreamUtil.read(reader);
	}
	
	public <E> void sendObject(E obj)
	{
		try {
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			log(obj.toString());
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Object readObject()
	{
		try {
			return objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void log(String s)
	{
		if(logs)
			System.out.println(s);
	}
	
}
