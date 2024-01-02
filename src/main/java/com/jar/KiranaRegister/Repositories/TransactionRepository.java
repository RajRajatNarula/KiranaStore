package com.jar.KiranaRegister.Repositories;

import com.jar.KiranaRegister.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

    // Query to retrieve Action ordered by Date
    List<Transaction> findByActionOrderByDate(String value);

    //Query to retrieve Status ordered by Date
    List<Transaction> findByStatusOrderByDate(String status);

    //Query to retrieve payMethod ordered by Date
    List<Transaction> findByPayMethodOrderByDate(String payMethod);

    //Query to retrieve records between given range of dates
    @Query(value = "Select * from transaction t where date between ? and ? order by time",nativeQuery = true)
    List<Transaction> findByDate(String from,String to);

    // query to retrive 1 day old records
    @Query(value = "SELECT * FROM transaction WHERE date >= DATE_SUB(CURDATE(), INTERVAL 1 DAY) AND date <= CURDATE() order by time;",nativeQuery = true)
    List<Transaction> findOneDayOld();

    // query to retrive 1 week old records
    @Query(value = "SELECT * FROM transaction WHERE date >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK) AND date <= CURDATE() order by time;",nativeQuery = true)
    List<Transaction> findOneWeekOld();

    // query to retrive 1 month old records
    @Query(value = "SELECT * FROM transaction WHERE date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND date <= CURDATE() order by time;",nativeQuery = true)
    List<Transaction> findOneMonthOld();
}
