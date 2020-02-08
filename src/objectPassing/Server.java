package objectPassing;

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
        Message obj = (Message) objectInputStream.readObject();
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

        System.out.println("header = " + obj.getHeader());
        System.out.println("data = " + obj.getData());
        System.out.println("ProtocolId = " + obj.getProtocolId());
        System.out.println("tailor = " + obj.isTailor());

        String binaryHeader = Integer.toBinaryString(obj.getHeader());
        String binaryPid = Integer.toBinaryString(obj.getProtocolId());
        String binaryData = obj.getData();
        int length = binaryData.length();

        String[] arr = new String[10];
        for (int i = 0; i<length; i++) {
            arr[i] = Integer.toBinaryString((int)(binaryData.charAt(i)));
        }

        int flag = 0;
        flag += oneCount(binaryHeader);
        flag += oneCount(binaryPid);

        for (int i = 0; i<length; i++) {
            flag += oneCount(arr[i]);
        }


        boolean bool = (flag % 2) == 0;
        if((bool == obj.isTailor())){
            dataOutputStream.writeUTF("The calculation is correct");
        } else {
            dataOutputStream.writeUTF("The calculation is wrong");
        }

        objectInputStream.close();
        s.close();
        ss.close();
    }

    public static int oneCount(String str) {

        int flag = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '1') {
                flag++;
            }
        }
        //System.out.println("string: " + str + " flag: " + flag);
        return flag;
    }
}
