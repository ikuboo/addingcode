package com.ikuboo.disruptor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //创建缓冲池
        ExecutorService executor = Executors.newCachedThreadPool();

        //创建bufferSize ,也就是RingBuffer大小，必须是2的N次方
        //如果生产的速度大于消费的速度，当RingBuffer满之后，生产就阻塞
        int ringBufferSize = 8; //

        /**
         new BlockingWaitStrategy() 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
         new SleepingWaitStrategy() 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
         new YieldingWaitStrategy() 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
         */


        /**
         *  参数说明：
         */

        //创建disruptor
        Disruptor<StringEvent> disruptor = new Disruptor<>(new EventFactory<StringEvent>() {
            @Override
            public StringEvent newInstance() {
                return new StringEvent();
            }
        }, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());

        // 注册消费者
        disruptor.handleEventsWith(new Customer("C1"));
        //disruptor.handleEventsWith(new Customer("C2"));

        // 启动
        disruptor.start();

        //Disruptor 的事件发布过程是一个两阶段提交的过程：
        //发布事件
        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();

        Producer producer = new Producer(ringBuffer);
        for (int i = 0; i < 100; i++) {
            producer.onData("value:" +i);
            System.out.println("--------------------" + i);
        }


        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；

    }
}
