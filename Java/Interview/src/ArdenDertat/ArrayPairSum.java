package ArdenDertat;
import java.util.List;
import java.util.ArrayList;

public class ArrayPairSum {
	private static class Pair {
		int x;
		int y;
		Pair(int x, int y) { this.x = x; this.y = y; }
	}
	private static void printList(List<Pair> new_list) {
		for (Pair p : new_list) {
			System.out.print("["+p.x+","+p.y+"] ");
		}
		System.out.println();
	}
	private static List<Pair> getPairs(int[] arr, int n) {
		List<Pair> new_list = new ArrayList<Pair>();
		List<Integer> seen_list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			int current_num = arr[i];
			int number_to_get = n - current_num;
			if (seen_list.contains(number_to_get)) {
				new_list.add(new Pair(current_num,number_to_get));
			}
			else {
				seen_list.add(current_num);
			}
		}
		printList(new_list);
		return new_list;
	}
	public static void main(String[] args) {
		int[] arr = {1, 4, 45, 6, 10, 8, 12};
		int n = 16;
		getPairs(arr, n);
	}
}
