package my.projects.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "createProduct_exc")
public class CreateProductException extends RuntimeException {
    public CreateProductException(Throwable cause) {
        super(cause);
    }
}
