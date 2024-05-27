package it.tsp.control;

import java.util.List;
import it.tsp.entity.Recharge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class TransactionStore {

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

    public static List<TransactionStore> findTransactionsByAccountId(long accountId) {
        return em.createNamedQuery(TransactionStore.FIND_BY_ACCOUNT_ID, TransactionStore.class)
                .setParameter("id", accountId)
                .getResultList();
    }
}
