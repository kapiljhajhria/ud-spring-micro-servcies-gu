package jhajhria.com.brewery.services;

import jhajhria.com.brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder().
                id(UUID.randomUUID())
                .name("John")
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID())
                .name("John Doe")
                .build();
    }

    @Override
    public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
        CustomerDto foundCustomerDto = getCustomerById(customerId);
        foundCustomerDto.setName(customerDto.getName());

        return foundCustomerDto;
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        //delete customer from db
    }
}