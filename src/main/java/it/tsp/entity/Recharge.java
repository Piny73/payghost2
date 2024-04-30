package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recharge")
public class Recharge extends BaseEntity implements Serializable {

    public Recharge (){}

    public Recharge(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }
    @ManyToOne(optional = false)
    private Account account;
    
    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate tDate = LocalDate.now();;
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getOperation() {
        return tDate;
    }
    public void setOperation(LocalDate tDate) {
        this.tDate = tDate;
    }
    @Override
    public String toString() {
        return "Recharge [id=" + id + ", account=" + account + ", amount=" + amount + ", tDate=" + tDate + "]";
    }

    
}
