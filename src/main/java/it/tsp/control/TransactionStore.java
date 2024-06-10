package it.tsp.control;

import java.io.Serializable;
import java.util.List;
import it.tsp.entity.Transaction;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class TransactionStore implements Serializable {

    @PersistenceContext(unitName = "payghost")
    private EntityManager em;
    
    public Transaction saveTransaction(Transaction e) {
        Transaction saved = em.merge(e);
        return saved;
    }

    public List<Transaction> findTransactionsByAccountId(long accountId) {
        return em.createNamedQuery(Transaction.FIND_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
    }
}
