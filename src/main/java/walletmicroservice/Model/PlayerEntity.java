package walletmicroservice.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PLAYER", schema = "walletmicroservice", catalog = "")
public class PlayerEntity {
    private Integer id;
    private Integer connectedWallet;
    private String name;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONNECTED_WALLET", nullable = true)
    public Integer getConnectedWallet() {
        return connectedWallet;
    }

    public void setConnectedWallet(Integer connectedWallet) {
        this.connectedWallet = connectedWallet;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(connectedWallet, that.connectedWallet) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, connectedWallet, name);
    }
}
