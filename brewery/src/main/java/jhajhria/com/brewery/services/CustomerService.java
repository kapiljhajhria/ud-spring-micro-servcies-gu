package jhajhria.com.brewery.services;

import jhajhria.com.brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    public CustomerDto getCustomerById(UUID id);
}