package com.CK;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        new Solution().addTwoNumbers(l1, l2);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0) return l2;
        if (l2.val == 0) return l1;

        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
        ListNode curr1 = l1, curr2 = l2, head = null, res = head;
        int num1, num2;
        int carry = 0;

        while (!(curr1 == null && curr2 == null)) {
            while (!(curr1 == null)) {
                s1.push(curr1.val);
                curr1 = curr1.next;
            }
            while (!(curr2 == null)) {
                s2.push(curr2.val);
                curr2 = curr2.next;
            }
        }

        while (!(s1.isEmpty() && s2.isEmpty())) {
            if (!s1.isEmpty())
                num1 = s1.pop();
            else
                num1 = -1;

            if (!s2.isEmpty())
                num2 = s2.pop();
            else
                num2 = -1;

            if (num2 == -1) {
                if (res == null) {
                    head = new ListNode(num1 + carry);
                    res = head;
                    continue;
                }
                if (num1 + carry >= 10) {
                    res = addNode(res, (num1 + carry) % 10);
                    carry = 1;
                } else {
                    res = addNode(res, (num1 + carry));
                    carry = 0;
                }
            } else if (num1 == -1) {
                if (res == null) {
                    head = new ListNode(num2 + carry);
                    res = head;
                    continue;
                }
                if (num2 + carry >= 10) {
                    res = addNode(res, (num2 + carry) % 10);
                    carry = 1;
                } else {
                    res = addNode(res, (num2 + carry));
                    carry = 0;
                }
            } else {
                if (num1 + num2 + carry >= 10) {
                    if (res == null) {
                        head = new ListNode((num1 + num2 + carry) % 10);
                        res = head;
                        carry = 1;
                        continue;
                    }
                    res = addNode(res, (num1 + num2 + carry) % 10);
                    carry = 1;
                } else {
                    if (res == null) {
                        head = new ListNode(num1 + num2 + carry);
                        res = head;
                        continue;
                    }
                    res = addNode(res, (num1 + num2 + carry));
                    carry = 0;
                }
            }
        }
        if (carry == 1) res = addNode(res,1);
        return reverse(head);
    }

    private ListNode addNode(ListNode head, int num) {
        head.next = new ListNode(num);
        return head.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) return null;

        ListNode curr = head, next = head.next;
        if (next == null) return head;

        while (next != null) {
            ListNode nextNext = next.next;
            next.next = curr;
            if (curr == head) curr.next = null;
            curr = next;
            next = nextNext;
        }

        return curr;
    }
}


class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }

        return list.val == 0 ? list.next : list;
    }
}