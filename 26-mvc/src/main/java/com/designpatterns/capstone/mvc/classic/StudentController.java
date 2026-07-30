package com.designpatterns.capstone.mvc.classic;

/**
 * The Controller is the only class that mutates the Model, and it does so in response to "user
 * input" (here, plain method calls standing in for UI events). It wires a {@link StudentView} up
 * as a listener at construction time; from then on the Controller never talks to the View
 * directly — the Model's notification does that.
 */
public class StudentController {

    private final StudentModel model;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        model.addListener(view);
    }

    public void updateGpa(double newGpa) {
        model.setGpa(newGpa);
    }

    public void rename(String newName) {
        model.setName(newName);
    }
}
