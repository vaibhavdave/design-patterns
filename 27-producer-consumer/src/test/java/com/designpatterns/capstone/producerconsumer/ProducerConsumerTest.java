package com.designpatterns.capstone.producerconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class ProducerConsumerTest {

    @Test
    @Timeout(10)
    void everyProducedTaskIsProcessedExactlyOnceAcrossMultipleConsumers() throws InterruptedException {
        int taskCount = 50;
        int consumerCount = 4;
        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(3);
        AtomicInteger processed = new AtomicInteger();
        List<Integer> processedIds = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(consumerCount + 1);
        executor.submit(new Producer(queue, taskCount, consumerCount));
        for (int i = 0; i < consumerCount; i++) {
            executor.submit(new Consumer(queue, task -> {
                processed.incrementAndGet();
                processedIds.add(task.id());
            }));
        }

        executor.shutdown();
        boolean finishedInTime = executor.awaitTermination(9, TimeUnit.SECONDS);

        assertThat(finishedInTime).isTrue();
        assertThat(processed.get()).isEqualTo(taskCount);
        assertThat(processedIds).hasSize(taskCount).doesNotHaveDuplicates();
    }

    @Test
    @Timeout(10)
    void aSingleProducerAndConsumerPreserveFifoOrderThroughABoundedQueue() throws InterruptedException {
        BlockingQueue<Task> queue = new ArrayBlockingQueue<>(1);
        List<Integer> processedIds = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Producer(queue, 10, 1));
        executor.submit(new Consumer(queue, task -> processedIds.add(task.id())));

        executor.shutdown();
        executor.awaitTermination(9, TimeUnit.SECONDS);

        assertThat(processedIds).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }
}
