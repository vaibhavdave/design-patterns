package com.designpatterns.behavioral.memento;

/**
 * Types several "edits", checkpointing the state immediately before each one, then undoes them
 * one at a time. Saving BEFORE mutating (not after) is what makes undo meaningful: the most
 * recent checkpoint on the stack is always the state you'd want to go back to.
 */
public final class MementoDemo {

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        history.save(editor.save());
        editor.append("Hello");
        System.out.println("After edit 1: " + editor.getContent());

        history.save(editor.save());
        editor.append(", World");
        System.out.println("After edit 2: " + editor.getContent());

        history.save(editor.save());
        editor.append("!");
        System.out.println("After edit 3: " + editor.getContent());

        editor.restore(history.undo());
        System.out.println("After undo 1: " + editor.getContent());

        editor.restore(history.undo());
        System.out.println("After undo 2: " + editor.getContent());

        editor.restore(history.undo());
        System.out.println("After undo 3: " + editor.getContent());
    }
}
