package si.fri.prpo.skupina27.storitve.zrna;


import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;


@ApplicationScoped
public class BeleziKliceZrno {

    private HashMap<String, Integer> klici = new HashMap<String, Integer>();


    public void vstavi(String key) {

        if(klici.containsKey(key)) {
            int value = klici.get(key) + 1;
            klici.put(key, value);
        } else {
            klici.put(key, 1);
        }

    }

    public String toString() {
        return klici.toString();
    }



}
