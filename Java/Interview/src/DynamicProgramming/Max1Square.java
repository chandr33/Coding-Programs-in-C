package DynamicProgramming;

public class Max1Square {
	private static int calcMax(char[][] matrix) {
		int[][] result = new int[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = matrix.length-1; i >= 0; i--) {
            for (int j = matrix[i].length-1; j >= 0; j--) {
                if (matrix[i][j] == '0' - '0')
                    result[i][j] = 0;
                else {
                    if (j+1 <= matrix[i].length-1 && i+1 <= matrix.length-1) {
                        int right_cell = result[i][j+1];
                        int down_cell = result[i+1][j];
                        int diagnol_cell = result[i+1][j+1];
                        int min = Math.min(right_cell, Math.min(down_cell, diagnol_cell));
                        result[i][j] = min+1;
                    }
                    else {
                        result[i][j] = 1;
                    }
                    count = Math.max(count,result[i][j]);
                }
            }
        }
        printMatrix(result);
        return count;
	}
	
	private static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		char[][] matrix = {{0, 1, 1, 0, 1},  
      		  			   {1, 1, 0, 1, 0},  
      		  			   {0, 1, 1, 1, 0}, 
      		  			   {1, 1, 1, 1, 0}, 
      		  			   {1, 1, 1, 1, 1}, 
      		  			   {0, 0, 0, 0, 0}};
		
		System.out.println(calcMax(matrix));
	}
}
