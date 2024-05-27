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
    
    public TransactionStore saveTransaction(TransactionStore e) {
        if (em.getTransaction().isActive()) {
            return em.merge(e);
        }
        em.getTransaction().begin();
        TransactionStore saved = em.merge(e);
        em.getTransaction().commit();
        return saved;
    }

    public List<Transaction> findTransactionsByAccountId(long accountId) {
        return em.createNamedQuery(Transaction.FIND_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", accountId)
                .getResultList();
    }
}
