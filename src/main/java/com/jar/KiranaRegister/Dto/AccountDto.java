package com.jar.KiranaRegister.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    private String accountNumber; // Account Number
    @NotBlank(message = "Account Holder name cannot be blank")
    private String accountHolderName;// Name of account holder
    private double totalAmountInAccount; // Amount in account
}
