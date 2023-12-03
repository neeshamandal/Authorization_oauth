package ouath.crud.controller.impl;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ouath.crud.controller.CustomerController;
import ouath.crud.entity.Customer;
import ouath.crud.model.ICustomerUpdateRequest;
import ouath.crud.service.CustomerService;
import ouath.crud.service.impl.CustomerServiceImpl;
@RestController
@RequestMapping("/v1.0")
public class CustomerControllerImpl implements CustomerController{
	@Autowired
	CustomerService customerService;

	@Override
	public ResponseEntity<String> create(@Valid Customer customer) {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
		}catch(final RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}

	@Override
	public ResponseEntity<List<Customer>> retrive() {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.retrive());
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.delete(id));
		}catch(final IllegalArgumentException E) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(E.getMessage());
		}catch(final RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> updateCustomer(@Valid @Validated(ICustomerUpdateRequest.class) Customer customer)
			throws JsonMappingException, JsonProcessingException, org.json.simple.parser.ParseException{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customer));
		}catch(final IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(final RuntimeException E){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(E.getMessage());
		}
	}
}
		
	
	
