/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Continue if there are nodes left in l1, l2, OR if there's a leftover carry
        while (l1 != null || l2 != null || carry != 0) {
            // Get values from the current nodes. If null, use 0.
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            
            // Calculate the sum and update the carry
            int sum = x + y + carry;
            carry = sum / 10;
            
            // Create a new node with the digit part of the sum
            current.next = new ListNode(sum % 10);
            
            // Move our pointers forward
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Return the actual head of the result list
        return dummyHead.next;
    }
}