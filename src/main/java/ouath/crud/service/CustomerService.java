package ouath.crud.service;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ouath.crud.entity.Customer;
@Service
public interface CustomerService {

	public String create(Customer customer);

	public List<Customer> retrive();

	public String delete(String id);

	public String  updateCustomer(@Valid Customer customer)
	throws JsonProcessingException, JsonMappingException, ParseException;


}
