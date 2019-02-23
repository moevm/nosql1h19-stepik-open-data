package leti.nosql19.service;

import leti.nosql19.model.Entity;

import java.util.List;

public interface EntityService {

    void add(Entity entity);

    List<Entity> findAll();

    List<Entity> findByLastName(String lastName);

}
