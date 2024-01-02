package com.jar.KiranaRegister.Mapper;

import com.jar.KiranaRegister.Dto.AccountDto;
import com.jar.KiranaRegister.Entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto mapAccountToAccountDto(Account account);

    Account mapAccountDtoToAccount(AccountDto accountDto);
}
