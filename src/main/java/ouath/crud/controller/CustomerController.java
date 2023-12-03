package ouath.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ouath.crud.entity.Customer;
import ouath.crud.model.ICustomerCreateRequest;
import ouath.crud.model.ICustomerUpdateRequest;

public interface CustomerController {
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@Valid @Validated(ICustomerCreateRequest.class) @RequestBody Customer customer);
	
	
	@GetMapping("/get")
	public ResponseEntity<List<Customer>> retrive();
	
	@DeleteMapping("/deleteId/{id}")
	public ResponseEntity<String> delete(@PathVariable String id);
	
	@PatchMapping("updateByUid")
	public ResponseEntity<String> updateCustomer(@Valid @Validated(ICustomerUpdateRequest.class)@RequestBody Customer customer)
	throws JsonMappingException,JsonProcessingException, org.json.simple.parser.ParseException;

}
