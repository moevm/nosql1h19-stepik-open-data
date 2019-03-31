package leti.nosql19.model;

import lombok.Data;

import java.util.List;

@Data
public class UserProgress {

    private UserCourse course;

    private List<UserModule> modules;

    private List<UserStep> steps;

    public UserProgress(UserCourse course, List<UserModule> modules, List<UserStep> steps) {
        this.course = course;
        this.modules = modules;
        this.steps = steps;
    }
}
