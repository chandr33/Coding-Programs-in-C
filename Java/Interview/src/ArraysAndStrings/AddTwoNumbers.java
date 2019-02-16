package ArraysAndStrings;

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

public class AddTwoNumbers {
	
	public static ListNode ReverseList(ListNode l1) {
		ListNode prev = null;
		ListNode current = l1;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p = l1; ListNode q = l2;
        ListNode result = new ListNode(0);
        ListNode head = result;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x+y+carry;
            carry = sum/10;
            result.next = new ListNode(sum%10);
            result = result.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0)
            result.next = new ListNode(carry);
        return head.next;
    }
	public static void printList(ListNode l1) {
		if (l1 == null)
			return;
		System.out.print(l1.val+" ");
		printList(l1.next);
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(9);
		l1.next.next = new ListNode(2);
		l1.next.next.next = null;
		printList(l1);
		System.out.println();
		l1 = ReverseList(l1);
		printList(l1);
	}
}
