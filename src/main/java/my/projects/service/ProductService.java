package my.projects.service;

import lombok.AllArgsConstructor;
import my.projects.model.Product;
import my.projects.repository.ProductRepository;
import my.projects.ws.ProductSOA;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductSOA setProductSOA(String title){
        Product product = productRepository.findByTitle(title);
        ProductSOA productSOA = new ProductSOA();
        productSOA.setId(product.getId());
        productSOA.setTitle(product.getTitle());
        productSOA.setPrice(product.getPrice());
        return productSOA;
    }
}
