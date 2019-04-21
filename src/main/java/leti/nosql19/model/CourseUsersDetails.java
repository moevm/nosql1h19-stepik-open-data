package leti.nosql19.model;

import lombok.Data;

@Data
public class CourseUsersDetails {

    private int all;

    private int graduated;

    private int graduatedWithHonours;

    public CourseUsersDetails(int all, int graduated, int graduatedWithHonours) {
        this.all = all;
        this.graduated = graduated;
        this.graduatedWithHonours = graduatedWithHonours;
    }
}
