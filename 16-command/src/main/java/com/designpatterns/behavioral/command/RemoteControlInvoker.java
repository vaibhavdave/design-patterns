package com.designpatterns.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The Invoker. It knows nothing about {@link Light} or {@link Fan} — only that every
 * {@link Command} can be executed and undone — and keeps a history stack so undo can walk
 * backwards through everything that has happened.
 */
public final class RemoteControlInvoker {

    private final Deque<Command> history = new ArrayDeque<>();

    public void pressButton(Command command) {
        command.execute();
        history.push(command);
    }

    /** Undoing with an empty history is a defined no-op, not an exception — nothing to undo. */
    public void pressUndo() {
        if (history.isEmpty()) {
            return;
        }
        history.pop().undo();
    }

    public int historySize() {
        return history.size();
    }
}
