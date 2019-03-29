package io.bhagat.projects.audio;

import io.bhagat.math.Function;
import io.bhagat.server.Server;
import io.bhagat.server.Server.ConnectionIndex;
import io.bhagat.util.ArrayUtil;

public class ServerRunner {

	public static void main(String[] args) {
		Server server = new Server(8000, 10);
		
		server.setCallback(new Function<ConnectionIndex, Object>() {

			@Override
			public Object f(ConnectionIndex x) {
				if(x.getObject() instanceof String)
					System.out.println("Connection " + x.getIndex() + ": " + x.getHostname() + " - " + x.getObject());
				else
					ArrayUtil.printArr((short[])x.getObject());
				return null;
			}
			
		});
		
		server.start();
	}

}
