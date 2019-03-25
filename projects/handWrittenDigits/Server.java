import io.bhagat.server.Py4jNeuralNetworkServer;

public class Server {
    public static void main(String[] args) {
        Py4jNeuralNetworkServer server = new Py4jNeuralNetworkServer(784, 10);
        server.start();
    }
}