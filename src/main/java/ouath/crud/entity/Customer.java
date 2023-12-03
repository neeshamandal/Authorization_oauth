package ouath.crud.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ouath.crud.model.ICustomerCreateRequest;
import ouath.crud.model.ICustomerUpdateRequest;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(groups = {ICustomerUpdateRequest.class}, message = "Id must not be null or empty")
	@Id
	private String id;
	
	@NotEmpty(groups = {ICustomerCreateRequest.class}, message = "First Name must not be null or empty")
	private String firstName;
	
	@NotEmpty(groups = {ICustomerCreateRequest.class}, message = "Last Name must not be null or empty")
	private String lastName;
	
	private String street;
	private String city;
	private String state;
	private String email;
	private String phone;
	
	
	

}
