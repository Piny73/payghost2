package it.tsp.control;

import java.util.List;

import it.tsp.entity.Recharge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class RechargeStore {

    @PersistenceContext(unitName = "payghost")
    private EntityManager em;

    public Recharge saveRecharge(Recharge r) {
            Recharge saved = this.em.merge(r);
                return saved;
            }

     public static List<Recharge> findRechargesByAccountId(long accountId) {
        return em.createNamedQuery(Recharge.FIND_BY_ACCOUNT_ID, Recharge.class)
                .setParameter("id", accountId)
                .getResultList();
    }

    
}
