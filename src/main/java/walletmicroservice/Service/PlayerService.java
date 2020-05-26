package walletmicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walletmicroservice.Model.PlayerEntity;
import walletmicroservice.Repository.PlayerRepository;

import java.util.List;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerEntity> getAll() {
        return playerRepository.getAll();
    }

    public void delete(PlayerEntity player) {
        this.deleteById(player.getId());
    }

    public void deleteById(int id) {
        playerRepository.deleteById(id);
    }

    public void save(PlayerEntity player) {
        playerRepository.save(player);
    }

}
