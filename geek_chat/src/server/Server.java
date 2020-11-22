package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private int port;
    private List<ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        this.clients = new CopyOnWriteArrayList<>();

        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Server has been started " + this.server.getLocalSocketAddress());

            while (true) {
                this.socket = this.server.accept();
                this.clients.add(new ClientHandler(this, this.socket));
                System.out.println("Client has been connected " + this.socket.getRemoteSocketAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server closed!");
            try {
                this.server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(ClientHandler sender, String msg) throws IOException {
        String message = String.format("%s : %s", "sender", msg);

        for (ClientHandler c : this.clients) {
            c.sendMessage(message);
        }
    }
}
