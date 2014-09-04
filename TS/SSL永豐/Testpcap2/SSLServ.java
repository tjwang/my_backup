import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLServ {
  public static void main(String[] arstring) {

    int port = 9999;

    try {
      // 取得factory : 亦可由SSLContext產生
      SSLServerSocketFactory f = (SSLServerSocketFactory) SSLServerSocketFactory
          .getDefault();
      SSLServerSocket server = (SSLServerSocket) f.createServerSocket(port);
      SSLSocket s = (SSLSocket) server.accept();

      // 使用anonymous的cipher suite (測試用)
      s.setEnabledCipherSuites(s.getSupportedCipherSuites());

      InputStreamReader in = new InputStreamReader(s.getInputStream());
      BufferedReader reader = new BufferedReader(in);

      String str = null;
      while ((str = reader.readLine()) != null) {
        System.out.println(str);
        System.out.flush();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}