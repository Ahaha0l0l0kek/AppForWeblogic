package my.projects.config;

import org.apache.wss4j.dom.WSConstants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Collections;
import java.util.List;

@EnableWs
@Configuration
@ComponentScan
public class WsConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/*");
    }

    @Bean(name = "productSOADetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductSOADetailsPort");
        wsdl11Definition.setLocationUri("/description");
        wsdl11Definition.setTargetNamespace("http://projects.my/ws");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema productSchema() {
        return new SimpleXsdSchema(new ClassPathResource("product.xsd"));
    }

    @Bean
    SimplePasswordValidationCallbackHandler callbackHandler() {
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        callbackHandler.setUsersMap(Collections.singletonMap("userName", "password"));
        return callbackHandler;
    }

    @Bean
    public Wss4jSecurityInterceptor wss4jSecurityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setValidationActions(WSConstants.USERNAME_TOKEN_LN);
        interceptor.setValidationCallbackHandler(callbackHandler());
        return interceptor;
    }

    @Bean
    PayloadLoggingInterceptor payloadLoggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(payloadLoggingInterceptor());
        interceptors.add(wss4jSecurityInterceptor());
    }
}
