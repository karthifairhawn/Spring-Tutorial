package in.karthifairhawn.soap.webservices;

import java.util.List;
import java.util.Collections;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	
	@Bean
	public ServletRegistrationBean messageDispatchServlet(ApplicationContext context){
		MessageDispatcherServlet messageDispatchServlet = new MessageDispatcherServlet();
		messageDispatchServlet.setApplicationContext(context);
		messageDispatchServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatchServlet, "/webservice/*");
	}	
	
	@Bean(name="emp")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema empSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("EmployeesPort");
		definition.setTargetNamespace("http://karthifairhawn.in/emp");
		definition.setLocationUri("/webservice");
		definition.setSchema(empSchema);
		return definition;
	}
		
	@Bean
	public XsdSchema empSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Schema.xsd"));
	}
	
	//XwsSecurityInterceptor
		@Bean
		public XwsSecurityInterceptor securityInterceptor(){
			XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
			//Callback Handler -> SimplePasswordValidationCallbackHandler
			securityInterceptor.setCallbackHandler(callbackHandler());
			//Security Policy -> securityPolicy.xml
			securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
			return securityInterceptor;
		}
	
		@Bean
		public SimplePasswordValidationCallbackHandler callbackHandler() {
			SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
			handler.setUsersMap(Collections.singletonMap("user", "password"));
			return handler;
		}

		//Interceptors.add -> XwsSecurityInterceptor
		@Override
		public void addInterceptors(List<EndpointInterceptor> interceptors) {
			interceptors.add(securityInterceptor());
		}

}
