package thread;

import java.util.concurrent.*;

/**
 * Title:
 * Description:
 * Create Time: 2017-03-26 17:53
 * author: wangyan
 * version: 1.0
 */
public class MutiThreadMaxFinder {

	public static void main(String[] args) throws Exception {
		int[] data = {1, 2, 3, 6, 12, 5, 4};
		int max = max(data);
		System.out.println(max);
	}

	public static int max(int[] data) throws Exception{
		int length = data.length;
		if (length == 0) {
			return Integer.MIN_VALUE;
		}
		if (length == 1) {
			return data[0];
		}
		FindMaxTask task1 = new FindMaxTask(data, 0, length / 2);
		FindMaxTask task2 = new FindMaxTask(data, length / 2, length);

		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> future1 = service.submit(task1);
		Future<Integer> future2 = service.submit(task2);

		return Math.max(future1.get(), future2.get());
	}

	static class FindMaxTask implements Callable<Integer> {
		private int[] data;
		private int begin;
		private int end;

		public FindMaxTask(int[] data, int begin, int end) {
			this.data = data;
			this.begin = begin;
			this.end = end;
		}

		public Integer call() throws Exception {
			int max = Integer.MIN_VALUE;
			for (int i = begin; i < end; i++) {
				int item = data[i];
				max = max > item ? max : item;
			}
			return max;
		}
	}
}


