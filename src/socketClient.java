import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class socketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host = "127.0.0.1";
		Integer port = 3456;
		int i = 0;
		try {
			Socket socket = new Socket(host, port);

		 while (true) {
//			Socket socket = new Socket(host, port);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			// String str = "test";
			pw.println(str);
			pw.flush();

			Thread.sleep(4000);

			InputStreamReader isr = new InputStreamReader(
					socket.getInputStream());
			//
			BufferedReader br = new BufferedReader(isr);
			String str1 = null;
			System.out.println(br.readLine());
			
		
		 }
//		
//			socket.close();
//			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
