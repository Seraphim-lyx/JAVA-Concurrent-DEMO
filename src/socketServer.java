import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class socketServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket ss = new ServerSocket(3456);
//			ss.setSoTimeout(3000);
//			Socket s = ss.accept();
			while (true) {
				
//				s.setSoTimeout(3);
				
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String info = null;
				
				System.out.println(br.readLine());
				
				OutputStream os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.println("success");
				pw.flush();
//				Thread.sleep(1000);
				s.close();
				
//				pw.close();
			}
			// s.shutdownInput();
			// br.close();
			// is.close();
			// isr.close();
			// s.close();
			// ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
