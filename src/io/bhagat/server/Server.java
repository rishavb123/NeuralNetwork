package io.bhagat.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import io.bhagat.math.Function;

// TODO document this

public class Server extends Thread{
	
	private ServerSocket server;
	private ArrayList<ServerThread> threads;
	
	private int port;
	
	private Function<ConnectionIndex, Object> callback;
	
	public Server(int port, int backlog)
	{
		this.port = port;
		threads = new ArrayList<>();
		try {
			server = new ServerSocket(port,backlog);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		callback = new Function<ConnectionIndex, Object>() {

			@Override
			public Object f(ConnectionIndex x) {
				System.out.println("Connection " + x.getIndex() + ": " + x.getHostname() + " - " + x.getObject());
				return null;
			}
			
		};
	}
	
	public void run()
	{
		System.out.println("Server started on port " + port);
		try {
			while(true)
			{
				try {
					Socket s = server.accept();
					ServerThread serverThread = new ServerThread(s, this);
					threads.add(serverThread);
					serverThread.start();
				} catch (EOFException eof) {
					eof.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}	
	
	public void close()
	{
		for(ServerThread s: threads)
			s.close();
	}
	
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the callback
	 */
	public Function<ConnectionIndex, Object> getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(Function<ConnectionIndex, Object> callback) {
		this.callback = callback;
	}

	/**
	 * @return the threads
	 */
	public ArrayList<ServerThread> getThreads() {
		return threads;
	}

	public class ServerThread extends Thread {

		private Socket connection;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private Object readObject;
				
		private ConnectionIndex connectionIndex;
		
		private volatile boolean stop;
		
		public ServerThread(Socket socket, Server parentServer) {
			stop = false;
			connection = socket;
			setupStreams();
			connectionIndex = new ConnectionIndex(connection, this, parentServer);
			System.out.println("Connection " + threads.size() + ": " + connectionIndex.getHostname() + " joined");
		}
		
		public Object read()
		{
			return readObject;
		}
		
		public void run()
		{
			while(!stop) {
				try {
					synchronized(input) {
						readObject = input.readObject();					
						connectionIndex.setObject(readObject);
						send(callback.f(connectionIndex));
					}
					
				} catch (EOFException | SocketException e) {
					System.out.println("Connection " + connectionIndex.getIndex() + ": " + connectionIndex.getHostname() + " left");
					close();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void send(Object obj)
		{
			synchronized(output) {
				try {
					if(obj != null)
						output.writeObject(obj);
					output.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void close()
		{
			stop = true;
			
			try {
				input.close();
				output.close();
				connection.close();
				threads.remove(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		private void setupStreams() {
			try {
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * @return the connectionIndex
		 */
		public ConnectionIndex getConnectionIndex() {
			return connectionIndex;
		}

		/**
		 * @param connectionIndex the connectionIndex to set
		 */
		public void setConnectionIndex(ConnectionIndex connectionIndex) {
			this.connectionIndex = connectionIndex;
		}

	}

	public class ConnectionIndex {
		
		private Object object;
		private Socket connection;
		private String hostname;
		private String host;
		
		private ServerThread parent;
		private Server parentServer;
		
		public ConnectionIndex(Socket connection, ServerThread parent, Server parentServer) {
			this.connection = connection;
			hostname = connection.getInetAddress().getHostName();
			host = connection.getInetAddress().getHostAddress();
			this.parent = parent;
			this.parentServer = parentServer;
		}

		/**
		 * @return the index in the arraylist of server threads
		 */
		public int getIndex() {
			return threads.indexOf(parent);
		}
		
		/**
		 * @return the object
		 */
		public Object getObject() {
			return object;
		}

		/**
		 * @param object the object to set
		 */
		public void setObject(Object object) {
			this.object = object;
		}

		/**
		 * @return the connection
		 */
		public Socket getConnection() {
			return connection;
		}

		/**
		 * @return the hostname
		 */
		public String getHostname() {
			return hostname;
		}

		/**
		 * @return the host
		 */
		public String getHost() {
			return host;
		}

		/**
		 * @return the parent
		 */
		public ServerThread getParent() {
			return parent;
		}

		/**
		 * @return the parentServer
		 */
		public Server getParentServer() {
			return parentServer;
		}
		
	}
	
}
