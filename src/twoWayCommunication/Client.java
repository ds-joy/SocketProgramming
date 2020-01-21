package twoWayCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        //setting up a new client socket
        Socket s = new Socket("localhost", 4999);


        String str = "";
        Scanner scanner = new Scanner(System.in);
        DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
        DataInputStream inputStream = new DataInputStream(s.getInputStream());

        //the communication will continue until
        //server or client says "Done"
        while (!str.equals("Done")) {

            //sending data to server
            str = scanner.nextLine();
            outputStream.writeUTF(str);
            outputStream.flush();

            if(str.equals("Done")) {
                break;
            } else {
                //receiving data from server
                str = inputStream.readUTF();
                System.out.println("Server: " + str);
            }
        }

        inputStream.close();
        outputStream.close();
        s.close();
    }
}