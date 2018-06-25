#include <iostream>
#include <list>
#include <utility>
#include <map> 
#include <cstdlib>
#include <chrono>

/* USAGE - Enter the Start Vertex (Start Vertex <= Number of Vertices ,i.e. 9) of the Graph as the first Argument of the Command Line*/

using namespace std;
using namespace std::chrono;

class MinHeap
{
	int capacity;
	int size;
public:
	struct MinHeapNode {
		int vertex,weight;
	};

	MinHeapNode * minHeap;//array of structs
	map<int,int> position_table;//Map that stores position of every node in the heap

	MinHeap(int V) { 
		capacity = V;
		size = 0;
		minHeap = new MinHeapNode[V];
	}

	int getSize() { return size; }

	bool hasParent(int index) { return ((index - 1) >= 0) ? true : false; }

	int getParentIndex(int index) { return ((index-1)/2); }

	MinHeapNode getParent(int index) { return minHeap[(index-1)/2];}

	bool hasLeftChild(int index) { return (((index * 2) + 2) <= size) ? true : false; }

	bool hasRightChild(int index)  { return (((index * 2) + 2) <= size) ? true : false; }

	int getLeftChildIndex(int index) { return (index * 2) + 1; }

	int getRightChildIndex(int index) { return (index * 2) + 2; }

	MinHeapNode getLeftChild(int index) { return minHeap[(index * 2) + 1]; }

	MinHeapNode getRightChild(int index) { return minHeap[(index * 2) + 2]; }

	//Removes the first element from the heap and returns it
	MinHeapNode extractMin() {
		if (size == 0) throw "Empty Heap";
		MinHeapNode first = minHeap[0];
		position_table[minHeap[0].vertex] = size-1;
		position_table[minHeap[size-1].vertex] = 0;
		minHeap[0] = minHeap[size - 1];//Replace the last node with the first node
		size--;//Delete the last node
		heapifyDown();//Balance the heap from top to bottom
		return first;//Return the first(min) node 
	}

	//Finds the smaller child index, then checks if the parent's weight is greater than the smaller child index's weight, if greater then swaps otherwise breaks
	void heapifyDown() {
		int index = 0; //Start from the first index
		while (hasLeftChild(index)) {//Check for the left index first because that's the insertion order ,i.e.(Left then Right)
			int smallerIndex = getLeftChildIndex(index);//Set the defaulr
			if (hasRightChild(index) && (getRightChild(index).weight < getLeftChild(index).weight))
				smallerIndex = getRightChildIndex(index);//Right Child is smaller than Left Child
			if (minHeap[index].weight < minHeap[smallerIndex].weight)//Heapify complete ,i.e. Parent is smaller than children
				break;
			else {
				position_table[minHeap[index].vertex] = smallerIndex;
				position_table[minHeap[smallerIndex].vertex] = index;
				swap(index,smallerIndex);//Swap parent with the smaller child
			}
			index = smallerIndex;//Update index to the smaller Child index and continue
		}
	}

	//Initializes the heap by adding structs to the heap with their default values
	void add(int vertex, int weight) {
		MinHeapNode * new_node = new MinHeapNode;//Create new Heap Node
		new_node -> vertex = vertex;
		new_node -> weight = weight;
		position_table[new_node -> vertex] = size;
		minHeap[size] = *new_node;//Add to the last index of the array
		size++;//Increase the size
		heapifyUp(size-1);//Balance the heap from bottom to top
	}

	//Checks whether the adjacent vertice's distance is greater than the distance of the parent vertex and edge length
	void updateDistance(int vertex, int edge_weight, int src_weight, int * dist, int * parent, int src_vertex) {
		int pos = position_table[vertex];
		if (minHeap[pos].weight > (edge_weight + src_weight)) {
			minHeap[pos].weight = edge_weight + src_weight;
			dist[minHeap[pos].vertex] = edge_weight + src_weight;//Update distance of adjacent vertex
			parent[minHeap[pos].vertex] = src_vertex;//Update parent of adjacent vertex
		}
		heapifyUp(pos);//Balance the heap bottomUp from the position where the adjacent node was present
	}

	//Swaps until all parent nodes are smaller than their children nodes
	void heapifyUp(int index) {
		while (hasParent(index) && (getParent(index).weight > minHeap[index].weight)) {//Check if the parent's weight is greater than the current index's weight
			position_table[getParent(index).vertex] = index;
			position_table[minHeap[index].vertex] = getParentIndex(index);
			swap(getParentIndex(index),index);//If parent's weight is greater than swap parent with current node
			index = getParentIndex(index);//Move the index to the parent node
		}
	}

