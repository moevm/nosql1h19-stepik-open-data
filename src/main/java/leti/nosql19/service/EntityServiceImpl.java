package leti.nosql19.service;

import leti.nosql19.model.Course;
import leti.nosql19.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityServiceImpl(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
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
}
