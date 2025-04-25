package mobile.banking.customer;

import mobile.banking.account.Account;
import mobile.banking.account.AccountRequest;
import mobile.banking.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
