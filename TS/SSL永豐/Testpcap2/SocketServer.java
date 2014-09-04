import java.net.ServerSocket;
import java.net.Socket;
 
public class SocketServer extends java.lang.Thread {
 
    private boolean OutServer = false;
    private ServerSocket server;
    private final int ServerPort = 443;// 要監控的port
 
    public SocketServer() {
        try {
            server = new ServerSocket(ServerPort);
 
        } catch (java.io.IOException e) {
            System.out.println("Socket啟動有問題 !");
            System.out.println("IOException :" + e.toString());
        }
    }
 
    public void run() {
        Socket socket;
        java.io.BufferedInputStream in;
 
        System.out.println("伺服器已啟動 !");
        while (!OutServer) {
            socket = null;
            try {
                synchronized (server) {
                    socket = server.accept();
                }
                System.out.println("取得連線 : InetAddress = "
                        + socket.getInetAddress());
                // TimeOut時間
                socket.setSoTimeout(15000);
 
                in = new java.io.BufferedInputStream(socket.getInputStream());
                OutputStream os = socket.getOutputStream();
                byte[] b = new byte[4096];
                String data = "";
                int length = 0;
                while ((length = in.read(b)) > 0)// <=0的話就是結束了
                {
                    if(length<4096)
                    {
                    
                    }  
                }
 
                System.out.println("我取得的值:" + data);
                in.close();
                in = null;
                socket.close();
 
            } catch (java.io.IOException e) {
                System.out.println("Socket連線有問題 !");
                System.out.println("IOException :" + e.toString());
            }
 
        }
    }
 
    public static void main(String args[]) {
        (new SocketServer()).start();
    }
 
}