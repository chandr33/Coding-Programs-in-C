package ArraysAndStrings;
import java.util.*;
class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
	public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals.size() <= 1) {
            return intervals;
        }
        Collections.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= result.get(result.size()-1).end) {
            	int start_time = result.get(result.size()-1).start;
            	int end_time = result.get(result.size()-1).end;
                result.remove(result.size()-1);
                result.add(new Interval(Math.min(start_time,intervals.get(i).start),Math.max(end_time,intervals.get(i).end)));
            }
            else {    
                result.add(intervals.get(i));
            }
        }
        return result;
    }
	
	public static void printList(List<Interval> list) {
		for (Interval i : list) {
			System.out.print("["+i.start+","+i.end+"] ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(1,3));
		list.add(new Interval(2,3));
		list.add(new Interval(2,2));
		list.add(new Interval(2,2));
		list.add(new Interval(3,3));
		list.add(new Interval(5,7));
		list.add(new Interval(4,6));
		printList(merge(list));
		
	}
}
