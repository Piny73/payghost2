package it.tsp.boundary;

import java.math.BigDecimal;
import java.util.List;
import it.tsp.entity.Transaction;

public class PayGhost {

    public static void registration(String fname, String lname, String amail, String pwd, String confirmPwd, BigDecimal Credit){
        

    }

    public static void doRecharge(long accountId, BigDecimal amount) {


    }

    public static void doTransaction(long tDate, long receiverId, long senderId, BigDecimal amount) {


    }

    public static List <Transaction> transactionByUser(long accountId){
        throw new UnsupportedOperationException("not implement yet...");
    }
        
    }


