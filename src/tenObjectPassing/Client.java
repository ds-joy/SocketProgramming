package tenObjectPassing;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 4999);
        tenObjectPassing.Message msg = new Message(36, "Hello", 16, true);




        ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
        outputStream.writeObject(msg);

        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
        String str = dataInputStream.readUTF();

        System.out.println(str);



        outputStream.close();
        dataInputStream.close();
        s.close();
    }


}

