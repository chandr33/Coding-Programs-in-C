package DailyCodingProblems;

import java.util.Random;

public class UniformProbability {
	static int count_so_far = 0;
	static int result;
	public static int getNum(int x) {
		count_so_far++;
		if (count_so_far == 1)
			result = x;
		else {
			Random rand = new Random();
			int n = rand.nextInt(count_so_far+1);//This will return a number between 0 and count_so_far with the probability of 1/(count_so_far)
			if (n == count_so_far)
				result = x;
		}
		return result;
	}
	public static void main(String[] args) {
		int[] stream = {1,2,3,4,5,6};
		for (int i = 0; i < stream.length; i++) {
			System.out.println("The randomly chosen number in the stream with "+Integer.toString(i+1)+" elements is "+ Integer.toString(getNum(stream[i])));
		}
		
	}
}
