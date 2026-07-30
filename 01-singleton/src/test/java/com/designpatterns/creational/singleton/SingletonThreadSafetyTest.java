package com.designpatterns.creational.singleton;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonThreadSafetyTest {

    private static final int THREAD_COUNT = 50;

    @Test
    void enumSingletonAlwaysReturnsSameInstance() {
        assertThat(EnumSingleton.INSTANCE).isSameAs(EnumSingleton.INSTANCE);
    }

    @RepeatedTest(5)
    void doubleCheckedLockingProducesExactlyOneInstanceUnderConcurrency() throws InterruptedException {
        Set<DoubleCheckedLockingSingleton> instances = collectInstancesConcurrently(
                DoubleCheckedLockingSingleton::getInstance);
        assertThat(instances).hasSize(1);
    }

    @RepeatedTest(5)
    void threadSafeSingletonProducesExactlyOneInstanceUnderConcurrency() throws InterruptedException {
        Set<ThreadSafeSingleton> instances = collectInstancesConcurrently(ThreadSafeSingleton::getInstance);
        assertThat(instances).hasSize(1);
    }

    /**
     * {@link NaiveSingleton} is NOT asserted to be broken here: a data race that "usually" wins
     * isn't a reliable test signal, and flaky assertions erode trust in the whole suite. The
     * README explains the race in prose; this test suite only asserts the guarantees that the
     * thread-safe variants actually make.
     */
    private static <T> Set<T> collectInstancesConcurrently(java.util.function.Supplier<T> factory)
            throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch readyLatch = new CountDownLatch(THREAD_COUNT);
        CountDownLatch startLatch = new CountDownLatch(1);
        Set<T> instances = Collections.synchronizedSet(new HashSet<>());

        try {
            for (int i = 0; i < THREAD_COUNT; i++) {
                executor.submit(() -> {
                    readyLatch.countDown();
                    try {
                        startLatch.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    instances.add(factory.get());
                });
            }
            readyLatch.await();
            startLatch.countDown();
        } finally {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }

        return instances;
    }
}
