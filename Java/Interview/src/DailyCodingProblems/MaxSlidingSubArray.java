package DailyCodingProblems;

import java.util.*;

public class MaxSlidingSubArray {
	public static void maxSubArray(int[] arr, int k) {
		Deque<Integer> deque = new LinkedList<>();
		int i;
		for (i = 0; i < k; i++) {
			while (!deque.isEmpty() && arr[deque.peekFirst()] < arr[i])
				deque.pollFirst();
			deque.offerLast(i);
		}
		
		for (; i < arr.length; i++) {
			System.out.println(arr[deque.peekFirst()]);
			while (!deque.isEmpty() && deque.peekFirst() <= i-k) //Remove elements not in the current window
				deque.pollFirst();
			while (!deque.isEmpty() && arr[deque.peekFirst()] < arr[i]) //Remove all elements that are smaller than the max.element present in the window
				deque.pollFirst();
			deque.offerLast(i);
		}
		System.out.println(arr[deque.peekFirst()]);
		
	}
	public static void main(String[] args) {
		int[] arr = {10,5,2,7,8,7};
		maxSubArray(arr, 3);
	}
}	
