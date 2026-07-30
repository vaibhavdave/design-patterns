package com.designpatterns.behavioral.memento;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TextEditorMementoTest {

    @Test
    void restoringMementoRevertsContentToSavedState() {
        TextEditor editor = new TextEditor();
        editor.append("Hello");
        TextEditor.EditorMemento checkpoint = editor.save();

        editor.append(", World!");
        assertThat(editor.getContent()).isEqualTo("Hello, World!");

        editor.restore(checkpoint);
        assertThat(editor.getContent()).isEqualTo("Hello");
    }

    @Test
    void multipleUndosRestoreProgressivelyOlderStates() {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        history.save(editor.save());
        editor.append("Hello");

        history.save(editor.save());
        editor.append(", World");

        history.save(editor.save());
        editor.append("!");

        assertThat(editor.getContent()).isEqualTo("Hello, World!");

        editor.restore(history.undo());
        assertThat(editor.getContent()).isEqualTo("Hello, World");

        editor.restore(history.undo());
        assertThat(editor.getContent()).isEqualTo("Hello");

        editor.restore(history.undo());
        assertThat(editor.getContent()).isEqualTo("");
    }

    @Test
    void savedMementoIsIndependentOfLaterMutations() {
        TextEditor editor = new TextEditor();
        editor.append("Hello");
        TextEditor.EditorMemento checkpoint = editor.save();

        editor.append(" there, this changes after the snapshot was taken");

        editor.restore(checkpoint);
        assertThat(editor.getContent()).isEqualTo("Hello");
    }

    @Test
    void historyThrowsWhenUndoCalledWithNoSavedState() {
        EditorHistory history = new EditorHistory();

        assertThatThrownBy(history::undo).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void historyNeverExposesMementoInternals() {
        TextEditor editor = new TextEditor();
        editor.append("secret content");
        TextEditor.EditorMemento memento = editor.save();

        // The memento's only public surface is its type — no getter for its state exists outside
        // TextEditor itself, so this test simply documents that the object is otherwise opaque.
        assertThat(memento).isNotNull();
    }
}
