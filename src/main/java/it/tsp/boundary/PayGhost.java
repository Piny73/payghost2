package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


import it.tsp.control.Store;
import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import it.tsp.entity.Transaction;

public class PayGhost {

    public static Account registration(String fname, String lname, String email, String pwd, String confirmPwd,BigDecimal Credit) {

        try{
        if (!Objects.equals(pwd, confirmPwd)) {
            throw new RegistrationException("le pwd non corrispondono");
        }

        Store.beginTran();

        Account account = new Account(fname, lname, email, pwd, Credit);
        
        Account saved = Store.saveAccount(account);

        if(Credit.compareTo(BigDecimal.ZERO) > 0){
            Recharge recharge =new Recharge(saved, Credit);
            Recharge r = Store.saveRecharge(recharge);
            saved.setCredit(Credit);
            saved = Store.saveAccount(saved);
        }
        Store.commitTran();
        return saved;
    } catch (Exception ex){
        Store.rollTran();
        throw new RegistrationException(ex.getMessage());
    }
    }

    public static Account doRecharge(long accountId, BigDecimal amount) {
        try {
            Account found = Store.findAccountById(accountId).orElseThrow(() -> new RechargeException("Account non trovato" + accountId));
            Store.beginTran();
            Store.saveRecharge(new Recharge(found, amount));
            amount = increaseCredit(amount);
            Store.saveAccount(found);
            Store.commitTran();            
            return found;
        } catch (Exception ex){
            Store.rollTran();
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