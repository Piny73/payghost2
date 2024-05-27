package it.tsp;




import java.math.BigDecimal;

import it.tsp.boundary.PayGhost;
import it.tsp.entity.Account;


public class App 
{
    /**
     * @param args
     */
    public static void main( String[] args )
    { 
        Account saved = PayGhost.registration(
            "Giuseppina", 
            "Salemme", 
            "Piny73@gmail.com",
            "8373",
            "8373",
            BigDecimal.valueOf(100));

        /*ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Account>> result = validator.validate(a);
        result.forEach(v -> System.out.println(v));*/


        System.out.println(saved);    
    }
}
