package leti.nosql19.repository;

import leti.nosql19.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityRepository extends MongoRepository<Course, String> {
    Course findByCourseName(String courseName);
}
