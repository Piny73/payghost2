package it.tsp.control;

import java.io.Serializable;
import java.util.Optional;

import it.tsp.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

@TransactionScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AccountStore implements Serializable {
        @PersistenceContext(unitName = "payghost")
        private EntityManager em = null;

        public Account saveAccount(Account e) {
            Account saved = em.merge(e);
                return saved;
            }        
            
        public Optional<Account> findAccountById(long accountId){
            Account account = em.find(Account.class, accountId);
            return account==null ? Optional.empty() : Optional.of(account);
        }
}
