package Graphs;

import java.io.*;
import java.util.*;

public class BFS_Search {
	private int numVertices;
	private LinkedList<Integer> adj_list[];//Array of Linked Lists to store all the adjacencies
	
	BFS_Search(int numVertices) {
		this.numVertices = numVertices;
		adj_list = new LinkedList[this.numVertices];//Allocate space for the array
	}
	
	public void addEdge(int src, int dest) throws NullPointerException{//Adding edge of the graph (src -> dest)
		if (adj_list[src] == null)
			adj_list[src] = new LinkedList<Integer>();//Initialize each array element to contain a Linked List
		adj_list[src].add(dest);
	}
	
	public void BFS(int startVertex) {//startVertex is the vertex where BFS begins
		boolean [] visited = new boolean[numVertices];//Array which keeps track of visited nodes
		Arrays.fill(visited, false);//Initializes all the nodes visited as false
		Queue<Integer> q = new LinkedList<>();//Here Queue is an interface which is implemented by the LinkedList class
		q.add(startVertex);//Add the first vertex to Queue
		while (!q.isEmpty()) {//Loop till the queue is not empty
			int frontNode = q.peek();//Save the information of the front node of the queue
			visited[frontNode] = true;//Mark it visited
			System.out.print(frontNode + " ");//Print the vertex
			q.remove();//Remove the first visited node
			for (int adjacencies : adj_list[frontNode]) {//Enqueue all he adjacencies of the visited node
				if (!visited[adjacencies])//Only add the non-visited adjacencies
					q.add(adjacencies);
			}						
		}
		
	}
	public static void main(String[] args) {
		BFS_Search graph = new BFS_Search(4);
		try {
			graph.addEdge(0, 1);
			graph.addEdge(0, 2);
			graph.addEdge(1, 2);
			graph.addEdge(2, 0);
			graph.addEdge(2, 3);
			graph.addEdge(3, 3);
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		graph.BFS(2);
	}
}
