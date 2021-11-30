package my.projects.sender;

import my.projects.model.Product;
import my.projects.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class JmsSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(fixedDelay = 10000)
    public void send(){
        Product product = productRepository.getById(1L);
        jmsTemplate.convertAndSend("DEV.QUEUE.1", product);
    }
}
