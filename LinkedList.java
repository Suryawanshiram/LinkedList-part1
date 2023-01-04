
public class LinkedList {
	public static class Node{
		int data;
		Node next;
		
		public Node(int data) {
			this.data=data;
			this.next=null;
		}
	}
	public static Node head;
	public static Node tail;
	public static int size;
	
	
	// methods  1 Add Operation Tc O(1)
	public void addFirst(int data) {
		// Step-1 = Create new node
				Node  newNode = new Node(data);
				size++;
				
		if(head  == null) {
			head = tail  = newNode;
			return;
		}
		
		// Step -2 = newNode next = head
		newNode.next = head;
		
		// Step -3 = head = newNode
		head = newNode;
	}
	
	// 2 AddLast Operation  Tc=O(1)
	public void addLast(int data) {
		Node newNode = new Node(data);
		size++;
		if(head == null) {
			head = tail = newNode;
			return ;
		}
		tail.next = newNode;
		tail = newNode;
	}
	
	// to Print LL
	public static void print() {
//		if(head == null) {
//			System.out.println("LL is empty");
//			return ;
//		}
		Node  temp = head;
		while(temp != null) {
			System.out.print(temp.data+ "-> ");
			temp = temp.next;
		}
		System.out.println("null");
	}
	
	// Add in Middle
	public static void add(int idx, int data) {
		Node newNode = new Node(data);
		size++;
		Node temp = head;
		int i=0;
		
		while(i < idx -1) {
			temp = temp.next;
			i++;
		}
		// i=idx-1 temp -> prev
		newNode.next = temp.next;
		temp.next = newNode;
		
	}
	
	// remove First
	public int removeFirst() {
		if(size == 0) {
			System.out.println("LL is Empty");
			return Integer.MIN_VALUE;
		} else if(size == 1) {
	        int val = head.data;
	        head= tail =null;
	        size = 0;
	        return val;
		}
		int val = head.data;
		head = head.next;
		size--;
		return val;
	}
	
	// Remove Last
	public int removeLast() {
		if(size == 0) {
			System.out.println("LL is Empty");
			return Integer.MIN_VALUE;
		} else if(size == 1) {
			int val = head.data;
			head = tail = null;
			size = 0;
			return val;
		}
		// prev node  i = size-2
		 Node prev = head;
		for(int i=0; i<size-2; i++) {
			prev = prev.next;
		}
		int val = prev.next.data;
		prev.next = null;
		tail = prev;
		size--;
		return val;
		
	}
	
	// Iterative Search    TC=O(n)
	public int itrSearch(int key) {
		Node temp =head;
		int i =0;
		
		while(temp != null) {
			if(temp.data ==key) {
				return i;
			}
			temp = temp.next;
			i++;
		}
		// Key isnot found
		return -1;
	}
	
	public int helper(Node head, int key) {
		if(head == null) {
			return -1;
		}
		
		if(head.data == key) {
			return 0;
		}
		int idx = helper(head.next, key);
		if(idx == -1) {
			return -1;
		}
		return idx+1;
	}
	
