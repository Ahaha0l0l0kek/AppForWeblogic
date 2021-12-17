package my.projects.repository;

import my.projects.model.ProductMg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryMongo extends MongoRepository<ProductMg, Long> {
    ProductMg findByTitle(String title);
}
