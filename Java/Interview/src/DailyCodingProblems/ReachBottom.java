package DailyCodingProblems;

import java.util.LinkedList;
import java.util.Queue;

public class ReachBottom {
	/*
	 * Apply Lee's Algorithm
	 * Use BFS
	 */
	private static class QueueNode {
		int x_coord;
		int y_coord;
		int dist;
		QueueNode(int x, int y, int dist) { x_coord = x; y_coord = y; this.dist = dist; }
	}
	
	public static boolean isValid(int curr_x, int curr_y, int len_x, int len_y) {
		if ((curr_x >= 0 && curr_x < len_x) && (curr_y >= 0 && curr_y < len_y)) {
			return true;
		}
		return false;
	}
	
	public static int calcMin(char[][] arr, int startX, int startY, int endX, int endY) {
		boolean [][] visited = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 'f')
					visited[i][j] = false;
				else
					visited[i][j] = true;
			}
		}
		Queue<QueueNode> queue = new LinkedList<>();
		queue.offer(new QueueNode(startX, startY, 0));
		visited[startX][startY] = true;
		while (!queue.isEmpty()) {
			QueueNode front = queue.poll();
			if (front.x_coord == endX && front.y_coord == endY)
				return front.dist;
			
			//Check for moving Down
			if (isValid(front.x_coord+1, front.y_coord, arr.length, arr[0].length) && !visited[front.x_coord+1][front.y_coord]) {
				QueueNode q = new QueueNode(front.x_coord+1,front.y_coord,front.dist+1);
				queue.offer(q);
				visited[front.x_coord+1][front.y_coord] = true;
			}
			
			//Check for moving Up
			if (isValid(front.x_coord-1, front.y_coord, arr.length, arr[0].length) && !visited[front.x_coord-1][front.y_coord]) {
				QueueNode q = new QueueNode(front.x_coord-1,front.y_coord,front.dist+1);
				queue.offer(q);
				visited[front.x_coord-1][front.y_coord] = true;
			}
			
			//Check for moving left
			if (isValid(front.x_coord, front.y_coord-1, arr.length, arr[0].length) && !visited[front.x_coord][front.y_coord-1]) {
				QueueNode q = new QueueNode(front.x_coord,front.y_coord-1,front.dist+1);
				queue.offer(q);
				visited[front.x_coord][front.y_coord-1] = true;
			}
			
			//Check for moving right
			if (isValid(front.x_coord, front.y_coord+1, arr.length, arr[0].length) && !visited[front.x_coord][front.y_coord+1]) {
				QueueNode q = new QueueNode(front.x_coord,front.y_coord+1,front.dist+1);
				queue.offer(q);
				visited[front.x_coord][front.y_coord+1] = true;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		char[][] arr = {{'f','f','f','f'},
						{'t','t','f','t'},
						{'f','f','f','f'},
						{'f','f','f','f'}};
		
		System.out.println(calcMin(arr, 1,0,1,2));
		//System.out.println(calcMin(arr2, 0, 0, 1, 0));
	}
}
