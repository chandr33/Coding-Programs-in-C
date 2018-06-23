package Graphs;
import java.util.*;

public class Dijkstra {
	static final int V = 9;
	static final int INT_MAX = 99999;
	
	int find_min_distance(boolean [] visited, int [] distance) {
		int min = INT_MAX;
		int min_index = 0;
		for (int i = 0; i < 9; i++) {
			if ((distance[i] < min) && (!visited[i])) {
				min_index = i;
				min = distance[i];
			}
		}
		return min_index;
	}
	void printDistance(int [] distance) {
		for (int i = 0; i < V; i++) {
			System.out.println(i + "         " + distance[i]);
		}
	}
	
	void compute(int [][] adj_matrix, int startVertex) {
		boolean [] visited = new boolean[9];//Array for visited nodes
		int [] distance = new int[9];//Array for distances from source node
		Arrays.fill(distance, INT_MAX);
		Arrays.fill(visited,false);
		visited[startVertex] = true;
		distance[startVertex] = 0;
		int i = startVertex;
		int count = 0;
		while(count < V) {
			for (int j = 0; j < V; j++) {
				if (adj_matrix[i][j] != 0) {//If nodes are connected
					if (!visited[j]){//Check if the node has been visited
						if ((distance[i] + adj_matrix[i][j]) < distance[j])
							distance[j] = distance[i] + adj_matrix[i][j];//Update the distance
					}
				}
			}
			i = find_min_distance(visited, distance);//Go to the next min_distance node
			visited[i] = true;
			count++;
		}
		printDistance(distance);
	}
	public static void main(String[] args) {
		int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
									{4, 0, 8, 0, 0, 0, 0, 11, 0},
									{0, 8, 0, 7, 0, 4, 0, 0, 2},
									{0, 0, 7, 0, 9, 14, 0, 0, 0},
	                                {0, 0, 0, 9, 0, 10, 0, 0, 0},
	                                {0, 0, 4, 14, 10, 0, 2, 0, 0},
	                                {0, 0, 0, 0, 0, 2, 0, 1, 6},
	                                {8, 11, 0, 0, 0, 0, 1, 0, 7},
	                                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
		
		Dijkstra d = new Dijkstra();
		d.compute(graph, 0);
	}
}
