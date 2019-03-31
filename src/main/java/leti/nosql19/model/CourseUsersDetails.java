package leti.nosql19.model;

import lombok.Data;

@Data
public class CourseUsersDetails {

    private int all;

    private int graduated;

    private int graduatedWirthHonours;

    public CourseUsersDetails(int all, int graduated, int graduatedWirthHonours) {
        this.all = all;
        this.graduated = graduated;
        this.graduatedWirthHonours = graduatedWirthHonours;
    }
}
