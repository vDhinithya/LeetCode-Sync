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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // 1. Find the middle
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 2. Reverse the second half
        ListNode headSecond = reverse(slow);
        ListNode reversedCopy = headSecond; // Keep to restore later if needed
        
        // 3. Compare both halves
        ListNode headFirst = head;
        while (headSecond != null) {
            if (headFirst.val != headSecond.val) {
                return false;
            }
            headFirst = headFirst.next;
            headSecond = headSecond.next;
        }
        
        // Optional: restore the list by reversing `reversedCopy` back
        
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode present = head;
        while (present != null) {
            ListNode next = present.next;
            present.next = prev;
            prev = present;
            present = next;
        }
        return prev;
    }
}