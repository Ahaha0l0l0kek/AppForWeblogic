package my.projects.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "productDetails_exc")
public class ProductDetailsException extends RuntimeException {

    public ProductDetailsException(Throwable cause) {
        super(cause);
    }
}
