package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.json.Json;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@NamedQueries({
    @NamedQuery(name = Account.FIND_BY_USR, query ="select e from Account e where e.email=:email"),
    @NamedQuery(name = Account.FIND_ALL, query = "select e from Account e order by e.lname")
})
@Entity
@Table(name = "account")

public class Account extends BaseEntity implements Serializable {

    public static final String FIND_BY_USR ="Account.findByUser";
    public static final String FIND_ALL = "Account.findAll";
    public Account (){}
    
    public Account(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    @Email(message = "Indirizzo mail non valido!") 
    @NotBlank(message = "la pwd non può avere solo spazi!") @Size(min = 4, message = "Inserisci più di 4 caratteri!")
    @PositiveOrZero
    public Account(String fname, String lname, String email, String pwd,BigDecimal credit) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pwd = pwd;
        this.credit = credit;
    }
     
     private String fname;
     private String lname;
     @Transient
     private String confirmPwd;

     @NotBlank
     @Email(message = "Indirizzo mail non valido!" )
     @Column(nullable = false, unique = true)
     private String email;

     @NotBlank(message = "la pwd non può avere solo spazi!")
     @Size(min = 4, message = "Inserisci più di 4 caratteri!")
     @Column(nullable = false)
     private String pwd;

    @PositiveOrZero
     @Column(precision = 6, scale = 2)
     private BigDecimal credit;

     /* il costruttore non è visibile, ma è presente e vuoto */
    
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @JsonbTransient
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public BigDecimal getCredit() {
        return credit;
    }
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
    
    @Override
    public String toString() {
        return "Account [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", pwd=" + pwd
                + ", credit=" + credit + "]";
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

     
}
