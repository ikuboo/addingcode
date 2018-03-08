package com.ikuboo.multithread.base;

/**
 * 测试多线程的速度
 * 循环{100000    }次，多线程耗时：{11   }单线程耗时:{12   }
 * 循环{1000000   }次，多线程耗时：{36   }单线程耗时:{30   }
 * 循环{10000000  }次，多线程耗时：{158  }单线程耗时:{134  }
 * 循环{100000000 }次，多线程耗时：{1174 }单线程耗时:{1222 }
 * 循环{1000000000}次，多线程耗时：{7843 }单线程耗时:{8517 }
 * @author yuanchunsen@jd.com
 *         2018/3/8.
 */
public class TestMultiTheadSpeed {
    //十万，一百万，一千万，一亿，十亿
    private static final Long[] loopCounts = {100000L,1000000L,10000000L,100000000L,1000000000L};

    public static void main(String[] args) throws InterruptedException {
        TestMultiTheadSpeed t = new TestMultiTheadSpeed();
        for(Long loopCount : loopCounts){
            long multi = t.multi(loopCount);
            long serial = t.serial(loopCount);
            System.out.println(String.format("循环{%-10d}次，多线程耗时：{%-5d}单线程耗时:{%-5d}", loopCount, multi, serial));
        }

    }

    private long multi(final Long loopCount) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Long a = 0L;
                for(int i = 0; i < loopCount; i ++){
                    a+=5;
                }
            }
        });
        thread.start();
        Long b = 0L;
        for(int i = 0; i < loopCount; i ++){
            b-=5;
        }
        thread.join();
        return System.currentTimeMillis() - startTime;
    }

    private long serial(final Long loopCount){
        long startTime = System.currentTimeMillis();

        Long a = 0L;
        for(int i = 0; i < loopCount; i ++){
            a+=5;
        }
        Long b = 0L;
        for(int i = 0; i < loopCount; i ++){
            b-=5;
        }

        return System.currentTimeMillis() - startTime;
    }
}
