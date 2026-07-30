package com.designpatterns.capstone.mvc.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * The View renders whatever the Model currently holds and never mutates it. It also never talks
 * to the Controller directly — it only reacts to {@link #onModelChanged}. {@link #renderedLines}
 * exists so tests can assert what was rendered without parsing stdout.
 */
public class StudentView implements ModelChangeListener {

    private final List<String> renderedLines = new ArrayList<>();

    @Override
    public void onModelChanged(StudentModel model) {
        String line = "%s: GPA %.2f".formatted(model.getName(), model.getGpa());
        renderedLines.add(line);
        System.out.println(line);
    }

    public List<String> getRenderedLines() {
        return List.copyOf(renderedLines);
    }
}
