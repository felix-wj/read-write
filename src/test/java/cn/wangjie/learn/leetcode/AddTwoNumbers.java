package cn.wangjie.learn.leetcode;

import org.junit.Test;

/**
 * @program: learn
 * @description: 两数之和
 * 给出两个 非空 的链表用来表示两个非负的整数。如321 表示为3->2->1,并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * @author: WangJie
 * @create: 2019-06-03 17:49
 **/


public class AddTwoNumbers {
    @Test
    public void addTest(){
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next=b;
        b.next=c;
        c.next=d;

        ListNode e = new ListNode(4);
        ListNode f = new ListNode(9);
        ListNode g = new ListNode(9);
        ListNode h = new ListNode(9);
        ListNode i = new ListNode(9);
        ListNode j = new ListNode(9);
        e.next=f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        print(a);
        print(e);
        print(addTwoNumbers(a,e));

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);
        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode node;
        while (rl1!=null&& rl2!=null){
            node = new ListNode(rl1.val+rl2.val);
            tail.next = node;
            tail = tail.next;
            rl1 = rl1.next;
            rl2 = rl2.next;

        }
        ListNode notNull = rl1==null?rl2:rl1;
        while (notNull!=null){
            node = new ListNode(notNull.val);
            tail.next = node;
            tail = tail.next;
            notNull = notNull.next;
        }
        head = head.next;
        ListNode i = head;
        while (i!=null){
            if (i.val>9){
                int m = i.val/10;
                int n = i.val%10;
                if (i.next == null){
                    i.next = new ListNode(m);
                }else {
                    i.next.val+=m;
                }
                i.val = n;
            }
            i = i.next;
        }
        return reverse(head);
    }
    public ListNode reverse(ListNode l){
        ListNode head =null;
        ListNode i = l;
        while (i!=null){
            ListNode node = new ListNode(i.val);
            node.next=head;
            head = node;
            i = i.next;
        }
        return head;
    }
    public void print(ListNode l){
        System.out.println();
        for(ListNode i = l;i!=null;i=i.next){
            System.out.print(i.val+" ");
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}