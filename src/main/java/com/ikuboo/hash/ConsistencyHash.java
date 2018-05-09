package com.ikuboo.hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author yuanchunsen@jd.com
 * 2018/5/8.
 */
public class ConsistencyHash {
    // 环的所有节点
    private TreeMap<Long, String> allNodes = new TreeMap<>();

    // 真实服务器节点
    private List<String> realNodes = new ArrayList<>();

    // 设置虚拟节点数目
    private int VIRTUAL_NODE_COUNT = 150;

    /**
     * 初始化一致环
     */
    public void init() {

        // 加入五台真实服务器
        realNodes.add("192.168.0.0-服务器0");
        realNodes.add("192.168.0.1-服务器1");
        realNodes.add("192.168.0.2-服务器2");
        realNodes.add("192.168.0.3-服务器3");
        realNodes.add("192.168.0.4-服务器4");

        // 构造每台真实服务器的虚拟节点
        for (int i = 0; i < realNodes.size(); i++) {
            String nodeInfo = realNodes.get(i);
            for (int j = 0; j < VIRTUAL_NODE_COUNT; j++) {
                byte[] md5Bytes = Utils.computeMd5("NODE-" + i + "-VIRTUAL-" + j);
                long hash = Utils.hash(md5Bytes, 0);
                allNodes.put(hash, nodeInfo);
            }
        }
    }

    public static void main(String[] args) {

        ConsistencyHash consistencyHash = new ConsistencyHash();
        consistencyHash.init();

        // 循环50次，是为了取500个数来测试效果，当然也可以用其他任何的数据来测试
        int _0 = 0;
        int _1 = 0;
        int _2 = 0;
        int _3 = 0;
        int _4 = 0;


        for (int i = 0; i < 10000; i++) {
            // 随便取一个数的md5
            byte[]  ranNum = Utils.computeMd5(String.valueOf(i));

            // 分配到随即的hash环上面
            long key = Utils.hash(ranNum, 2);
            // long key = consistencyHash.hash(ranNum, ran.nextInt(consistencyHash.VIRTUAL_NODE_COUNT));

            // 获取他所属服务器的信息
            // System.out.println(consistencyHash.getNodeInfo(key));
            if (consistencyHash.getNodeInfo(key).equals("192.168.0.0-服务器0")){
                _0++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.1-服务器1")){
                _1++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.2-服务器2")){
                _2++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.3-服务器3")){
                _3++;
            }else if (consistencyHash.getNodeInfo(key).equals("192.168.0.4-服务器4")){
                _4++;
            }else{
                System.out.println("error");
            }
        }

        // 输出每台服务器负载情况
        System.out.println("_0 = " + _0);
        System.out.println("_1 = " + _1);
        System.out.println("_2 = " + _2);
        System.out.println("_3 = " + _3);
        System.out.println("_4 = " + _4);
    }





    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public String getNodeInfo(long hash) {
        Long key = hash;
        SortedMap<Long, String> tailMap = allNodes.tailMap(key);
        if (tailMap.isEmpty()) {
            key = allNodes.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        return allNodes.get(key);
    }


}
