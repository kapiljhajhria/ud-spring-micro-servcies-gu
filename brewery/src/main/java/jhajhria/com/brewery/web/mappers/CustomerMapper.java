package jhajhria.com.brewery.web.mappers;

import jhajhria.com.brewery.domain.Customer;
import jhajhria.com.brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}