package service;


import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringRunner;
import walletmicroservice.Exception.IdUniquenessException;
import walletmicroservice.Exception.InsufficientFundsException;
import walletmicroservice.Model.PlayerEntity;
import walletmicroservice.Model.TransactionlistEntity;
import walletmicroservice.Model.WalletEntity;
import walletmicroservice.RunApplication;
import walletmicroservice.Service.AccountService;
import walletmicroservice.Service.PlayerService;
import walletmicroservice.Service.TransactionService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan({"walletmicroservice.Service", "walletmicroservice.Repository"})
@EntityScan({"walletmicroservice.Model"})
@EnableJpaRepositories("walletmicroservice.Repository")
@ContextConfiguration(classes = RunApplication.class)
public class ApplicationTest {

    private PlayerEntity player;
    private WalletEntity account;
    private TransactionlistEntity transaction;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PlayerService playerService;

    @Before
    public  void setupPlayerAccount() throws Exception {

        account = new WalletEntity();
        account.setId(10);
        account.setBalance(100.00);

        player = new PlayerEntity();
        player.setId(10);
        player.setName("Tester");
        player.setConnectedWallet(10);

        transaction = new TransactionlistEntity();
        transaction.setTransactionType("credit");
        transaction.setAmount(150.0);
        transaction.setWalletId(10);
        transaction.setId(100);
        transaction.setTransactionDate(new Date().toString());

        accountService.save(account);
        playerService.save(player);
        Assertions.assertThatCode(() ->transactionService.check(transaction)) .doesNotThrowAnyException();
        transactionService.save(transaction);
    }


    @Test(expected = InsufficientFundsException.class)
    public void debitTransaction() throws Exception {

        TransactionlistEntity TransactionlistEntity = new TransactionlistEntity();
        TransactionlistEntity.setTransactionType("debit");
        TransactionlistEntity.setAmount(50000.0); //Not enough funds!
        TransactionlistEntity.setWalletId(10);
        TransactionlistEntity.setId(110);
        accountService.accountCheck(TransactionlistEntity);
    }

    @Test(expected = IdUniquenessException.class)
    public void erroneousCreditTransaction() throws Exception {
        TransactionlistEntity TransactionlistEntity = new TransactionlistEntity();
        TransactionlistEntity.setTransactionType("credit");
        TransactionlistEntity.setAmount(50000.0);
        TransactionlistEntity.setWalletId(10);
        TransactionlistEntity.setId(100); //Already exists in db!
        transactionService.check(TransactionlistEntity);
    }

    @Test
    public void saveTransactionAndGetHistory() throws IdUniquenessException {
        List<TransactionlistEntity> transactionHistory = transactionService.getTransactionHistory(player.getId());
        assertNotNull(transactionHistory);
        assertTrue(transactionHistory.size() == 1);
        assertTrue(transactionHistory.get(0).getId().equals(this.transaction.getId()));
        assertFalse(!accountService.getBalance(player.getId()).equals(account.getBalance())); //The balance in the db should not be the same as before.

    }

    @After
    public void cleanDB(){
        transactionService.delete(transaction);
        playerService.delete(player);
        accountService.delete(account);
    }


}
