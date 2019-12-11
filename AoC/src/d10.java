import java.util.ArrayList;
import java.util.HashMap;

public class d10
{
    public static int gcf(int num1,int num2)
    {
        for(int i = 1; i <= num1 && i <= num2; i++)
        {
            if(num1%i==0 && num2%i==0) return i;
        }
        return 1;
    }
    public static boolean isM(pear<Integer,Integer> e1,pear<Integer,Integer> e2,HashMap<pear<Integer,Integer>,Boolean> m3)
    {
        boolean ret = false;

        int dx = e2.first - e1.first;
        int dy = e2.second - e1.second;
        int g = gcf(dx,dy);
        if (e1 == e2) return true;
        if (g==1) return true;

        // go from e1 to e2.
        while (e1 != e2)
        {
            e1 = new pear<Integer,Integer>(e1.first+dx,e1.second+dy);
            if (m3.containsKey(e1)) return false;
        }
        return true;
    }
    public static void main(String []a)
    {
        ArrayList<String> m = d10d.getd();
        pear<Integer,Integer> p = new pear<Integer,Integer>(1,0);
        HashMap<String,Integer> m2 = new HashMap<>();
        HashMap<pear<Integer,Integer>,Boolean> m3 = new HashMap<>();

        for (int i=0;i<m.size();i++)
        {
            String s = m.get(i);
            System.out.println("->" + s);
            for (int j=0;j<s.length();j++)
            {
                if (s.charAt(j)=='#')
                {
                    //System.out.println("A at(" + i + "," + j + ")");
                    p = new pear<>(i,j);
                    m3.put(p,true);
                }
            }
        }
        for (pear<Integer,Integer> e1: m3.keySet())
        {
            System.out.println("Checking " + e1);
            for (pear<Integer,Integer> e2: m3.keySet())
            {
                if (isM(e1,e2,m3))
                {
                    System.out.println("Match => " + e1 + "-" + e2);
                }
            }
        }


    }
}
