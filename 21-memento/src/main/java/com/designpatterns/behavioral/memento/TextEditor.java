package com.designpatterns.behavioral.memento;

/**
 * The Originator. {@link EditorMemento} is a static nested class with a private constructor and a
 * private accessor — only {@code TextEditor} can create one or read its state back (Java grants a
 * nested class's private members to its enclosing class), so a {@link EditorHistory} holding a
 * pile of mementos can never peek inside them or mutate the editor's past.
 */
public final class TextEditor {

    private final StringBuilder content = new StringBuilder();

    public void append(String text) {
        content.append(text);
    }

    public String getContent() {
        return content.toString();
    }

    public EditorMemento save() {
        return new EditorMemento(content.toString());
    }

    public void restore(EditorMemento memento) {
        content.setLength(0);
        content.append(memento.getState());
    }

    public static final class EditorMemento {

        private final String state;

        private EditorMemento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }
    }
}
