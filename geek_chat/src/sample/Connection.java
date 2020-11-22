package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private String ipAddress;
    private int port;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Connection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;


        try {
            this.socket = new Socket(this.ipAddress, this.port);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

//            new Thread(() -> {
//                while (true) {
//                    try {
//                        String str = this.in.readUTF();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getInStream() {
        return this.in;
    }

    public DataOutputStream getOutStream() {
        return this.out;
    }

    public Socket getSocket() { return this.socket; }

    public boolean established() {
        if (this.socket == null) {
            return false;
        }
        return this.socket.isConnected();
    }
}
