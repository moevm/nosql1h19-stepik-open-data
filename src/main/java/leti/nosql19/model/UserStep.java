package leti.nosql19.model;

import lombok.Data;

@Data
public class UserStep {

    private int step;

    private int attempts;

    private int comments;

    private boolean successPassing;

    public UserStep(int step, int attempts, int comments, boolean successPassing) {
        this.step = step;
        this.attempts = attempts;
        this.comments = comments;
        this.successPassing = successPassing;
    }
}
