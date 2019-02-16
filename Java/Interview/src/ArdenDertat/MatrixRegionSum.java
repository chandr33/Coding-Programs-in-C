package ArdenDertat;

public class MatrixRegionSum {
	static int[][] cache;
	
	public MatrixRegionSum(int m, int n) {
		cache = new int[m][n];
	}	
	
	private static void computeCache(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int north = 0, west = 0, north_west = 0, sum = 0;
				west = (j >= 1) ? cache[i][j-1] : 0;
				north = (i >= 1) ? cache[i-1][j] : 0;
				north_west = (i >= 1 && j >= 1) ? cache[i-1][j-1] : 0;
				sum = north + west - north_west + matrix[i][j];
				cache[i][j] = sum;
			}
		}
	}
	
	private static int calcSum(int x1, int y1, int x2, int y2) {
		int north, west, north_west, sum;
		west = (y1 >= 1) ? cache[x2][y1-1] : 0;
		north = (x1 >= 1) ? cache[x1-1][y2] : 0;
		north_west = (x1 >= 1 && y1 >= 1) ? cache[x1-1][y1-1] : 0;
		sum = north + west - north_west;
		return cache[x2][y2] - sum;
	}
	
	public static void main(String[] args) {
		int arr[][] = {
			    {1, 2, 0, 3, 4, 1, 5, 8, 1, 0},
			    {1, 5, 5, 2, 4, 9, 1, 2, 0, 1},
			    {3, 8, 1, 3, 3, 7, 2, 1, 4, 9},
			    {5, 2, 8, 6, 1, 0, 8, 4, 2, 3},
			    {1, 4, 2, 5, 6, 3, 0, 1, 8, 1},
			    {8, 2, 3, 5, 4, 1, 7, 2, 9, 3},
			    {1, 7, 1, 0, 0, 1, 2, 7, 4, 3},
			    {8, 5, 5, 9, 1, 2, 0, 3, 4, 2} };
		MatrixRegionSum obj = new MatrixRegionSum(arr.length,arr[0].length);
		computeCache(arr);
		System.out.println(calcSum(1, 1, 2, 5));
	}
}
