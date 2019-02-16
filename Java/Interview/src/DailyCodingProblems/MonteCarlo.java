package DailyCodingProblems;

public class MonteCarlo {
	private static double estimatePie() {
		double pie = 0;
		int circle_points = 0, square_points = 0;
		double random_x = 0,random_y = 0,origin_dist = 0;
		int low = -100, high = 100;
		for (int i = 0; i < 10000; i++) {
			random_x = ((Math.random()*(high-low+1))+low)/100; //Normalizing hte values so that the coordinates are between -1 and 1
			random_y = ((Math.random()*(high-low+1))+low)/100;
			origin_dist = (random_x*random_x) + (random_y*random_y);
			if (origin_dist <= 1)
				circle_points++;
			square_points++;
		}
		pie = (double)(4*circle_points)/square_points;
		return pie;
	}
	public static void main(String[] args) {
		System.out.println(estimatePie());
	}
}
