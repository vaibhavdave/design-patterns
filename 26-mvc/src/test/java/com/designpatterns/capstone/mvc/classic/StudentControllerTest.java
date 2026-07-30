package com.designpatterns.capstone.mvc.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentControllerTest {

    @Test
    void updatingTheModelThroughTheControllerNotifiesTheView() {
        StudentModel model = new StudentModel("Ada", 3.5);
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        controller.updateGpa(4.0);

        assertThat(view.getRenderedLines()).containsExactly("Ada: GPA 4.00");
    }

    @Test
    void multipleViewsCanObserveTheSameModelIndependently() {
        StudentModel model = new StudentModel("Grace", 3.0);
        StudentView view1 = new StudentView();
        StudentView view2 = new StudentView();
        model.addListener(view2);
        StudentController controller = new StudentController(model, view1);

        controller.rename("Grace Hopper");

        assertThat(view1.getRenderedLines()).hasSize(1);
        assertThat(view2.getRenderedLines()).hasSize(1);
    }

    @Test
    void controllerNeverExposesTheViewAMutationMethodDirectly() {
        StudentModel model = new StudentModel("Linus", 3.9);
        StudentView view = new StudentView();
        new StudentController(model, view);

        assertThat(view.getRenderedLines()).isEmpty();
    }
}
