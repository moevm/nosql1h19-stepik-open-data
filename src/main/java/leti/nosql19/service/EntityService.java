package leti.nosql19.service;

import leti.nosql19.model.Course;

import java.util.List;

public interface EntityService {

    void add(Course entity);

    List<Course> findAll();

    void delete(Course course);

    void deleteById(String id);

    void saveOrUpdate(Course course);

}
