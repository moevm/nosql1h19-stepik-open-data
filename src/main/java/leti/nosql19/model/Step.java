package leti.nosql19.model;

import lombok.Data;

@Data
public class Step {

    private int step;

    private int attempts;

    private int passing;

    private int comments;

    public Step(int step, int attempts, int passing, int comments) {
        this.step = step;
        this.attempts = attempts;
        this.passing = passing;
        this.comments = comments;
    }
}
