package oneWayCommunication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        //setting up a new client socket
        Socket s = new Socket("localhost", 4999);

        //taking user input
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();

        //sending data to server
        DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
        outputStream.writeUTF(str);
        outputStream.flush();

        s.close();
    }
}