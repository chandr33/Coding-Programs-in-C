package Graphs;

import java.util.ArrayList;

public class Kruskal extends RankAndPathCompression{
	ArrayList<Edge> MST= new ArrayList<Edge>();//Stores the Minimum Spanning Tree
	int numVertices;
	Kruskal(int numVertices, int numEdges) {
		super(numEdges);
		this.numVertices = numVertices;
	}
	//Swaps the edges in the Edge array
	void swap(int a, int b, Edge arr[]) {
		Edge temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	//Returns the pivot index of the partitioned array
	int partition(Edge arr[], int start, int end) {//Returns pivot index after partitioning (Smaller elements to left of pivot
		int pivot = arr[end].weight;//Pivot is the end vertex of the passed sub-array
		int i = start;
		while (i < end) {//Loop through all the edges but the last one(pivot)
			if (arr[i].weight <= pivot) {//Check for vertices whose weights are lesser than those of the pivot
				if (i != start) {
					swap(i,start,arr);//Swap the current index with the index of the 'Wall'(start index)
				}
				start = start + 1;//If the current index is the same as the index of the 'Wall' then dont't swap, but just move the wall
			}
			i = i + 1;//Increment the current index
		}
		swap(start,end,arr);//Finally swap the pivot with the index of the wall,] -> This ensures that all the smaller vertices are to the left of the pivot
		return start;//This is the 'Wall' index
	}
	
	void quickSort_util(Edge arr[], int start, int end) {
		if (start < end) {//Checks if the sub-array is partitioned till it reaches one element in the array
			int pivot = partition(arr, start, end);//Partitions the array according to the edge weights
			quickSort_util(arr, start, pivot - 1);//Recursively sort [start..pivot-1] sub-array
			quickSort_util(arr, pivot + 1, end);//Recursively sort [pivot+1..end] sub-array
		}
	}
	
	void quickSort(Edge arr[]) {
		quickSort_util(arr,0,arr.length-1);
	}
	
	void MinSpanningTree() {
		quickSort(edges);//Sort the edges according to their weights
		int i = 0;
		int edgeNum = 0;
		while (i < numVertices-1) {//Loop till V-1 as V-1 is the maximum number of edges in the MST
			int src = edges[edgeNum].src;
			int dest = edges[edgeNum].dest;
			int root_src = (parent.get(src) == -1) ? src : find(src,parent.get(src));
			int root_dest = (parent.get(dest) == -1) ? dest : find(dest,parent.get(dest));
			if (root_src == root_dest) {//If cycle is formed
				edgeNum++;//Discard this edge and move to the next
			}
			else {
				union(root_src,root_dest);
				MST.add(edges[edgeNum]);//Add this edge to the MST as it does not form a loop
				i++;//Move to the next vertex
				edgeNum++;//Increment the current edge
			};
		}
		printMST();
	}
	
	void printMST() {
		for (int i = 0; i < MST.size(); i++) {
			System.out.println(MST.get(i).src + "-->" + MST.get(i).dest + "==" + MST.get(i).weight);
		}
	}
	
	public static void main(String[] args) {
		Kruskal graph = new Kruskal(9,14);
		graph.addEdge(0, 1, 4, 0);
		graph.addEdge(0, 7, 8, 1);
		graph.addEdge(1, 7, 11, 2);
		graph.addEdge(1, 2, 8, 3);
		graph.addEdge(2, 8, 2, 4);
		graph.addEdge(2, 5, 4, 5);
		graph.addEdge(2, 3, 7, 6);
		graph.addEdge(3, 4, 9, 7);
		graph.addEdge(3, 5, 14, 8);
		graph.addEdge(5, 4, 10, 9);
		graph.addEdge(7, 8, 7, 10);
		graph.addEdge(8, 6, 6, 11);
		graph.addEdge(7, 6, 1, 12);
		graph.addEdge(6, 5, 2, 13);
		
		graph.MinSpanningTree();
	}

}
