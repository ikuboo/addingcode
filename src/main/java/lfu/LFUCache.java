package lfu;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    private Map<Integer,DLinkedNode> cache = new HashMap<Integer,DLinkedNode>();
    private int capacity,size;
    private DLinkedNode head,tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node==null){
            return -1;
        }
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        //若key值不存在，直接插入结点到头部，再判断当前容量是否大于capacity，如果是，就删除最不经常访问的结点
        if(node==null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;
            cache.put(key,newNode);
            addNode(newNode);
            size++;
            if(size>capacity){
                DLinkedNode res = removeLastNode();
                cache.remove(res.key);
                size--;
            }
        }else{
            //若key值存在，则更新value值，并移动该结点到链表头部
            node.value = value;
            removeToHead(node);
        }
    }
    //添加结点到头部
    private void addNode(DLinkedNode node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        node.count++;
    }
    //删除结点
    private void removeNode(DLinkedNode node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    //移动结点到头部
    private void removeToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }
    //删除最不经常访问的结点
    private DLinkedNode removeLastNode(){
        //从head.next.next结点开始查找，因为head.next是我们刚插入的结点，不参与这次筛选
        DLinkedNode pNode = head.next.next;
        int temp = tail.prev.count;
        //找到链表中的结点最低的访问频率
        while(pNode!=null && pNode.count>0){
            if(temp>pNode.count){
                temp = pNode.count;
            }
            pNode = pNode.next;
        }
        pNode = tail.prev;
        //从链表尾部开始查找具有最低访问频率的结点
        while(pNode.count>0 && pNode.count!=temp){
            pNode = pNode.prev;
        }
        removeNode(pNode);
        return pNode;
    }

    class DLinkedNode{
        int key;
        int value;
        int count = 0;
        DLinkedNode prev;
        DLinkedNode next;
    }
}
