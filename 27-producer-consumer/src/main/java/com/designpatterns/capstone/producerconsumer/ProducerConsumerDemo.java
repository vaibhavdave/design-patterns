package com.designpatterns.capstone.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ProducerConsumerDemo {

    private ProducerConsumerDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        int taskCount = 20;
        int consumerCount = 3;
        // Capacity 5 with 20 tasks forces the producer to block and wait for consumers
        // repeatedly — that blocking IS the backpressure the pattern provides for free.
        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(5);
        AtomicInteger processed = new AtomicInteger();

        ExecutorService executor = Executors.newFixedThreadPool(consumerCount + 1);
        executor.submit(new Producer(queue, taskCount, consumerCount));
        for (int i = 0; i < consumerCount; i++) {
            executor.submit(new Consumer(queue, task -> {
                processed.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " processed " + task);
            }));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("Total processed: " + processed.get() + " / " + taskCount);
    }
}
