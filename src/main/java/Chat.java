import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Chat {
    private Scanner in;
    private Scanner input;
    private PrintWriter out;
    private Thread threadIn;
    private Thread threadOut;

    public Chat(Socket socket, String name) {
        try {
            in = new Scanner(socket.getInputStream());
            input = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadOut = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (input.hasNext()) {
                        String str = input.nextLine().trim();
                        sendMsg(name + ": " + str);
                        if (str.equalsIgnoreCase("/end"))
                            break;
                    }
                }
                close(socket);
            }
        });

        threadOut.start();

        threadIn = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (in.hasNext()) {
                        String str = in.nextLine().trim();
                        System.out.println(str);
                        if (str.contains("/end"))
                            break;
                    }
                }
                close(socket);
            }
        });

        threadIn.start();
    }

    private void sendMsg(String str) {
        out.println(str);
        out.flush();
    }
    private void close(Socket socket){
        threadIn.interrupt();
        threadOut.interrupt();
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}