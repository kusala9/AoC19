/*
   DOCO:  
*/

import java.math.BigInteger;

public class d9
{

    public static BigInteger []parseprog(String s)
    {
        String []f = s.split(",");
        BigInteger []f2 = new BigInteger[f.length];
        int c=0;
        for (String s2:f)
        {
            f2[c++] = new BigInteger(s2);
        }
        return f2;
    }

    public static void main(String []a)
    {
        System.out.println ("hw. d9");
        String prog = "1102,34915192,34915192,7,4,7,99,0";
        BigInteger []b = parseprog(prog);

        //eddie4 E = new eddie4();

    }
}
