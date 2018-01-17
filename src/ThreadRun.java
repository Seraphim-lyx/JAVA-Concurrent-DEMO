public class ThreadRun extends Thread {
	NIOServer nioserver;
	public static final String READ = "READ";
	public static final String WRITE = "WRITE";
	public static final String RUN = "RUN";
	private String target;

	public ThreadRun(NIOServer server, String target) {
		this.nioserver = server;
		this.target = target;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (target.equals(RUN)) {
			nioserver.serverRun();
		}
		else if(target.equals(READ)){
			nioserver.testRead();
		}
	}

}
