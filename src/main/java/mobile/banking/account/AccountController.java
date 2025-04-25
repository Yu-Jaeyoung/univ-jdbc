package mobile.banking.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest request) {
        Account account = new Account(
                request.getAccountNumber(),
                request.getBalance(),
                request.getCustomerId()
        );

        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.findAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") final Long id) {
        Account account = accountService.findAccountById(id).get();

        return ResponseEntity.ok(account);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable("id") final Long id,
                                                @RequestBody final AccountRequest request) {
        Account updatedAccount = new Account(
                id,
                request.getAccountNumber(),
                request.getBalance(),
                request.getCustomerId()
        );

        accountService.updateAccount(updatedAccount);

        return ResponseEntity.ok("계좌정보 수정 성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAccount(@PathVariable("id") final Long id) {
        accountService.removeAccount(id);
        return ResponseEntity.ok("계좌 정보 삭제 성공");
    }
}
