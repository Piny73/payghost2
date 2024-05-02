package it.tsp.control;

import java.util.Optional;

import it.tsp.entity.Account;
import it.tsp.entity.Recharge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Store {
        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("payghost");
        private static EntityManager em = emf.createEntityManager();

        //costruttore static
        {
            
        }

        public static void beginTran() {
           if(em.getTransaction().isActive()){
            throw new StoreExceptions("Esiste già una transazione attiva!");
           }
           em.getTransaction().begin();
        }

        public static void rollTran() {
            if(!em.getTransaction().isActive()){
             throw new StoreExceptions("Nessuna transazione attiva!");
            }
            em.getTransaction().rollback();
         }

        public static void commitTran(){
            if(!em.getTransaction().isActive()){
                throw new StoreExceptions("Nessuna transazione attiva!");
            }
            em.getTransaction().commit();
        }

        public static Account saveAccount(Account e) {
            if(em.getTransaction().isActive()){
                return em.merge(e);
            }
            em.getTransaction().begin();
            Account saved = em.merge(e);
            em.getTransaction().commit();
            return saved;            
        }

        public static Recharge saveRecharge(Recharge recharge) {
            if(em.getTransaction().isActive()){
                return em.merge(recharge);
            }
            em.getTransaction().begin();
            Recharge saved = em.merge(recharge);
            em.getTransaction().commit();
            return saved;
        }
        public static Optional<Account> findAccountById(long accountId){
            Account account = em.find(Account.class, accountId);
            return account==null ? Optional.empty() : Optional.of(account);
        }
}
