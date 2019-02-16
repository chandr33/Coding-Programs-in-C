package Java_Course;
class T2 extends Thread{
	public static int count = 0;
	private synchronized void update() {
		int v = count;
		try {
			sleep(10);
		} catch (Exception e) {}
		v++;
		count = v;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			update();
		}
	}
}

public class Main2 {
	public static void main(String[] args) throws Exception{
		T2 t2_1 = new T2();
		T2 t2_2 = new T2();
		t2_1.start();
		t2_2.start();
		t2_1.join();
		t2_2.join();
		System.out.println(T2.count);
	}
}
