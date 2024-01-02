package com.jar.KiranaRegister.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    private String accountNumber;
    @NotBlank(message = "Account Holder name cannot be blank")
    private String accountHolderName;
    private double totalAmountInAccount=0;
}
