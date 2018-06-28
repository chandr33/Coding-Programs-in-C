package Graphs;
import java.util.*;
/*This program builds a HuffMan Tree and stores codes for all characters in a Hash Table.
 * The Huffman Tree is built using Priority Queue(Abstract Class) implemented via a MinHeap
 */
class Node {
	int frequency;
	char c;
	Node left,right;
	public Node(char c, int frequency) {
		this.c = c;
		this.frequency = frequency;
	}
	
}

class Heap{
	int size;
	int capacity;
	public Node heap[];//Heap containing objects of Node class
	public Heap(int capacity)
	{
		this.capacity = capacity;
		size = 0;
		heap = new Node[capacity];
	}
	
	int getSize() { return size; }
	
	boolean hasParent(int index) { return ((index-1) > 0) ? true : false; }
	
	int getParentIndex(int index) { return (index-1)/2; }
	
	Node getParent(int index) { return heap[(index-1)/2]; }
	
	boolean hasLeftChild(int index) { return (((2*index)+1) <= size) ? true : false; }
	
	int getLeftChildIndex(int index) { return ((2*index)+1); }
	
	Node getLeftChild(int index) { return heap[(2*index)+1]; }
	
	boolean hasRightChild(int index) { return (((2*index)+2) <= size) ? true : false; }
	
	int getRightChildIndex(int index) { return ((2*index)+2); }
	
	Node getRightChild(int index) { return heap[(2*index)+2]; }
	
	void swap(int index1, int index2) {
		Node temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}
	
	void heapifyDown() {
		int index = 0;
		while (hasLeftChild(index)) {
			int smallerIndex = getLeftChildIndex(index);
			if ((hasRightChild(index)) && (getRightChild(index).frequency < heap[smallerIndex].frequency))
				smallerIndex = getRightChildIndex(index);
			if (heap[index].frequency < heap[smallerIndex].frequency)
				break;
			else {
				swap(smallerIndex,index);
				index = smallerIndex;
			}
		}
	}
	
	Node extractMin() {
		if (size == 0) throw new IllegalStateException();
		Node first = heap[0];
		heap[0] = heap[size-1];
		size--;
		heapifyDown();
		return first;
	}
	
	void heapifyUp(int index) {
		while((hasParent(index)) && (getParent(index).frequency > heap[index].frequency)) {
			swap(getParentIndex(index),index);
			index = getParentIndex(index);
		}
	}
	
	void add(Node new_node) {
		heap[size] = new_node;
		size++;
		heapifyUp(size-1);
	}
}

public class Huffman extends Heap {
	char literals[];
	int frequency_arr[];
	int capacity;
	public HashMap<Character, String> table;
	
	public Huffman(char arr[], int frequency[], int capacity) {
		super(capacity);
		this.capacity = capacity;
		this.literals = Arrays.copyOf(arr, capacity);
		this.frequency_arr = Arrays.copyOf(frequency, capacity);
		table = new HashMap<>(capacity);
	}
	
	public void buildTree() {
		//TODO - extract the two min.heap nodes and compare their frequency
		for (int i = 0; i < capacity; i++) {
			Node new_node = new Node(literals[i],frequency_arr[i]);//Creating New Heap nodes
			new_node.left = new_node.right = null;
			add(new_node);//Add to heap
		}
	}
	
	public void computeHuffman() {
		buildTree();
		while (getSize() != 1) {
			Node first_node = extractMin();
			Node second_node = extractMin();
			Node result = new Node('\0',(first_node.frequency + second_node.frequency));//Add internal node with character NULL
			result.left = first_node;
			result.right = second_node;
			add(result);
		}
		storeCode();
	}
	
	void storeCode() {
		String code = "";		
		storeCode_util(heap[getSize()-1],code);
	}
	
	void storeCode_util(Node node, String code) {
		if (node == null) {
			code = code.substring(0, code.length()-1);
			return;
		}
		if (node.left == null && node.right == null) {
			table.put(node.c,code);
			code = "";
		}
		storeCode_util(node.left, code.concat("0"));
		storeCode_util(node.right, code.concat("1"));
	}
	
	
	public static void main(String[] args) {
		char arr[] = {'Z','K','M','C','U','D','L','E'};
		int frequency[] = {2,7,24,32,37,42,42,120};
		
		Huffman encoding = new Huffman(arr,frequency,frequency.length);
		encoding.computeHuffman();
		System.out.println(encoding.table);
	}
	
}
