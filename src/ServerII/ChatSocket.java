package ServerII;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ChatSocket extends Thread {
    Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    ChatSocket(Socket s) {
        this.socket = s;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    this.socket.getOutputStream(), StandardCharsets.UTF_8));
            br = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送数据
    void out(String out) {
        try {
                bw.write(out + "\n");// 必须要加换行符号,不然数据发不出去
                bw.flush();
            /*若关闭输入输出流，则socket会被关闭*/
            //bw.close();
        } catch (IOException e) {
            /*此处，未发出消息则放弃*/
            //System.out.println("ERROR_socket_out");
            //e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //str_file strf = new str_file();
            out("echo Success");
            String line = null;
            while((line = br.readLine()) != null) {//监听客户端发来的数据
                if(isSocketStr(line)){
                    JSONObject jsonObject = JSONObject.fromObject(line);
                    String file = "./in/."+jsonObject.get("N")+"data.json";
                        exStr2JS(line,file);
                    System.setOut(new PrintStream(new BufferedOutputStream(
                            new FileOutputStream(FileDescriptor.out)),true));
                    System.out.println(dateFormat.format(date.getTime())+" "+"ReceiveSuccess."+"<br>");

                }else{
                    System.setOut(new PrintStream(new BufferedOutputStream(
                            new FileOutputStream(FileDescriptor.out)),true));
                    System.out.println("FormatError!");
                }
               // System.out.println(dateFormat.format(date.getTime()) + " " + "Client:" + line);
                ChatManager.getChatManager().publish(this, "echoSuccess");
            }
        } catch (IOException ignore) {}
    }
    Boolean isSocketStr(String str) {
        return (str.startsWith("{") && str.endsWith("}") && str.substring(2,str.length()).startsWith("N"));
    }

    void exStr2JS(String str,String file){
        try {
            System.setOut(new PrintStream(new BufferedOutputStream(
                    new FileOutputStream(file)),true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

}
