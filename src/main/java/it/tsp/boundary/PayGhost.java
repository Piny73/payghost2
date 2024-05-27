package it.tsp.boundary;

public class PayGhost {
/* 
    public static Account registration(String fname, String lname, String email,
            String pwd, String confirmPwd, BigDecimal credit) {

       

    public static void recharge(long accountId, BigDecimal amount) {
        try {
            // nuovo oggetto Recharge
            Account account = Store.findAccountById(accountId)
                    .orElseThrow(() -> new RechargeException("account non trovato: " + accountId));
            Store.beginTran();
            Store.saveRecharge(new Recharge(account, amount));
            account.increaseCredit(amount);
            Store.saveAccount(account);
            Store.commitTran();
        } catch (Exception ex) {
            Store.rollTran();
            throw new RechargeException(ex.getMessage());
        }
    }

    public static void sendMoney(long senderId, long receiverId, BigDecimal amount) {
        try {
            Account sender = Store.findAccountById(senderId)
                    .orElseThrow(() -> new TransactionException("account non trovato: " + senderId));
            Account receiver = Store.findAccountById(receiverId)
                    .orElseThrow(() -> new TransactionException("account non trovato: " + receiverId));
            if (!sender.hasSufficientCredit(amount)) {
                throw new TransactionException("Credito insufficiente per: " + sender);
            }
            Store.beginTran();
            Store.saveTransaction(new Transaction(sender, receiver, amount));
            receiver.increaseCredit(amount);
            sender.decreaseCredit(amount);
            Store.saveAccount(receiver);
            Store.saveAccount(sender);
            Store.commitTran();
        } catch (Exception ex) {
            Store.rollTran();
            throw new TransactionException(ex.getMessage());
        }
    }

    public static List<Transaction> transactionByUser(long accountId) {
        return Store.findTransactionsByAccountId(accountId);
    }

    public static List<Recharge> rechargeByUser(long accountId) {
        return Store.findRechargesByAccountId(accountId);
    }*/
}