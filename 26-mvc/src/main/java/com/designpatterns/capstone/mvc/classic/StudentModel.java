package com.designpatterns.capstone.mvc.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * The Model owns state and knows nothing about any View or Controller. It only knows how to
 * notify whoever is listening ({@link ModelChangeListener}) when it changes — this listener
 * relationship is the Observer pattern (see {@code 14-observer}), and it is what makes classic
 * MVC "MVC" rather than just three loosely related classes: the View updates itself in reaction
 * to Model changes, it is never handed a value directly by the Controller.
 */
public class StudentModel {

    private final List<ModelChangeListener> listeners = new ArrayList<>();
    private String name;
    private double gpa;

    public StudentModel(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public void addListener(ModelChangeListener listener) {
        listeners.add(listener);
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        notifyListeners();
    }

    public void setName(String name) {
        this.name = name;
        notifyListeners();
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    private void notifyListeners() {
        for (ModelChangeListener listener : listeners) {
            listener.onModelChanged(this);
        }
    }
}
