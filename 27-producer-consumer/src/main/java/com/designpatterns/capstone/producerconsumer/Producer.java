package com.designpatterns.capstone.producerconsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Knows nothing about who consumes its output or how many consumers exist — it only knows a
 * queue. If the queue is bounded and full, {@link BlockingQueue#put} blocks until a consumer
 * makes room: that backpressure is the whole reason to prefer a blocking queue over an unbounded
 * one — a slow consumer naturally throttles a fast producer instead of memory growing without
 * limit.
 */
public class Producer implements Runnable {

    private final BlockingQueue<Task> queue;
    private final int taskCount;
    private final int poisonPillsToSend;

    public Producer(BlockingQueue<Task> queue, int taskCount, int poisonPillsToSend) {
        this.queue = queue;
        this.taskCount = taskCount;
        this.poisonPillsToSend = poisonPillsToSend;
    }

    @Override
    public void run() {
        try {
            for (int id = 1; id <= taskCount; id++) {
                queue.put(new Task(id, "payload-" + id));
            }
            // One poison pill per consumer so every consumer thread gets a shutdown signal and
            // none is left blocked forever on an empty queue.
            for (int i = 0; i < poisonPillsToSend; i++) {
                queue.put(Consumer.POISON_PILL);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
