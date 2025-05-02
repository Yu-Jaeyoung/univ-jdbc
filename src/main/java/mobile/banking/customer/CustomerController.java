package mobile.banking.customer;

import mobile.banking.account.Account;
import mobile.banking.account.AccountRequest;
import mobile.banking.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerController(final CustomerService customerService, final AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody final CustomerRequest request) {
        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getCellphone()
        );

        // URI location = URI.create("/customers/" + customer.getId());
        // return ResponseEntity.created(location).body(customerService.createCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(
            final CustomerSearchCondition cond
    ) {
        List<Customer> customers = customerService.findCustomers(cond);
        return customers.size() > 0
                ? ResponseEntity.status(HttpStatus.OK).body(customers)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") final Long id) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        return customer.isPresent()
                ? ResponseEntity.ok(customer.get())
                : ResponseEntity.notFound().build();
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

    @GetMapping("/search")
    public ResponseEntity<Customer> searchCustomer(
            @RequestParam("email") final String email
    ) {
        Customer customer = customerService.findCustomerByEmail(email).get();
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(
            @PathVariable("id") final Long id
    ) {
        List<Account> accounts = accountService.findByCustomerId(id);
        return ResponseEntity.ok(accounts);
    }


    @PostMapping("/{id}/accounts")

    public ResponseEntity<Account> createAccount(
            @PathVariable("id") final Long id,
            @RequestBody final AccountRequest request
    ) {
        Account account = accountService.createAccount(
                new Account(request.getAccountNumber(), request.getBalance(), id)
        );

        return ResponseEntity.ok(account);
    }
}
