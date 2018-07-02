package Graphs;

import java.util.*;

//Topological Sort in Directed Acyclic Graph
public class TopologicalSort {
	int numVertices;
	int startVertex;
	boolean visited[];
	LinkedList<Integer> adj_list[];
	public TopologicalSort(int numV, int startVertex) {
		this.numVertices = numV;
		this.startVertex = startVertex;
		adj_list = new LinkedList[numV];
		visited = new boolean[numV];
		for (int i = 0; i < numV; i++) {
			adj_list[i] = new LinkedList<Integer>();
			visited[i] = false;
		}
	}
	
	public void addEdge(int src, int dest) {
		adj_list[src].add(dest);
	}
	
	public void Sort() {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < numVertices; i++) {
			if (!visited[i])
				Sort_util(i,s);
		}
		printStack(s);
	}
	
	public void Sort_util(int vertex, Stack<Integer> stack) {
		if (visited[vertex])
			return;
		visited[vertex] = true;
		if (adj_list[vertex] != null) {
			for (int adjacency : adj_list[vertex]) {
				if (!visited[adjacency]) {
					Sort_util(adjacency,stack);
				}
			}
		}
		stack.push(vertex);//Push to the stack all the adjacencies of a given vertex before pushing the vertex
	}
	
	public void printStack(Stack<Integer> stack) {
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		TopologicalSort graph = new TopologicalSort(6,5);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(0, 4);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		
		graph.Sort();
	}
}
