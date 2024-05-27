package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


import it.tsp.control.AccountStore;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PayGhost {
    @Inject
   AccountStore accountStore;
    public static Account registration(String fname, String lname, String email, String pwd, String confirmPwd,BigDecimal Credit) {

        try{
        if (!Objects.equals(pwd, confirmPwd)) {
            throw new RegistrationException("le pwd non corrispondono");
        }

        AccountStore.beginTran();

        Account account = new Account(fname, lname, email, pwd, Credit);
        
        Account saved = AccountStore.saveAccount(account);

        if(Credit.compareTo(BigDecimal.ZERO) > 0){
            Recharge recharge =new Recharge(saved, Credit);
            Recharge r = AccountStore.saveRecharge(recharge);
            saved.setCredit(Credit);
            saved = AccountStore.saveAccount(saved);
        }
        AccountStore.commitTran();
        return saved;
    } catch (Exception ex){
        AccountStore.rollTran();
        throw new RegistrationException(ex.getMessage());
    }
    }

    public static Account doRecharge(long accountId, BigDecimal amount) {
        try {
            Account found = AccountStore.findAccountById(accountId).orElseThrow(() -> new RechargeException("Account non trovato" + accountId));
            AccountStore.beginTran();
            AccountStore.saveRecharge(new Recharge(found, amount));
            amount = increaseCredit(amount);
            AccountStore.saveAccount(found);
                  
            return found;
        } catch (Exception ex){
            throw new RechargeException(ex.getMessage());
        }
    }

    private static BigDecimal increaseCredit(BigDecimal amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'increaseCredit'");
    }

    public static void doTransaction(long tDate, long receiverId, long senderId, BigDecimal amount) {
        try {
           
           }catch (Exception ex){
            throw new TransactionException(ex.getMessage());
           }
    }

    public static List<Transaction> transactionByUser(long accountId) {
        throw new UnsupportedOperationException("Unimplemented method 'transactionByUser'");
    }

}