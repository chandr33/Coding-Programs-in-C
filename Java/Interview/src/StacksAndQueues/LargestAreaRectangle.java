package StacksAndQueues;

import java.util.*;

public class LargestAreaRectangle {

	static int LargestArea(int arr[]) {
		int maxArea = 0;
		int curr_index = 0;
		Stack<Integer> stack = new Stack<>();
		while (curr_index <= arr.length-1) {
			if (stack.isEmpty() || arr[curr_index] >= arr[stack.peek()])
				stack.push(curr_index++);
			else {
				int top = stack.pop();
				maxArea = Math.max(maxArea, (stack.isEmpty()) ? arr[top] * (curr_index) : arr[top] * (curr_index - 1 - stack.peek()));
			}
		}
		while (!stack.isEmpty()) {
			int top = stack.pop();
			maxArea = Math.max(maxArea, (stack.isEmpty()) ? arr[top] * (curr_index) : arr[top] * (curr_index - 1 - stack.peek()));
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int arr2[] = {2,1,5,6,3};
		System.out.println(LargestArea(arr2));
	}
}
