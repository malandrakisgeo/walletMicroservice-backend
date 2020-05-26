package walletmicroservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import walletmicroservice.Model.TransactionlistEntity;

import java.util.List;
@Repository
public interface TransactionRepository extends CrudRepository<TransactionlistEntity, Integer> {

    @Override
    List<TransactionlistEntity> findAll();

    @Override
    void delete(TransactionlistEntity trs);

    @Query(nativeQuery = true,
            value = "SELECT * FROM walletmicroservice.TRANSACTIONLIST WHERE WALLET_ID = (SELECT CONNECTED_WALLET FROM walletmicroservice.PLAYER WHERE PLAYER.ID= ?) ")
    List<TransactionlistEntity> getTransactions(int playerId);


    @Query(nativeQuery = true, value = "SELECT ID FROM walletmicroservice.TRANSACTIONLIST")
    List<Integer> getIDs();

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.TRANSACTIONLIST WHERE ID = ?")
    TransactionlistEntity getTransaction(int id);

    @Query(nativeQuery = true, value = "DELETE FROM walletmicroservice.TRANSACTIONLIST WHERE ID = ? ")
    void deleteById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.TRANSACTIONLIST ")
    List<TransactionlistEntity> getAll();

}
