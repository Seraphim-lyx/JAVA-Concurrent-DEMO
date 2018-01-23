package ForkJoinPool;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForkJoinPool fjp = new ForkJoinPool(20);
		Task task = new Task(0, 20);
		fjp.execute(task);
		List<Integer> list;
		try {
			list = task.get();
			for(Integer i : list){
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
