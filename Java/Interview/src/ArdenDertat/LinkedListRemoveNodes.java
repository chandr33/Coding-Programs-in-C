package ArdenDertat;

public class LinkedListRemoveNodes {
	private static class ListNode {
		int data; 
		ListNode next;
		ListNode(int data) { this.data = data; this.next = null; }
	}
	
	private static void printList(ListNode head) {
		if (head == null) {
			System.out.println("NULL");
			return;
		}
		ListNode head_ref = head;
		while (head_ref != null) {
			System.out.print(head_ref.data +" ");
			head_ref = head_ref.next;
		}
		System.out.println();
	}
	
	private static ListNode removeNode(ListNode head, int num) {
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = head;
		ListNode previous = dummy;
		ListNode next = null;
		
		while (current != null && current.data != num) {
			previous = current;
			current = current.next;
		}
		
		if (current != null) {
			next = current.next;
			while (next != null && next.data == current.data) {
				current = next;
				next = next.next;
			}
		}
		
		if (current == head) {
			return head = next;
		}
		
		if (current != null) {
			if (current.data == head.data)
				head = next;
		}
		
		previous.next = next;
		
		return head;
	}
	
	public static void main(String[] args) {
		//TestCase 1
		ListNode head = null;
		
		//TestCase 2
		ListNode head2 = new ListNode(1);
		
		//TestCase 3
		ListNode head3 = new ListNode(5);
		
		//TestCase4
		ListNode head4 = new ListNode(1);
		head4.next = new ListNode(2);
		
		//TestCase 5
		ListNode head5 = new ListNode(5);
		head5.next = new ListNode(5);
		head5.next.next = new ListNode(5);
		
		//TestCase 6
		ListNode head6 = new ListNode(1);
		head6.next = new ListNode(2);
		head6.next.next = new ListNode(5);
		head6.next.next.next = new ListNode(5);
		
		//TestCase 7
		ListNode head7 = new ListNode(1);
		head7.next = new ListNode(2);
		head7.next.next = new ListNode(5);
		head7.next.next.next = new ListNode(5);
		head7.next.next.next.next = new ListNode(3);
		
		printList(removeNode(head,5));
		printList(removeNode(head2,5));
		printList(removeNode(head3,5));
		printList(removeNode(head4,5));
		printList(removeNode(head5,5));
		printList(removeNode(head6,5));
		printList(removeNode(head7,5));
	}
}
