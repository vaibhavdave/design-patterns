package com.designpatterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The Caretaker. Stores and retrieves {@link TextEditor.EditorMemento} instances but never
 * inspects what's inside them — it only ever calls {@code push}/{@code pop} on an opaque handle.
 */
public final class EditorHistory {

    private final Deque<TextEditor.EditorMemento> history = new ArrayDeque<>();

    public void save(TextEditor.EditorMemento memento) {
        history.push(memento);
    }

    public TextEditor.EditorMemento undo() {
        if (history.isEmpty()) {
            throw new IllegalStateException("No saved state to restore");
        }
        return history.pop();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
