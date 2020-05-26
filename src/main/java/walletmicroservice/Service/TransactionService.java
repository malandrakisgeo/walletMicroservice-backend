package walletmicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walletmicroservice.Exception.CategoryException;
import walletmicroservice.Model.TransactionlistEntity;
import walletmicroservice.Model.WalletEntity;
import walletmicroservice.Repository.TransactionRepository;
import walletmicroservice.Exception.IdUniquenessException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public TransactionlistEntity check(TransactionlistEntity trs) throws Exception {
        /*if (trs.getId() == null) {
            throw new NoIdException();
        }*/

        if (trs.getTransactionType() == null ||
                !(trs.getTransactionType().toLowerCase().equals("credit") | trs.getTransactionType().toLowerCase().equals("debit"))) {
            throw new CategoryException();
        }


        if (transactionRepository.getIDs().contains(trs.getId())) {
            throw new IdUniquenessException();
        }
        return trs;
    }

    public TransactionlistEntity save(TransactionlistEntity trs) {
        if (trs.getTransactionDate() == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            trs.setTransactionDate(df.format(new java.util.Date()));
        }
        transactionRepository.save(trs);
        return trs;
    }


    public void delete(TransactionlistEntity trs) {
        transactionRepository.deleteById(trs.getId());
    }

    public List<TransactionlistEntity> getTransactionHistory(Integer playerId) {
        return transactionRepository.getTransactions(playerId);

    }

    public List<TransactionlistEntity> getAll(){
        return transactionRepository.getAll();
    }


}
