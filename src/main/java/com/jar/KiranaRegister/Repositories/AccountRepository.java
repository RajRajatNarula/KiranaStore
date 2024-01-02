package com.jar.KiranaRegister.Repositories;

import com.jar.KiranaRegister.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    //query to find account_number using account number
    Account findByAccountNumber(String ban);


}
