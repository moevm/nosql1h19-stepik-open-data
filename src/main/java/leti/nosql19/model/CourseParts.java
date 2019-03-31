package leti.nosql19.model;

import lombok.Data;

import java.util.List;

@Data
public class CourseParts {

    private List<Module> modules;

    private List<User> users;

    public CourseParts(List<Module> modules, List<User> users) {
        this.modules = modules;
        this.users = users;
    }
}
