package my.projects.endpoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.projects.exception.NoProductException;
import my.projects.service.ProductService;
import my.projects.service.ProductServicePostgres;
import my.projects.ws.ProductSOADetailsRequest;
import my.projects.ws.ProductSOADetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

@Slf4j
@AllArgsConstructor
@Endpoint
public class ProductDetailsEndpoint {

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String NAMESPACE_URI = "http://projects.my/ws";

    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProductSOADetailsRequest")
    @ResponsePayload
    public ProductSOADetailsResponse getProductDetails(@RequestPayload ProductSOADetailsRequest request) {
        ProductSOADetailsResponse response = new ProductSOADetailsResponse();
        try {
            response.setProductSOA(productService.setProductSOA(request.getTitle()));
        } catch (NullPointerException e) {
            NoProductException ex = new NoProductException(request.getTitle());
            try {
                SOAPFault soapFault = SOAPFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createFault();
                soapFault.setFaultString(ex.getMessage());
                throw new SOAPFaultException(soapFault);
            } catch (SOAPException exception) {
            }
        }
        return response;
    }
}
