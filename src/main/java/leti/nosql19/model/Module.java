package leti.nosql19.model;


import lombok.Data;

import java.util.List;

@Data
public class Module {

    private String module;

    private int attempts;

    private int passing;

    private int comments;

    private List<Step> steps;

    public Module(String module, int attempts, int passing, int comments, List<Step> steps) {
        this.module = module;
        this.attempts = attempts;
        this.passing = passing;
        this.comments = comments;
        this.steps = steps;
    }
}
