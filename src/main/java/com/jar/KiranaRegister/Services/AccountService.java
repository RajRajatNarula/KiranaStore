package com.jar.KiranaRegister.Services;

import com.jar.KiranaRegister.Dto.AccountDto;

import java.util.List;

public interface AccountService {

    //interface to insert new account
    AccountDto insertAccount(AccountDto accountDto);

    //interface to retrieve all accounts
    List<AccountDto> getAllAccounts();
}
