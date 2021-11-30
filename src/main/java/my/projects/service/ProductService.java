package my.projects.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.projects.model.Product;
import my.projects.repository.ProductRepository;
import my.projects.ws.ProductSOA;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductSOA setProductSOA(String title){
        Product product = productRepository.findByTitle(title);
        log.info(String.valueOf(product));
        ProductSOA productSOA = new ProductSOA();
        productSOA.setId(product.getId());
        productSOA.setTitle(product.getTitle());
        productSOA.setPrice(product.getPrice());
        return productSOA;
    }
}
