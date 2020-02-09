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
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());

        Message[] obj = new Message[10];


        for(int i=0; i<10; i++){
            obj[i] = (Message) objectInputStream.readObject();

            parityCheck(obj[i]);
            if(obj[i].getAcknowledgement().equals("correct")){
                printObject(obj[i], i+1);
            } else {
                i -= 1;
            }
            dataOutputStream.writeUTF(obj[i].getAcknowledgement());

        }


        objectInputStream.close();
        dataOutputStream.close();
        s.close();
        ss.close();
    }

    private static void printObject(Message obj, int n) {
        System.out.println();
        System.out.println("Object " + n);
        System.out.println("header = " + obj.getHeader());
        System.out.println("data = " + obj.getData());
        System.out.println("ProtocolId = " + obj.getProtocolId());
        System.out.println("tailor = " + obj.isTailor());
        System.out.println();
    }

    private static void parityCheck(Message msg) {
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
        boolean bool = flag%2 == 0;

        if(bool == msg.isTailor()){
            msg.setAcknowledgement("correct");
        } else {
            msg.setAcknowledgement("wrong");
        }
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
