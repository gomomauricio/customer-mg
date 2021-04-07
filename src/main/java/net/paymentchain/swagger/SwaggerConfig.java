package net.paymentchain.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.HttpRequestHandler;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{

	public static final Contact DEFAULT_CONTAC = new Contact(
			                                             "Ing.Mauricio-GS", 
			                                             "https://github.com/gomomauricio", 
														 "gomomauricio@gmail.com");
	//proveedores de la pagina
	public static final List<VendorExtension> VENDOR_EXTENSION = new ArrayList<VendorExtension>();
	
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			                   "Demo-1 servicio para manejo de usuarios", 
			                   "Este servicio usa Mysql para persistencia de datos", 
			                   "1.0.0", 
			                   "Terminos de servicio", 
			                   DEFAULT_CONTAC, 
			                   "Compañia MGM", 
			                   "Informacion extra de la compañia https://gitlab.com/gomomauricio", 
			                   VENDOR_EXTENSION);
	
	
	//encargado de generar la documentacion
	@Bean
	public Docket api()
	{
		return new Docket( DocumentationType.SWAGGER_2	)  //tipo documentacion
				    .apiInfo(DEFAULT_API_INFO)  //
				    .select()
//				    .apis(RequestHandlerSelectors.any())  //que servicios rest (todos)
				    .apis(RequestHandlerSelectors.basePackage("net.paymentchain"))
				    .paths(PathSelectors.any())   //que paths (todos)
				    .build();   //regresamos el docket
	}
	
}
