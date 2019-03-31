package leti.nosql19.model;

import lombok.Data;

@Data
public class User {

    private String user;

    private UserProgress progressBy;

    public User(String user, UserProgress progressBy) {
        this.user = user;
        this.progressBy = progressBy;
    }
}
