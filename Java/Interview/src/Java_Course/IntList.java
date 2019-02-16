package Java_Course;

public class IntList implements MyList {
	private IntList next;
	private int data;
	
	public IntList(int data) {
		this.data = data;
	}
	public IntList(IntList n, int data) {
		this.next = n;
		this.data = data;
	}
	public int getData() {
		return data;
	}
	@Override
	public IntList next() {
		return next;
	}
	@Override
	public void printNode() {
		System.out.print("IntList Node, data is: "+data);
	}
}
