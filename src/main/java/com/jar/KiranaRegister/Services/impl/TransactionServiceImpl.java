package com.jar.KiranaRegister.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jar.KiranaRegister.Dto.TransactionDto;
import com.jar.KiranaRegister.Entities.Account;
import com.jar.KiranaRegister.Entities.Product;
import com.jar.KiranaRegister.Entities.Transaction;
import com.jar.KiranaRegister.Exceptions.UserDefinedException;
import com.jar.KiranaRegister.Mapper.TransactionMapper;
import com.jar.KiranaRegister.Repositories.AccountRepository;
import com.jar.KiranaRegister.Repositories.ProductRepository;
import com.jar.KiranaRegister.Repositories.TransactionRepository;
import com.jar.KiranaRegister.Services.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    private TransactionRepository transactionRepository;
    private ProductRepository productRepository;
    private AccountRepository accountRepository;
    private final ApiService apiService;

    private final TransactionMapper transactionMapper;


    // Method to retrieve all transaction records
        public List<TransactionDto> getAllTransactions(){
            logger.info("Entered getAllTransactions in TransactionServiceImpl");
            List<TransactionDto> transactionDtos= transactionRepository.findAll().stream().map(transactionMapper :: mapTransactionToTransactionDto).collect(Collectors.toList());
            if(transactionDtos.isEmpty())
            {
                logger.info("No results found");
            }
            return transactionDtos;
        }


    //Method to insert new transaction records
    @Transactional
    public TransactionDto insertNewTransaction(TransactionDto transactionDto) throws JsonProcessingException, UserDefinedException {
        logger.info("Entered insertNewTransaction in TransactionServiceImpl");
        Transaction transaction=new Transaction();
        //mapping transactionDto to Transaction Entity
        transaction=transactionMapper.mapTransactionDtoToTransaction(transactionDto);
        // checking currency type to confirm if currency conversion is required or not
        if(!transactionDto.getCurrencyType().equals("INR")) {
            double inrRate = apiService.fetchDataFromApi();
            transactionDto.setCreditAmount(transactionDto.getCreditAmount() * inrRate);
        }


        //calculating the total amount on the basis of item*count
        setTotalCalculatedAmount(transaction);
        setStatus(transaction);
        // updating new balance in transaction table after current transaction
        setTotalAmountInAccount(transaction);
        transactionRepository.save(transaction);
        // updating balance in account table
        updateAccountBalance(transaction.getCreditAccount(),transaction.getTotalAmountInAccountAfter());
        return transactionDto;
    }

    // Method to update status of transaction i.e success or failure
    public void setStatus(Transaction transaction) throws UserDefinedException {
        logger.info("Entered setStatus in TransactionServiceImpl");
        // Setting status as success if amount paid is equal to amount calculated on the basis of items
        if(transaction.getTotalCalculatedAmount() != transaction.getCreditAmount()){
            transaction.setStatus("FAILURE");
            throw new RuntimeException("Amount paid is not correct");
        }
        else {
            transaction.setStatus("SUCCESS");
        }
    }

    // Method to calculate total amount for the products
    public void setTotalCalculatedAmount(Transaction transaction) throws UserDefinedException {

        logger.info("Entered setTotalCalculatedAmount in TransactionServiceImpl");
        Product product=new Product();
        double amount=0;
        // calculating total amount that needs to be paid or received on basis of item*count
        for(Product i:transaction.getProduct()){
            product=productRepository.findByProductId(i.getProductId());
            if(product!=null) {
                amount += product.getProductAmount() * i.getProductCount();
            }
            else {
                throw  new UserDefinedException("No Product found");
            }
        }
        transaction.setTotalCalculatedAmount(amount);
    }

    // Method to calculate amount in account after current transaction
    public void setTotalAmountInAccount(Transaction transaction)
    {
        logger.info("Entered setTotalAmountInAccount in TransactionServiceImpl");
        // checking if action is credit or debit to confirm if we need to add or deduct from current balance
        if(!transaction.getAction().equalsIgnoreCase("credit"))
        {
            transaction.setCreditAmount(transaction.getCreditAmount()*-1);
        }
        logger.info("Credit amount is: "+transaction.getCreditAmount());
        Account account=accountRepository.findByAccountNumber(transaction.getCreditAccount());
        double old=account.getTotalAmountInAccount();
        transaction.setTotalAmountInAccountBefore(old);
        double newBalance=transaction.getCreditAmount() +  old;
        transaction.setTotalAmountInAccountAfter(newBalance);
    }

    // Method to select records based on column and values
    public List<Transaction> getGroupedRecords(String value1,String value2)
    {
        logger.info("Entered getGroupedRecords of TransactionServiceImpl");
        // to retrieve records based on action column
        if(value1.equalsIgnoreCase("action")) {
            return transactionRepository.findByActionOrderByDate(value2);
        }
        // to retrieve records based on status column
        else if(value1.equalsIgnoreCase("status")){
            return transactionRepository.findByStatusOrderByDate(value2);
        }
        // to retrieve records based on paymethod column
        else if(value1.equalsIgnoreCase("payMethod")){
            return transactionRepository.findByPayMethodOrderByDate(value2);
        }
        // to retrieve records based on date range
        else {
            return transactionRepository.findByDate("value1","value2");
        }
    }

    // Method to retrieve 1 day/week/month records
    public List<Transaction> getOldTransaction(String value) {
        logger.info("Entered getOldTransaction of TransactionServiceImpl");
        // to retrieve 1 day old records
        if(value.equals("day"))
        {
            return transactionRepository.findOneDayOld();
        }
        // to retrieve 1 week old records
        else if(value.equals("week")){
            return transactionRepository.findOneWeekOld();
        }
        // to retrieve 1 month old records
        else if(value.equals("month")){
            return transactionRepository.findOneMonthOld();
        }
        // to retrieve All old records
        else {
            return transactionRepository.findAll();
        }

    }

    // Method to update Account balance from which amount is credited/debited
    public void updateAccountBalance(String ban,double newbalance)
    {
        logger.info("Entered updateAccountBalance of TransactionServiceImpl");
        Account account=accountRepository.findByAccountNumber(ban);
        account.setTotalAmountInAccount(newbalance);
        accountRepository.save(account);
    }
}
