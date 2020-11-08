package server;

import threads.ReadThread;
import threads.WriteThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private int port;

    public Server(int port) {
        this.port = port;

        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Server has been started " + this.server.getLocalSocketAddress());

            this.socket = this.server.accept();
            System.out.println("Client has been connected " + this.socket.getRemoteSocketAddress());
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            Thread read = new Thread(new ReadThread(this.in));
            Thread write = new Thread(new WriteThread(this.out, "server: "));

            read.start();
            write.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