	public int recSearch(int key) {
		return helper(head, key);
	}
	
	
	// Reverse LL (Iterative Approach)
	public void reverse() {
		Node prev = null;
		Node curr =tail = head ;
		Node next;
		
		while(curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev ;
	}
	
	
	// Find and Remove Nth node from ll (VVIMP Q)   TC=O(n)
	
	public void deleteNthfromEnd(int n) {
		// calculate size
		int sz = 0;
		Node temp = head;
		while(temp != null) {
			temp = temp.next;
			sz++;
		}
		
		if( n == sz) {
			head = head.next;  // Remove Fist
			return ; 
		}
		
		// Size - n
		int i=1;
		int iToFind = sz-n;
		Node prev = head;
		while(i < iToFind) {
			prev = prev.next;
			i++;
		}
		prev.next = prev.next.next;
		return ;
	}
	
	// Check LL is Palindrome
	
	// Slow-Fast Approach
	public Node findMid(Node head) {  // helper fun
		Node slow = head;
		Node fast = head;
		
		while(fast != null && fast.next  != null) {  // check boundry cond. for odd even
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow; // slow is my midNode
	}
	
	public boolean checkIsPalindrome() {
		// base case
		if(head == null || head.next  == null) {
			return true;
		}
		// step1 => find mid
		Node midNode = findMid(head);
		
		// step2 => reverse 2nd half
		Node  prev = null;
		Node curr = midNode;
		Node next;
		while(curr !=null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		Node right = prev;  // right half head
		Node left = head;
		
		// step3 => check left and right half
		while(right != null) {
			if(left.data != right.data) {
				return false;
			}
			left = left.next;
			right = right.next;
		}
		
		return true;
	}
	
	// Detect Cycle in LL 
	public static boolean isCycle() {
		Node slow = head;
		Node fast = head;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;     // +1
			fast  = fast.next.next;  // +2
			
		      if(slow == fast) {
		    	  return true;  // Cycle is exist
		      }
		}
		return false ;
	}
	
	// Remove Cycle in LL
	public static void removeCycle() {
		// Detect cycle
		Node slow = head;
		Node fast = head;
		boolean cycle = false;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow) {
				cycle = true;
				break;
			}
		}
		if(cycle == false) {
			return ;
		}
		
		// Find meeting point
		slow = head;
		Node prev = null ;   // last node
		while(slow != fast) {
			prev = fast;
			slow = slow.next;
			fast = fast.next.next;	
		}
		
		// remove cycle  --> last.next = null
		prev.next = null;
	}
	
	private Node getMid(Node head) {
		Node slow = head;
		Node fast = head.next;
		
		while(fast != null && fast.next != null) {
			slow =slow.next;
			fast = fast.next.next;
		}
		return slow;   // mid node
	}
	
	private  Node merge(Node head1, Node head2) {
		Node mergedLL = new Node(-1);
		Node temp = mergedLL;
		
		while(head != null && head2 != null) {
			if(head.data <= head2.data) {
				temp.next = head1;
				head1 = head1.next;
				temp = temp.next;
			} else {
				temp.next = head2;
				head2 = head2.next;
				temp = temp.next;
			}
		}
		while(head1 != null) {
			temp.next = head1;
			head1 = head1.next;
			temp = temp.next;
		}
		
		while(head2 != null) {
			temp.next = head2;
			head2 = head2.next;
			temp = temp.next;
		}
		return mergedLL.next;
	}
	
	// Merge LL
	public Node mergeSort(Node head) {
		if(head == null || head.next == null) {
			return head;
		}
		// find mid
		Node mid = getMid(head);
		// left and right Merge
		Node rightHead = mid.next;
		mid.next = null;
		Node newLeft = mergeSort(head);
		Node  newRight = mergeSort(rightHead);
		
		// merge
		return merge(newLeft, newRight);
	}
	
	//System.out.println("null");
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		// 5-4-3-3-2-1
		ll.print();
		ll.head = ll.mergeSort(ll.head);
		ll.print();
		
		
		
		
		
//		LinkedList ll =new LinkedList();
		
//      head = new Node(1);
//      Node temp = new Node(2);
//      head.next = temp;
//      head.next.next = new Node(3);
//      head.next.next.next = temp;     // 1->2->3->1
//      System.out.println(isCycle());
//      removeCycle();
//      System.out.println(isCycle());
//		//ll.addFirst(1);
//		//ll.addFirst(2);
//		ll.addLast(1);
//		ll.addLast(2);
//		ll.addLast(2);
//		ll.addLast(1);
		
//		ll.print(); // 1-2-2-1
//		System.out.println(ll.checkIsPalindrome());
		
		//ll.add(2, 3);
		
		//ll.print();   // 1-> 2-> 9-> 3-> 4-> null
//		System.out.println(ll.size); // 5
//		ll.removeFirst();
//		ll.print();
//		ll.removeLast();
//		ll.print();
//		System.out.println(ll.size);

//		System.out.println(ll.recSearch(3));  // 2  key found
//		System.out.println(ll.recSearch(10));  // -1  key not found
		
//		System.out.println(ll.itrSearch(3));  // 2  key found
//		System.out.println(ll.itrSearch(10));  // -1  key not found
		
//		ll.print();
//		ll.deleteNthfromEnd(3);
//		ll.print();
//		
//		ll. reverse();
//		ll.print();

	}
}


