package com.ikuboo.annotaiton;


import java.lang.reflect.Field;

public class Main3 {
    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
        MyService myService = new MyService();

        Thread t = new Thread(() -> {
            while (true){
                myService.print();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


        //修改变量的值，模拟配置重新远端下发配置
        Field[] declaredFields = MyService.class.getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(AutoConfig.class)){
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                AutoConfig annotation = field.getAnnotation(AutoConfig.class);
                String key = annotation.key();
                if(key.equals("key123")){
                    for (int i = 0; i < 100; i++) {
                        field.set(myService, i+"");
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }
}
