package Graphs;
import java.util.*;

/*This functions detects a cycle in the undirectd graph by using a stack(Recursion). 
 * If the vertex is present in the recursion stack then the function returns true(Cycle Found) 
 */
class Detect_Cycle_DFS {
	HashMap<Integer,LinkedList<Integer>> adj_list;//Stores the adjacencies via a LinkedList which is indexed in a HashMap
	HashMap<Integer,Boolean> visited;//Containing the visited vertices
	public Detect_Cycle_DFS() {
		visited = new HashMap<>();
		adj_list = new HashMap<Integer, LinkedList<Integer>>();
	}
	public void addEdge(int src, int dest) {
		LinkedList<Integer> list = new LinkedList<>();
		if (adj_list.containsKey(src)) {
			adj_list.get(src).add(dest);
		}
		else {
			list.add(dest);
			adj_list.put(src, list);
		}
	}
	
	public boolean DFS() {
		int i = adj_list.entrySet().iterator().next().getKey();
		boolean cycle = false;
		HashMap<Integer,Integer> arr = new HashMap<>();
		cycle = DFS_util(visited,i,arr, cycle);
		return cycle;
	}
	//Implement DFS and check if the vertex appears again in the recursion stack
	boolean DFS_util(HashMap<Integer,Boolean> check, int index,HashMap<Integer,Integer> arr, boolean cycle) {
		if (check.containsKey(index)) {//Return if visited
			if (arr.containsKey(index)) {
				return true;
			}
			return false;
		}
		else {
			check.put(index, true);
			arr.put(index, 1);
		}
		if (adj_list.get(index) != null) {
			for (int i = 0; i < adj_list.get(index).size(); i++)
				cycle = DFS_util(check, adj_list.get(index).get(i),arr,cycle);
		}
		return cycle;
	}
}
//Implements Rank(Union) and Path Compression(Find)
public class RankAndPathCompression{
	HashMap<Integer,Integer> parent;
	HashMap<Integer,Integer> rank;
	
	//Class to store Edge attributes
	class Edge {
		int src,dest,weight;
	}
	Edge edges[];//Array that stores Edge objects
	public RankAndPathCompression(int numEdges) {
		edges = new Edge[numEdges];
		for (int i = 0; i < numEdges; i++)
			edges[i] = new Edge();
		parent = new HashMap<>();
		rank = new HashMap<>();
	}
	//Adds edges of the graph and initializes every vertice's parent as -1 and rank as 0
	void addEdge(int src, int dest, int weight, int index) {
		edges[index].src = src;
		edges[index].dest = dest;
		edges[index].weight = weight;
		parent.put(src,-1);
		parent.put(dest,-1);
		rank.put(src, 0);
		rank.put(dest, 0);
	}
	//Recursively finds the parent of the given node node and once found implements path compression by setting the parent of the node as the parent of the set
	int find (int node, int parent_node) {
		if (parent.get(parent_node) == -1) {
			parent.put(node, parent_node);//Set the parent of the given node as the parent of the set
			return parent_node;//Parent of the entire set
		}
		return find(node,parent.get(parent_node));
	}
	//Performs union by rank, i.e. Union of a lower ranked subset with the higher ranked subset as its parent 
	void union(int src, int dest) {
		if (rank.get(src) > rank.get(dest))
			parent.put(dest, src);
		else if (rank.get(src) < rank.get(dest))
			parent.put(src, dest);
		else {
			parent.put(src, dest);
			rank.put(dest, rank.get(dest)+1);
		}
	}
}
