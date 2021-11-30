package my.projects.endpoint;

import lombok.AllArgsConstructor;
import my.projects.exception.NoProductException;
import my.projects.service.ProductService;
import my.projects.ws.ProductSOADetailsRequest;
import my.projects.ws.ProductSOADetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@AllArgsConstructor
@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://projects.my/ws";

    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductSOADetailsRequest")
    @ResponsePayload
    public ProductSOADetailsResponse getProductDetails(@RequestPayload ProductSOADetailsRequest request) {
        ProductSOADetailsResponse response = new ProductSOADetailsResponse();
        try {
            response.setProductSOA(productService.setProductSOA(request.getTitle()));
        } catch (NullPointerException e) {
            throw new NoProductException();
        }


//        ProductSOA productSOA = productService.setProductSOA(request.getTitle());
//        if(productSOA == null) {
//            throw new NoProductException();
//        } else response.setProductSOA(productSOA);



        return response;
    }
}
