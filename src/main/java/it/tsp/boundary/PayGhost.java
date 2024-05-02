package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import it.tsp.control.Store;
import it.tsp.entity.Account;
import it.tsp.entity.Transaction;

public class PayGhost {

    public static Account registration(String fname, String lname, String email, String pwd, String confirmPwd,
            BigDecimal Credit) {

        if (!Objects.equals(pwd, confirmPwd)) {
            throw new RegistrationException("le pwd non corrispondono");
        }

        Account account = new Account(fname, lname, email, pwd, Credit);

        Account saved = Store.saveAccount(account);

        return saved;
    }

    public static void doRecharge(long accountId, BigDecimal amount) {
        throw new UnsupportedOperationException("not implement yet...");
        
    }

    public static void doTransaction(long tDate, long receiverId, long senderId, BigDecimal amount) {
        throw new UnsupportedOperationException("not implement yet...");
    }

    public static List<Transaction> transactionByUser(long accountId) {
        throw new UnsupportedOperationException("not implement yet...");
    }

}