package com.jar.KiranaRegister.Mapper;

import com.jar.KiranaRegister.Dto.TransactionDto;
import com.jar.KiranaRegister.Entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    TransactionDto mapTransactionToTransactionDto(Transaction transaction);

    Transaction mapTransactionDtoToTransaction(TransactionDto transactionDto);
}
