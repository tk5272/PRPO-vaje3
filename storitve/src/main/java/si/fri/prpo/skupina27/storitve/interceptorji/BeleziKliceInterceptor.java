package si.fri.prpo.skupina27.storitve.interceptorji;


import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.storitve.anotacije.BeleziKlice;
import si.fri.prpo.skupina27.storitve.zrna.BeleziKliceZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice

public class BeleziKliceInterceptor {


    @Inject
    private BeleziKliceZrno beleziKliceZrno;

    Logger log = Logger.getLogger(BeleziKliceInterceptor.class.getName());

    @AroundInvoke
    public Object belezenjeKlicev(InvocationContext con) throws Exception
    {

        //https://docs.oracle.com/javaee/7/api/javax/interceptor

        String ime = con.getMethod().getName();
        beleziKliceZrno.vstavi(ime);
        System.out.println(beleziKliceZrno);

        /* METODA, KI PRESTREZE PARAMETER SOBE IN GA SPREMENI NA 2, ZATO SE VEDNO PRIKAZE SOBA 2
         * Object[] parametri = con.getParameters();
         * for(int i = 0; i < parametri.length; i++) {
         *     System.out.println(parametri[i].toString());
         * }
         * Object[] druga = {2};
         * con.setParameters(druga);
         */

        return con.proceed();
    }

}
