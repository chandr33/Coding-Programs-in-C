package Java_Course;

public class LongList implements MyList{
	private LongList next;
	private long data;
	
	public LongList(long data) {
		this.data = data;
	}
	public LongList(LongList n, int data) {
		this.next = n;
		this.data = data;
	}
	public long getData() {
		return this.data;
	}
	@Override
	public LongList next() {
		return this.next;
	}
	@Override
	public void printNode() {
		System.out.print("LongList Node, data is: "+data);
	}
}
