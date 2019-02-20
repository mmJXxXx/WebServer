import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class TCP_test {
    public static void main(String[] args) {
        int port=7400;
       // String ss = null;
        Socket socket = null;
        try {
            socket = new Socket("119.23.110.11", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Send Message & Close socket*/
        try {
            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            String reply=null;
          //  while(!((reply=br.readLine())==null)){
          //      System.out.println(reply);
          //  }
            printWriter.print("{\"N\":3,\"T\":32.4,\"H\":65.5,\"V\":643,\"C\":123.4,\"P\":101.11}"+"\n");
           //printWriter.println("As the moon,so beautiful");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
