package io.bhagat.test;

import io.bhagat.server.Py4jNeuralNetworkServer;

public class ServerTest {

	public static void main(String[] args) throws Exception {
		Py4jNeuralNetworkServer server = new Py4jNeuralNetworkServer(2, 4, 1);
		server.start();
    }

}
