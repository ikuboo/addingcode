package com.ikuboo.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCache2 {
    private Map<Integer,DLinkedNode> cache = new HashMap<Integer,DLinkedNode>();
    private int capacity,size;
    private DLinkedNode head,tail;

    public LruCache2(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        //将头结点和尾结点相连
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node==null){
            return -1;
        }
        //若key值存在，移动该结点到头部
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        //若key值不存在，直接插入结点到头部，再判断当前容量是否大于capacity，如果是，就删除尾部结点
        if(node==null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key,newNode);
            addNode(newNode);
            size++;
            if(size>capacity){
                DLinkedNode last = tail.prev;
                cache.remove(last.key);
                removeLastNode();
                size--;
            }
        }else{
            //若key值存在，则更新value值，并移动该结点到链表头部
            node.value = value;
            removeToHead(node);
        }
    }

    //插入结点到头部
    private void addNode(DLinkedNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    //删除结点
    private void removeNode(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    //删除尾部结点
    private void removeLastNode(){
        removeNode(tail.prev);
    }
    //移动结点到头部
    private void removeToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }
}
