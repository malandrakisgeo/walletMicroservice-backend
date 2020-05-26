package walletmicroservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import walletmicroservice.Model.WalletEntity;
import walletmicroservice.Model.WalletEntity;

import java.util.List;
@Repository
public interface AccountRepository extends CrudRepository<WalletEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.WALLET ")
    List<WalletEntity> getAll();

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.WALLET WHERE ID= (SELECT CONNECTED_WALLET FROM walletmicroservice.PLAYER WHERE ID = ?)")
    List<WalletEntity> getPlayersAccount(int playerid);

    WalletEntity findById(int id);

    @Query(nativeQuery = true, value = "SELECT BALANCE FROM walletmicroservice.WALLET WHERE ID = ? ")
    Double getBalance(int id);

    @Query(nativeQuery = true, value = "DELETE FROM walletmicroservice.WALLET WHERE ID = ? ")
    void deleteById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.WALLET WHERE ID= (SELECT CONNECTED_WALLET FROM walletmicroservice.PLAYER WHERE ID = ALL)")
    List<WalletEntity> getAvailable(int playerid);
}
