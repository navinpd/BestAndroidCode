package com.big.ds_algo.leetcode

class _19_RemoveNthFromEnd_ {

    //    Definition for singly-linked list.
    class ListNode(var `val`: Int, var node: ListNode?) {
        var next = node
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val head = ListNode(2, ListNode(3, ListNode(4, ListNode(5, null))))
            var i = removeNthFromEnd(head = head, 2)
            println(i)
        }

        fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            var front = head
            var back = head
            var start = n
            while (start > 0) {
                front = front!!.next
                start--
            }
            while (front != null && front!!.next != null) {
                front = front!!.next
                back = back!!.next
            }
            if (back!!.next != null && back.next!!.next != null)
                back.next = back.next!!.next
            else if(back.next != null && back.next!!.next == null)
                return back.next
            else
                return null
            return head
        }
    }

}