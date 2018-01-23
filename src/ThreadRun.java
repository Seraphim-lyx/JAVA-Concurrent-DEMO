public class ThreadRun implements Runnable{
	NIOServer nioserver;
	public static final String READ = "READ";
	public static final String WRITE = "WRITE";
	public static final String RUN = "RUN";
	private String target;

	public ThreadRun(NIOServer server) {
		this.nioserver = server;
//		this.target = target;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		nioserver.serverRun();
	}

}
