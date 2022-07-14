package com.Edu;

import com.Edu.student.Student;
import com.Edu.student.StudentController;
import com.Edu.student.StudentRepository;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.Route;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * A Designer generated component for the my-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("my-view")
@JsModule("./src/views/my-view.ts")
@Route("")
public class MyView extends LitTemplate {

    @Id("vaadinGrid")
    private Grid<Student> grid;

    StudentRepository studentRepository;
    StudentController studentController;

    /**
     * Creates a new MyView.
     */
    // You can initialise any data required for the connected UI components here.
    public MyView(StudentRepository studentRepository, StudentController studentController) {

        this.studentRepository = studentRepository;
        this.studentController = studentController;
        //grid.addColumn(Student::getId).setHeader("id").setVisible(false);;
        grid.addColumn(Student::getLastName).setHeader("Last name");
        grid.addColumn(Student::getFirstName).setHeader("First name");
        grid.addColumn(Student::getStudyYear).setHeader("Year");
        grid.addColumn(Student::getEmail).setHeader("Email");
        grid.addColumn(Student::getStatus).setHeader("Status");

        grid.setItems(studentRepository.findAll());

        grid.addComponentColumn(student -> {
                    Button button = new Button("report");
                    button.addClickListener(click ->
                    {
                        try {
                            studentController.GenerateReport("pdf",student.getId());
                        } catch (JRException | SQLException | FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return button;
                }
            ).setHeader("Action");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

}
