package ouath.crud.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ouath.crud.entity.Customer;
@Repository
public interface CustomerRepository extends MongoRepository<Customer,String>{

	Customer findCustomerById(String id);

}
