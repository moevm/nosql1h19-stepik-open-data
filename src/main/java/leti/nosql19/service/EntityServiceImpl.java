package leti.nosql19.service;

import leti.nosql19.model.Entity;
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
    public void add(Entity entity) {

        entityRepository.save(entity);
    }

    @Override
    public List<Entity> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public Entity findByFirstName(String firstName) {
        return entityRepository.findByFirstName(firstName);
    }

    @Override
    public List<Entity> findByLastName(String lastName) {
        return entityRepository.findByLastName(lastName);
    }
}
