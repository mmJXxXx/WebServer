package ServerII;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class MyServerSocket {
    public static void main(String[] args) {
        try {
            System.out.println(">>Local Server IP:"+InetAddress.getLocalHost().getHostAddress()+"<br>");
            System.out.println(">>Server is Running..."+"<br>");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //启动服务器监听线程
        new ServerListener().start();
        System.out.println("Start listening..."+"<br>");
    }
}