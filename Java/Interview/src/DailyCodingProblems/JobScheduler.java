package DailyCodingProblems;

interface Lambda {
	public void function();
}
public class JobScheduler {
	public static void execute(Lambda l, int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {};
		l.function();
	}
	public static void main(String[] args) {
		execute(() -> System.out.println("Hello World"),500);
	}
}
