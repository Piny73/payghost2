package it.tsp;

import it.tsp.control.Store;
import it.tsp.entity.Account;

public class App 
{
    /**
     * @param args
     */
    public static void main( String[] args )
    { 
        Account a = new Account("","");

        Account saved = Store.saveAccount(a);

        System.out.println(saved);    
    }
}
