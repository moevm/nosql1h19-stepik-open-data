package leti.nosql19.repository;

import leti.nosql19.model.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntityRepository extends MongoRepository<Entity, String> {

    List<Entity> findByLastName(String lastName);
}
