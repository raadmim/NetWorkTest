import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client  {
    static String message;
    static DataInputStream inputStream=null;
    static DataOutputStream outputStream=null;
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        Socket ClientSocket;
        try {
          ClientSocket = new Socket("LocalHost", 56446);
           inputStream=new DataInputStream(ClientSocket.getInputStream());
           outputStream=new DataOutputStream(ClientSocket.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Thread threadSend=new Thread(()->{

          while (true){

              try {
                  message=input.next();
                  outputStream.writeUTF(message);
                  outputStream.flush();
              }
              catch (IOException e){
                  e.printStackTrace();
              }
          }
        });
        Thread threadRead=new Thread(()->{
          while (true){
              try {
                  message= inputStream.readUTF();
                  System.out.println(message);
              }
              catch (IOException e){
                  //  e.printStackTrace();
              }
          }
        });
            threadRead.start();
            threadSend.start();
    }



}
