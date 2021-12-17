package my.projects.endpoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.projects.exception.InvalidPriceException;
import my.projects.service.ProductServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

@Slf4j
@Endpoint
public class CreateProductEndpoint {
    private static final String NAMESPACE_URI = "http://projects.my/ws";

    @Autowired
    private ProductServicePostgres productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateProductSOARequest")
    @ResponsePayload
    public my.projects.ws.CreateProductSOAResponse createProduct(@RequestPayload my.projects.ws.CreateProductSOARequest request) {
        my.projects.ws.CreateProductSOAResponse response = new my.projects.ws.CreateProductSOAResponse();
        if(request.getPrice() > 0) {
            productService.createProductSOA(request.getTitle(), request.getPrice());
            response.setStatus("Success");
            return response;
        } else {
            InvalidPriceException ex = new InvalidPriceException("1E", "invalid price");
            QName faultCode = new QName(NAMESPACE_URI, ex.getCode());
            try {
                SOAPFault soapFault = SOAPFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createFault();
                soapFault.setFaultString(ex.getMessage());
                soapFault.setFaultCode(faultCode);
                throw new SOAPFaultException(soapFault);
            } catch (SOAPException e) {
            }
            response.setStatus("Not success");
            return response;
        }
    }
}
