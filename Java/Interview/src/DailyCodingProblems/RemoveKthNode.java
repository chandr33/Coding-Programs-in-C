package DailyCodingProblems;

public class RemoveKthNode {
	private static class ListNode {
		int data;
		ListNode next;
		ListNode(int data) { this.data = data; next = null; }
	}
	
	ListNode head;
	
	private static ListNode remove(ListNode head, int n) {
		ListNode new_node = new ListNode(0);
        new_node.next = head;
        ListNode head_ref = head;
        if (head_ref == null)
            return null;
        if (head.next == null) {
            head = null;
            return head;
        }
        ListNode current = new_node;
        ListNode previous = new_node;
        for (int i = 0; i <= n; i++) {
            current = current.next;   
        }
        while (current != null) {
            current = current.next;
            previous = previous.next;
        }
        
        previous.next = previous.next.next;
        return new_node.next;
	}
	
	private static void printList(ListNode head) {
		ListNode head_ref = head;
		while (head_ref != null) {
			System.out.print(head_ref.data+" ");
			head_ref = head_ref.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		RemoveKthNode obj = new RemoveKthNode();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		remove(obj.head, 2);
		printList(obj.head);
	}
}
