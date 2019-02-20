package ServerII;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            int port = 7400;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.print("Port:"+port+">");
            // 循环的监听
            while (true) {
                Socket socket = serverSocket.accept();// 阻塞
                System.out.println("ip:"+socket.getInetAddress().
                        toString().replace("/", "")+" is connected."+"<br>");
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                //把socket加入ChatManager
                ChatManager.getChatManager().add(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //Thread.interrupted();
        }
    }
}