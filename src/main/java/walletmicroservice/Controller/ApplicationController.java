package walletmicroservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walletmicroservice.Exception.CategoryException;
import walletmicroservice.Exception.InsufficientFundsException;
import walletmicroservice.Exception.IdUniquenessException;
import walletmicroservice.Model.TransactionlistEntity;
import walletmicroservice.Model.WalletEntity;
import walletmicroservice.Model.PlayerEntity;
import walletmicroservice.Service.AccountService;
import walletmicroservice.Service.PlayerService;
import walletmicroservice.Service.TransactionService;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping("/newtransaction")
    @PostMapping
    public ResponseEntity transaction(@RequestBody TransactionlistEntity transactionModel) {
        WalletEntity account;

        try {
            transactionModel = transactionService.check(transactionModel);
            account = accountService.accountCheck(transactionModel);
        } catch (CategoryException | InsufficientFundsException p) {
            return new ResponseEntity<String>(p.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        transactionService.save(transactionModel);
        accountService.save(account);

        account = accountService.getAccount(transactionModel.getWalletId());
        return new ResponseEntity<>(account, HttpStatus.CREATED);

    }

    @RequestMapping("/transactionhistory/{playerId}")
    @GetMapping
    public ResponseEntity getTransactionHistory(@PathVariable int playerId) {
        return new ResponseEntity<>(transactionService.getTransactionHistory(playerId), HttpStatus.OK);
    }

    @RequestMapping("/balance/{playerId}")
    @GetMapping
    public ResponseEntity getCurrentBalance(@PathVariable int playerId) {
        return new ResponseEntity<>(accountService.getBalance(playerId), HttpStatus.OK);
    }


    @RequestMapping("/createPlayer")
    @PostMapping
    public ResponseEntity createPlayer(@RequestBody PlayerEntity playerEntity) {
        playerService.save(playerEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/createAccount")
    @PostMapping
    public ResponseEntity createAccount(@RequestBody WalletEntity WalletEntity) {
        accountService.save(WalletEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/availableAccounts")
    @GetMapping
    public List<WalletEntity> getAvailableAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping("/players")
    @GetMapping
    public List<PlayerEntity> getPlayers() {
        return playerService.getAll();
    }

    @RequestMapping("/deleteplayer/{playerId}")
    @GetMapping
    public ResponseEntity deletePlayer(@PathVariable int playerId) {
        playerService.deleteById(playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/transactions")
    @GetMapping
    public List<TransactionlistEntity> getTransactions() {
        return transactionService.getAll();
    }

    @RequestMapping("walletDetails/{walletId}")
    @GetMapping
    public WalletEntity walletDetails(@PathVariable int walletId) {
        return accountService.getAccount(walletId);
    }
}
