package threads;

import java.io.DataInputStream;
import java.io.IOException;

public class ReadThread implements Runnable {
    private DataInputStream in;

    public ReadThread(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = this.in.readUTF();
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
