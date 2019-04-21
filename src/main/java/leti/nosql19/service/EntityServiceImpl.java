package leti.nosql19.service;

import leti.nosql19.model.Course;
import leti.nosql19.model.Module;
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
        return entityRepository.findByCourseName(id);
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

        for (Module model: course.getListOf().getModules()) {
            result.add(model.getModule());
        }

        return result;
    }

    @Override
    public List<Integer> getComments(String id) {
        Course course = findById(id);

        List<Integer> result = new ArrayList<>();

        for (Module model: course.getListOf().getModules()) {
            result.add(model.getComments());
        }

        return result;
    }

    @Override
    public List<Integer> getAttempts(String id) {
        Course course = findById(id);

        List<Integer> result = new ArrayList<>();

        for (Module model: course.getListOf().getModules()) {
            result.add(model.getAttempts());
        }

        return result;
    }
}
