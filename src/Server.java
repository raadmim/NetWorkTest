import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Vector;

public class Server{

    static Vector<ClientHandler> clients=new Vector<>();// for keeping clients(clientHandlers)
    
        public static void main(String[] args) throws IOException {

            int clientId=0;
            ServerSocket serverSocket=new ServerSocket(56446);
            Socket socket=null;
            while (true){
                socket=serverSocket.accept();// program will wait in this line until any client send any request
            //    System.out.println("new client");
                DataInputStream inputStream=new DataInputStream(socket.getInputStream());//creating InputStream for getting Objects from clients
                DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());//creating OutputStream for sending Objects to clients
                ClientHandler clientHandler=new ClientHandler(clientId,socket,inputStream,dataOutputStream);
                Thread thread=new Thread(clientHandler);
                clients.add(clientHandler);
                System.out.println("new client id : "+clients.get(clientId).id);
                thread.start();
                clientId++;
            }
        }




}
class ClientHandler implements Runnable{
    int id;
    Socket socket;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String text;
    boolean isLogedIn;
    ClientHandler(int id,Socket socket,DataInputStream inputStream,DataOutputStream outputStream){
        this.id=id;
        this.inputStream=inputStream;
        this.outputStream=outputStream;
        this.socket=socket;
        isLogedIn=true;

    }
    @Override
    public void run() {
        String recievedText="";
        while (true) {
           // System.out.println(Server.clients.size());
            try {
                recievedText = inputStream.readUTF();//recieve message from client ( in shape of message@#)
                String[] splited = recievedText.split("@");
                System.out.println("recieved Text from "+this.id+"to "+""+Integer.parseInt(splited[1])+"  : "+recievedText);
                for (ClientHandler clientHandler : Server.clients) {//'for' to find the destination client
                    int temp=Integer.parseInt(splited[1]);
                    if (clientHandler.id == temp) {
                      //  System.out.println("here tar");
                        clientHandler.outputStream.writeUTF(this.id + " : " + splited[0]);//sending recieved message to destination client
                        outputStream.flush();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (recievedText.equals("end")){
                try {

                    inputStream.close();//closing streams
                    outputStream.close();
                    System.out.println("streams closed");
                    break;
                } catch (IOException e) {

                }

        }
        }



    }
}