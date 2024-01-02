package com.jar.KiranaRegister.Services.impl;

import com.jar.KiranaRegister.Dto.AccountDto;
import com.jar.KiranaRegister.Entities.Account;
import com.jar.KiranaRegister.Exceptions.UserDefinedException;
import com.jar.KiranaRegister.Mapper.AccountMapper;
import com.jar.KiranaRegister.Repositories.AccountRepository;
import com.jar.KiranaRegister.Services.AccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    //Function to insert new Account
    public AccountDto insertAccount(AccountDto accountDto) {
        logger.info("Entered insertAccount of AccountServiceImpl");
        Account account=accountMapper.mapAccountDtoToAccount(accountDto);
        accountRepository.save(account);
        return  accountDto;
    }

    // Function to retrieve All accounts
    public List<AccountDto> getAllAccounts(){
        logger.info("Entered getAllAcounts of AccountServiceImpl");
        List<AccountDto> accountDtos=accountRepository.findAll().stream().map(accountMapper :: mapAccountToAccountDto).collect(Collectors.toList());
        if(accountDtos.isEmpty())
        {
            logger.info("No records found");
        }
        return accountDtos;
    }
}
