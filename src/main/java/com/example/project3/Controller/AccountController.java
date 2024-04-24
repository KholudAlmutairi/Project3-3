package com.example.project3.Controller;
import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Service.AccountService;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

   private final AccountService accountService;

    @GetMapping("/get-all")//admin
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @GetMapping("/get")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getMyAccount(user.getId()));
    }


    @PostMapping ("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
       accountService.addAccount(user.getId(), account);
        return ResponseEntity.status(200).body("Done Account added");
    }



    @PostMapping("/update/{accountId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user,@RequestBody @Valid Account account,@PathVariable Integer accountId){
       accountService.updateAccount(user.getId(),account,accountId);
        return ResponseEntity.status(200).body("Done Account update");
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user,@PathVariable Integer accountId){
        accountService.deleteAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body("Done Account deleted");
    }




    @GetMapping("/details/{accountId}")
    public ResponseEntity viewtDetailsAccount(@AuthenticationPrincipal User user , Integer accountId) {
        return ResponseEntity.status(200).body(accountService.viewtDetailsAccount(user.getId(), accountId));
    }


    @PutMapping("/active/{accountId}")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user ,@PathVariable Integer accountId ){
        accountService.activateAccount(user.getId(),accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated"));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user , @PathVariable Integer accountId , @PathVariable Integer amount) {
        accountService.deposit(user.getId(),accountId , amount);

        return ResponseEntity.status(200).body(new ApiResponse("Deposit successfully"));
    }


    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user ,@PathVariable Integer accountId ,  @PathVariable Integer amount){
        accountService.withdraw(user.getId(),accountId , amount);

        return ResponseEntity.status(200).body(new ApiResponse("Withdraw successfully"));
    }

    @PutMapping("/transfer/{userAccountId}/{anotherUserId}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user , @PathVariable Integer userAccountId , @PathVariable Integer anotherUserId , @PathVariable Integer amount){

        accountService.transferFunds(userAccountId, anotherUserId, amount);

        return ResponseEntity.status(200).body(new ApiResponse("Transfer successfully"));
    }

    @PostMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId ){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("account blocked"));
    }


}
