package com.jar.KiranaRegister.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jar.KiranaRegister.Dto.TransactionDto;
import com.jar.KiranaRegister.Entities.Transaction;
import com.jar.KiranaRegister.Exceptions.UserDefinedException;

import java.util.List;

public interface TransactionService {

    //interface to insert new transaction record
    TransactionDto insertNewTransaction(TransactionDto transactionDto) throws JsonProcessingException, UserDefinedException;

    // interface to retrieve all transaction records
    List<TransactionDto> getAllTransactions();
}
