package DailyCodingProblems;

import java.util.Arrays;
import java.util.*;
/*
 * Implement a Max Heap
 */
class QueueNode {
	int data;
	int priority;
	QueueNode(int data, int priority) {
		this.data = data;
		this.priority = priority;
	}
}

public class RecordLastNOrders {
	static int heap_capacity = 10;
	static int size = 0;
	QueueNode[] node_list = new QueueNode[heap_capacity];
	
	int getParentIndex(int index) { return (index-1)/2; }
	int getLeftChildIndex(int index) { return 2*index+1; }
	int getRightChildIndex(int index) { return 2*index+2; }
	
	boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size; }
	boolean hasRightChild(int index) { return getRightChildIndex(index) < size; }
	boolean hasParent(int index) { return getParentIndex(index) >= 0; }
	
	QueueNode getParent(int index) { return node_list[getParentIndex(index)]; }
	QueueNode getLeftChild(int index) { return node_list[getLeftChildIndex(index)]; }
	QueueNode getRightChild(int index) { return node_list[getRightChildIndex(index)]; }
	
	void ensureCapacity() {
		if (size >= heap_capacity) {
			heap_capacity = 2*heap_capacity;
			node_list = Arrays.copyOf(node_list, heap_capacity);
		}
	}
	
	void swap(int i, int j) {
		QueueNode temp = node_list[i];
		node_list[i] = node_list[j];
		node_list[j] = temp;
	}
	
	void heapifyUp() {
		int index = size-1;
		while(hasParent(index) && getParent(index).priority < node_list[index].priority) {
			int temp = getParentIndex(index);
			swap(index,temp);
			index = temp;
		}
	}
	
	void heapifyDown() {
		int index = 0;
		while (hasLeftChild(index)) {
			int larger_index = getLeftChildIndex(index);
			if (hasRightChild(index) && getRightChild(index).priority > node_list[larger_index].priority)
				larger_index = getRightChildIndex(index);
			if (node_list[index].priority < node_list[larger_index].priority) {
				swap(index,larger_index);
			}
			else {
				break;
			}
			index = larger_index;
		}
	}
	
	void record(QueueNode recordId) {
		ensureCapacity();
		node_list[size] = recordId;
		size++;
		heapifyUp();
	}
	
	List<QueueNode> get_last(int index) {
		List<QueueNode> result = new ArrayList<>();
		for (int i = 0; i < index; i++) {
			QueueNode q = node_list[0];
			result.add(0,q);
			node_list[0] = node_list[size-1];
			size--;
			heapifyDown();
		}
		return result;
	}
	
	static void printList(List<QueueNode> list) {
		for (QueueNode q : list)
			System.out.println(q.data);
	}

	public static void main(String[] args) {
		RecordLastNOrders obj = new RecordLastNOrders();
		for (int i = 1; i <= 20; i++) {
			obj.record((new QueueNode(i, i)));
		}
		List<QueueNode> result = new ArrayList<>();
		result = obj.get_last(10);
		printList(result);
	}
}
