package io.bhagat.test;

import java.util.Scanner;

import io.bhagat.server.Client;

public class ClientTest {

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		Client client = new Client("192.168.1.33", 8000);
		client.start();
		String msg;
		while(!(msg = scanner.nextLine()).equals("exit") && client.isAlive())
		{
			client.send(msg);
		}
		client.close();
		scanner.close();
	}

}
