import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer{
	private static final int port = 3456;
	private static final int timeout = 3000;
	private static final int buffsize = 1024;

	public static void serverRun() {
		/**
		 * build server channel for socket connection build selector to monitor
		 * socket behaviors in channels
		 */
		Selector selector = null;
		ServerSocketChannel ssc = null;
		try {
			selector = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress(port));
			ssc.configureBlocking(false);
			ssc.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				if (selector.select(timeout) == 0) {
					System.out.println("server is waiting...");
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys()
						.iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					iter.remove();
					if (key.isConnectable()) {
						System.out.println("server is connectable");
					}
					if (key.isAcceptable()) {
						serverAccept(key);
					}
					if (key.isReadable()) {
						serverRead(key);
					}
					if (key.isValid() && key.isWritable()) {
						serverWrite(key);
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (selector != null) {
					selector.close();
				}
				if (ssc != null) {
					ssc.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void serverAccept(SelectionKey key) throws IOException {
		/**
		 * handling socket accept for new socket
		 */
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		sc.register(key.selector(), SelectionKey.OP_READ,
				ByteBuffer.allocateDirect(buffsize));
		String address = sc.getRemoteAddress().toString();
		System.out.println("connection from" + address + "accepted");

	}

	public static void serverRead(SelectionKey key) throws IOException {
		/**
		 * handling socket reading from client
		 */
		System.out.println("server reading...");
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		System.out.println();
		long offset = sc.read(buffer);
		while (offset > 0) {

			buffer.flip();
			String str = "";
			while (buffer.hasRemaining()) {
				str += (char) buffer.get();
			}
			System.out.println(str);
			buffer.clear();
			offset = sc.read(buffer);

		}

		if (offset == 0) {
			System.out.println("reading finish");
			
//			sc.close();
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
//			sc.register(key.selector(), SelectionKey.OP_WRITE,
//					ByteBuffer.allocateDirect(buffsize));
		}

	}

	public static void serverWrite(SelectionKey key) throws IOException {
		/**
		 * handling data sending from server
		 */
		key.interestOps(SelectionKey.OP_READ);
		System.out.println("writing...");
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		buffer.clear();
		String msg = "success\n";
		sc.write(ByteBuffer.wrap(msg.getBytes()));
		System.out.println("writing finish");
		buffer.compact();
		buffer.clear();

	}
	
	public static void testRead(){
		System.out.println("read");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		serverRun();

	}

}
