package com.tianyangche.practice.others;

import com.apple.concurrent.Dispatch;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by tianyangche on 4/26/16.
 */
public class MergeKSortedLists {
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private class ListComparator implements Comparator<ListNode> {
        public int compare(ListNode node1, ListNode node2) {
            if (node1 == null || node2 == null) {
                return node1 == null ? 1 : -1;
            }
            return  node1.val - node2.val;
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, new ListComparator());
        for (ListNode node : lists) {
            heap.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (heap.peek() != null) {
            ListNode temp = heap.poll();
            curr.next = temp;
            curr = curr.next;
            temp = temp.next;
            heap.offer(temp);
        }

        return dummy.next;
    }
}
