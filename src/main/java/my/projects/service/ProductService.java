package my.projects.service;
import my.projects.ws.ProductSOA;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductSOA setProductSOA(String title);
    void createProductSOA(String title, float price);
}
