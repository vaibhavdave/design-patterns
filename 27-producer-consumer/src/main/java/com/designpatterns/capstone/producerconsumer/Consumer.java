package com.designpatterns.capstone.producerconsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Pulls work off the queue with no idea which producer put it there, or how many other consumers
 * are pulling from the same queue — that mutual ignorance is what lets you scale either side
 * independently (more consumers to process faster, more producers to generate faster) without
 * touching the other side's code.
 */
public class Consumer implements Runnable {

    /** Sentinel signaling "no more work" — reference equality, never a real task's contents. */
    public static final Task POISON_PILL = new Task(-1, "POISON_PILL");

    private final BlockingQueue<Task> queue;
    private final java.util.function.Consumer<Task> onTaskProcessed;

    public Consumer(BlockingQueue<Task> queue, java.util.function.Consumer<Task> onTaskProcessed) {
        this.queue = queue;
        this.onTaskProcessed = onTaskProcessed;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = queue.take();
                if (task == POISON_PILL) {
                    return;
                }
                onTaskProcessed.accept(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
