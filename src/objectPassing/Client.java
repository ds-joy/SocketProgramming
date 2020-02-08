package objectPassing;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 4999);
        Message msg = new Message(36, "Hello", 16, true);


        //toBitString
        String binaryHeader = Integer.toBinaryString(msg.getHeader());
        String binaryPid = Integer.toBinaryString(msg.getProtocolId());
        String binaryData = msg.getData();
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

//

        if(flag%2 == 0 ){
            msg.setTailor(true);
        } else {
            msg.setTailor(false);
        }


        ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
        outputStream.writeObject(msg);

        DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
        String str = dataInputStream.readUTF();

        System.out.println(str);



        outputStream.close();
        dataInputStream.close();
        s.close();
    }

    public static int oneCount(String str) {

        int flag = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '1') {
                flag++;
            }
        }
//        System.out.println("string: " + str + " flag: " + flag);
        return flag;
    }
}
