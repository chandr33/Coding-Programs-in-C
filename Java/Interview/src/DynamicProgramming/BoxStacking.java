package DynamicProgramming;

import java.util.*;


public class BoxStacking {
	static class Box {
		int depth;
		int width;
		int height;
		Box(int height, int width, int length) {
			this.depth = length;
			this.width = width;
			this.height = height;
		}
	}
	
	static class CompareArea implements Comparator<Box> {

		@Override
		public int compare(Box box1, Box box2) {
			// TODO Auto-generated method stub
			return (box2.width*box2.depth) - (box1.width*box1.depth);//Arrange in decreasing order of base area
		}
		
	}
	
	static int maxStackHeight(int arr_boxes[][]) {
		Box arr[] = new Box[arr_boxes.length*3];
		int arr_index = 0;
		//TODO - Populate the Box array with the boxes and their rotations in order of their decreasing base area
		for (int i = 0; i < arr_boxes.length; i++) {
			Box a = new Box(arr_boxes[i][0], Math.min(arr_boxes[i][1], arr_boxes[i][2]), Math.max(arr_boxes[i][1], arr_boxes[i][2]));
			Box b = new Box(arr_boxes[i][1], Math.min(arr_boxes[i][0], arr_boxes[i][2]), Math.max(arr_boxes[i][0], arr_boxes[i][2]));
			Box c = new Box(arr_boxes[i][2], Math.min(arr_boxes[i][0], arr_boxes[i][1]), Math.max(arr_boxes[i][0], arr_boxes[i][1]));
			arr[arr_index+0] = a;
			arr[arr_index+1] = b;
			arr[arr_index+2] = c;
			arr_index += 3;
		}
		//TODO - Sort the boxes in decreasing order of their base areas		
		Arrays.sort(arr, new CompareArea());
		
		//TODO - Generate the longest decreasing subsequence in order of the length of the box and add the corresponding heights
		
		//TODO - Store all the decreasing subsequences first in an array
		int longestDecreasingSequence[] = new int[arr_boxes.length*3];
		for (int i = 0; i < longestDecreasingSequence.length; i++) {
			longestDecreasingSequence[i] = arr[i].height;
		}
		for (int i = 1; i < longestDecreasingSequence.length; i++) {
			for (int j = 0; j < i; j++) {
				if ((arr[i].depth < arr[j].depth) && (arr[i].width < arr[j].width)) 
				{//Check for decreasing sequence
					longestDecreasingSequence[i] = longestDecreasingSequence[j]+arr[i].height;
				}
			}
		}
		int max = 0;
		for (int i = 1; i < longestDecreasingSequence.length; i++) {
			if (longestDecreasingSequence[max] < longestDecreasingSequence[i])
				max = i;
		}
	
		return longestDecreasingSequence[max];
	}
	
	public static void main(String[] args) {
		int arr_boxes[][] = {{4,6,7},{1,2,3},{4,5,6},{10,12,32}};
		System.out.println(maxStackHeight(arr_boxes));
	}
}
