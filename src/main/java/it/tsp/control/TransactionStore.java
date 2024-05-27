package it.tsp.control;

import java.util.List;

public class TransactionStore {

    public static TransactionStore saveTransaction(TransactionStore e) {
        if (em.getTransaction().isActive()) {
            return em.merge(e);
        }
        em.getTransaction().begin();
        TransactionStore saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }

    public static List<TransactionStore> findTransactionsByAccountId(long accountId) {
        return em.createNamedQuery(TransactionStore.FIND_BY_ACCOUNT_ID, TransactionStore.class)
                .setParameter("id", accountId)
                .getResultList();
    }
}
