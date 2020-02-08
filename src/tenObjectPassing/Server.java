package tenObjectPassing;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket ss = new ServerSocket(4999);
        System.out.println("Server is Waiting...");
        Socket s = ss.accept();
        System.out.println("Client has Connected");

        ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
        tenObjectPassing.Message obj = (Message) objectInputStream.readObject();
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

        System.out.println("header = " + obj.getHeader());
        System.out.println("data = " + obj.getData());
        System.out.println("ProtocolId = " + obj.getProtocolId());
        System.out.println("tailor = " + obj.isTailor());








        objectInputStream.close();
        s.close();
        ss.close();
    }

}
