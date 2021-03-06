package leti.nosql19.service;

import leti.nosql19.model.*;
import leti.nosql19.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityServiceImpl(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }


    @Override
    public Course findById(String id) {
        return entityRepository.findByCourseNameStartingWith(id);
    }

    @Override
    public void add(Course entity) {

        entityRepository.insert(entity);
    }

    @Override
    public List<Course> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public void delete(Course course) {
        entityRepository.delete(course);
    }

    @Override
    public void deleteById(String id) {
        entityRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Course course) {
        entityRepository.save(course);
    }

    @Override
    public List<String> getModules(String id) {
        Course course = findById(id);

        List<String> result = new ArrayList<>();

        for (Module model : course.getListOf().getModules()) {
            result.add(model.getModule());
        }

        return result;
    }

    @Override
    public List<String> getCoursesNames() {
        List<String> result = new ArrayList<>();

        for (Course course : findAll()) {
            result.add(course.getCourseName());
        }
        return result;
    }

    @Override
    public List<String> getUsersName(String courseName) {
        Course course = findById(courseName);

        List<String> result = new ArrayList<>();

        for (User user : course.getListOf().getUsers()) {
            result.add(user.getUser());
        }
        return result;
    }

    @Override
    public List<Integer> getComments(String id) {
        Course course = findById(id);

        List<Integer> result = new ArrayList<>();

        for (Module model : course.getListOf().getModules()) {
            result.add(model.getComments());
        }

        return result;
    }

    @Override
    public List<Integer> getAttempts(String id) {
        Course course = findById(id);

        List<Integer> result = new ArrayList<>();

        for (Module model : course.getListOf().getModules()) {
            result.add(model.getAttempts());
        }

        return result;
    }


    @Override
    public User getUserByName(String userName, String courseName) {
        Course course = findById(courseName);

        User user = null;

        for (User user1 : course.getListOf().getUsers()) {
            if (user1.getUser().startsWith(userName))
                user = user1;
        }

        return user;
    }

    @Override
    public List<Integer> getUserModuleAttempts(String userName, String courseName) {
        User user = getUserByName(userName, courseName);
        if (user == null)
            return null;
        List<Integer> result = new ArrayList<>();

        for (UserModule module : user.getProgressBy().getModules()) {
            result.add(module.getAttempts());
        }

        return result;
    }

    @Override
    public List<Integer> getUserModuleComments(String userName, String courseName) {
        User user = getUserByName(userName, courseName);
        if (user == null)
            return null;
        List<Integer> result = new ArrayList<>();

        for (UserModule module : user.getProgressBy().getModules()) {
            result.add(module.getComments());
        }

        return result;
    }

    @Override
    public List<Integer> getUserStepAttempts(String userName, String courseName) {
        User user = getUserByName(userName, courseName);
        if (user == null)
            return null;
        List<Integer> result = new ArrayList<>();

        for (UserStep step : user.getProgressBy().getSteps()) {
            result.add(step.getAttempts());
        }

        return result;
    }

    @Override
    public List<Integer> getUserStepComments(String userName, String courseName) {
        User user = getUserByName(userName, courseName);
        if (user == null)
            return null;
        List<Integer> result = new ArrayList<>();

        for (UserStep step : user.getProgressBy().getSteps()) {
            result.add(step.getComments());
        }

        return result;
    }
}
