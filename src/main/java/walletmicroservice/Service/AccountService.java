package walletmicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walletmicroservice.Exception.InsufficientFundsException;
import walletmicroservice.Model.TransactionlistEntity;
import walletmicroservice.Repository.AccountRepository;
import walletmicroservice.Repository.PlayerRepository;
import walletmicroservice.Model.WalletEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public WalletEntity accountCheck(TransactionlistEntity trs) throws InsufficientFundsException {
        WalletEntity account = accountRepository.findById(trs.getWalletId()).get();
        Double amount = 0.0;
        if(trs.getTransactionType().toLowerCase().equals("credit") || trs.getTransactionType().toLowerCase().equals("debit")) {
            amount = trs.getTransactionType().toLowerCase().equals("credit") ? trs.getAmount() : -trs.getAmount(); //If it is a debit transaction, the sum will be subtracted.
        }
        Double newBalance = account.getBalance() + amount;
        if (newBalance< 0) {
            throw new InsufficientFundsException();
        }

        account.setBalance(this.round(newBalance,2));
        return account;
    }

    public Double getBalance(Integer playerId) {
        Integer accountId = playerRepository.findById(playerId).get().getConnectedWallet();
            return accountRepository.getBalance(accountId);
    }

    public WalletEntity getAccount(int id) {
        try {
            return accountRepository.findById(id);
        } catch (DataAccessException ee) {
            ee.printStackTrace();
            return null;
        }
    }

    public void save(WalletEntity acc) {
        accountRepository.save(acc);
    }

    //Used for testing!
    public void delete(WalletEntity acc) {
        accountRepository.deleteById(acc.getId());
    }

    public List<WalletEntity> getAccounts() {
            return accountRepository.getAll();
    }



    //Rounds a double to two floating points. Not my creation.
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
