/*
   DOCO:  
*/

import java.util.HashMap;

public class d3
{


    public static void main(String []a)
    {
        mz m = new mz();
        System.out.println("Hello world");
        String []in = m.wire1.split(",");
        m.add(0,0);
        for (String ins:in) m.goTo(ins);

        HashMap<String,Integer> v = m.getMap();
        HashMap<String,Integer> cv = new HashMap<String,Integer>(v);

        mz m2 = new mz();
        m2.setOtherWire(cv);

        in = m2.wire2.split(",");

        for (String ins:in) m2.goTo(ins);

        System.out.println("minman=" + m2.minman);
        System.out.println("minsteps=" + m2.minsteps);


    }

}
