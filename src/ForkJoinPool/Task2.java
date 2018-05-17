package ForkJoinPool;

import java.util.concurrent.RecursiveAction;

public class Task2 extends RecursiveAction{
	public static final Integer GRID = 2;
	private Integer start;
	private Integer end;

	public Task2(Integer start, Integer end) {
		this.start = start;
		this.end = end;
//		System.out.println(end);
	}

	@Override
	public void compute() {
		int mid = (start+end)/2;
		
		if ((end-start) < 2) {
			execute();
		} else {
			Task2 t1 = new Task2(start, mid);
			Task2 t2 = new Task2(mid, end);
			invokeAll(t1,t2);
			
		}
	}

	private void execute() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getId());
	}
}
