package walletmicroservice.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTIONLIST", schema = "walletmicroservice", catalog = "")
public class TransactionlistEntity {
    enum Transactiontype {
        CREDIT,
        DEBIT
    }

    ;
    private Integer id;
    private Double amount;
    private Transactiontype transactionType;
    private String transactionDate;
    private Integer walletId;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "TRANSACTION_TYPE", nullable = false, length = -1)
    public String getTransactionType() {
        return transactionType.toString();
    }

    public void setTransactionType(String transactionType) {
        Transactiontype value;
        if (transactionType.toUpperCase().equals("CREDIT")) {
            value = Transactiontype.CREDIT;
        } else if (transactionType.toUpperCase().equals("DEBIT")) {
            value = Transactiontype.DEBIT;
        } else {
            value = null;
        }
        this.transactionType = value;
    }



    @Basic
    @Column(name = "TRANSACTION_DATE", nullable = false, length = -1)
    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Basic
    @Column(name = "WALLET_ID", nullable = false)
    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionlistEntity that = (TransactionlistEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(transactionDate, that.transactionDate) &&
                Objects.equals(walletId, that.walletId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, transactionType, transactionDate, walletId);
    }
}
