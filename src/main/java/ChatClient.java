import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private final String ADDRESS = "localhost";
    private final int PORT = 8189;

    public ChatClient() {
        try {
            Socket socket = new Socket(ADDRESS, PORT);
            System.out.println("Client entered");
            new Chat(socket, "Client X");
            while(true){
                if(socket.isClosed()){
                    break;
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}