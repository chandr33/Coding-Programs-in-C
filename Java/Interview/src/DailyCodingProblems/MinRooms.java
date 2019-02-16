package DailyCodingProblems;

import java.util.*;

public class MinRooms {

	public static int minRooms(int[] arrival,int[] departure) {
		int min_rooms = 1;
		int result = 1;
		Arrays.sort(arrival);
		Arrays.sort(departure);
		int arrival_index = 1;
		int departure_index = 0;
		int len = arrival.length;
		while (arrival_index < len && departure_index < len) {
			if (arrival[arrival_index] >= departure[departure_index]) {
				min_rooms--;
				departure_index++;
			}
			else {
				min_rooms++;
				result = Math.max(result, min_rooms);
				arrival_index++;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		int[] arrival = {900,940,950,1100,1500,1800};
		int[] departure = {910,1120,1130,1200,1900,2000};		
		System.out.println(minRooms(arrival, departure));
	}
}
