package com.jar.KiranaRegister.Controllers;
import com.jar.KiranaRegister.Dto.AccountDto;
import com.jar.KiranaRegister.Services.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private AccountServiceImpl accountService;

    // Controller for inserting new Account details
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto)
    {
        logger.info("Entered  createAccount in AccountController");
        return new ResponseEntity<>(accountService.insertAccount(accountDto), HttpStatus.CREATED);
    }

    // Controller to retrieve all account details
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        logger.info("Entered  getAllAccounts in AccountController");
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }
}
