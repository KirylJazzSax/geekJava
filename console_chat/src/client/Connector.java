package client;

import threads.ReadThread;
import threads.WriteThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Connector {
    private String ipAddress;
    private int port;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private List<String> messages = new ArrayList();

    public Connector(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;

        Scanner scanner = new Scanner(System.in);

        try {
            this.socket = new Socket(this.ipAddress, this.port);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            Thread read = new Thread(new ReadThread(this.in));
            Thread write = new Thread(new WriteThread(this.out, "client: "));

            read.start();
            write.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
