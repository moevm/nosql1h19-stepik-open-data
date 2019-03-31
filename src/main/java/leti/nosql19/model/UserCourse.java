package leti.nosql19.model;

import lombok.Data;

@Data
public class UserCourse {

    private int attempts;

    private int comments;

    private double percent;

    private int stepsPassingCount;

    private boolean successPassing;

    public UserCourse(int attempts, int comments, double percent, int stepsPassingCount, boolean successPassing) {
        this.attempts = attempts;
        this.comments = comments;
        this.percent = percent;
        this.stepsPassingCount = stepsPassingCount;
        this.successPassing = successPassing;
    }
}

