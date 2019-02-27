package cn.wangjie.learn.entity;

import lombok.Getter;

import java.util.LinkedList;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-02-22 18:44
 **/
@Getter
public class Node<T> {
    private T value;
    private LinkedList<Node<String>> childNodes;

    public Node(T value) {
        this.value = value;
        childNodes = new LinkedList<>();
    }

    public boolean hasChild(){
        return childNodes.size()>0;
    }

    public void addChild(Node<String> node){
        childNodes.add(node);
    }

    public static void main(String[] args) {
        /**
         *               a
         *         b     c     d
         *      e    f   g     h
         *               i
         */
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        Node<String> g = new Node<>("g");
        Node<String> h = new Node<>("h");
        Node<String> i = new Node<>("i");
        g.addChild(i);
        b.addChild(e);
        b.addChild(f);
        c.addChild(g);
        d.addChild(h);
        a.addChild(b);
        a.addChild(c);
        a.addChild(d);

        System.out.println("广度优先遍历 ");
        LinkedList<Node<String>> nodeList = new LinkedList<>();
        nodeList.add(a);
        while (!nodeList.isEmpty()){
            Node<String> m = nodeList.pollFirst();
            System.out.println(m.getValue());
            nodeList.addAll(m.getChildNodes());
        }
        System.out.println("深度优先遍历 ");
        LinkedList<Node<String>> nodeList2 = new LinkedList<>();
        nodeList2.add(a);
        while (!nodeList2.isEmpty()){
            Node<String> m = nodeList2.pollLast();
            System.out.println(m.getValue());
            nodeList2.addAll(m.getChildNodes());
        }
    }
}
