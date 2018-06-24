package Graphs;
import java.util.*;
/* This program prints the Distance and Routes to each vertex of the graph from the given Start Vertex.
 * The program implements Dijkstra's algorithm using Adjacency Matrix
 */
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
	void printDistance_util(int [] parent, int src, int dest) {
		if (src == dest) {
			System.out.print(dest + " ");
			return;
		}
		printDistance_util(parent, src, parent[dest]);
		System.out.print(dest + " ");
		
	}
	void printDistance(int [] distance, int [] parent, int startVertex) {
		System.out.println("Start->End \t Distance \t Route");
		for (int i = 0; i < V; i++) {
			if (i != startVertex){
				System.out.printf("%d->%d \t\t %d \t\t ",startVertex,i,distance[i]);
				printDistance_util(parent, startVertex, i);
				System.out.println();
			}
		}
	}
	
	void compute(int [][] adj_matrix, int startVertex) {
		boolean [] visited = new boolean[9];//Array for visited nodes
		int [] distance = new int[9];//Array for distances from source node
		int [] parent = new int[9];//Array for storing parent vertex of each node in the shortest path
		Arrays.fill(distance, INT_MAX);
		Arrays.fill(visited,false);
		parent[startVertex] = -1;
		visited[startVertex] = true;
		distance[startVertex] = 0;
		int i = startVertex;
		int count = 0;
		while(count < V) {
			for (int j = 0; j < V; j++) {
				if (adj_matrix[i][j] != 0) {//If nodes are connected
					if (!visited[j]){//Check if the node has been visited
						if ((distance[i] + adj_matrix[i][j]) < distance[j]) {
							distance[j] = distance[i] + adj_matrix[i][j];//Update the distance
							parent[j] = i;
						}
					}
				}
			}
			i = find_min_distance(visited, distance);//Go to the next min_distance node
			visited[i] = true;
			count++;
		}
		printDistance(distance,parent,startVertex);
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
		d.compute(graph, 4);
	}
}
