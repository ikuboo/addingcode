package com.ikuboo.multithread.base.dump;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * http://note.youdao.com/noteshare?id=9ad67a7075140003e3ef5c8ae744ffe7&sub=E367BE7FAC2143838530784EF75B9D83
 * @author yuanchunsen@jd.com
 *         2018/3/23.
 */
public class DumpTest {
    public static List<String> listStr = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        List<Long> listLong = new ArrayList<>();

        while(true){
            for(Long i = 0L; i < 10000; i++){
                DumpTest.listStr.add("list_str_" + i );
                listLong.add(i);
            }
            System.out.println("DumpTest.add.success");
            TimeUnit.MINUTES.sleep(1);
        }
    }
}