	//Swaps the structs from the passed indices
	void swap(int ind_1, int ind_2) {
		MinHeapNode temp = minHeap[ind_1];
		minHeap[ind_1] = minHeap[ind_2];
		minHeap[ind_2] = temp;
	}
};

class Dijkstra : public MinHeap
{
	int startVertex;
	int numVertices;
	map<int, list <pair<int,int> > > adj_list;//HashMap containing A list of adjacencies with weights with their keys as the vertex
	int * dist;
	int * parent;
public:

	//Constructor to allocate memory and initialize member variables
	Dijkstra(int start, int V) : MinHeap(V) {
		startVertex = start;
		numVertices = V;
		dist = new int[V];
		parent = new int[V];
	}

	//Populates the adjacency list by connecting both nodes (Undirected Graph)
	void addEdge(int start, int end, int weight) {//Add edge from src -> dest and vice-versa
		adj_list[start].push_back(make_pair(end,weight));//Add start->end
		adj_list[end].push_back(make_pair(start,weight));//Add end->start
	}

	//Implements Dijkstra's algorithm
	void compute_dijkstra() {
		initialize_heap();
		while (getSize() != 0) {//Loop till there are no nodes in Min-Heap
			MinHeapNode node = extractMin();//Extract the min. node from the heap
			list<pair<int,int> > :: iterator iter;//Iterator to go through all adjacencies
			for (iter = adj_list[node.vertex].begin(); iter != adj_list[node.vertex].end(); iter++) {
				if (position_table[iter -> first] < getSize())//Check if the node is present in the heap
					updateDistance(iter -> first,iter -> second, node.weight,dist,parent,node.vertex);//Only updates distances of non-visited nodes
			}
		}
		printDijkstra(dist,parent);
	}

	//Initialize heap with startVertex's weight as 0 and all the other vertices' weight as Infinity. Parent of each node is initially set to -1
	void initialize_heap() {
		for (int i = 0; i < numVertices; i++) {
			if (i == startVertex) {
				add(i,0);
				dist[i] = 0;
			}
			else {
				add(i,INT_MAX);
				dist[i] = INT_MAX;
			}
			parent[i] = -1;
		}
	}

	//Prints the Current Vertex, Distance from Start Vertex, and the Shortest Route from Start Vertex to Current Vertex
	void printDijkstra(int * dist, int * parent) {
		cout<<"Vertex"<<" \t\t "<<"Distance"<<" \t  "<<"Path"<<endl;
		for (int i = 0; i < numVertices; i++)
		{
			if (i != startVertex) {
				cout<<startVertex<<"->"<<i<<"  \t\t  "<<dist[i]<<"  \t\t  ";
				printPath(parent,startVertex,i);
				cout<<endl;
			}
		}
	}

	//Recurse through each node's parent and print it
	void printPath(int * parent, int src, int dest) {
		if (dest == src) {
			cout<<dest<<" ";
			return;
		}
		printPath(parent,src,parent[dest]);
		cout<<dest<<" ";
	}

};

int main(int argc, char ** argv) {
	auto start = high_resolution_clock::now();

	if (argc != 2) {
		cout<<argc<<endl;
		return 0;
	}
	int numVertices = 9;
	int startV = atoi(argv[1]);

	if (startV > numVertices - 1)
		return 0;

	Dijkstra * graph = new Dijkstra(startV,numVertices);
	graph -> addEdge(0,1,4);
	graph -> addEdge(0,7,8);
	graph -> addEdge(1,2,8);
	graph -> addEdge(1,7,11);
	graph -> addEdge(2,3,7);
	graph -> addEdge(2,8,2);
	graph -> addEdge(2,5,4);
	graph -> addEdge(3,4,9);
	graph -> addEdge(3,5,14);
	graph -> addEdge(4,5,10);
	graph -> addEdge(5,6,2);
	graph -> addEdge(6,7,1);
	graph -> addEdge(6,8,6);
	graph -> addEdge(7,8,7);

	graph -> compute_dijkstra();

	auto stop = high_resolution_clock::now();
	auto duration = duration_cast<microseconds>(stop-start);
	cout<<"Execution Time : "<<duration.count()<<" microseconds "<<endl;

	return 0;
}