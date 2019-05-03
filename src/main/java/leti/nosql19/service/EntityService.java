package leti.nosql19.service;

import leti.nosql19.model.Course;
import leti.nosql19.model.User;

import java.util.List;

public interface EntityService {

    void add(Course entity);

    List<Course> findAll();

    Course findById(String id);

    void delete(Course course);

    void deleteById(String id);

    void saveOrUpdate(Course course);

    List<String> getModules(String id);

    List<Integer> getComments(String id);

    List<Integer> getAttempts(String id);

    List<String> getCoursesNames();

    List<String> getUsersName(String courseName);

    User getUserByName(String userName, String courseName);

    List<Integer> getUserModuleAttempts(String userName, String courseName);

    List<Integer> getUserModuleComments(String userName, String courseName);

    List<Integer> getUserStepAttempts(String userName, String courseName);

    List<Integer> getUserStepComments(String userName, String courseName);
}
