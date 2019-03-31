package leti.nosql19.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Course {

    @Id
    private String courseName;

    private CourseParts listOf;

    private CourseInformation countOf;

    public Course(String courseName, CourseParts listOf, CourseInformation countOf) {
        this.courseName = courseName;
        this.listOf = listOf;
        this.countOf = countOf;
    }
}
