package leti.nosql19.model;

import lombok.Data;

@Data
public class CourseInformation {

    private CourseUsersDetails users;

    private int modules;

    private int steps;

    private int comments;

    public CourseInformation(CourseUsersDetails users, int modules, int steps, int comments) {
        this.users = users;
        this.modules = modules;
        this.steps = steps;
        this.comments = comments;
    }
}
