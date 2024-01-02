package com.jar.KiranaRegister.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // Transaction date
    private String time = new SimpleDateFormat("HH:mm:ss").format(new Date()); // Transaction time
    private String action; // Credit or debit

    @ManyToMany
    private List<Product> product;  // List of products purchased
    private String currencyType; // currency type can be INR or $
    private double totalCalculatedAmount; // itemamount * count
    private String debitAccount;  // senders account number
    private String creditAccount; // shops account number
    private double creditAmount; // credited / debited  amount
    private String payMethod; // senders paymethod
    private String status; // success or failure
    private double totalAmountInAccountBefore; // total amount present in current account before transaction
    private double totalAmountInAccountAfter; // total amount present in current account after transaction
    private String transactionNote; // Message passed while doing transaction


}
