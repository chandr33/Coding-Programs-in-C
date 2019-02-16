package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConnectedComponents {
	static boolean[][] visited;
	static int max_count = Integer.MIN_VALUE;
	
	private static class QueueNode {
		int x;
		int y;
		QueueNode(int x, int y) { this.x = x; this.y = y;}
	}
	
	private static boolean isValid(int[][] arr, int i, int j) {
		if (i < 0 || i >= arr.length || j < 0 || j >= arr.length)
			return false;
		return true;
	}
	private static int currentMaximum(int[][] arr, int i, int j) {
		int count = 0;
		Queue<QueueNode> queue = new LinkedList<>();
		queue.offer(new QueueNode(i,j));
		visited[i][j] = true;
	
		while (!queue.isEmpty()) {
			count++;
			QueueNode front = queue.poll();
			int x_coord = front.x;
			int y_coord = front.y;
			
			//Check for up-node
			if (isValid(arr, x_coord-1, y_coord) && !visited[x_coord-1][y_coord] && (arr[x_coord][y_coord] == arr[x_coord-1][y_coord])) {
				queue.offer(new QueueNode(x_coord-1, y_coord));
				visited[x_coord-1][y_coord] = true;
			}
			
			//Check for down-node
			if (isValid(arr, x_coord+1, y_coord) && !visited[x_coord+1][y_coord] && (arr[x_coord][y_coord] == arr[x_coord+1][y_coord])) {
				queue.offer(new QueueNode(x_coord+1, y_coord));
				visited[x_coord+1][y_coord] = true;
			}
			
			//Check for left-node
			if (isValid(arr, x_coord, y_coord-1) && !visited[x_coord][y_coord-1] && (arr[x_coord][y_coord] == arr[x_coord][y_coord-1])) {
				queue.offer(new QueueNode(x_coord, y_coord-1));
				visited[x_coord][y_coord-1] = true;
			}
			
			//Check for right-node
			if (isValid(arr, x_coord, y_coord+1) && !visited[x_coord][y_coord+1] && (arr[x_coord][y_coord] == arr[x_coord][y_coord+1])) {
				queue.offer(new QueueNode(x_coord, y_coord+1));
				visited[x_coord][y_coord+1] = true;
			}
		}
		return count;
	}
	public static int max_connections(int [][] arr) {
		visited = new boolean[arr.length][arr[0].length];
		for (int i  = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				visited[i][j] = false;
			}
		}
		for (int i  = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (!visited[i][j]) {
					int ret_count = currentMaximum(arr, i, j);
					max_count = Math.max(max_count, ret_count);
				}
			}
		}
		return max_count;
	}
	public static void main(String[] args) {
		int[][] input = { { 1, 4, 4, 4, 4, 3, 3, 1 }, 
                		  { 2, 1, 1, 4, 3, 3, 1, 1 }, 
                		  { 3, 2, 1, 1, 2, 3, 2, 1 }, 
                		  { 3, 3, 2, 1, 2, 2, 2, 2 }, 
                		  { 3, 1, 3, 1, 1, 4, 4, 4 }, 
                		  { 1, 1, 3, 1, 1, 4, 4, 4 }}; 
		System.out.println(max_connections(input));
	}
}
