package com.jar.KiranaRegister.Controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jar.KiranaRegister.Dto.TransactionDto;
import com.jar.KiranaRegister.Entities.Transaction;
import com.jar.KiranaRegister.Exceptions.UserDefinedException;
import com.jar.KiranaRegister.Services.impl.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private TransactionServiceImpl transactionService;

    // Controller to get list of all transactions
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransaction(){
        logger.info("Entered getAllTransaction in TransactionController");
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    // Controller to record new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> newTransaction(@RequestBody TransactionDto transactionDto) throws JsonProcessingException, UserDefinedException {
        logger.info("Entered newTransaction in TransactionController");
        return new ResponseEntity<>(transactionService.insertNewTransaction(transactionDto),HttpStatus.CREATED);
    }

    // controller to select record based on column name and value
    @GetMapping("/grouping/{column}/{value}")
    public ResponseEntity<List<Transaction>> getGroupedTransactions(@PathVariable String column,@PathVariable String value)
    {
        logger.info("Entered getGroupedTransactions");
        return new ResponseEntity<>(transactionService.getGroupedRecords(column,value),HttpStatus.OK);
    }

    // Controller to retrieve 1 day/week/month records
    @GetMapping("/history/{value}")
    public ResponseEntity<List<Transaction>> getOldTransaction(@PathVariable String value)
    {
        logger.info("Entered getOldTransaction in TransactionController");

        return new ResponseEntity<>(transactionService.getOldTransaction(value),HttpStatus.OK);
    }



}
