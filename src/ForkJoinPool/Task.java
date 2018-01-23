package ForkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<List<Integer>> {
	private static final Integer Grid = 2;
	private Integer start;
	private Integer end;

	public Task(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected List<Integer> compute() {
		/**
		 * computing method
		 */
		List<Integer> result = new ArrayList<Integer>();
		if (end - start < Grid) {
			result = execute();
		} else {
			Integer size = (start + end)/2;
		    Task task1 = new Task(start,size);
		    Task task2 = new Task(size+1,end);
		    invokeAll(task1, task2);
		    try{
		    	result = groupResult(task1.get(),task2.get());
		    } catch(Exception e){
		    	e.printStackTrace();
		    }
		}

		return result;
	}

	private List<Integer> execute() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(Thread.currentThread().hashCode());
//		System.out.println(end);
		return list;
	}
	private List<Integer> groupResult(List list1, List list2){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(list1);
		list.addAll(list2);
		return list;
	}
}
