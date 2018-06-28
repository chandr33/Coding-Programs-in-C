package Graphs;

/*Finds the shortest path between the pairs of vertices by stopping at intermediate vertices
 * Time Complexity - O(V^3)
 */
public class FloydWarshall {
	static final int MAX = 99999;
	public void compute(int graph[][], int dist[][], int path[][], int V) {
		for (int row = 0; row < V; row++) {//Initialize 'dist' array with the passed array
			for (int col = 0; col < V; col++) {
				dist[row][col] = graph[row][col];
				if ((row == col) || (dist[row][col] == MAX))//If the vertices are connected
					path[row][col] = MAX;
				else
					path[row][col] = row;
			}
		}
		//Check if you can reach src to dest with an intermediate vertex
		//dist[i][j] > dist[i][k] + dist[k][j]
		for (int k = 0; k < V; k++) {
			for (int row_update = 0; row_update < V; row_update++) {
				for (int col_update = 0; col_update < V; col_update++) {
					if (dist[row_update][col_update] > (dist[row_update][k] + dist[k][col_update])) {
						dist[row_update][col_update] = (dist[row_update][k] + dist[k][col_update]);
						path[row_update][col_update] = k;
					}
				}
			}
		}
	}
	
	public void printDistance(int dist[][], int V) {
		System.out.println("Printing Distance Matrix");
		for (int row = 0; row < V; row++) {//Initialize 'dist' array with the passed array
			for (int col = 0; col < V; col++) {
				if (dist[row][col] == MAX)
					System.out.printf("MAX ");
				else
					System.out.print(dist[row][col]+"    ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printPath(int path[][], int V) {
		System.out.println("Printing all possible paths from every Vertex as Source and Destination");

		for (int row = 0; row < V; row++) {
			for (int col = 0; col < V; col++) {
				System.out.print((char)(row+65) + "->" + (char)(col+65) + ": ");
				printPath_util(path,row,col);
				System.out.println();
			}
			System.out.println();
		}
		
	}
	
	void printPath_util (int path[][], int row, int col) {
		if (row == col) {
			System.out.print("Not Allowed");
			return;
		}
		if (path[row][col] == MAX) {
			System.out.print("Can't be Reached");
			return;
		}
		if (path[row][col] == row) {
			System.out.print((char)(row + 65) + " " + (char)(col + 65) + " ");
			return;
		}
		System.out.print((char)(row + 65) + " ");
		printPath_util(path, path[row][col], col);	
	}	
	
	public static void main(String[] args) {
		int graph[][] = new int[][] {{0,5,MAX,10},
									 {MAX,0,3,MAX},
									 {MAX,MAX,0,1},
									 {MAX,MAX,MAX,0}};
		
		FloydWarshall fw = new FloydWarshall();
		int dist[][] = new int[graph.length][graph.length];
		int path[][] = new int[graph.length][graph.length];
		fw.compute(graph,dist,path,graph.length);
		fw.printDistance(dist,graph.length);
		fw.printPath(path,graph.length);
	}
}
