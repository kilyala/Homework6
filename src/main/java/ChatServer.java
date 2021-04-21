import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public ChatServer(){
        ServerSocket serverSocket = null;
        Socket socket;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Server is launching");
            socket = serverSocket.accept();
            System.out.println("Client connected");
            new Chat(socket, "Server X");
            while(true){
                if(socket.isClosed()){
                    break;
                }
            }
            serverSocket.close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Server initialization error");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}