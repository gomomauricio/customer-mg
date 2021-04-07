package net.paymentchain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import net.paymentchain.entities.Customer;
import net.paymentchain.repository.CustomerRepository;



@Api( value="Manejador de Usuarios",description = "Servicios que maneja los usuarios CRUD" )  //para la documentacion



@RestController
public class CustomerController 
{
	
	@Autowired
	CustomerRepository customerRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);// todos los logger solo se hace una sola vez (SIMPLETON)

	
	
	@ApiOperation(value = "Devuelve todos los clientes" ,response = Customer[].class, produces = "json/application",
	            consumes = "json/application", 
	            responseHeaders = {@ResponseHeader( name = "json/application"), @ResponseHeader(name = "text/application")}) //para documentacion
	@ApiResponses( value =  {
	@ApiResponse(code=200,message = "Consulta exitosa de clientes , devuelve todos los clientes"),
	@ApiResponse(code=400,message = "Acceso no autorizado"),
	@ApiResponse(code=401,message = "recurso no fue encontrado")
	})
	@PostMapping("/customer")
	public ResponseEntity<?> crear(@RequestBody Customer cus)
	{
		LOG.info("Entrando a crear con " + cus);
		
		Customer newCustomer = null;
		Map<String, Object> resp = new HashMap<String, Object>();
		
		try
		{
			newCustomer = customerRepository.save(cus); 
		}
		catch (DataAccessException dae) 
		{
			resp.put("mesaje", "Error al realizar el insert!");
			resp.put("error", dae.getMessage().concat(": ").concat( dae.getMostSpecificCause().getMessage()));
			return  new ResponseEntity<Map<String, Object>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if( newCustomer == null )
		{
			resp.put("mesaje", "Error al realizar el insert!");
			resp.put("error", "No se logro almacenar ningun objeto " + cus);
			return  new ResponseEntity<Map<String, Object>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity<Customer>(newCustomer,HttpStatus.CREATED);
		}
		
		
	}
	
	
	@GetMapping("/customer/all")
	public ResponseEntity<List<Customer>> listar()
	{
		LOG.info("Entrando a obtener todos los customers ");
		
		List<Customer> sucursales = customerRepository.findAll();
		
		return new ResponseEntity<List<Customer>>(sucursales,HttpStatus.OK);
	}
	

}
