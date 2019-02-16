package ArraysAndStrings;

import java.text.DecimalFormat;

public class Root {
	static double calc(int x, int n) {
		double i = 0.0001;
		boolean found = false;
		while (!found) {
			if (power(i,n) >= x-0.01) {
				found = true;
			}
			else
				i += 0.0001;
		}
		return i;
	}
	
	static double power(double n, double pow) {
		double num = n;
		while (pow > 1) {
			n *= num;
			pow--;
		}
		return n;
	}
	public static void main(String[] args) {
		int x = 2,n = 4;
		DecimalFormat df = new DecimalFormat("#.####"); 
		System.out.println(df.format(calc(x,n)));
	}

}
