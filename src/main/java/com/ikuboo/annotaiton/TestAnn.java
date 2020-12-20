package com.ikuboo.annotaiton;

/**
 * Created by Administrator on 2018/1/30 0030.
 *
 */

public class TestAnn {
    @MyAnn(name = "lisi")
   public void say() throws NoSuchMethodException {
        boolean exist = this.getClass().getMethod("say").isAnnotationPresent(MyAnn.class);

        if(exist){
            MyAnn say = this.getClass().getMethod("say").getAnnotation(MyAnn.class);
            System.out.println(say.name());
        }
   }
}
