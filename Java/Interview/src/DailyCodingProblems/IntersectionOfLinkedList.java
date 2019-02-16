package DailyCodingProblems;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}
public class IntersectionOfLinkedList {
	public static int length_list(ListNode head) {
		ListNode head_ref = head;
		int count = 0;
		while (head_ref != null) {
			count++;
			head_ref = head_ref.next;
		}
		return count;
	}
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int count1 = length_list(headA);
		int count2 = length_list(headB);
		int difference = 0;
		difference = count1 - count2;
		ListNode pA = headA, pB = headB;
		if (difference < 0) {
			pA = headB;
			pB = headA;
		}
		difference = Math.abs(difference);
		while (difference > 0) {
			pA = pA.next;
			difference--;
		}
		while (pA != null && pB != null) {
			if (pA.val == pB.val)
				break;
			pA = pA.next;
			pB = pB.next;
		}
		return pA;
	}
	public static void main(String[] args) {
		ListNode head2 = new ListNode(0);
		head2.next = new ListNode(9);
		head2.next.next = new ListNode(1);
		head2.next.next.next = new ListNode(2);
		head2.next.next.next.next = new ListNode(4);
		
		ListNode head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(4);
		
		ListNode result = getIntersectionNode(head, head2);
		if (result == null)
			System.out.println("NULL");
		else
			System.out.println(result.val);
	}
}
