package com.designpatterns.capstone.mvc.classic;

/** The View's role in classic MVC: it observes the Model, it doesn't get pushed a full redraw call. */
public interface ModelChangeListener {

    void onModelChanged(StudentModel model);
}
