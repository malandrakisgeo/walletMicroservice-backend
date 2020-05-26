package walletmicroservice.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import walletmicroservice.Model.PlayerEntity;

import java.util.List;
@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM walletmicroservice.PLAYER  ")
    List<PlayerEntity> getAll();

    PlayerEntity findById(int id);

    //@Query(nativeQuery = true, value = "DELETE FROM main.PLAYER WHERE main.PLAYER.ID = ? ")
    //public void deleteById(int id);

    public void deleteById(int id);

    public PlayerEntity save(PlayerEntity playerEntity);
}
