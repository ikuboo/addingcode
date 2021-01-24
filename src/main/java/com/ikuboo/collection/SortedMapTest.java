package com.ikuboo.collection;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * SortMap demo
 * @author yuanchunsen@jd.com
 * 2018/5/9.
 */
public class SortedMapTest {
    public static void main(String[] args) {
        SortedMapTest test = new SortedMapTest();
        //test.test1();
        //test.test2();
        test.test3();
    }

    public void test1(){
        SortedMap<Person, String> sortedMap = new TreeMap<Person, String>();
        sortedMap.put(new Person(10), "s-10");
        sortedMap.put(new Person(30), "s-30");
        sortedMap.put(new Person(20), "s-20");

        System.out.println(sortedMap);
    }


    public void test2(){
        SortedMap<Person, String> sortedMap = new TreeMap<Person, String>();
        sortedMap.put(new Person(10), "s-10");
        sortedMap.put(new Person(30), "s-30");
        sortedMap.put(new Person(20), "s-20");
        sortedMap.put(new Person(50), "s-50");
        sortedMap.put(new Person(80), "s-80");
        sortedMap.put(new Person(100), "s-100");

        System.out.println(sortedMap);

        System.out.println(sortedMap.firstKey());//第一个
        System.out.println(sortedMap.lastKey());//最后一个
        System.out.println(sortedMap.tailMap(new Person(50)));//大于等于
    }
    public void test3(){
        NavigableMap<Person, String> navigableMap = new TreeMap<Person, String>();
        navigableMap.put(new Person(10), "s-10");
        navigableMap.put(new Person(30), "s-30");
        navigableMap.put(new Person(20), "s-20");
        navigableMap.put(new Person(50), "s-50");
        navigableMap.put(new Person(80), "s-80");
        navigableMap.put(new Person(100), "s-100");

        System.out.println(navigableMap);
        System.out.println(navigableMap.descendingMap());//逆向排序
        System.out.println(navigableMap.floorEntry(new Person(50)));//小于等于指定key的最大元素
        System.out.println(navigableMap.lowerEntry(new Person(50)));//小于指定key的最大元素
        System.out.println(navigableMap.headMap(new Person(50),true));//小于等于50
        System.out.println(navigableMap.tailMap(new Person(50),true));//大于等于50
        System.out.println(navigableMap.subMap(new Person(30),true, new Person(80),false)); //小于80，大于等于30
    }



    }
class Person implements Comparable<Person>{
    public Integer id;

    public Person(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id + "";
    }

    @Override
    public int compareTo(Person o) {

        if(this.id > o.id){
            return 1;
        }else if(this.id < o.id){
            return -1;
        }else
            return 0;
    }
}
