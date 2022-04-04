package jhajhria.com.brewery.web.controller;

import jhajhria.com.brewery.services.CustomerService;
import jhajhria.com.brewery.web.model.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto newCustomer = customerService.createCustomer(customerDto);
        URI location = URI.create("/api/v1/customer/" + newCustomer.getId());
        return ResponseEntity.created(location).body(newCustomer);
    }

    @PutMapping("/{customerId}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);

        return ResponseEntity.accepted().body(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(value = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>(ex.getConstraintViolations().size());

        ex.getConstraintViolations().forEach(cv -> errors.add(
                cv.getPropertyPath() + ": " + cv.getMessage()
        ));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    }