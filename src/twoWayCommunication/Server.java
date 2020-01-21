package twoWayCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(4999); //setting up server socket
        System.out.println("Server is waiting...");
        Socket s = ss.accept(); //establishing the connection with client
        System.out.println("Client has connected.");

        //receiving the data sent by the user
        String str = "";
        Scanner scanner = new Scanner(System.in);
        DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
        DataInputStream inputStream = new DataInputStream(s.getInputStream());

        //the communication will continue until
        //server or client says "Done"
        while (!str.equals("Done")) {

            //receiving data from server
            str = inputStream.readUTF();
            System.out.println("Client: " + str);

            if(str.equals("Done")) {
                break;
            } else {
                //sending data to server
                str = scanner.nextLine();
                outputStream.writeUTF(str);
                outputStream.flush();
            }
        }


        inputStream.close();
        outputStream.close();
        s.close();
        ss.close();

    }
}
