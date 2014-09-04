import java.net.ServerSocket;
import java.net.Socket;
 
public class SocketServer extends java.lang.Thread {
 
    private boolean OutServer = false;
    private ServerSocket server;
    private final int ServerPort = 443;// �n�ʱ���port
 
    public SocketServer() {
        try {
            server = new ServerSocket(ServerPort);
 
        } catch (java.io.IOException e) {
            System.out.println("Socket�Ұʦ����D !");
            System.out.println("IOException :" + e.toString());
        }
    }
 
    public void run() {
        Socket socket;
        java.io.BufferedInputStream in;
 
        System.out.println("���A���w�Ұ� !");
        while (!OutServer) {
            socket = null;
            try {
                synchronized (server) {
                    socket = server.accept();
                }
                System.out.println("���o�s�u : InetAddress = "
                        + socket.getInetAddress());
                // TimeOut�ɶ�
                socket.setSoTimeout(15000);
 
                in = new java.io.BufferedInputStream(socket.getInputStream());
                OutputStream os = socket.getOutputStream();
                byte[] b = new byte[4096];
                String data = "";
                int length = 0;
                while ((length = in.read(b)) > 0)// <=0���ܴN�O�����F
                {
                    if(length<4096)
                    {
                    
                    }  
                }
 
                System.out.println("�ڨ��o����:" + data);
                in.close();
                in = null;
                socket.close();
 
            } catch (java.io.IOException e) {
                System.out.println("Socket�s�u�����D !");
                System.out.println("IOException :" + e.toString());
            }
 
        }
    }
 
    public static void main(String args[]) {
        (new SocketServer()).start();
    }
 
}