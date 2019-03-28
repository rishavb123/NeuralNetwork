package io.bhagat.test;

import io.bhagat.math.Function;
import io.bhagat.server.Server;
import io.bhagat.server.Server.ConnectionIndex;
import io.bhagat.server.Server.ServerThread;

public class ServerTest {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8000, 100);
		server.setCallback(new Function<ConnectionIndex, Object>() {

			@Override
			public Object f(ConnectionIndex x) {
				System.out.println("Connection " + x.getIndex() + ": " + x.getHostname() + " - " + x.getObject());
				for(ServerThread s: x.getParentServer().getThreads())
					if(s != x.getParent())
						s.send(x.getObject());
				return null;
			}
			
		});
		server.start();
    }

}
