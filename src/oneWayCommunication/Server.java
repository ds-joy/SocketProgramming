package oneWayCommunication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {


        ServerSocket ss = new ServerSocket(4999); //setting up server socket
        System.out.println("Server is waiting...");
        Socket s = ss.accept(); //establishing the connection with client
        System.out.println("Client has connected.");

        //receiving the data sent by the user
        String str;
        DataInputStream input = new DataInputStream(s.getInputStream());
        str = input.readUTF();

        //printing the received data
        System.out.println("Client: " + str);

        input.close();
        s.close();
        ss.close();

    }
}