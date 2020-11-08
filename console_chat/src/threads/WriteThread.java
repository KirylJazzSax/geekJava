package threads;

import interfaces.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteThread implements Runnable, Message {
    private DataOutputStream out;
    private String prefix = "";

    public WriteThread(DataOutputStream out) {
        this.out = out;
    }

    public WriteThread(DataOutputStream out, String prefix) {
        this.out = out;
        this.prefix = prefix;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String str = scanner.nextLine();
            this.sendMessage(str);
        }
    }

    @Override
    public void sendMessage(String msg) {
        try {
            if (msg.length() > 0) {
                String res = !this.prefix.isEmpty() ? this.prefix + msg : msg;
                this.out.writeUTF(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
