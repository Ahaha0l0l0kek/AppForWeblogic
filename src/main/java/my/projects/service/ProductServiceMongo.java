package my.projects.service;

import lombok.AllArgsConstructor;
import my.projects.model.Product;
import my.projects.model.ProductMg;
import my.projects.repository.ProductRepositoryMongo;
import my.projects.ws.ProductSOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@AllArgsConstructor
@Service
public class ProductServiceMongo implements ProductService{

    private final ProductRepositoryMongo productRepository;

    @Override
    public ProductSOA setProductSOA(String title) {
        ProductMg product = productRepository.findByTitle(title);
        ProductSOA productSOA = new ProductSOA();
        productSOA.setId(product.getId());
        productSOA.setTitle(product.getTitle());
        productSOA.setPrice(product.getPrice());
        return productSOA;
    }

    @Override
    public void createProductSOA(String title, float price) {
        ProductMg product = new ProductMg();
        product.setTitle(title);
        product.setPrice(price);
        productRepository.save(product);
    }
}
