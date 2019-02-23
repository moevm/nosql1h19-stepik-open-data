package leti.nosql19.model;

import org.springframework.data.annotation.Id;

public class Entity {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Entity() {}

    public Entity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s",
                firstName, lastName);
    }
}
