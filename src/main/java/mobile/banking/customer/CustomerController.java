package mobile.banking.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody final CustomerRequest request) {
        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getCellphone()
        );

        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.findCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id).get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") final Long id,
                                                 @RequestBody final CustomerRequest request) {
        Customer updatedCustomer = new Customer(
                id,
                request.getName(),
                request.getEmail(),
                request.getCellphone()
        );

        customerService.updateCustomer(updatedCustomer);

        return ResponseEntity.ok("고객정보 수정 성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCustomer(@PathVariable("id") final Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("고객 정보 삭제 성공");
    }
}
