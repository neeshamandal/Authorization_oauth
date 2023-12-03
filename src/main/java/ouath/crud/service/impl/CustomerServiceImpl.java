package ouath.crud.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ouath.crud.entity.Customer;
import ouath.crud.repo.CustomerRepository;
import ouath.crud.service.CustomerService;
@Component
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public String create(Customer customer) {
		validCustomerCreateRequest(customer);
		
		Customer customerObj = Customer.builder().firstName(customer.getFirstName()).lastName(customer.getLastName()).street(customer.getStreet()).id(customer.getId()).email(customer.getEmail()).city(customer.getCity()).state(customer.getState()).phone(customer.getPhone()).build();
		customerRepository.save(customerObj);
		return "successfully created";
		
	}

	private void validCustomerCreateRequest(Customer customer) {
		if(StringUtils.isEmpty(customer.getFirstName())) {
			throw new RuntimeException("first name must not be null");
		}
		if(StringUtils.isEmpty(customer.getLastName())) {
			throw new RuntimeException("last name must not be null or empty");
		}
		
	}

	@Override
	public List<Customer> retrive() {
		return customerRepository.findAll();
	}

	@Override
	public String delete(String id) {
		customerRepository.deleteById(id);
		return "successfully deleted";
	}

	@Override
	public String updateCustomer(Customer updateCustomer)
			throws JsonProcessingException, JsonMappingException, ParseException {
		if(StringUtils.isEmpty(updateCustomer.getId())) {
			throw new RuntimeException("UUID not found");
		}
		
		Customer customer = customerRepository.findCustomerById(updateCustomer.getId());
		JSONObject customerFromDb = (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(updateCustomer));
		
		JSONObject customerPayloadObject = (JSONObject) new JSONParser().parse(new ObjectMapper().writeValueAsString(updateCustomer));
		
		for(Object obj: customerPayloadObject.keySet()) {
			String param = (String) obj;
			customerFromDb.put(param, customerPayloadObject.get(param));
		}
		
		customer = new ObjectMapper().readValue(customerFromDb.toJSONString(),Customer.class);
		customerRepository.save(customer);
		return "successfully updated";
	}

}
