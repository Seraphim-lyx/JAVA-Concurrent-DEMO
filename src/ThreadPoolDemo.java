
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ThreadPoolExecutor fixedpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
//		ThreadPoolExecutor fixedpool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
			ThreadRun tr = new ThreadRun(new NIOServer());
			fixedpool.execute(tr);
			System.out.println("current Thread Num:"+fixedpool.getPoolSize());
			System.out.println("quening Thread Num:"+fixedpool.getQueue().size());
			System.out.println("finished Thread Num:"+fixedpool.getCompletedTaskCount());
			
		
	}
}

class ThreadTest implements Runnable {
	private int i;

	public ThreadTest(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("Executing thread-" + i);
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task-"+i+"executing finish");
	}
}
