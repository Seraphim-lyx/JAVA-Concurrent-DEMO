import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NIOClient{
	private static final int port = 3456;
	private static final String addr = "127.0.0.1";
	private static final int timeout = 3000;


	public NIOClient() {
		

	}

	public static void Send(SocketChannel sc) throws InterruptedException {
		String sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
		ByteBuffer bytebuffer = ByteBuffer.allocate(1024);
		bytebuffer.put(sdf.getBytes());
		bytebuffer.flip();
		try {
				while(bytebuffer.hasRemaining())
				sc.write(bytebuffer);
				System.out.println("writing finish");
//				sc.close();
//				Thread.sleep(4000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

	public void read() {

	}


	public void run() {
		
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		try {
			SocketChannel sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress(addr, port));
			if(sc.finishConnect()){
				while(true){
					Send(sc);
					Thread.sleep(1000);
//					sc.close();
				}
				
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
