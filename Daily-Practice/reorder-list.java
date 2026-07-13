/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 2. Reverse the second half and break the list
        ListNode hs = reverse(slow.next);
        slow.next = null; // Important: terminate the first half
        ListNode hf = head;
        
        // 3. Interleave the two halves
        while (hf != null && hs != null) {
            ListNode temp1 = hf.next;
            ListNode temp2 = hs.next;
            
            hf.next = hs;
            hs.next = temp1;
            
            hf = temp1;
            hs = temp2;
        }
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}