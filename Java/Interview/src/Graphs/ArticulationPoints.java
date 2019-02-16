package Graphs;

import java.util.*;


public class ArticulationPoints {
	int countCycles;
	List<Integer> ArticulationPoints;
	LinkedList<Integer> adj_list[];
	int numVertices,rootVertex;
	int numChildrenOfRoot;
	
	public class Bridge<U,V> {
		private U u;
		private V v;
		public Bridge(U u, V v) {
			this.u = u;
			this.v = v;
		}
		public U getSrcParent() { return u; }
		public V getSrc() { return v; }
	}
	
	List<Bridge<Integer,Integer>> bridges = new ArrayList<>();
	
	public ArticulationPoints(int numVertices) {
		this.numVertices = numVertices;
		this.rootVertex = 0;
		adj_list = new LinkedList[numVertices];
		countCycles = 0;
		numChildrenOfRoot = 0;
		ArticulationPoints = new ArrayList<Integer>();
		for (int i = 0; i < adj_list.length; i++) {
			adj_list[i] = new LinkedList<Integer>();
		}
	}
	
	//Adds edge to an undirected graph
	public void addEdge(int src, int dest) {
		adj_list[src].add(dest);
		adj_list[dest].add(src);
	}
	
	//Computes and prints articulation points
	public void compute() {
		int visitedTime[] = new int[numVertices];
		int lowTime[] = new int[numVertices];
		boolean visited[] = new boolean[numVertices];
		int parent[] = new int[numVertices];
		Arrays.fill(visitedTime, 0);
		Arrays.fill(lowTime, 0);
		Arrays.fill(parent, -1);
		Arrays.fill(visited, false);
		DFS(rootVertex,visitedTime,lowTime,parent,visited);
		if (numChildrenOfRoot >= 2)
			ArticulationPoints.add(rootVertex);
		for (int i = 0; i < numVertices; i++) {
			if (i != rootVertex) {
				if (lowTime[i] > visitedTime[parent[i]]) {
					Bridge<Integer, Integer> edge = new Bridge<>(parent[i],i);
					bridges.add(edge);
				}
			}
		}
		
		printPoints();
		printBridges();
	}
	
	public void DFS(int vertex, int visitedTime[], int lowTime[], int parent[], boolean visited[]) {
		visited[vertex] = true;
		visitedTime[vertex] = countCycles;
		lowTime[vertex] = countCycles;
		for (int adjacency : adj_list[vertex]) {
			if (!visited[adjacency]) {
				if (vertex == rootVertex)
					numChildrenOfRoot++;
				parent[adjacency] = vertex;
				countCycles++;
				DFS(adjacency, visitedTime, lowTime, parent, visited);
			}
			if (vertex != rootVertex) {
				if (adjacency != parent[vertex]) {
					if (visitedTime[vertex] <= lowTime[adjacency]) {//Check if the Visited Time of the current vertex is lesser than the low time of any of its adjacencies
						if ((ArticulationPoints.size() == 0) || (!ArticulationPoints.contains(vertex))) //Dont add the vertex if one of its adjacencies is already an Articulation Point 
						{
							ArticulationPoints.add(vertex);
						}
					}
					lowTime[vertex] = Math.min(lowTime[vertex], lowTime[adjacency]);
				}
			}
		}
	}
	
	
	public void printPoints() {
		for (int i : ArticulationPoints) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public void printBridges() {
		for (Bridge<Integer,Integer> e : bridges) {
			System.out.println(e.getSrcParent() + " " + e.getSrc());
		}
	}
	
	public static void main(String[] args) {
		ArticulationPoints g1 = new ArticulationPoints(5);
		g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
		g1.compute();
		System.out.println();
		
		ArticulationPoints g2 = new ArticulationPoints(4);
		g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.compute();
        System.out.println();
        
        ArticulationPoints g3 = new ArticulationPoints(7);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        g3.compute();
        System.out.println();
        
        ArticulationPoints g4 = new ArticulationPoints(8);
        g4.addEdge(0, 1);
        g4.addEdge(0, 2);
        g4.addEdge(1, 2);
        g4.addEdge(2, 3);
        g4.addEdge(3, 4);
        g4.addEdge(4, 5);
        g4.addEdge(5, 6);
        g4.addEdge(6, 4);
        g4.addEdge(5, 7);
        g4.compute();
	}
	

}
