package com.jar.KiranaRegister.Dto;
import com.jar.KiranaRegister.Entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    @NotBlank(message = "Action as credit/debit is required")
    private String action; // credit for selling or debit for product returned
    @NotBlank(message = "Atleast one product is required")
    private List<Product> product; //List of products purchased
    @NotBlank(message = "Debit account number is required")
    private String debitAccount; // Account from which money is paid
    @NotBlank(message = "CurrencyType as INR/$ is required")
    private String currencyType; // Type of currenct INR/$
    @NotBlank(message = "Credit account number is required")
    private String creditAccount; // Account in which amount is credited
    @NotBlank(message = "Amount paid cannot be blank")
    private double creditAmount; // Amount credited in account
    @NotBlank(message = "Pay method cannot be null")
    private String payMethod; // mode of payment
    private String transactionNote; // Note given while doing transaction

}
