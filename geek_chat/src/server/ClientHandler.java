package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String str = this.in.readUTF();
                        this.server.broadcastMsg(this, str);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Client disconnected! " + this.socket.getRemoteSocketAddress());
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) throws IOException {
        this.out.writeUTF(msg);
    }
}
