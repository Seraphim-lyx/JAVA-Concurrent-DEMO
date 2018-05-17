package ForkJoinPool;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws ExecutionException {
		// TODO Auto-generated method stub
		ForkJoinPool fjp = new ForkJoinPool(20);
		Task task = new Task(0, 20);
//		Task2 task = new Task2(0,20);
	
		try {
			System.out.println(fjp.submit(task).get());
			fjp.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		List<Integer> list;
//		try {
//			list = task.get();
//			for(Integer i : list){
//				System.out.println(i);
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
