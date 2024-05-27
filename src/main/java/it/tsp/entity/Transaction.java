package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
@NamedQueries({
    @NamedQuery(name = Transaction.FIND_BY_ACCOUNT_ID, query = "select t from Transaction t where t.account.id=:id")})
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity implements Serializable {

    public static final String FIND_BY_ACCOUNT_ID = "Transaction.findByAccountId";;
    public Transaction (){}

    public Transaction(Account sender, Account receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    @ManyToOne(optional = false)
    private Account sender;
    @ManyToOne(optional = false)
    private Account receiver;
    @Column(nullable = false)
    private LocalDate tDate = LocalDate.now();
    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal amount;

    public Account getSender() {
        return sender;
    }
    public void setSender(Account sender) {
        this.sender = sender;
    }
    public Account getReceiver() {
        return receiver;
    }
    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
    public LocalDate getOperation() {
        return tDate;
    }
    public void setOperation(LocalDate tDate) {
        this.tDate = tDate;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
        result = prime * result + ((tDate == null) ? 0 : tDate.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (sender == null) {
            if (other.sender != null)
                return false;
        } else if (!sender.equals(other.sender))
            return false;
        if (receiver == null) {
            if (other.receiver != null)
                return false;
        } else if (!receiver.equals(other.receiver))
            return false;
        if (tDate == null) {
            if (other.tDate != null)
                return false;
        } else if (!tDate.equals(other.tDate))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", tDate=" + tDate
                + ", amount=" + amount + "]";
    }

    



}
