package io.bhagat.projects.audio;

import io.bhagat.server.Server;

public class ServerRunner {

	public static void main(String[] args) {
		Server server = new Server(8000, 10);
		server.start();
	}

}
